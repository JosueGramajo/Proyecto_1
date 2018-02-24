/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class Factura {
    private int id;
    private int noFactura;
    private String serie;
    
    private String nombre;
    private String nit;
    private String direccion;

    private int formaPago;
    private double total;
    private boolean estado; // activo=true, anulado=false
    
    private String tipoVenta;
    
    private String fecha;
    
    private int sucursal;
    
    private ArrayList<DetalleFactura> detalle;
    
    public Factura(int id, int noFactura, String serie, String nit, String direccion, int formaPago, double total, boolean estado){
        this.id = id;
        this.noFactura = noFactura;
        this.serie = serie;
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.formaPago = formaPago;
        this.total = total;
        this.estado = estado;
    }
    
    public Factura(){}

    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the noFactura
     */
    public int getNoFactura() {
        return noFactura;
    }

    /**
     * @param noFactura the noFactura to set
     */
    public void setNoFactura(int noFactura) {
        this.noFactura = noFactura;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the formaPago
     */
    public int getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the detalle
     */
    public ArrayList<DetalleFactura> getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(ArrayList<DetalleFactura> detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the tipoVenta
     */
    public String getTipoVenta() {
        return tipoVenta;
    }

    /**
     * @param tipoVenta the tipoVenta to set
     */
    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

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
    
    
}
