/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import programacion3.proyecto1.Beans.Usuario;

/**
 *
 * @author Jose
 */
public class UsuarioView {
    
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbId, lbNombre, lbDireccion, lbTelefono, lbUsername, lbPassword, lbTipoUsuario; 
    private TextField tfId, tfNombre, tfDireccion, tfTelefono, tfUsername;
    private PasswordField pfPassword;
    private ComboBox cbTipoUsuario;
    private Button btnAgregar;
    private TableView tblUsuario;

    private final ObservableList<Usuario> data = FXCollections.observableArrayList(
            new Usuario(1,"Josue Gramajo", "Ciudad", "12345678", "jgramajo", "123",1),
            new Usuario(2,"Kevin Vasquez", "Ciudad", "12345678", "kvasquez", "123",1),
            new Usuario(3,"Manuel Vega", "Ciudad", "12345678", "mvega", "123",1),
            new Usuario(4,"Jose Perez", "Ciudad", "12345678", "jperez", "123",1)
        );
 
    
    public Stage ventana(){    
        
        lbId = new Label("Id");
        tfId = new TextField();
        tfId.setMaxWidth(150);
        
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        tfNombre.setMaxWidth(150);
        
        lbDireccion = new Label("Direccion");
        tfDireccion = new TextField();
        tfDireccion.setMaxWidth(150);
        
        lbTelefono = new Label("Telefono");
        tfTelefono = new TextField();
        tfTelefono.setMaxWidth(150);
        
        lbUsername = new Label("Username");
        tfUsername = new TextField();
        tfUsername.setMaxWidth(150);
        
        lbPassword = new Label("Password");
        pfPassword = new PasswordField();
        pfPassword.setMaxWidth(150);
        
        btnAgregar = new Button("Agregar");
        
        tblUsuario = new TableView();
        tblUsuario.setPrefWidth(600);
        tblUsuario.setEditable(true);
        
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("id"));
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        TableColumn colDireccion = new TableColumn("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<Usuario,String>("direccion"));
        TableColumn colTelefono = new TableColumn("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<Usuario,String>("telefono"));
        TableColumn colUsername = new TableColumn("Usuario");
        colUsername.setCellValueFactory(new PropertyValueFactory<Usuario,String>("username"));
        TableColumn colTipoUsuario = new TableColumn("Tipo Usuario");
        colTipoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("tipo_usuario"));
        
        tblUsuario.setItems(data);
        tblUsuario.getColumns().addAll(colId, colNombre, colDireccion, colTelefono, colUsername, colTipoUsuario);

        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Administrador",
            "Cajero"
        );
        lbTipoUsuario = new Label("Rol");
        cbTipoUsuario = new ComboBox(options);      
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lbId, 0,0);
        grid.add(tfId, 1,0);
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1);
        grid.add(lbDireccion, 0,2);
        grid.add(tfDireccion, 1,2);
        grid.add(lbTelefono, 0,3);
        grid.add(tfTelefono, 1,3);
        grid.add(lbUsername,0,4);
        grid.add(tfUsername, 1,4);
        grid.add(lbPassword, 0,5);
        grid.add(pfPassword, 1,5);
        grid.add(btnAgregar,1,6);
        grid.add(tblUsuario, 0,7 , 2,1);
             
                
        scene = new Scene(grid, 610, 500);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/usuario.png")));
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    
    public void eventos(){
    
    }
}
