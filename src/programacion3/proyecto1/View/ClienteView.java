/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import programacion3.proyecto1.Beans.Cliente;
import programacion3.proyecto1.Beans.Lists.ClientList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Handlers.ClientHandler;
import programacion3.proyecto1.Handlers.ProductHandler;
import programacion3.proyecto1.Handlers.UserHandler;
import programacion3.proyecto1.Static.ValoresStaticos;
import static programacion3.proyecto1.View.UsuarioView.editingId;
import programacion3.proyecto1.utils.JsonUtils;

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
    private Button btnAgregar, btnEditar, btnCancelarEdicion;
    private TableView tblCliente;
    
    private static String editingNit = "";

    private final ObservableList<Cliente> data = FXCollections.observableArrayList();
    
    private void getClients(){
        data.clear();
        try{
            ClientList list = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.CLIENT, ClientList.class);
            for(Cliente cl : list.getClients()){
                data.add(cl);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public Stage ventana(){    
        getClients();
        
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
        
        btnAgregar = new Button("Agregar");
        
        btnEditar = new Button("Editar");
        btnEditar.setVisible(false);
        
        btnCancelarEdicion = new Button("Cancelar");
        btnCancelarEdicion.setVisible(false);
        
        tblCliente = new TableView();
        tblCliente.setPrefWidth(600);
        tblCliente.setEditable(true);
    
        TableColumn colNit = new TableColumn("Nit");
        colNit.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nit"));
        
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        
        TableColumn colDireccion = new TableColumn("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        
        TableColumn colTelefono = new TableColumn("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        
        TableColumn colSaldoCredito = new TableColumn("Saldo Credito");
        colSaldoCredito.setCellValueFactory(new PropertyValueFactory<Cliente,Double>("saldoCredito"));
        
        TableColumn colAction = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        TableColumn colActionEdit = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        Callback<TableColumn<Cliente,String>, TableCell<Cliente, String>> cellFactory;
        cellFactory = new Callback<TableColumn<Cliente,String>, TableCell<Cliente, String>>(){
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, String> param) {
                final TableCell<Cliente, String> cell = new TableCell<Cliente, String>(){
                    final Button btn = new Button("Eliminar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Cliente cliente = getTableView().getItems().get(getIndex());
                                if(ClientHandler.INSTANCIA.deleteClient(cliente)){
                                    ValoresStaticos.MSG_INFO("Cliente eliminado exitosamente");
                                    getClients();
                                    tblCliente.refresh();
                                }else{
                                    ValoresStaticos.MSG_ERROR("Ocurrio un error al intentar eliminar el cliente");
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
        
        
        Callback<TableColumn<Cliente,String>, TableCell<Cliente, String>> cellFactoryEdicion;
        cellFactoryEdicion = new Callback<TableColumn<Cliente,String>, TableCell<Cliente, String>>(){
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, String> param) {
                final TableCell<Cliente, String> cell = new TableCell<Cliente, String>(){
                    final Button btn = new Button("Editar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Cliente client = getTableView().getItems().get(getIndex());
                                btnAgregar.setVisible(false);
                                btnEditar.setVisible(true);
                                btnCancelarEdicion.setVisible(true);
                                
                                tfNit.setText(client.getNit());
                                tfNombre.setText(client.getNombre());
                                tfDireccion.setText(client.getDireccion());
                                tfTelefono.setText(client.getTelefono());
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
        
        tblCliente.setItems(data);
        tblCliente.getColumns().addAll(colNit, colNombre, colDireccion, colTelefono, colSaldoCredito, colAction, colActionEdit);
        
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
        grid.add(btnAgregar,1,4);
        grid.add(btnEditar, 0, 4);
        grid.add(btnCancelarEdicion, 1, 4);
        grid.add(tblCliente, 0,5 , 2,1);
     
        scene = new Scene(grid, 640, 700);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/cliente.png")));
        stage.setTitle("Clientes");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;
    }
    private boolean checkFields(){
        if(tfNombre.getText().equals("") ||
                tfDireccion.getText().equals("") ||
                tfTelefono.getText().equals("") ||
                tfNit.getText().equals("")){

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
                    Cliente client = new Cliente();
                    client.setNombre(tfNombre.getText());
                    client.setNit(tfNit.getText());
                    client.setDireccion(tfDireccion.getText());
                    client.setTelefono(tfTelefono.getText());
                    client.setClienteCredito(false);
                    client.setSaldoCredito(0);
                    client.setConsumo(0);
                    
                    if(ClientHandler.INSTANCIA.addClient(client)){
                        ValoresStaticos.MSG_INFO("Cliente Agregado Exitosamente");
                        getClients();
                        tblCliente.refresh();
                        
                        tfNombre.setText("");
                        tfNit.setText("");
                        tfDireccion.setText("");
                        tfTelefono.setText("");
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al agregar el cliente");
                    }                
                }
            }
        });
        
        btnEditar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                if(checkFields()){
                    Cliente client = new Cliente();
                    client.setNombre(tfNombre.getText());
                    client.setNit(tfNit.getText());
                    client.setDireccion(tfDireccion.getText());
                    client.setTelefono(tfTelefono.getText());
                    client.setClienteCredito(false);
                    client.setSaldoCredito(0);
                    client.setConsumo(0);
                    
                    if(ClientHandler.INSTANCIA.editClient(client)){
                        ValoresStaticos.MSG_INFO("Cliente Editado Exitosamente");
                        getClients();
                        tblCliente.refresh();
                        
                        tfNombre.setText("");
                        tfNit.setText("");
                        tfDireccion.setText("");
                        tfTelefono.setText("");
                        
                        btnAgregar.setVisible(true);
                        btnEditar.setVisible(false);
                        btnCancelarEdicion.setVisible(false);
                        
                        editingId = 0;
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al editar el producto");
                    }                
                }
            }
        });
        
        btnCancelarEdicion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                tfNombre.setText("");
                tfNit.setText("");
                tfDireccion.setText("");
                tfTelefono.setText("");
                
                btnAgregar.setVisible(true);
                btnEditar.setVisible(false);
                btnCancelarEdicion.setVisible(false);

                editingId = 0;
            }
        });
    }
}
