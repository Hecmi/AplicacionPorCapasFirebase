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
public class ProductoSucursalDTO {
    String id_producto_sucursal;
    Integer id_sucursal;
    String producto;
    String fecha_creacion;
    String unidad_medida;
    Float precio_unitario;
    Float cantidad;   
    Float iva;
    Float descuento;
    String descripcion;

    public String getId_producto_sucursal() {
        return id_producto_sucursal;
    }

    public void setId_producto_sucursal(String id_producto_sucursal) {
        this.id_producto_sucursal = id_producto_sucursal;
    }
    
     public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre_producto() {
        return producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.producto = nombre_producto;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Float getPrecio() {
        return precio_unitario;
    }

    public void setPrecio(Float precio) {
        this.precio_unitario = precio;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }
    
    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }
    
   

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void parsearJSONaDTO(JsonObject json) {    
        System.out.println(json.toString());
        this.setId_producto_sucursal(Conversiones.JsonToString(json,"id_producto_sucursal"));
        this.setId_sucursal(Integer.valueOf(Conversiones.JsonToString(json,"id_sucursal")));
        this.setNombre_producto(Conversiones.JsonToString(json,"producto"));
        this.setFecha_creacion(Conversiones.JsonToString(json,"fecha_creacion"));
        this.setUnidad_medida(Conversiones.JsonToString(json,"unidad_medida"));
        this.setPrecio(Float.parseFloat(Conversiones.JsonToString(json,"precio_unitario")));
        this.setCantidad(Float.parseFloat(Conversiones.JsonToString(json,"cantidad")));
        this.setIva(Float.parseFloat(Conversiones.JsonToString(json,"iva")));
        this.setDescuento(Float.parseFloat(Conversiones.JsonToString(json,"descuento")));
        this.setDescripcion(Conversiones.JsonToString(json,"descripcion"));
    }
    
    public JsonObject parsearDTOaJSON() {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(this).getAsJsonObject();
        return jsonObject;
    }
    
    public JsonObject getJSONSubir() {
        JsonObject jsonProducto = Conversiones.crearJSONdata(this.parsearDTOaJSON());
        System.out.println(jsonProducto.toString());
        return jsonProducto;
    }
}
