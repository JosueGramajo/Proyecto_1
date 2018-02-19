/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Kevin Reus
 */
public class Producto {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty marca;
    private final SimpleStringProperty descripcion;
    private final SimpleDoubleProperty precio;
    private final SimpleIntegerProperty existencia;
    
    public Producto(int id, String nombre, String marca, String descripcion, Double precio, int existencia) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.marca = new SimpleStringProperty(marca);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.existencia = new SimpleIntegerProperty(existencia);    
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
    
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setMarca(String marca) {
        this.nombre.set(marca);
    }
    
    public String getMarca() {
        return marca.get();
    }
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public int getExistencia() {
        return existencia.get();
    }
    
    public void setExistencia(int existencia) {
        this.existencia.set(existencia);
    }
    
}
