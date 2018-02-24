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
    
    public void addClientIfNotExist(Cliente client){
        try {
            boolean exist = false;
            
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CLIENT.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
                for(Cliente c : existingList.getClients()){
                    if(c.getNit().equals(client.getNit())){
                        exist = true;
                        break;
                    }
                }
                if(!exist){
                    existingList.getClients().add(client);
                    JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT);  
                }
            }else{
                ClientList newList = new ClientList();
                ArrayList<Cliente> list = new ArrayList<Cliente>();
                list.add(client);
                newList.setClients(list);
                JsonUtils.INSTANCIA.writeJSON(newList, JsonUtils.FILE_TYPE.CLIENT);  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addConsumo(String nit, double consumo){
        String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CLIENT.rawValue() + ".json";
        if(new File(destinationPath).exists()){
            try {
                ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
                for(Cliente c : existingList.getClients()){
                    if(c.getNit().equals(nit)){
                        double nuevoConsumo = (c.getConsumo() + consumo);
                        c.setConsumo(nuevoConsumo);
                        if(nuevoConsumo > 5000 && !c.isClienteCredito()){
                            ValoresStaticos.MSG_INFO("El cliente a consumido mas de Q5000, ahora puede consumir al credito");
                            c.setClienteCredito(true);
                        }
                        
                        JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT); 
                        
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Cliente searchClientByNit(String nit){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CLIENT.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
                for(Cliente c : existingList.getClients()){
                    if(c.getNit().equals(nit)){
                        return c;
                    }
                }
          
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void addCreditAmount(String nit, double amount){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CLIENT.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                ClientList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
                for(Cliente c : existingList.getClients()){
                    if(c.getNit().equals(nit)){
                        double newAmount = c.getSaldoCredito() + amount;
                        c.setSaldoCredito(newAmount);
                        
                        if(newAmount > 20000 && c.isClienteCredito()){
                            ValoresStaticos.MSG_ERROR("El usuario ha superado el limite de credito, ya no se le otorgara mas credito");
                            c.setClienteCredito(false);
                        }
                        
                        JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CLIENT);
                        
                        break;
                    }
                }
          
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
