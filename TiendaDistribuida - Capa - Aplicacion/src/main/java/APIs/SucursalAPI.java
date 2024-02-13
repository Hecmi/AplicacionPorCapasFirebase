/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APIs;

import Controlador.SucursalControlador;
import DTO.SucursalDTO;
import Utils.Conversiones;
import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LUIS CASANOVA
 */

@Path("/sucursal")
public class SucursalAPI {
    
    SucursalControlador sucursalCtrl;
    
    public SucursalAPI(){
        sucursalCtrl = new SucursalControlador();
    }    
  
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/listar")
    public String getSucursales(){
        return sucursalCtrl.getSucursales();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/buscar")
    @Consumes(MediaType.TEXT_PLAIN)
    public String getSucursales(String texto_buscar){
        return sucursalCtrl.buscarSucursal(texto_buscar);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/get/{id_sucursal}")
    public String getSucursalesPorId(@PathParam("id_sucursal") String id_sucursal){
        return sucursalCtrl.getSucursalPorId(id_sucursal);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarSucursal (String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};
        
        if (json.size() > 0){
            SucursalDTO sucursal = new SucursalDTO();
            sucursal.parsearJSONaDTO(json_data);

            response = sucursalCtrl.insertarSucursal(sucursal);
        }
        String responseResultado = Conversiones.parsearResultado(response[0], response[1], response[2]);
        
        return Response.ok(responseResultado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarSucursal (String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};
        
        if (json.size() > 0){
            SucursalDTO sucursal = new SucursalDTO();
            sucursal.parsearJSONaDTO(json_data);

            response = sucursalCtrl.editarSucursal(sucursal);
        }
        String responseResultado = Conversiones.parsearResultado(response[0], response[1], response[2]);
        
        return Response.ok(responseResultado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/eliminar/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response eliminarSucursal (@PathParam("id") String id)
    {
        String [] response = {"-1", "Error", "Error"};
        response = sucursalCtrl.eliminarSucursal(id);
        String responseResultado = Conversiones.parsearResultado(response[0], response[1], response[2]);
        
        return Response.ok(responseResultado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
