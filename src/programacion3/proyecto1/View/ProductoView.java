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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import programacion3.proyecto1.Beans.Lists.ProductList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Producto;
import programacion3.proyecto1.Beans.Sucursal;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.Handlers.ProductHandler;
import programacion3.proyecto1.Handlers.UserHandler;
import programacion3.proyecto1.Static.ValoresStaticos;
import static programacion3.proyecto1.View.UsuarioView.editingId;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author Kevin Reus
 */
public class ProductoView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbNombre, lbMarca, lbDescripcion, lbPrecio, lbExistencia; 
    private TextField tfNombre, tfMarca, tfDescripcion, tfPrecio, tfExistencia;
    private ComboBox cbExistencia;
    private Button btnAgregar, btnEditar, btnCancelarEdicion;
    private TableView tblProducto;
    
    private static int editingId = 0;
    
    private final ObservableList<Producto> data = FXCollections.observableArrayList();
    
    public void getProducts(){
        data.clear();
        try{
            ProductList list = JsonUtils.INSTANCIA.readJSON(JsonUtils.FILE_TYPE.PRODUCT, ProductList.class);
            for(Producto pr : list.getProducts()){
                data.add(pr);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    public Stage ventana(){      
        getProducts();
                
        lbNombre = new Label("Nombre");
        tfNombre = new TextField();
        tfNombre.setMaxWidth(150);
        
        lbMarca = new Label("Marca");
        tfMarca = new TextField();
        tfMarca.setMaxWidth(150);
        
        lbDescripcion = new Label("Descripcion");
        tfDescripcion = new TextField();
        tfDescripcion.setMaxWidth(150);
        
        lbPrecio = new Label("Precio");
        tfPrecio = new TextField();
        tfPrecio.setMaxWidth(150);
        
        lbExistencia = new Label("Existencias");
        tfExistencia = new TextField();
        tfExistencia.setMaxWidth(150);
        
        btnAgregar = new Button("Agregar");
        
        btnEditar = new Button("Editar");
        btnEditar.setVisible(false);
        
        btnCancelarEdicion = new Button("Cancelar");
        btnCancelarEdicion.setVisible(false);
        
        tblProducto = new TableView();
        tblProducto.setPrefWidth(800);
        tblProducto.setEditable(true);
        
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("id"));
        
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        
        TableColumn colDescripcion = new TableColumn("Descripcion");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Producto,String>("descripcion"));
        
        TableColumn colPrecio = new TableColumn("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio"));
        
        TableColumn colExistencia = new TableColumn("Existencia");
        colExistencia.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("existencia"));
        
        TableColumn colAction = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        TableColumn colActionEdit = new TableColumn("");
        colAction.setCellFactory(new PropertyValueFactory<>(""));
        
        Callback<TableColumn<Producto,String>, TableCell<Producto, String>> cellFactory;
        cellFactory = new Callback<TableColumn<Producto,String>, TableCell<Producto, String>>(){
            @Override
            public TableCell<Producto, String> call(TableColumn<Producto, String> param) {
                final TableCell<Producto, String> cell = new TableCell<Producto, String>(){
                    final Button btn = new Button("Eliminar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Producto product = getTableView().getItems().get(getIndex());
                                if(ProductHandler.INSTANCIA.deleteProduct(product)){
                                    ValoresStaticos.MSG_INFO("Producto eliminado exitosamente");
                                    getProducts();
                                    tblProducto.refresh();
                                }else{
                                    ValoresStaticos.MSG_ERROR("Ocurrio un error al intentar eliminar el producto");
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
        
        Callback<TableColumn<Producto,String>, TableCell<Producto, String>> cellFactoryEdicion;
        cellFactoryEdicion = new Callback<TableColumn<Producto,String>, TableCell<Producto, String>>(){
            @Override
            public TableCell<Producto, String> call(TableColumn<Producto, String> param) {
                final TableCell<Producto, String> cell = new TableCell<Producto, String>(){
                    final Button btn = new Button("Editar");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Producto product = getTableView().getItems().get(getIndex());
                                
                                btnAgregar.setVisible(false);
                                btnEditar.setVisible(true);
                                btnCancelarEdicion.setVisible(true);
                                
                                
                                tfNombre.setText(product.getNombre());
                                tfMarca.setText(product.getMarca());
                                tfDescripcion.setText(product.getDescripcion());
                                tfPrecio.setText(product.getPrecio().toString());
                                tfExistencia.setText(Integer.toString(product.getExistencia()));
                                editingId = product.getId();
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
    
        tblProducto.setItems(data);
        tblProducto.getColumns().addAll(colId, colNombre, colDescripcion, colPrecio, colExistencia, colAction, colActionEdit);
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1);
        grid.add(lbMarca, 0,2);
        grid.add(tfMarca, 1,2);
        grid.add(lbDescripcion, 0,3);
        grid.add(tfDescripcion, 1,3);
        grid.add(lbPrecio, 0,4);
        grid.add(tfPrecio, 1,4);
        grid.add(lbExistencia, 0,5);
        grid.add(tfExistencia, 1,5);
        grid.add(btnAgregar,1,6);
        grid.add(btnEditar,0,6);
        grid.add(btnCancelarEdicion,1,6);
        grid.add(tblProducto, 0,7 , 2,1);
        
        scene = new Scene(grid, 660, 800);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/producto.png")));
        stage.setTitle("Productos");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;   
    }
    
    private boolean isInteger(String value){
        try{
            int tryParse = Integer.parseInt(value);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    private boolean isDouble(String value){
        try{
            double tryParse = Double.parseDouble(value);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    private boolean checkFields(){
        if(tfNombre.getText().equals("") ||
                tfMarca.getText().equals("") ||
                tfDescripcion.getText().equals("") ||
                tfPrecio.getText().equals("") ||
                tfExistencia.getText().equals("")){
            
            ValoresStaticos.MSG_ERROR("Todos los campos son obligatorios");
        
            return false;
        }
        if(!isInteger(tfExistencia.getText())){
            ValoresStaticos.MSG_ERROR("El campo existencia debe ser numerico");
            return false;
        }
        if(!isDouble(tfPrecio.getText())){
            ValoresStaticos.MSG_ERROR("El campo precio debe ser numerico");
            return false;
        }
        return true;
    }
    
    public void eventos(){
        btnAgregar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                if(checkFields()){
                    Producto product = new Producto();
                    product.setNombre(tfNombre.getText());
                    product.setMarca(tfMarca.getText());
                    product.setDescripcion(tfDescripcion.getText());
                    product.setPrecio(Double.parseDouble(tfPrecio.getText()));
                    product.setExistencia(Integer.parseInt(tfExistencia.getText()));
                    
                    if(ProductHandler.INSTANCIA.addProduct(product)){
                        ValoresStaticos.MSG_INFO("Producto Agregado Exitosamente");
                        getProducts();
                        tblProducto.refresh();
                        
                        tfNombre.setText("");
                        tfMarca.setText("");
                        tfDescripcion.setText("");
                        tfPrecio.setText("");
                        tfExistencia.setText("");
                    }else{
                        ValoresStaticos.MSG_ERROR("Ocurrio un error al agregar el producto");
                    }                
                }
            }
        });
        
        btnEditar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                if(checkFields()){
                    Producto product = new Producto();
                    product.setId(editingId);
                    product.setNombre(tfNombre.getText());
                    product.setMarca(tfMarca.getText());
                    product.setDescripcion(tfDescripcion.getText());
                    product.setPrecio(Double.parseDouble(tfPrecio.getText()));
                    product.setExistencia(Integer.parseInt(tfExistencia.getText()));
                    
                    if(ProductHandler.INSTANCIA.editProduct(product)){
                        ValoresStaticos.MSG_INFO("Producto Editado Exitosamente");
                        getProducts();
                        tblProducto.refresh();
                        
                        tfNombre.setText("");
                        tfMarca.setText("");
                        tfDescripcion.setText("");
                        tfPrecio.setText("");
                        tfExistencia.setText("");
                        
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
                tfMarca.setText("");
                tfDescripcion.setText("");
                tfPrecio.setText("");
                tfExistencia.setText("");
                
                btnAgregar.setVisible(true);
                btnEditar.setVisible(false);
                btnCancelarEdicion.setVisible(false);

                editingId = 0;
            }
        });
    }
}
