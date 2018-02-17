/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Jose
 */
public class Usuario {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleIntegerProperty tipo_usuario;

    public Usuario(int id, String nombre, String direccion, String telefono, String username, String password, int tipo_usuario) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.tipo_usuario = new SimpleIntegerProperty(tipo_usuario);    
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

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getTipo_usuario() {
        return tipo_usuario.get();
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario.set(tipo_usuario);
    }
    
    
    
    
}
