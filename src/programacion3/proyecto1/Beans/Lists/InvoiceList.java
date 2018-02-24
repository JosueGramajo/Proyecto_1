/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Factura;

/**
 *
 * @author josue
 */
public class InvoiceList extends BaseObject{
    private int currentId;
    private int currentNo;
    private ArrayList<Factura> facturas;
    
    public InvoiceList(int currentNo, ArrayList<Factura> facturas){
        this.currentNo = currentNo;
        this.facturas = facturas;
    }
    
    public InvoiceList(){}

    
    
    /**
     * @return the currentNo
     */
    public int getCurrentNo() {
        return currentNo;
    }

    /**
     * @param currentNo the currentNo to set
     */
    public void setCurrentNo(int currentNo) {
        this.currentNo = currentNo;
    }

    /**
     * @return the facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the currentId
     */
    public int getCurrentId() {
        return currentId;
    }

    /**
     * @param currentId the currentId to set
     */
    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
    
    
}
