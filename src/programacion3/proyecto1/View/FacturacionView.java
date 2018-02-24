/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableCell;
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
import programacion3.proyecto1.Beans.Factura;
import programacion3.proyecto1.Beans.FacturacionDetalle;
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Handlers.ClientHandler;
import programacion3.proyecto1.Handlers.InvoiceHandler;
import programacion3.proyecto1.Handlers.UserHandler;
import programacion3.proyecto1.ProgramacionIIIProyecto1;
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
    private Button btnAgregar,btnAgregarProducto;
    private ComboBox<Producto> cbProducto;
    private TableView tblFactura;
    
    private final ObservableList<DetalleFactura> data = FXCollections.observableArrayList();
    
    private ObservableList<Producto> products = FXCollections.observableArrayList();
    
    private Producto selectedProduct = new Producto();
    
    private static double totalFactura = 0;
    private static int idDetalle = 1;
    
    private String serie = "";
    private int noFactura = 0;
    
    private void initInvoiceData(){
        noFactura = InvoiceHandler.INSTANCIA.getCurrentInvoiceNumber() + 1;
        serie = "A";
    }
    
    public Stage ventana(){    
        totalFactura = 0;
        
        initInvoiceData();
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
        tfSerie.setText(serie);
        tfSerie.setEditable(false);
        
        lbCorrelativo = new Label("No. Factura");
        lbCorrelativo.setMinWidth(Region.USE_PREF_SIZE);
        tfCorrelativo = new TextField();
        tfCorrelativo.setMaxWidth(100);
        tfCorrelativo.setEditable(false);
        tfCorrelativo.setText(Integer.toString(noFactura));
        
        lbFormaPago = new Label("Forma Pago");
        cbFormaPago = new ComboBox();      
        cbFormaPago.getItems().addAll("Contado");
        lbTotal = new Label("Total");
        tfTotal = new TextField();
        tfTotal.setMaxWidth(80);
        tfTotal.setEditable(false);
        tfTotal.setText(Double.toString(totalFactura));
        
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
        
        btnAgregar = new Button("Facturar");
        
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
        
        TableColumn colAction = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        Callback<TableColumn<DetalleFactura,String>, TableCell<DetalleFactura, String>> cellFactory;
        cellFactory = new Callback<TableColumn<DetalleFactura,String>, TableCell<DetalleFactura, String>>(){
            @Override
            public TableCell<DetalleFactura, String> call(TableColumn<DetalleFactura, String> param) {
                final TableCell<DetalleFactura, String> cell = new TableCell<DetalleFactura, String>(){
                    final Button btn = new Button("Eliminar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                DetalleFactura detalle = getTableView().getItems().get(getIndex());
                                data.removeIf((DetalleFactura d) -> d.getIdDetalle() == detalle.getIdDetalle());
                                totalFactura = totalFactura - detalle.getSubTotal();
                                tfTotal.setText(Double.toString(totalFactura));
                                tblFactura.refresh();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }                    
                };
                return cell;
            }   
        };
        colAction.setCellFactory(cellFactory);
        
        tblFactura.setItems(data);
        tblFactura.getColumns().addAll(colNombre, colCantidad, colPrecio, colDescuento, colSubTotal, colAction);
        
        
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
            if(p != null){
                tfPrecio.setText(p.getPrecio().toString());
            }
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
        grid.add(lbNit, 0,1);
        grid.add(tfNit, 1,1);
        grid.add(lbNombre, 2,1);
        grid.add(tfNombre, 3,1,4,1);
        grid.add(lbDireccion, 0,2);
        grid.add(tfDireccion, 1,2,6,1);
        grid.add(lbFormaPago, 3,7);
        grid.add(cbFormaPago, 4,7);
        grid.add(lbTotal, 5,7);
        grid.add(tfTotal, 6,7);
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
    private boolean checkInvoiceValues(){
        if(data.size() == 0){
            ValoresStaticos.MSG_ERROR("Debe seleccionar al menos un producto");
            return false;
        }
        if(tfSerie.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese la serie de la factura");
            return false;
        }
        if(tfSerie.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese el numero de la factura");
            return false;
        }
        if(tfNombre.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese el nombre de la factura");
            return false;
        }
        if(tfNit.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese el numero de la factura");
            return false;
        }
        if(tfDireccion.getText().equals("")){
            ValoresStaticos.MSG_ERROR("Ingrese el numero de la factura");
            return false;
        }
        if(cbFormaPago.getValue() == null){
            ValoresStaticos.MSG_ERROR("Seleccione una forma de pago");
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
        detalle.setIdDetalle(idDetalle);
        
        totalFactura = totalFactura + (cantidad * selectedProduct.getPrecio());
        tfTotal.setText(Double.toString(totalFactura));
        
        data.add(detalle);
        
        tblFactura.refresh();
        
        idDetalle++;
    }
    public void eventos(){
        tfNit.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal){
                Cliente client = ClientHandler.INSTANCIA.searchClientByNit(tfNit.getText());
                if(client != null){
                    tfNombre.setText(client.getNombre());
                    tfDireccion.setText(client.getDireccion());
                    
                    cbFormaPago.getItems().clear();
                    if(client.isClienteCredito()){ 
                        cbFormaPago.getItems().addAll("Contado", "Crédito", "Cheque" );
                    }else{    
                        cbFormaPago.getItems().addAll("Contado");
                    }
                }else{
                    tfNombre.setText("");
                    tfDireccion.setText("");
                    cbFormaPago.getItems().clear();
                    cbFormaPago.getItems().addAll("Contado");
                }
            }
        });
                
        btnAgregarProducto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(checkProductValues()){
                    double descuento = tfCantidad.getText().equals("") ? 0 : Double.parseDouble(tfCantidad.getText());
                    addProductDetail(Integer.parseInt(tfCantidad.getText()),descuento);
                    
                    tfCantidad.setText("");
                    tfPrecio.setText("");
                    tfDescuento.setText("");
                    cbProducto.setValue(null);
                }
            }
        });
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(checkInvoiceValues()){
                    Factura fac = new Factura();
                    fac.setNoFactura(Integer.parseInt(tfCorrelativo.getText()));
                    fac.setSerie(tfSerie.getText());
                    fac.setNombre(tfNombre.getText());
                    fac.setDireccion(tfDireccion.getText());
                    fac.setNit(tfNit.getText());
                    fac.setTotal(totalFactura);
                    fac.setTipoVenta(cbFormaPago.getValue().toString());
                    fac.setSucursal(ValoresStaticos.ID_SUCURSAL);
                    
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    
                    fac.setFecha(dateFormat.format(date));
                    
                    ArrayList<DetalleFactura> detalle = new ArrayList<DetalleFactura>();
                    for(DetalleFactura d : data){
                        detalle.add(d);
                    }
                    fac.setDetalle(detalle);
                    
                    if(InvoiceHandler.INSTANCIA.addInvoice(fac)){
                        ValoresStaticos.MSG_INFO("Factura generada correctamente");
                        totalFactura = 0;
                        stage.close(); 
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al generar la factura");
                    }
                }
            }
        });
    }
}
