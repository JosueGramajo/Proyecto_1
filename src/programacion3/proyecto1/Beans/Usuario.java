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
public class Usuario extends BaseObject{
    
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String username;
    private String password;
    private int tipo_usuario;
    private int sucursal;

    /**
     *
     */
    public Usuario() {
 
    }
    
    public Usuario(int id, String nombre, String direccion, String telefono, String username, String password, int tipo_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.tipo_usuario = tipo_usuario;    
    }

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipo_usuario
     */
    public int getTipo_usuario() {
        return tipo_usuario;
    }

    /**
     * @param tipo_usuario the tipo_usuario to set
     */
    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
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
