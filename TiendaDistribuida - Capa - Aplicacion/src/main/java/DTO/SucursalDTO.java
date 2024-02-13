/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Utils.Conversiones;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author LUIS CASANOVA
 */
public class SucursalDTO {
    String id_sucursal;
    String pais;
    String ciudad;
    String direccion;
    String descripcion;
    String fecha_creacion;    

    public String getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(String idsucursal) {
        this.id_sucursal = idsucursal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fechacreacion) {
        this.fecha_creacion = fechacreacion;
    }
    
    public void parsearJSONaDTO(JsonObject json) {   
        this.setId_sucursal(Conversiones.JsonToString(json,"id_sucursal"));
        this.setPais(Conversiones.JsonToString(json,"pais"));
        this.setCiudad(Conversiones.JsonToString(json,"ciudad"));
        this.setDireccion(Conversiones.JsonToString(json,"direccion"));
        this.setDescripcion(Conversiones.JsonToString(json,"descripcion"));        
    }
    
    public JsonObject parsearDTOaJSON() {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(this).getAsJsonObject();
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
    
    public JsonObject getJSONSubir() {
        JsonObject jsonSucursal = Conversiones.crearJSONdata(this.parsearDTOaJSON());
        return jsonSucursal;
    }
}
