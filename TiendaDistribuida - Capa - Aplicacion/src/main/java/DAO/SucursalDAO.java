/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.SucursalDTO;
import Utils.ConexionBDD;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LUIS CASANOVA
 */
public class SucursalDAO {
    
    ConexionBDD conexion;
    
    public SucursalDAO(){
        conexion = new ConexionBDD();
    }

    public String [] insertarSucursal(SucursalDTO sucursal) {
        String comando = String.format("Select * from func_insertar_sucursal('%s')", sucursal.getJSONSubir());
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

    public String getSucursales() {
        String comando = String.format("Select * from func_listar_sucursal()");
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){            
            return tabla.getValueAt(0, 0).toString();
        }
        else {            
            return "[]";
        }
    }

    public String buscarSucursal(String texto_buscar) {
        String comando = String.format("Select * from func_buscar_sucursal('%s', 1)", texto_buscar);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }
    
    public String getSucursalPorId(String id) {
        String comando = String.format("Select * from func_buscar_sucursal('%s', 2)", id);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }

    public String[] editarSucursal(SucursalDTO sucursal) {
        String comando = String.format("Select * from func_editar_sucursal('%s')", sucursal.getJSONSubir());
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

    public String[] eliminarSucursal(String id) {
       String comando = String.format("Select * from func_eliminar_sucursal('%s')", id);
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
}
