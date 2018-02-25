/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programacion3.proyecto1.Beans.CorteCaja;
import programacion3.proyecto1.Beans.Factura;
import programacion3.proyecto1.Beans.Lists.InvoiceList;
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Handlers.CorteCajaHandler;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author Jose
 */
public class CorteCajaView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbNit, lbNombre, lbSerie, lbCorrelativo, lbFormaPago, lbTotalContado, lbTotalCredito, lbTotalCheques, lbFilterFecha, lbFilterSucursal;
    private TextField tfNit, tfNombre, tfSerie, tfCorrelativo;
    private ComboBox cbFormaPago;
    private Button btnBuscar, btnRestaurarDatos, btnRealizarCorte;
    
    private TableView tblFactura;

    private final ObservableList<Factura> data = FXCollections.observableArrayList();
    
    private double totalContado = 0;
    private double totalCredito = 0;
    private double totalCheques = 0;
    
    private String dateFilter = "";
    private String agencyFilter = "";
    
    private void initInvoiceData(){
        data.clear();
        try{
            InvoiceList list = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.SALE, InvoiceList.class);
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            
            List<Factura> results = list.getFacturas();
            results = results.stream().filter((Factura f) -> f.getFecha().equals(dateFormat.format(date))).collect(Collectors.toList());
            results = results.stream().filter((Factura f) -> f.getSucursal() == ValoresStaticos.ID_SUCURSAL).collect(Collectors.toList());

            dateFilter = dateFormat.format(date);
            agencyFilter = "Sucursal: " + ValoresStaticos.ID_SUCURSAL;
            
            for(Factura f : results){
                data.add(f);
                if(f.getTipoVenta().equals("Contado")){
                    totalContado = totalContado + f.getTotal();
                }else if(f.getTipoVenta().equals("Crédito")){
                    totalCredito = totalCredito + f.getTotal();
                }else if(f.getTipoVenta().equals("Cheque")){
                    totalCheques = totalCheques + f.getTotal();
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private void filterInformation(){
        data.clear();
        try{
            InvoiceList list = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.SALE, InvoiceList.class);
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            
            List<Factura> results = list.getFacturas();
            results = results.stream().filter((Factura f) -> f.getFecha().equals(dateFormat.format(date))).collect(Collectors.toList());
            results = results.stream().filter((Factura f) -> f.getSucursal() == ValoresStaticos.ID_SUCURSAL).collect(Collectors.toList());
            
            if(!tfNit.getText().equals("")) results = results.stream().filter((Factura f) -> f.getNit().equals(tfNit.getText())).collect(Collectors.toList());
            
            if(!tfNombre.getText().equals("")) results = results.stream().filter((Factura f) -> f.getNombre().equals(tfNombre.getText())).collect(Collectors.toList());
            
            if(!tfCorrelativo.getText().equals("")) results =  results.stream().filter((Factura f) -> f.getNoFactura() == Integer.parseInt(tfCorrelativo.getText())).collect(Collectors.toList());
            
            if(!tfSerie.getText().equals("")) results = results.stream().filter((Factura f) -> f.getSerie().equals(tfSerie.getText())).collect(Collectors.toList());
            
            if(cbFormaPago.getValue() != null) results = results.stream().filter((Factura f) -> f.getTipoVenta().equals(cbFormaPago.getValue())).collect(Collectors.toList());
            
            
            for(Factura f : results){
                data.add(f);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public Stage ventana(){    
        initInvoiceData();
        
        lbNit = new Label("Nit");
        tfNit = new TextField();
        tfNit.setMaxWidth(80);
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        lbSerie = new Label("Serie");
        tfSerie = new TextField();
        tfSerie.setMaxWidth(80);
        lbCorrelativo = new Label("No Factura");
        tfCorrelativo = new TextField();
        tfCorrelativo.setMaxWidth(80);
        lbFormaPago = new Label("Forma Pago");
        cbFormaPago = new ComboBox();      
        cbFormaPago.getItems().addAll("Contado", "Crédito", "Cheque" );        
        
        btnBuscar = new Button("Filtrar Información");
        
        btnRestaurarDatos = new Button("Restaurar valores");
        btnRestaurarDatos.setVisible(false);
        
        lbTotalContado = new Label("Total contado: Q." + totalContado);
        lbTotalCredito = new Label("Total credito: Q." + totalCredito);
        lbTotalCheques = new Label("Total cheques: Q." + totalCheques);
        
        btnRealizarCorte = new Button("Realizar Corte");
        
        tblFactura = new TableView();
        tblFactura.setPrefWidth(800);
        
        lbFilterFecha = new Label("Fecha: " + dateFilter);
        lbFilterSucursal = new Label(agencyFilter);
        
        tblFactura.setMaxHeight(400);
        tblFactura.setEditable(true);
    
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("id"));
        colId.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colSerie = new TableColumn("Serie");
        colSerie.setCellValueFactory(new PropertyValueFactory<Factura,String>("serie"));
        colSerie.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colCorrelativo = new TableColumn("No Factura");
        colCorrelativo.setCellValueFactory(new PropertyValueFactory<Factura,String>("noFactura"));
        colCorrelativo.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colNit = new TableColumn("Nit");
        colNit.setCellValueFactory(new PropertyValueFactory<Factura,String>("nit"));
        colNit.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("nombre"));
        colNombre.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colFormaPago = new TableColumn("Forma Pago");
        colFormaPago.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("tipoVenta"));
        colFormaPago.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colTotal = new TableColumn("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("total"));
        colTotal.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colEstado = new TableColumn("Fecha");
        colEstado.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("fecha"));
        colEstado.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        TableColumn colSucursal = new TableColumn("Sucursal");
        colSucursal.setCellValueFactory(new PropertyValueFactory<Factura,Integer>("sucursal"));
        colSucursal.prefWidthProperty().bind(tblFactura.widthProperty().divide(9));
        
        tblFactura.setItems(data);
        tblFactura.getColumns().addAll(colId, colSerie, colCorrelativo, colNit, colNombre, colFormaPago, colTotal, colEstado, colSucursal);
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(5, 5, 0, 25));

        grid.add(lbSerie, 0,0);
        grid.add(tfSerie, 0,1);
        grid.add(lbCorrelativo, 1,0);
        grid.add(tfCorrelativo,1,1);
        grid.add(lbNit, 2,0);
        grid.add(tfNit, 2,1);
        grid.add(lbNombre, 3,0);
        grid.add(tfNombre, 3,1);
        grid.add(lbFormaPago, 4,0);
        grid.add(cbFormaPago, 4,1);
        grid.add(btnBuscar, 3,2,2,1);
        grid.add(btnRestaurarDatos, 1,2,2,1);
        
        grid.add(tblFactura, 0,3,8,1);
        
        grid.add(btnRealizarCorte, 5, 4);
        
        grid.add(lbFilterFecha, 0, 4);
        grid.add(lbFilterSucursal, 0, 5);
        
        grid.add(lbTotalContado, 0, 6);
        grid.add(lbTotalCredito, 0, 7);
        grid.add(lbTotalCheques, 0, 8);

        scene = new Scene(grid, 900, 800);
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Corte Caja");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    public void eventos(){
        btnBuscar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                filterInformation();
                tblFactura.refresh();
                btnRestaurarDatos.setVisible(true);
            }
        });
        btnRestaurarDatos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initInvoiceData();
                tblFactura.refresh();
                btnRestaurarDatos.setVisible(false);
            }
        });
        btnRealizarCorte.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CorteCaja c = new CorteCaja();
                
                c.setFecha(dateFilter);
                c.setSucursal(ValoresStaticos.ID_SUCURSAL);
                c.setTotalContado(totalContado);
                c.setTotalCredito(totalCredito);
                c.setTotalCheque(totalCheques);
                
                if(CorteCajaHandler.INSTANCIA.addCorteCaja(c)){
                    ValoresStaticos.MSG_INFO("Corte de caja ingresado exitosamente");
                    stage.close(); 
                }else{
                    ValoresStaticos.MSG_ERROR("Ocurrio un error");
                }
            }
        });
    }
}
