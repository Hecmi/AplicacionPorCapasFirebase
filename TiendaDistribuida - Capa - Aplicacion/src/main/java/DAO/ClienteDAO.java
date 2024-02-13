/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ClienteDTO;
import Utils.ConexionBDD;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LUIS CASANOVA
 */
public class ClienteDAO {
    ConexionBDD conexion;
    
    public ClienteDAO(){
        conexion = new ConexionBDD();
    }

    public String[] insertarCliente(ClienteDTO cliente) {
        String comando = String.format("Select * from func_insertar_cliente('%s')", cliente.getJSONSubir());
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

    public String[] editarCliente(ClienteDTO cliente) {
        String comando = String.format("Select * from func_editar_cliente('%s')", cliente.getJSONSubir());
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
    
    public String[] eliminarCliente(String identificador) {
        String comando = String.format("Select * from func_eliminar_cliente('%s')", identificador);
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

    public String getClientePorId(String identificador) {
        String comando = String.format("Select * from func_buscar_cliente('%s', 2)", identificador);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }    }

    public String buscarCliente(String texto_buscar) {
        String comando = String.format("Select * from func_buscar_cliente('%s', 1)", texto_buscar);
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){
            return tabla.getValueAt(0, 0).toString();            
        }
        else {
            return "[]";
        }
    }

    public String getClientes() {
        String comando = String.format("Select * from func_listar_cliente()");
        DefaultTableModel tabla = conexion.returnRecord(comando);
        
        if (tabla.getRowCount() > 0){            
            return tabla.getValueAt(0, 0).toString();
        }
        else {            
            return "[]";
        }
    }
}
