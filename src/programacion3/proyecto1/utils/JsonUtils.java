/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Static.ValoresStaticos;

/**
 *
 * @author josue
 */
public class JsonUtils {
    private final static String PATH = ValoresStaticos.PATH;
    public static JsonUtils INSTANCIA = new JsonUtils();
    
    public enum FILE_TYPE{
        USER("users"),
        AGENCY("agencies"),
        PRODUCT("products"),
        CLIENT("clients"),
        SALE("sales");
        
        private String value;
        
        FILE_TYPE(String value){
            this.value = value;
        }
        
        public String rawValue(){
            return this.value;
        }
    }
    
    public boolean writeJSON(BaseObject object, FILE_TYPE type){
        if(!new File(PATH).exists()){
            new File(PATH).mkdirs();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        
        try{
            writer.writeValue(new File(PATH + "/"+type.rawValue()+".json"), object);
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
                
        return true;
    }
    public <T> T readJSON(final FILE_TYPE type, final Class<T> responseClass) throws IOException {
        String jsonPath = PATH + "/" + type.rawValue() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(jsonPath), responseClass);
    }
}
