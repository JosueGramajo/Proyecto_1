/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import programacion3.proyecto1.Beans.Lists.AgencyList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Sucursal;
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
    
    private Label lbNombre, lbDireccion, lbTelefono, lbUsername, lbPassword, lbTipoUsuario, lbSucursal; 
    private TextField tfNombre, tfDireccion, tfTelefono, tfUsername;
    private PasswordField pfPassword;
    private ComboBox cbTipoUsuario, cbSucursal;
    private Button btnAgregar, btnEditar, btnCancelarEdicion;
    private TableView tblUsuario;
    
    private JsonUtils json = new JsonUtils();
    
    public static int editingId = 0;
    
    AgencyList agencyList = new AgencyList();

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
        
        lbSucursal = new Label("Sucursal");
        cbSucursal = new ComboBox();
        cbSucursal.setMaxWidth(150);
        
        btnAgregar = new Button("Agregar");
        btnEditar = new Button("Editar");
        btnEditar.setVisible(false);
        
        btnCancelarEdicion = new Button("Cancelar");
        btnCancelarEdicion.setVisible(false);
        
        tblUsuario = new TableView();
        tblUsuario.setPrefWidth(800);
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
        
        TableColumn colActionEdit = new TableColumn("");
        colActionEdit.setCellFactory(new PropertyValueFactory<>(""));
        
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
        
        
        Callback<TableColumn<Usuario,String>, TableCell<Usuario, String>> cellFactoryEdicion;
        cellFactoryEdicion = new Callback<TableColumn<Usuario,String>, TableCell<Usuario, String>>(){
            @Override
            public TableCell<Usuario, String> call(TableColumn<Usuario, String> param) {
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>(){
                    final Button btn = new Button("Editar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Usuario user = getTableView().getItems().get(getIndex());
                                btnAgregar.setVisible(false);
                                btnEditar.setVisible(true);
                                btnCancelarEdicion.setVisible(true);
                                
                                tfNombre.setText(user.getNombre());
                                tfDireccion.setText(user.getDireccion());
                                tfTelefono.setText(user.getTelefono());
                                tfUsername.setText(user.getUsername());
                                pfPassword.setText(user.getPassword());
                                editingId = user.getId();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }                    
                };
                return cell;
            }   
        };
        colActionEdit.setCellFactory(cellFactoryEdicion);
        
        tblUsuario.setItems(data);
        tblUsuario.getColumns().addAll(colId, colNombre, colDireccion, colTelefono, colUsername, colTipoUsuario, colAction, colActionEdit);

        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Administrador",
            "Cajero"
        );
        lbTipoUsuario = new Label("Cargo");
        cbTipoUsuario = new ComboBox(options);  
        
        
        ArrayList<String> agencies = new ArrayList<String>();
        try {
            agencyList = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.AGENCY, AgencyList.class);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Sucursal agency : agencyList.getSucursales()){
            agencies.add(agency.getNombre());
        }
        
        ObservableList<String> option = 
        FXCollections.observableArrayList(agencies);
        lbSucursal = new Label("Sucursal");
        cbSucursal = new ComboBox(option);
        
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
        grid.add(lbSucursal, 0,7);
        grid.add(cbSucursal, 1,7);
        grid.add(btnAgregar,1,8);
        grid.add(btnEditar,0,8);
        grid.add(btnCancelarEdicion, 1, 8);
        grid.add(tblUsuario, 0, 9, 2,1);
             
                
        scene = new Scene(grid, 660, 800);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/usuario.png")));
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;
    }
    public boolean checkFields(){
        if(tfNombre.getText().equals("") ||
                tfDireccion.getText().equals("") ||
                tfTelefono.getText().equals("") ||
                tfUsername.getText().equals("") ||
                pfPassword.getText().equals("") ||
                cbTipoUsuario.getValue() == null ||
                cbSucursal.getValue() == null){

            ValoresStaticos.MSG_ERROR("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }
    public void eventos(){
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                if(checkFields()){
                    Usuario user = new Usuario();
                    user.setNombre(tfNombre.getText());
                    user.setDireccion(tfDireccion.getText());
                    user.setTelefono(tfTelefono.getText());
                    user.setUsername(tfUsername.getText());
                    user.setPassword(pfPassword.getText());

                    int rol = 0;
                    if(cbTipoUsuario.getValue().equals("Administrador")){
                        rol = 1;
                    }else if(cbTipoUsuario.getValue().equals("Cajero")){
                        rol = 2;
                    }
                    user.setTipo_usuario(rol);
                    
                    int agency = 0;
                    for(Sucursal s : agencyList.getSucursales()){
                        if(s.getNombre().equals(cbSucursal.getValue())){
                            agency = s.getId();
                            break;
                        }
                    }
                    user.setSucursal(agency);

                    if(UserHandler.INSTANCIA.addUser(user)){
                        ValoresStaticos.MSG_INFO("Usuario Agregado Exitosamente");
                        getUsers();
                        tblUsuario.refresh();
                        
                        tfNombre.setText("");
                        tfDireccion.setText("");
                        tfTelefono.setText("");
                        tfUsername.setText("");
                        pfPassword.setText("");
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al agregar el usuario");
                    }                
                }
            }
        });
        btnEditar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(checkFields()){
                    Usuario user = new Usuario();
                    user.setId(editingId);
                    user.setNombre(tfNombre.getText());
                    user.setDireccion(tfDireccion.getText());
                    user.setTelefono(tfTelefono.getText());
                    user.setUsername(tfUsername.getText());
                    user.setPassword(pfPassword.getText());

                    int rol = 0;
                    if(cbTipoUsuario.getValue().equals("Administrador")){
                        rol = 1;
                    }else if(cbTipoUsuario.getValue().equals("Cajero")){
                        rol = 2;
                    }
                    user.setTipo_usuario(rol);
                    
                    int agency = 0;
                    for(Sucursal s : agencyList.getSucursales()){
                        if(s.getNombre().equals(cbSucursal.getValue())){
                            agency = s.getId();
                            break;
                        }
                    }
                    user.setSucursal(agency);

                    if(UserHandler.INSTANCIA.editUser(user)){
                        ValoresStaticos.MSG_INFO("Usuario Editado Exitosamente");
                        getUsers();
                        tblUsuario.refresh();
                        
                        btnEditar.setVisible(false);
                        btnCancelarEdicion.setVisible(false);
                        btnAgregar.setVisible(true);

                        tfNombre.setText("");
                        tfDireccion.setText("");
                        tfTelefono.setText("");
                        tfUsername.setText("");
                        pfPassword.setText("");
                        
                        editingId = 0;
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al editar el usuario");
                    }                
                }
            }
        });
        btnCancelarEdicion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEditar.setVisible(false);
                btnCancelarEdicion.setVisible(false);
                btnAgregar.setVisible(true);
                
                tfNombre.setText("");
                tfDireccion.setText("");
                tfTelefono.setText("");
                tfUsername.setText("");
                pfPassword.setText("");
            }
        });
    }
}
