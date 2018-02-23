/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Sucursal;

/**
 *
 * @author josue
 */
public class AgencyList extends BaseObject{
    private ArrayList<Sucursal> sucursales;

    public AgencyList(){}
    
    public AgencyList(ArrayList<Sucursal> sucursales){
        this.sucursales = sucursales;
    }
    
    /**
     * @return the sucursales
     */
    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    /**
     * @param sucursales the sucursales to set
     */
    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    
    
}
