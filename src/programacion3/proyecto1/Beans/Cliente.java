/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kevin Reus
 */
public class Cliente {
    
    private final SimpleStringProperty nit;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty telefono;
    private final SimpleIntegerProperty tipo_cliente;

    public Cliente(String nit, String nombre, String direccion, String telefono, int tipo_cliente) {
        this.nit = new SimpleStringProperty(nit);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.tipo_cliente = new SimpleIntegerProperty(tipo_cliente);    
    }

    public String getNit() {
        return nit.get();
    }
    
    public void setId(String nit) {
        this.nit.set(nit);
    }
    
    public String getNombre() {
        return nombre.get();
    }
    
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return direccion.get();
    }
    
    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public int getTipo_cliente() {
        return tipo_cliente.get();
    }
    
    public void setTipo_cliente(int tipo_cliente) {
        this.tipo_cliente.set(tipo_cliente);
    }

       
}
