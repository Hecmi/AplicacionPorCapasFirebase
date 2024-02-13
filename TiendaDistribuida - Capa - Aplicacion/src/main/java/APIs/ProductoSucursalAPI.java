/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APIs;

import Controlador.ProductoSucursalControlador;
import DTO.ProductoSucursalDTO;
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
@Path("/producto")
public class ProductoSucursalAPI {
    
    ProductoSucursalControlador productoControlador;
    
    public ProductoSucursalAPI(){
        productoControlador = new ProductoSucursalControlador();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/get/{id_producto_sucursal}")
    public Response getProducto(@PathParam("id_producto_sucursal") String id_producto_sucursal){
        String producto = productoControlador.getProductoPorId(id_producto_sucursal);
        
        return Response.ok(producto)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/listar")
    public Response getProductos(){
        String productos =  productoControlador.getProductos();
        
        return Response.ok(productos)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/listar/{id_sucursal}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String getProductosSucursal(@PathParam("id_sucursal") String id_sucursal){
        return productoControlador.getProductoPorIdSucursal(id_sucursal);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/buscar")
    @Consumes(MediaType.TEXT_PLAIN)
    public String buscarCliente(String texto_buscar){
        return productoControlador.buscarProducto(texto_buscar);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/buscar/{id_sucursal}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String buscarCliente(@PathParam("id_sucursal") String id_sucursal, String texto_buscar){
        return productoControlador.buscarProductoSucursal(id_sucursal, texto_buscar);
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProducto(String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};
        
        Boolean errores = false;
        
        if (json.size() > 0){            
            
            ProductoSucursalDTO producto =  new ProductoSucursalDTO();
            producto.parsearJSONaDTO(json_data);
            
            if (errores == false) {
                response = productoControlador.insertarProductoSucursal(producto);
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
    public Response actualizarCliente(String data)
    {
        JsonObject json = Conversiones.StringToJSON(data);
        JsonObject json_data = json.getAsJsonObject("data");
        String [] response = {"-1", "Error", "Error"};        
        Boolean errores = false;        
        if (json.size() > 0){
            ProductoSucursalDTO producto =  new ProductoSucursalDTO();
            producto.parsearJSONaDTO(json_data);
            
            if (errores == false) {
                response = productoControlador.actualizarProductoSucursal(producto);
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
    public Response eliminarSucursal (@PathParam("id") String id)
    {
        String [] response = {"-1", "Error", "Error"};
        response = productoControlador.eliminarProductoSucursal(id);
        
        String responseResultado = Conversiones.parsearResultado(response[0], response[1], response[2]);
        
        return Response.ok(responseResultado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
