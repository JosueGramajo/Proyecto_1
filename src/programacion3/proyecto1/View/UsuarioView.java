/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Handlers.UserHandler;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author Jose
 */
public class UsuarioView {
    
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbNombre, lbDireccion, lbTelefono, lbUsername, lbPassword, lbTipoUsuario; 
    private TextField tfNombre, tfDireccion, tfTelefono, tfUsername;
    private PasswordField pfPassword;
    private ComboBox cbTipoUsuario;
    private Button btnAgregar;
    private TableView tblUsuario;
    
    private JsonUtils json = new JsonUtils();

    private final ObservableList<Usuario> data = FXCollections.observableArrayList();
 
    public void getUsers(){
        data.clear();
        try{
            UserList list = json.readJSON(JsonUtils.FILE_TYPE.USER, UserList.class);
            for(Usuario us : list.getUserList()){
                data.add(us);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public Stage ventana(){   
        getUsers();
                
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
        
        lbTipoUsuario = new Label("Tipo de Usuario");
        cbTipoUsuario = new ComboBox();
        cbTipoUsuario.setMaxWidth(150);
        
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
        
                TableColumn colAction = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        Callback<TableColumn<Usuario,String>, TableCell<Usuario, String>> cellFactory;
        cellFactory = new Callback<TableColumn<Usuario,String>, TableCell<Usuario, String>>(){
            @Override
            public TableCell<Usuario, String> call(TableColumn<Usuario, String> param) {
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>(){
                    final Button btn = new Button("Eliminar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Usuario user = getTableView().getItems().get(getIndex());
                                UserHandler userHandler = new UserHandler();
                                if(userHandler.deleteUser(user)){
                                    ValoresStaticos.MSG_INFO("Usuario eliminado exitosamente");
                                    getUsers();
                                    tblUsuario.refresh();
                                }else{
                                    ValoresStaticos.MSG_ERROR("Ocurrio un error al intentar eliminar el usuario");
                                }
                                
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
        
        tblUsuario.setItems(data);
        tblUsuario.getColumns().addAll(colId, colNombre, colDireccion, colTelefono, colUsername, colTipoUsuario, colAction);

        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Administrador",
            "Cajero"
        );
        lbTipoUsuario = new Label("Cargo");
        cbTipoUsuario = new ComboBox(options);      
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
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
        grid.add(lbTipoUsuario, 0,6);
        grid.add(cbTipoUsuario, 1,6);
        grid.add(btnAgregar,1,7);
        grid.add(tblUsuario, 0, 8, 2,1);
             
                
        scene = new Scene(grid, 610, 500);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/usuario.png")));
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    
    public void eventos(){
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Usuario user = new Usuario();
                user.setNombre(tfNombre.getText());
                user.setDireccion(tfDireccion.getText());
                user.setTelefono(tfTelefono.getText());
                user.setUsername(tfUsername.getText());
                user.setPassword(pfPassword.getText());
                
                UserHandler userHandler = new UserHandler();
                
                if(userHandler.addUser(user)){
                    ValoresStaticos.MSG_INFO("Usuario Agregado Exitosamente");
                    getUsers();
                    tblUsuario.refresh();
                }else{
                    ValoresStaticos.MSG_ERROR("Ocurrio un error al agregar el usuario");
                }
            }
        });
    }
}
