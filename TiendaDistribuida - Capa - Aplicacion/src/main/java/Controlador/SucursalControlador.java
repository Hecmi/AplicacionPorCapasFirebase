/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.SucursalDAO;
import DTO.SucursalDTO;

/**
 *
 * @author LUIS CASANOVA
 */
public class SucursalControlador {
    
    SucursalDAO sucursalDao;
    
    public SucursalControlador(){
        sucursalDao = new SucursalDAO();
    }

    public String [] insertarSucursal(SucursalDTO sucursal) {
        
        String [] resultado  = sucursalDao.insertarSucursal(sucursal);
        
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se agregó la sucursal correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la inserción de la sucursal.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }

    public String getSucursales() {
        String sucursal = sucursalDao.getSucursales();        
        return sucursal;
    }

    public String buscarSucursal(String texto_buscar) {
        String sucursal = sucursalDao.buscarSucursal(texto_buscar);        
        return sucursal;
    }
    
    public String getSucursalPorId(String id) {
        String sucursal = sucursalDao.getSucursalPorId(id);        
        return sucursal;
    }

    public String[] editarSucursal(SucursalDTO sucursal) {
        String [] resultado = sucursalDao.editarSucursal(sucursal);
        
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se actualizó la sucursal correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la actualización de la sucursal.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }

    public String[] eliminarSucursal(String id) {
       String [] resultado = sucursalDao.eliminarSucursal(id);
        
        String estado = resultado[0];
        String consulta = resultado[1];
        String mensaje = "";
        
        if (estado.equals("1")){
            mensaje = "Se eliminó la sucursal correctamente.";
        }
        else{
            mensaje = "Ocurrió un problema durante la eliminación de la sucursal.";
        }
        
        return new String[]{estado, consulta, mensaje};
    }
    
}
