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
import programacion3.proyecto1.Beans.Cliente;
import programacion3.proyecto1.Beans.Lists.ClientList;
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author josue
 */
public class ClientHandler {
    public static ClientHandler INSTANCIA = new ClientHandler();
    
    public boolean addClient(Cliente client){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CLIENT.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
                for(Cliente c : existingList.getClients()){
                    if(c.getNit().equals(client.getNit())){
                        ValoresStaticos.MSG_ERROR("Este cliente ya existe");
                        return false;
                    }
                }
                existingList.getClients().add(client);
                return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT);           
            }else{
                ClientList newList = new ClientList();
                ArrayList<Cliente> list = new ArrayList<Cliente>();
                list.add(client);
                newList.setClients(list);
                return JsonUtils.INSTANCIA.writeJSON(newList, JsonUtils.FILE_TYPE.CLIENT);  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteClient(Cliente client){
        try {
            ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
            
            existingList.getClients().removeIf((Cliente c) -> c.getNit().equals(client.getNit()));

            return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT);           
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }     
    }
    public boolean editClient(Cliente client){
        try {
            ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
            
            existingList.getClients().removeIf((Cliente c) -> c.getNit().equals(client.getNit()));
            
            existingList.getClients().add(client);

            return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT);           
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }    
}
