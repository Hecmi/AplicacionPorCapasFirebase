/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author LUIS CASANOVA
 */
public class Conversiones {
    public static String JsonToString(JsonObject json, String elemento){
        try {
            JsonElement res = (JsonElement) json.get(elemento);
            if (res != null) {
                String result = res.getAsString();
                result = result.trim().replace("\n", "\\n").replace("\t", "\\t").replace("'", "''");
                return result;
            } else {
                return "[]";
            }
        } catch (Exception e) {
            return "[]";
        }
    }
    
    public static JsonObject StringToJSON(String json) {
        try {
            JsonParser parser = new JsonParser();
            com.google.gson.JsonObject Jso = parser.parse(json).getAsJsonObject();
            return Jso;
        } catch (Exception e) {
            return new JsonObject();
        }
    }
    
    public static JsonObject crearJSONdata(JsonObject json) {
        try {
            JsonObject jsonData = new JsonObject();
            jsonData.add("data", json);
            return jsonData;
        } catch (Exception e) {
            return new JsonObject();
        }
    }
    
    public static String parsearResultado(String estado, String resultado, String mensaje) {
        try {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("estado", estado);
            jsonObject.addProperty("resultado", resultado);
            jsonObject.addProperty("mensaje", mensaje);

            Gson gson = new Gson();
            String json = gson.toJson(jsonObject);
            return json;        
        } catch (Exception e) {
            return "{}";
        }
    }
}
