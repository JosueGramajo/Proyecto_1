/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Jose
 */
public class Facturacion {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty nit;
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty serie;
    private final SimpleIntegerProperty correletivo;
    private final SimpleIntegerProperty forma_pago;
    private final SimpleDoubleProperty total;
    private final SimpleBooleanProperty estado; // activo=true, anulado=false

    public Facturacion(int id, String nombre, String nit, String direccion, String serie, int correletivo, int forma_pago, double total, boolean estado) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.nit = new SimpleStringProperty(nit);
        this.direccion = new SimpleStringProperty(direccion);
        this.serie = new SimpleStringProperty(serie);
        this.correletivo = new SimpleIntegerProperty(correletivo);
        this.forma_pago = new SimpleIntegerProperty(forma_pago);
        this.total = new SimpleDoubleProperty(total);
        this.estado = new SimpleBooleanProperty(estado);
    }
    
    public Facturacion(String serie, int correletivo){
        this.id = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.nit = new SimpleStringProperty("");
        this.direccion = new SimpleStringProperty("");
        this.serie = new SimpleStringProperty(serie);
        this.correletivo = new SimpleIntegerProperty(correletivo);
        this.forma_pago = new SimpleIntegerProperty(0);
        this.total = new SimpleDoubleProperty(0);
        this.estado = new SimpleBooleanProperty(true);
    }

    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getNit() {
        return nit.get();
    }
    public void setNit(String nit) {
        this.nombre.set(nit);
    }

    public String getDireccion() {
        return direccion.get();
    }
    public void setDireccion(String direccion) {
        this.nombre.set(direccion);
    }

    public String getSerie() {
        return serie.get();
    }
    public void setSerie(String serie) {
        this.nombre.set(serie);
    }

    public int getCorreletivo() {
        return correletivo.get();
    }
    public void setCorrelativo(String correlativo) {
        this.nombre.set(correlativo);
    }
    
    public int getFormaPago() {
        return forma_pago.get();
    }
    public void setFormaPago(int forma_pago) {
        this.forma_pago.set(forma_pago);
    }
    
    public double getTotal() {
        return forma_pago.get();
    }
    public void setTotal(double total) {
        this.total.set(total);
    }
    
    public boolean getActivo() {
        return estado.get();
    }
    public void setActivo(boolean estado) {
        this.estado.set(estado);
    }
}
