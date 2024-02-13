/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Utils.Conversiones;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 *
 * @author LUIS CASANOVA
 */
public class ClienteDTO {

    String id_sucursal;
    String identificacion;    
    String nombres;
    String apellidos;   
    String email;
    String telefono;
    String direccion;
    String fecha_ingreso;
    String fecha_nacimiento;

    public String getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(String id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificador) {
        this.identificacion = identificador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    

    public ClienteDTO() { }
    
    public void parsearJSONaDTO(JsonObject json) {        
        this.setId_sucursal(Conversiones.JsonToString(json,"id_sucursal"));
        this.setIdentificacion(Conversiones.JsonToString(json,"identificacion"));
        this.setNombres(Conversiones.JsonToString(json,"nombres"));
        this.setApellidos(Conversiones.JsonToString(json,"apellidos"));
        this.setEmail(Conversiones.JsonToString(json,"email"));
        this.setTelefono(Conversiones.JsonToString(json,"telefono"));
        this.setDireccion(Conversiones.JsonToString(json,"direccion"));
        this.setFecha_nacimiento(Conversiones.JsonToString(json,"fecha_nacimiento"));        
    }
    
    public JsonObject parsearDTOaJSON() {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(this).getAsJsonObject();
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
    
    public JsonObject getJSONSubir() {
        JsonObject jsonCliente = Conversiones.crearJSONdata(this.parsearDTOaJSON());
        return jsonCliente;
    }
}
