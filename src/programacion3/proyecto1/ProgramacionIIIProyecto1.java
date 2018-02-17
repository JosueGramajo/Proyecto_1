/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.View.LoginView;
import programacion3.proyecto1.View.UsuarioView;

/**
 *
 * @author Jose
 */
public class ProgramacionIIIProyecto1 extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        LoginView ventana = new LoginView();
        ventana.login(primaryStage, this).show();
    }
    
    public void menuPrincipal(Stage primaryStage){
        GridPane grid = new GridPane();
        String titulo = "";
        if(ValoresStaticos.TIPO_USUARIO==1){
            grid.add(menuAdministrador(), 0, 0);
            titulo = "Administrador";
        }else{
            grid.add(menuCajero(), 0, 0);
            titulo = "Cajero";
        }
        grid.setAlignment(Pos.CENTER); 
        
        Scene scene = new Scene(grid, 170, 470);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("View/img/facturacion.png")));
        primaryStage.setTitle(titulo);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(190);
        primaryStage.setMinHeight(170);
        primaryStage.setMaxWidth(470);
        primaryStage.setMaxHeight(470);
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.initStyle(StageStyle.UNDECORATED); // Aprender bien a usarlo
        //primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.initStyle(StageStyle.TRANSPARENT); // Aprender bien a usarlo
        primaryStage.show();
    }
    
    private FlowPane menuAdministrador(){
        FlowPane flowAdmin = new FlowPane();
        flowAdmin.setPadding(new Insets(20, 20, 20, 20));
        flowAdmin.setVgap(10);
        flowAdmin.setHgap(10);
        flowAdmin.setPrefWrapLength(500); // preferred width allows for two columns
        flowAdmin.setAlignment(Pos.CENTER); 
        
        Button btnCrudUsuaro = new Button();
        ImageView crudUsuario = new ImageView();
        crudUsuario = new ImageView(new Image(getClass().getResourceAsStream("View/img/usuario.png")));
        crudUsuario.setFitWidth(80);
        crudUsuario.setFitHeight(80);
        btnCrudUsuaro.setGraphic(crudUsuario);
        btnCrudUsuaro.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new UsuarioView().ventana();
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
        flowAdmin.getChildren().add(btnCrudUsuaro);
        
        Button btnCrudProducto = new Button();
        ImageView crudProducto = new ImageView();
        crudProducto = new ImageView(new Image(getClass().getResourceAsStream("View/img/producto.png")));
        crudProducto.setFitWidth(80);
        crudProducto.setFitHeight(80);
        btnCrudProducto.setGraphic(crudProducto);
        flowAdmin.getChildren().add(btnCrudProducto);
        
        Button btnCrudCliente = new Button();
        ImageView crudCliente= new ImageView();
        crudCliente = new ImageView(new Image(getClass().getResourceAsStream("View/img/cliente.png")));
        crudCliente.setFitWidth(80);
        crudCliente.setFitHeight(80);
        btnCrudCliente.setGraphic(crudCliente);
        flowAdmin.getChildren().add(btnCrudCliente);
        
        Button btnCrudCorteCaja = new Button();
        ImageView corteCaja = new ImageView();
        corteCaja = new ImageView(new Image(getClass().getResourceAsStream("View/img/corte_caja.png")));
        corteCaja.setFitWidth(80);
        corteCaja.setFitHeight(80);
        btnCrudCorteCaja.setGraphic(corteCaja);
        flowAdmin.getChildren().add(btnCrudCorteCaja);
        
        return flowAdmin;
    }
    
    private FlowPane menuCajero(){
        FlowPane flowCajero = new FlowPane();
        flowCajero.setPadding(new Insets(5, 0, 5, 0));
        flowCajero.setVgap(4);
        flowCajero.setHgap(4);
        flowCajero.setPrefWrapLength(200); // preferred width allows for two columns

        ImageView facturacion = new ImageView();
        facturacion = new ImageView(new Image(getClass().getResourceAsStream("View/img/facturacion.png")));
        facturacion.setFitWidth(80);
        facturacion.setFitHeight(80);
        flowCajero.getChildren().add(facturacion);
        
        return flowCajero;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
