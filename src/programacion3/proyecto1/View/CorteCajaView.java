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
import programacion3.proyecto1.Beans.Facturacion;

/**
 *
 * @author Jose
 */
public class CorteCajaView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbNit, lbNombre, lbSerie, lbCorrelativo, lbFormaPago;
    private TextField tfNit, tfNombre, tfSerie, tfCorrelativo;
    private ComboBox cbFormaPago;
    private Button btnBuscar;
    
    private TableView tblFactura;

    /*private final ObservableList<Cliente> data = FXCollections.observableArrayList(
            new Cliente("1","Josue Gramajo", "Ciudad", "12345678", 1),
            new Cliente("2","Kevin Vasquez", "Ciudad", "12345678", 1),
            new Cliente("3","Manuel Vega", "Ciudad", "12345678", 1),
            new Cliente("4","Jose Perez", "Ciudad", "12345678", 1)
        );*/
    
    public Stage ventana(){    
        
        lbNit = new Label("Nit");
        tfNit = new TextField();
        tfNit.setMaxWidth(80);
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        lbSerie = new Label("Serie");
        tfSerie = new TextField();
        tfSerie.setMaxWidth(80);
        lbCorrelativo = new Label("Correlativo");
        tfCorrelativo = new TextField();
        tfCorrelativo.setMaxWidth(80);
        lbFormaPago = new Label("Forma Pago");
        cbFormaPago = new ComboBox();      
        cbFormaPago.getItems().addAll("Contado", "Crédito", "Cheque" );        
        
        btnBuscar = new Button("Actualizar Información");
        
        tblFactura = new TableView();
        tblFactura.setPrefWidth(650);
        tblFactura.setMaxHeight(250);
        tblFactura.setEditable(true);
    
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Facturacion,Integer>("id"));
        TableColumn colSerie = new TableColumn("Serie");
        colSerie.setCellValueFactory(new PropertyValueFactory<Facturacion,String>("serie"));
        TableColumn colCorrelativo = new TableColumn("Correlativo");
        colCorrelativo.setCellValueFactory(new PropertyValueFactory<Facturacion,String>("correletivo"));
        TableColumn colNit = new TableColumn("Nit");
        colNit.setCellValueFactory(new PropertyValueFactory<Facturacion,String>("nit"));
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Facturacion,Integer>("nombre"));
        TableColumn colFormaPago = new TableColumn("Forma Pago");
        colFormaPago.setCellValueFactory(new PropertyValueFactory<Facturacion,Integer>("forma_pago"));
        TableColumn colTotal = new TableColumn("Total");
        colTotal.setCellValueFactory(new PropertyValueFactory<Facturacion,Integer>("total"));
        TableColumn colEstado = new TableColumn("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<Facturacion,Integer>("estado"));
        
        //tblFactura.setItems(datacolNombre
        tblFactura.getColumns().addAll(colId, colSerie, colCorrelativo, colNit, colNombre, colFormaPago, colTotal, colEstado);
        
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
        
        grid.add(tblFactura, 0,3,5,1);

        scene = new Scene(grid, 615, 400);
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Corte Caja");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    public void eventos(){
    
    }
}
