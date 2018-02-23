/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Producto;

/**
 *
 * @author josue
 */
public class ProductList extends BaseObject{
    private int currentID;
    private ArrayList<Producto> products;
    
    public ProductList(){}
    
    public ProductList(int currentID, ArrayList<Producto> products){
        this.currentID = currentID;
        this.products = products;
    }

    /**
     * @return the currentID
     */
    public int getCurrentID() {
        return currentID;
    }

    /**
     * @param currentID the currentID to set
     */
    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    /**
     * @return the products
     */
    public ArrayList<Producto> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(ArrayList<Producto> products) {
        this.products = products;
    }
    
}
