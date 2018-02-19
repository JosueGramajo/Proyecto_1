/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Static.ValoresStaticos;
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
        UserList user = new UserList();
        
        try {
            user = json.readJSON(JsonUtils.FILE_TYPE.USER, UserList.class);
        } catch (IOException ex) {
            response.setSuccess(false);
            response.setStatus("No existe el archivo JSON");
            return response;
        }
        
        if(user != null){ 
            for(Usuario us : user.getUserList()){
                if(us.getUsername().equals(username) && us.getPassword().equals(password)){
                    ValoresStaticos.TIPO_USUARIO = us.getTipo_usuario();
                    response.setSuccess(true);
                    response.setStatus("Login exitoso");
                    return response;
                }     
            }
            response.setSuccess(false);
            response.setStatus("Usuario o contraseña incorrecto");
        }
        
        return response;
    }
    public boolean addUser(Usuario user){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.USER.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                UserList existingList = json.readJSON(JsonUtils.FILE_TYPE.USER, UserList.class);
                user.setId(existingList.getCurrentID() + 1);
                existingList.setCurrentID(existingList.getCurrentID() + 1);

                existingList.getUserList().add(user);
               
                return json.writeJSON(existingList, JsonUtils.FILE_TYPE.USER);           
            }else{
                UserList newList = new UserList();
                ArrayList<Usuario> usrList = new ArrayList<Usuario>();
               
                user.setId(1);
                usrList.add(user);
                
                newList.setCurrentID(1);
                newList.setUserList(usrList);
                
                return json.writeJSON(newList, JsonUtils.FILE_TYPE.USER);  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteUser(Usuario user){
        try {
            UserList existingList = json.readJSON(JsonUtils.FILE_TYPE.USER, UserList.class);
            
            existingList.getUserList().removeIf((Usuario u) -> u.getId() == user.getId());

            return json.writeJSON(existingList, JsonUtils.FILE_TYPE.USER);           
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }     
    }
}
