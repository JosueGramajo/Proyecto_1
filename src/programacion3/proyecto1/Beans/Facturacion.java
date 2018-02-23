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
    private int id;
    private int noFactura;
    private String serie;
    
    private String nombre;
    private String nit;
    private String direccion;

    private int forma_pago;
    private double total;
    private boolean estado; // activo=true, anulado=false

    

}
