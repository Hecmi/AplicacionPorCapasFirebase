/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ProductoSucursalDTO;
import Utils.ConexionBDD;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LUIS CASANOVA
 */
public class ProductoSucursalDAO {
    ConexionBDD conexion;
    
    public ProductoSucursalDAO(){
        conexion = new ConexionBDD();
    }

    public String[] insertarProductoSucursal(ProductoSucursalDTO producto) {
        String comando = String.format("Select * from func_insertar_producto_sucursal('%s')", producto.getJSONSubir());
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return new String [] {
                tabla.getValueAt(0, 0).toString(),
                tabla.getValueAt(0, 1).toString()
            };
        }
        else {
            return new String [] {
                "-1", "{}"
            };
        }
    }

    public String[] actualizarProductoSucursal(ProductoSucursalDTO producto) {
        String comando = String.format("Select * from func_editar_producto_sucursal('%s')", producto.getJSONSubir());
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return new String [] {
                tabla.getValueAt(0, 0).toString(),
                tabla.getValueAt(0, 1).toString()
            };
        }
        else {
            return new String [] {
                "-1", "{}"
            };
        }
    }
    
    public String[] eliminarProductoSucursal(String id_producto) {
        String comando = String.format("Select * from func_eliminar_producto_sucursal('%s')", id_producto);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return new String [] {
                tabla.getValueAt(0, 0).toString(),
                tabla.getValueAt(0, 1).toString()
            };
        }
        else {
            return new String [] {
                "-1", "{}"
            };
        }
    }

      
    public String getProductos(){
         String comando = String.format("Select * from func_listar_productos()");
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String getProductoPorIdSucursal(String idSucursal) {
        String comando = String.format("Select * from func_buscar_producto_sucursal(%s, '', 2)", idSucursal);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String buscarProductoSucursal(String idSucursal, String texto_buscar) {
        String comando = String.format("Select * from func_buscar_producto_sucursal(%s, '%s', 1)", idSucursal, texto_buscar);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String getProductoSucursalPorId(String idSucursal) {
        String comando = String.format("Select * from func_buscar_producto_sucursal('%s', 2)", idSucursal);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String getProductoPorId(String idSucursal) {
        String comando = String.format("Select * from func_buscar_producto_sucursal('%s', 2)", idSucursal);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String buscarProducto (String texto_buscar) {
        String comando = String.format("Select * from func_buscar_producto_sucursal('%s', 1)", texto_buscar);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
}
