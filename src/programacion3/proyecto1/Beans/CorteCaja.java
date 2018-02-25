/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

/**
 *
 * @author josue
 */
public class CorteCaja {
    private String fecha;
    private int sucursal;
    private double totalContado;
    private double totalCredito;
    private double totalCheque;
    
    public CorteCaja(){}
    
    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the sucursal
     */
    public int getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the totalContado
     */
    public double getTotalContado() {
        return totalContado;
    }

    /**
     * @param totalContado the totalContado to set
     */
    public void setTotalContado(double totalContado) {
        this.totalContado = totalContado;
    }

    /**
     * @return the totalCredito
     */
    public double getTotalCredito() {
        return totalCredito;
    }

    /**
     * @param totalCredito the totalCredito to set
     */
    public void setTotalCredito(double totalCredito) {
        this.totalCredito = totalCredito;
    }

    /**
     * @return the totalCheque
     */
    public double getTotalCheque() {
        return totalCheque;
    }

    /**
     * @param totalCheque the totalCheque to set
     */
    public void setTotalCheque(double totalCheque) {
        this.totalCheque = totalCheque;
    }
    
    
}
