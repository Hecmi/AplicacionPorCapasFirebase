/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ProductoSucursalDAO;
import DTO.ProductoSucursalDTO;

/**
 *
 * @author LUIS CASANOVA
 */
public class ProductoSucursalControlador {
    
    ProductoSucursalDAO productoDao;
    
    public ProductoSucursalControlador(){
        productoDao = new ProductoSucursalDAO();
    }

    public String[] insertarProductoSucursal(ProductoSucursalDTO producto) {
        String [] resultado = productoDao.insertarProductoSucursal(producto);
       
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se agregó el producto correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la inserción del producto.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }

    public String[] actualizarProductoSucursal(ProductoSucursalDTO producto) {
        String [] resultado = productoDao.actualizarProductoSucursal(producto);
       
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se actualizó el producto correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la actualización del producto.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }
    
    public String[] eliminarProductoSucursal(String id_producto) {
        String [] resultado = productoDao.eliminarProductoSucursal(id_producto);
       
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se eliminó el producto correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la eliminación del producto.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }

      
    public String getProductos(){
        String productos = productoDao.getProductos();
        return productos;
    }
    
    public String getProductoPorIdSucursal(String idSucursal) {
        String productos = productoDao.getProductoPorIdSucursal(idSucursal);
        return productos;
    }
    
    public String buscarProductoSucursal(String idSucursal, String texto_buscar) {
        String productos = productoDao.buscarProductoSucursal(idSucursal, texto_buscar);
        return productos;
    }
    
    public String getProductoPorId(String idSucursal) {
        String productos = productoDao.getProductoPorId(idSucursal);
        return productos;
    }
    
    public String buscarProducto(String texto_buscar) {
        String productos = productoDao.buscarProducto(texto_buscar);
        return productos;
    }
}
