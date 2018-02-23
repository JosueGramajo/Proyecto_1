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
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;
import programacion3.proyecto1.utils.StatusResponse;

/**
 *
 * @author josue
 */
public class ProductHandler {
    public static ProductHandler INSTANCIA = new ProductHandler();
    
    public boolean addProduct(Producto product){
        try {
            String destinationPath = ValoresStaticos.PATH + "/" + JsonUtils.FILE_TYPE.PRODUCT.rawValue() + ".json";
            if(new File(destinationPath).exists()){
                ProductList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.PRODUCT, ProductList.class);
                
                product.setId(existingList.getCurrentID() + 1);

                existingList.setCurrentID(existingList.getCurrentID() + 1);

                existingList.getProducts().add(product);
               
                return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.PRODUCT);           
            }else{
                ProductList newList = new ProductList();
                ArrayList<Producto> list = new ArrayList<Producto>();
               
                product.setId(1);
                list.add(product);
                
                newList.setCurrentID(1);
                newList.setProducts(list);
                
                return JsonUtils.INSTANCIA.writeJSON(newList, JsonUtils.FILE_TYPE.PRODUCT);  
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteProduct(Producto product){
        try {
            ProductList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.PRODUCT, ProductList.class);
            
            existingList.getProducts().removeIf((Producto p) -> p.getId() == product.getId());

            return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.PRODUCT);           
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }     
    }
    public boolean editProduct(Producto product){
        try {
            ProductList existingList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.PRODUCT, ProductList.class);
            
            existingList.getProducts().removeIf((Producto p) -> p.getId() == product.getId());
            
            existingList.getProducts().add(product);

            return JsonUtils.INSTANCIA.writeJSON(existingList, JsonUtils.FILE_TYPE.PRODUCT);           
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }  
}
