/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LUIS CASANOVA
 */
public class ConexionBDD {
    java.sql.Connection conexion;
    ResultSet result;
    DefaultTableModel dataModel;    
    ResultSetMetaData rsmd;
    java.sql.Statement st;
    
    public boolean abrirConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String cadenaConexion = String.format("jdbc:postgresql://localhost:%s/%s", DatosEstaticos.puertoBD, DatosEstaticos.nombreBD);
            String usuario = DatosEstaticos.usuarioBD;
            String clave = DatosEstaticos.contraseniaBD;
            conexion = DriverManager.getConnection(cadenaConexion, usuario,  clave);
            
        } catch (Exception exc) {
            System.out.println("No se pudo realizar una conexión con la base de datos");
            System.out.println(exc.getMessage());
            return false;
        }
         
        return true;
    }
     
    public boolean cerrarConexion() {
        try {
            st.close();
            conexion.close();
        } catch (Exception exc) {
            System.out.println("Error close connection:" + exc.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean cerrarResultSet(){
         try {
            result.close();
        } catch (SQLException ex) {
            System.out.println("error in close resulset:" + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public DefaultTableModel returnRecord(String comando) {
        dataModel = new DefaultTableModel();
        if (abrirConexion()) {
            try {
                st = conexion.createStatement();
                result = st.executeQuery(comando);
                rsmd = result.getMetaData();
                int n = rsmd.getColumnCount();
                for (int i = 1; i <= n; i++) {
                    dataModel.addColumn(rsmd.getColumnName(i));
                }
                String[] row = new String[n];
                while (result.next()) {
                    for (int i = 0; i < n; i++) {
                        row[i] = (result.getString(rsmd.getColumnName(i + 1)) == null) ? "" : result.getString(rsmd.getColumnName(i + 1));
                    }
                    dataModel.addRow(row);
                }
            } catch (Exception exc) {
                System.out.println("Error return Record:" + exc.getMessage());
                dataModel = new DefaultTableModel();
            } finally {
                if (result != null) {
                    cerrarResultSet();
                }
            };
            cerrarConexion();
        }
        return dataModel;
    }
}
