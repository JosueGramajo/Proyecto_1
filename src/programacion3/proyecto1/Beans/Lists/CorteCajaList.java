/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.CorteCaja;

/**
 *
 * @author josue
 */
public class CorteCajaList extends BaseObject {
    private ArrayList<CorteCaja> lista;

    /**
     * @return the lista
     */
    public ArrayList<CorteCaja> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList<CorteCaja> lista) {
        this.lista = lista;
    }
    
    
}
