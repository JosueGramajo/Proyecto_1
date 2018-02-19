/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programacion3.proyecto1.Beans.Cliente;

/**
 *
 * @author Kevin Reus
 */
public class ClienteView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbNit, lbNombre, lbDireccion, lbTelefono, lbTipoCliente; 
    private TextField tfNit, tfNombre, tfDireccion, tfTelefono;
    private ComboBox cbTipoCliente;
    private Button btnAgregar;
    private TableView tblCliente;

    private final ObservableList<Cliente> data = FXCollections.observableArrayList(
            new Cliente(1,"Josue Gramajo", "Ciudad", "12345678", 1),
            new Cliente(2,"Kevin Vasquez", "Ciudad", "12345678", 1),
            new Cliente(3,"Manuel Vega", "Ciudad", "12345678", 1),
            new Cliente(4,"Jose Perez", "Ciudad", "12345678", 1)
        );
    
    public Stage ventana(){    
        
        lbNit = new Label("Nit");
        tfNit = new TextField();
        tfNit.setMaxWidth(150);
        
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        tfNombre.setMaxWidth(150);
        
        lbDireccion = new Label("Direccion");
        tfDireccion = new TextField();
        tfDireccion.setMaxWidth(150);
        
        lbTelefono = new Label("Telefono");
        tfTelefono = new TextField();
        tfTelefono.setMaxWidth(150);
        
        lbTipoCliente = new Label("Tipo de Cliente");
        cbTipoCliente = new ComboBox();
        cbTipoCliente.setMaxWidth(150);
        
        btnAgregar = new Button("Agregar");
        
        tblCliente = new TableView();
        tblCliente.setPrefWidth(600);
        tblCliente.setEditable(true);
    
        TableColumn colNit = new TableColumn("Nit");
        colNit.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("nit"));
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        TableColumn colDireccion = new TableColumn("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        TableColumn colTelefono = new TableColumn("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        TableColumn colTipoCliente = new TableColumn("TipoCliente");
        colTipoCliente.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("tipo_cliente"));
        
        tblCliente.setItems(data);
        tblCliente.getColumns().addAll(colNit, colNombre, colDireccion, colTelefono, colTipoCliente);

        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Contado",
            "Crédito"
        );
        lbTipoCliente = new Label("Tipo de Cliente");
        cbTipoCliente = new ComboBox(options);      
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lbNit, 0,0);
        grid.add(tfNit, 1,0);
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1);
        grid.add(lbDireccion, 0,2);
        grid.add(tfDireccion, 1,2);
        grid.add(lbTelefono, 0,3);
        grid.add(tfTelefono, 1,3);
        grid.add(lbTipoCliente, 0,4);
        grid.add(cbTipoCliente, 1,4);
        grid.add(btnAgregar,1,5);
        grid.add(tblCliente, 0,6 , 2,1);
     
        scene = new Scene(grid, 610, 500);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Clientes");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    public void eventos(){
    
    }
}
