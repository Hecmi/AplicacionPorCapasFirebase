/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APIs;

import Controlador.ClienteControlador;
import DTO.ClienteDTO;
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
import org.json.JSONObject;

/**
 *
 * @author LUIS CASANOVA
 */
@Path("/cliente")
public class ClienteAPI {
    
    ClienteControlador clienteCtrl;
    
    public ClienteAPI(){
        clienteCtrl = new ClienteControlador();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/listar")
    public Response getClientes(){
        String clientes = clienteCtrl.getClientes();
        
        return Response.ok(clientes)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/get/{id_cliente}")
    public Response getClientes(@PathParam("id_cliente") String id_cliente){
        String cliente = clienteCtrl.getClientePorId(id_cliente);
        
        return Response.ok(cliente)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/buscar")
    @Consumes(MediaType.TEXT_PLAIN)
    public String buscarCliente(String texto_buscar){
        return clienteCtrl.buscarCliente(texto_buscar);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarCliente(String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};
        
        Boolean errores = false;
        
        if (json.size() > 0){            
            
            ClienteDTO cliente =  new ClienteDTO();
            cliente.parsearJSONaDTO(json_data);
            
            //Validaciones antes de enviar a la base de datos
            if (cliente.getIdentificacion().length() != 10){
                errores = true;
            }            
            if (cliente.getTelefono().length() != 10){
                errores = true;
            }
                
            if (errores == false) {
                response = clienteCtrl.insertarCliente(cliente);
            }
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
    public Response editarCliente(String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};
        
        if (json.size() > 0){            
            ClienteDTO cliente =  new ClienteDTO();
            cliente.parsearJSONaDTO(json_data);
            
            Boolean errores = false;
            
            //Validaciones antes de enviar a la base de datos
            if (cliente.getIdentificacion().length() != 10){
                errores = true;
            }            
            if (cliente.getTelefono().length() != 10){
                errores = true;
            }
                
            if (errores == false) {
                response = clienteCtrl.editarCliente(cliente);
            }
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
        response = clienteCtrl.eliminarCliente(id);
        
        String responseResultado = Conversiones.parsearResultado(response[0], response[1], response[2]);
        
        return Response.ok(responseResultado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
