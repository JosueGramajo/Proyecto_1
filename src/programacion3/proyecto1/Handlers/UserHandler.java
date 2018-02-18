/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Handlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.utils.JsonUtils;
import programacion3.proyecto1.utils.ObjectResponse;
import programacion3.proyecto1.utils.StatusResponse;

/**
 *
 * @author josue
 */
public class UserHandler {
    JsonUtils json = new JsonUtils();
    
    public StatusResponse doLogin(String username, String password){
        StatusResponse response = new StatusResponse();
        Usuario user = new Usuario();
        
        try {
            user = json.deserializeJSON(JsonUtils.FILE_TYPE.USER, Usuario.class);
        } catch (IOException ex) {
            response.setSuccess(false);
            response.setStatus("Ocurrio un error inesperado");
        }
        
        if(user != null){ 
           if(user.getUsername().equals(username) && user.getPassword().equals(password)){
               response.setSuccess(true);
               response.setStatus("Login exitoso");
           }else{
               response.setSuccess(false);
               response.setStatus("Usuario o contraseña incorrecto");
           }
        }
        
        return response;
    }
}
