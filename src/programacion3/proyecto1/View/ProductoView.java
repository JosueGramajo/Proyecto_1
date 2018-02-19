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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programacion3.proyecto1.Beans.Producto;

/**
 *
 * @author Kevin Reus
 */
public class ProductoView {
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    private Label lbId, lbNombre, lbMarca, lbDescripcion, lbPrecio, lbExistencia; 
    private TextField tfId, tfNombre, tfMarca, tfDescripcion, tfPrecio;
    private ComboBox cbExistencia;
    private Button btnAgregar;
    private TableView tblProducto;
    
    private final ObservableList<Producto> data = FXCollections.observableArrayList(
            new Producto(1,"Lapiz", "Maped", "Tipo Triangular", 1.50, 1),
            new Producto(2,"Lapicero", "Bic", "Color negro", 2.00,1),
            new Producto(3,"Borrador", "Maped", "Tipo Rectangular", 1.00,1),
            new Producto(4,"Sacapuntas", "Maped", "Color rojo con recipiente", 4.00,1)
    );
    
    
    public Stage ventana(){    
        
        lbId = new Label("Id");
        tfId = new TextField();
        tfId.setMaxWidth(150);
        
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
        
        lbExistencia = new Label("Tipo de Usuario");
        cbExistencia = new ComboBox();
        cbExistencia.setMaxWidth(150);
        
        btnAgregar = new Button("Agregar");
        
        tblProducto = new TableView();
        tblProducto.setPrefWidth(600);
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
    
        tblProducto.setItems(data);
        tblProducto.getColumns().addAll(colId, colNombre, colDescripcion, colPrecio, colExistencia);
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Si",
            "No"
        );
        lbExistencia = new Label("Existencia");
        cbExistencia = new ComboBox(options);   
        
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lbId, 0,0);
        grid.add(tfId, 1,0);
        grid.add(lbNombre, 0,1);
        grid.add(tfNombre, 1,1);
        grid.add(lbMarca, 0,2);
        grid.add(tfMarca, 1,2);
        grid.add(lbDescripcion, 0,3);
        grid.add(tfDescripcion, 1,3);
        grid.add(lbPrecio, 0,4);
        grid.add(tfPrecio, 1,4);
        grid.add(lbExistencia, 0,5);
        grid.add(cbExistencia, 1,5);
        grid.add(btnAgregar,1,6);
        grid.add(tblProducto, 0,7 , 2,1);
        
        scene = new Scene(grid, 640, 700);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/producto.png")));
        stage.setTitle("Productos");
        stage.setScene(scene);
        
        scene.getStylesheets().add(LoginView.class.getResource("css/bootstrap3.css").toExternalForm());
        
        eventos();
        return stage;   
    }
    public void eventos(){
    
    }
}
