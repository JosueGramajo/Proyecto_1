/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programacion3.proyecto1.Beans.FacturacionDetalle;

/**
 *
 * @author Jose
 */
public class FacturacionView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    // Encabezado Factura
    private Label lbId, lbNit, lbNombre, lbDireccion, lbSerie, lbCorrelativo, lbFormaPago, lbTotal; 
    private TextField tfId, tfNit, tfNombre, tfDireccion, tfSerie, tfCorrelativo, tfTotal;
    private ComboBox cbFormaPago;
    
    // Detalle Factura
    private Label lbIdProducto, lbNombreProducto, lbCantidad, lbPrecio, lbDescuento;
    private TextField tfIdProducto, tfNombreProducto, tfCantidad, tfPrecio, tfDescuento;
    private Button btnAgregar,btnAnular;
    private TableView tblFactura;

    public Stage ventana(){    
        
        lbId = new Label("Id");
        tfId = new TextField();
        tfId.setMaxWidth(80);
        lbNit = new Label("Nit");
        tfNit = new TextField();
        tfNit.setMaxWidth(80);
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        lbDireccion = new Label("Dirección");
        tfDireccion = new TextField();
        lbSerie = new Label("Serie");
        tfSerie = new TextField();
        tfSerie.setMaxWidth(80);
        lbCorrelativo = new Label("Correlativo");
        tfCorrelativo = new TextField();
        tfCorrelativo.setMaxWidth(80);
        lbFormaPago = new Label("Forma Pago");
        cbFormaPago = new ComboBox();      
        cbFormaPago.getItems().addAll("Contado", "Crédito", "Cheque" );
        lbTotal = new Label("Total");
        tfTotal = new TextField();
        tfTotal.setMaxWidth(80);
        
        lbIdProducto = new Label("Id Producto");
        tfIdProducto = new TextField();
        tfIdProducto.setMaxWidth(50);
        lbNombreProducto = new Label("Descripción");
        tfNombreProducto = new TextField();
        lbCantidad = new Label("Cantidad");
        tfCantidad = new TextField();
        tfCantidad.setMaxWidth(50);
        lbPrecio = new Label("Precio");
        tfPrecio = new TextField();
        tfPrecio.setMaxWidth(50);
        lbDescuento = new Label("Descuento");
        tfDescuento = new TextField();        
        tfDescuento.setMaxWidth(50);
        
        btnAgregar = new Button("Agregar");
        btnAnular = new Button("Anular");
        
        tblFactura = new TableView();
        tblFactura.setPrefWidth(600);
        tblFactura.setMaxHeight(250);
        tblFactura.setEditable(true);
    
        TableColumn colProducto = new TableColumn("Producto");
        colProducto.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,Integer>("id_producto"));
        TableColumn colNombre = new TableColumn("Descripción");
        colNombre.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("nombre"));
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("cantidad"));
        TableColumn colPrecio = new TableColumn("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("precio"));
        TableColumn colDescuento = new TableColumn("Descuento");
        colDescuento.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,Integer>("descuento"));
        TableColumn colSubTotal = new TableColumn("SubTotal");
        colSubTotal.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,Integer>("sub_total"));
        
        //tblFactura.setItems(data);
        tblFactura.getColumns().addAll(colProducto, colNombre, colCantidad, colPrecio, colDescuento, colSubTotal);
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(5, 5, 0, 15));
        
        // Encabezado
        grid.add(lbId, 0,0);
        grid.add(tfId, 1,0);
        grid.add(lbSerie, 3,0);
        grid.add(tfSerie, 4,0);
        grid.add(lbCorrelativo, 5,0);
        grid.add(tfCorrelativo, 6,0);
        grid.add(lbNit, 5,1);
        grid.add(tfNit, 6,1);
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1,4,1);
        grid.add(lbDireccion, 0,2);
        grid.add(tfDireccion, 1,2,6,1);
        grid.add(lbFormaPago, 3,6);
        grid.add(cbFormaPago, 4,6);
        grid.add(lbTotal, 5,6);
        grid.add(tfTotal, 6,6);
        grid.add(btnAnular, 5, 7);
        grid.add(btnAgregar,6,7);

        // Detalle
        grid.add(lbIdProducto, 0,3);
        grid.add(tfIdProducto, 0,4);
        grid.add(lbNombreProducto, 1,3,3,1);
        grid.add(tfNombreProducto, 1,4,3,1);
        grid.add(lbCantidad, 4,3);
        grid.add(tfCantidad, 4,4);
        grid.add(lbPrecio, 5,3);
        grid.add(tfPrecio, 5,4);
        grid.add(lbDescuento, 6,3);
        grid.add(tfDescuento, 6,4);
        
        grid.add(tblFactura, 0,5,7,1);

        scene = new Scene(grid, 600, 600);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Facturación");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;
    }
    public void eventos(){
    
    }
}
