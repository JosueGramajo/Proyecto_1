/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Handlers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacion3.proyecto1.Beans.CorteCaja;
import programacion3.proyecto1.Beans.Lists.CorteCajaList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;
import programacion3.proyecto1.utils.StatusResponse;

/**
 *
 * @author josue
 */
public class CorteCajaHandler {
    public static CorteCajaHandler INSTANCIA = new CorteCajaHandler();
   
    public boolean addCorteCaja(CorteCaja corte){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CORTE.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                
                CorteCajaList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CORTE, CorteCajaList.class);
               
                existingList.getLista().add(corte);
               
                return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.CORTE);           
            }else{
                
                CorteCajaList newList = new CorteCajaList();
                ArrayList<CorteCaja> cList = new ArrayList<CorteCaja>();

                cList.add(corte);
                
                newList.setLista(cList);
                
                return JsonUtils.INSTANCIA.writeJSON(newList, JsonUtils.FILE_TYPE.CORTE);  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean checkExisting(int sucursal){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
   
        String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.CORTE.rawValue() + ".json";
        if(new File(destinationPath).exists()){

            try {
                CorteCajaList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CORTE, CorteCajaList.class);
                
                for(CorteCaja c : existingList.getLista()){
                    if(c.getFecha().equals(currentDate) && c.getSucursal() == sucursal){
                        return true;
                    }
                }
                           
                return false;
            } catch (IOException ex) {
                return false;
            }
        }
        return false;
    }
}
