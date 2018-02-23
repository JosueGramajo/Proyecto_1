/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

/**
 *
 * @author Kevin Reus
 */
public class Cliente {
    
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean clienteCredito;
    private double saldoCredito;
    private double consumo;

    public Cliente(String nit, String nombre, String direccion, String telefono, boolean clienteCredito, double saldoCredito, double consumo) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.clienteCredito = clienteCredito;    
        this.saldoCredito = saldoCredito;
        this.consumo = consumo;
    }
    public Cliente(){
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the clienteCredito
     */
    public boolean isClienteCredito() {
        return clienteCredito;
    }

    /**
     * @param clienteCredito the clienteCredito to set
     */
    public void setClienteCredito(boolean clienteCredito) {
        this.clienteCredito = clienteCredito;
    }

    /**
     * @return the saldoCredito
     */
    public double getSaldoCredito() {
        return saldoCredito;
    }

    /**
     * @param saldoCredito the saldoCredito to set
     */
    public void setSaldoCredito(double saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

    /**
     * @return the consumo
     */
    public double getConsumo() {
        return consumo;
    }

    /**
     * @param consumo the consumo to set
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    
}
