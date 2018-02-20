/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jose
 */
public class FacturacionDetalle {
    private final SimpleIntegerProperty id; // ID de la factura
    private final SimpleIntegerProperty id_producto;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty cantidad;
    private final SimpleDoubleProperty precio ;
    private final SimpleDoubleProperty descuento;
    private final SimpleDoubleProperty sub_total;

    public FacturacionDetalle(int id, int id_producto, String nombre, int cantidad, double precio, double descuento, double sub_total) {
        this.id = new SimpleIntegerProperty(id);
        this.id_producto = new SimpleIntegerProperty(id_producto);
        this.nombre = new SimpleStringProperty(nombre);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.precio = new SimpleDoubleProperty(precio);
        this.descuento = new SimpleDoubleProperty(descuento);
        this.sub_total = new SimpleDoubleProperty(sub_total);
    }

    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public int getIdProducto() {
        return id_producto.get();
    }
    public void setIdProducto(int id_producto) {
        this.id_producto.set(id_producto);
    }

    public String getNombre() {
        return nombre.get();
    }
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getCantidad() {
        return cantidad.get();
    }
    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public double getPrecio() {
        return precio.get();
    }
    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public double getDescuento() {
        return descuento.get();
    }
    public void setDescuento(double descuento) {
        this.descuento.set(descuento);
    }

    public double getSubTotal() {
        return sub_total.get();
    }
    public void setSubTotal() {
        double sub = cantidad.get() * (precio.get() - (precio.get() * descuento.get() / 100));
        this.sub_total.set(sub);
    }
    
}
