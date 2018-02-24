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
import programacion3.proyecto1.Beans.Factura;
import programacion3.proyecto1.Beans.Lists.InvoiceList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author josue
 */
public class InvoiceHandler {
    public static InvoiceHandler INSTANCIA = new InvoiceHandler();
    
    public int getCurrentInvoiceNumber(){
        String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.SALE.rawValue() + ".json";
        if(new File(destinationPath).exists()){
            try {
                InvoiceList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.SALE, InvoiceList.class);
                return existingList.getCurrentNo();
            } catch (IOException ex) {
                return 0;
            }
        }
        return 0;
    }
    public boolean addInvoice(Factura fac){
        boolean result = false;
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.SALE.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                InvoiceList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.SALE, InvoiceList.class);

                fac.setId(existingList.getCurrentId() + 1);
                
                existingList.setCurrentId(existingList.getCurrentId() + 1);
                existingList.setCurrentNo(fac.getNoFactura());
                existingList.getFacturas().add(fac);
               
                result = JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.SALE);           
            }else{
                InvoiceList newList = new InvoiceList();
                ArrayList<Factura> facList = new ArrayList<Factura>();
               
                fac.setId(100);
                
                facList.add(fac);
                
                newList.setCurrentNo(1);
                newList.setCurrentId(100);
                newList.setFacturas(facList);
                
                result = JsonUtils.INSTANCIA.writeJSON(newList, JsonUtils.FILE_TYPE.SALE);  
            }
            
            if(result){
                Cliente client = new Cliente();
                client.setNombre(fac.getNombre());
                client.setNit(fac.getNit());
                client.setDireccion(fac.getDireccion());
                client.setTelefono("");
                client.setSaldoCredito(0);
                client.setClienteCredito(false);
                client.setConsumo(fac.getTotal());
                ClientHandler.INSTANCIA.addClientIfNotExist(client);
                
                ClientHandler.INSTANCIA.addConsumo(fac.getNit(), fac.getTotal());
            }
            
            return result;
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
