/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import programacion3.proyecto1.Beans.Cliente;
import programacion3.proyecto1.Beans.DetalleFactura;
import programacion3.proyecto1.Beans.FacturacionDetalle;
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author Jose
 */
public class FacturacionView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    // Encabezado Factura
    private Label lbNit, lbNombre, lbDireccion, lbSerie, lbCorrelativo, lbFormaPago, lbTotal; 
    private TextField tfNit, tfNombre, tfDireccion, tfSerie, tfCorrelativo, tfTotal;
    private ComboBox cbFormaPago;
    
    // Detalle Factura
    private Label lbNombreProducto, lbCantidad, lbPrecio, lbDescuento;
    private TextField tfNombreProducto, tfCantidad, tfPrecio, tfDescuento;
    private Button btnAgregar,btnAnular,btnAgregarProducto;
    private ComboBox<Producto> cbProducto;
    private TableView tblFactura;
    
    private final ObservableList<DetalleFactura> data = FXCollections.observableArrayList();
    
    private ObservableList<Producto> products = FXCollections.observableArrayList();
    
    private Producto selectedProduct = new Producto();
    
    public Stage ventana(){    
        initProducts();
        
        lbNit = new Label("Nit");
        tfNit = new TextField();
        tfNit.setMaxWidth(100);
        
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        
        lbDireccion = new Label("Dirección");
        tfDireccion = new TextField();
        
        lbSerie = new Label("Serie");
        tfSerie = new TextField();
        tfSerie.setMaxWidth(80);
        
        lbCorrelativo = new Label("No. Factura");
        lbCorrelativo.setMinWidth(Region.USE_PREF_SIZE);
        tfCorrelativo = new TextField();
        tfCorrelativo.setMaxWidth(100);
        tfCorrelativo.setEditable(false);
        
        lbFormaPago = new Label("Forma Pago");
        cbFormaPago = new ComboBox();      
        cbFormaPago.getItems().addAll("Contado", "Crédito", "Cheque" );
        lbTotal = new Label("Total");
        tfTotal = new TextField();
        tfTotal.setMaxWidth(80);
        
        btnAgregarProducto = new Button("Agregar");
        
        lbNombreProducto = new Label("Descripción");
        tfNombreProducto = new TextField();
        
        lbCantidad = new Label("Cantidad");
        tfCantidad = new TextField();
        tfCantidad.setMaxWidth(100);
        
        lbPrecio = new Label("Precio");
        tfPrecio = new TextField();
        tfPrecio.setMaxWidth(100);
        tfPrecio.setEditable(false);
        
        lbDescuento = new Label("Descuento");
        tfDescuento = new TextField();        
        tfDescuento.setMaxWidth(100);
        
        btnAgregar = new Button("Agregar");
        btnAnular = new Button("Anular");
        
        tblFactura = new TableView();
        tblFactura.setPrefWidth(600);
        tblFactura.setMaxHeight(250);
        tblFactura.setEditable(true);
    
        TableColumn colNombre = new TableColumn("Producto");
        colNombre.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("nombre"));
        
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("cantidad"));
        
        TableColumn colPrecio = new TableColumn("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,String>("precio"));
        
        TableColumn colDescuento = new TableColumn("Descuento");
        colDescuento.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,Integer>("descuento"));
        
        TableColumn colSubTotal = new TableColumn("SubTotal");
        colSubTotal.setCellValueFactory(new PropertyValueFactory<FacturacionDetalle,Integer>("subTotal"));
        
        tblFactura.setItems(data);
        tblFactura.getColumns().addAll(colNombre, colCantidad, colPrecio, colDescuento, colSubTotal);
        
        
        cbProducto = new ComboBox(products);
        Callback<ListView<Producto>, ListCell<Producto>> factory = lv -> new ListCell<Producto>() {
            @Override
            protected void updateItem(Producto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };
        cbProducto.setCellFactory(factory);
        cbProducto.setButtonCell(factory.call(null));
        
        cbProducto.valueProperty().addListener((ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) -> {
            Producto p = cbProducto.getValue();
            selectedProduct = p;
            tfPrecio.setText(p.getPrecio().toString());
        });
       
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(5, 5, 0, 15));
        
        // Encabezado
        grid.add(lbSerie, 0,0);
        grid.add(tfSerie, 1,0);
        grid.add(lbCorrelativo, 2,0);
        grid.add(tfCorrelativo, 3,0);
        grid.add(lbNit, 5,1);
        grid.add(tfNit, 6,1);
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1,4,1);
        grid.add(lbDireccion, 0,2);
        grid.add(tfDireccion, 1,2,6,1);
        grid.add(lbFormaPago, 3,7);
        grid.add(cbFormaPago, 4,7);
        grid.add(lbTotal, 5,7);
        grid.add(tfTotal, 6,7);
        grid.add(btnAnular, 5, 8);
        grid.add(btnAgregar,6,8);

        // Detalle
        grid.add(lbNombreProducto, 0,3,3,1);
        grid.add(cbProducto, 0,4,3,1);
        grid.add(lbPrecio, 2,3);
        grid.add(tfPrecio, 2,4);
        grid.add(lbCantidad, 3,3);
        grid.add(tfCantidad, 3,4);
        grid.add(lbDescuento, 4,3);
        grid.add(tfDescuento, 4,4);
        grid.add(btnAgregarProducto, 0, 5);
        grid.add(tblFactura, 0,6,7,1);

        scene = new Scene(grid, 800, 700);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Facturación");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;
    }
    private boolean checkProductValues(){
        if(cbProducto.getValue() == null){
            ValoresStaticos.MSG_ERROR("Seleccione un producto para agregar");
            return false;
        }
        if(tfCantidad.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese una cantidad");
            return false;
        }
        if(!isInteger(tfCantidad.getText())){
            ValoresStaticos.MSG_ERROR("La cantidad debe ser un valor numerico");
            return false;
        }
        if(!tfDescuento.getText().equals("") && isDouble(tfDescuento.getText())){
            ValoresStaticos.MSG_ERROR("El descuento debe ser un valor numerico");
            return false;
        }
        return true;
    }
    private boolean isInteger(String value){
        try{
            int test = Integer.parseInt(value);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    private boolean isDouble(String value){
        try{
            double test = Double.parseDouble(value);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    private void initProducts(){
        products.clear();
        try{
            ProductList list = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.PRODUCT, ProductList.class);
            for(Producto p : list.getProducts()){
                products.add(p);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private void addProductDetail(int cantidad, double descuento){
        DetalleFactura detalle = new DetalleFactura();
        detalle.setIdProducto(selectedProduct.getId());
        detalle.setNombre(selectedProduct.getNombre());
        detalle.setCantidad(cantidad);
        detalle.setDescuento(descuento);
        detalle.setPrecio(selectedProduct.getPrecio());
        detalle.setSubTotal((cantidad * selectedProduct.getPrecio()));
        
        data.add(detalle);
        
        tblFactura.refresh();
    }
    public void eventos(){
        btnAgregarProducto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(checkProductValues()){
                    double descuento = tfCantidad.getText().equals("") ? 0 : Double.parseDouble(tfCantidad.getText());
                    addProductDetail(Integer.parseInt(tfCantidad.getText()),descuento);
                }
            }
        });
    }
}
