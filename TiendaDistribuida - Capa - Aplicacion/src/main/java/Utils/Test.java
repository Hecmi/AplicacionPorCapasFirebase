/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Controlador.SucursalControlador;
import DAO.ProductoSucursalDAO;
import DTO.ProductoSucursalDTO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LUIS CASANOVA
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        /*ConexionBDD conexion = new ConexionBDD();
        DefaultTableModel tabla = conexion.returnRecord("Select json_agg(t) from (Select * from sucursal) as t");
        SucursalControlador sucursalCtrl = new SucursalControlador();
        
        System.out.println(sucursalCtrl.getSucursales());
        for (int i = 0; i < tabla.getRowCount(); i++){
            System.out.println(tabla.getValueAt(0, 0).toString());
        }*/
        System.out.println(Float.parseFloat("52.00"));
        
    }
}
