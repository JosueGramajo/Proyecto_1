/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.View.ClienteView;
import programacion3.proyecto1.View.CorteCajaView;
import programacion3.proyecto1.View.FacturacionView;
import programacion3.proyecto1.View.LoginView;
import programacion3.proyecto1.View.ProductoView;
import programacion3.proyecto1.View.UsuarioView;

/**
 *
 * @author Jose
 */
public class ProgramacionIIIProyecto1 extends Application {
    
    private Stage primaryStage;
    private Application menu = this;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        LoginView ventana = new LoginView();
        ventana.login(menu).show();
    }
    
    public void menuPrincipal(){
        GridPane grid = new GridPane();
        String titulo = "";
        Scene scene;
        
        if(ValoresStaticos.TIPO_USUARIO==1){
            grid.add(menuAdministrador(), 0, 0);
            titulo = "Administrador";
            scene = new Scene(grid, 170, 570);
        }else{
            grid.add(menuCajero(), 0, 0);
            titulo = "Cajero";
            scene = new Scene(grid, 250, 250);
        }
        grid.setAlignment(Pos.CENTER); 
        
        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("View/img/facturacion.png")));
        primaryStage.setTitle(titulo);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(620);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(620);
        //primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.initStyle(StageStyle.DECORATED);
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
        
        
        Button btnCrudUsuaro = new Button("Usuarios");
        btnCrudUsuaro.setPrefSize(240, 100);
        ImageView crudUsuario = new ImageView(new Image(getClass().getResourceAsStream("View/img/usuario.png")));
        crudUsuario.setFitWidth(80);
        crudUsuario.setFitHeight(80);
        btnCrudUsuaro.setGraphic(crudUsuario);
        Tooltip ttCrudUsuario = new Tooltip();
        ttCrudUsuario.setText("Usuarios");
        btnCrudUsuaro.setTooltip(ttCrudUsuario);
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
        
        Button btnCrudProducto = new Button("Productos");
        btnCrudProducto.setPrefSize(240, 100);
        ImageView crudProducto = new ImageView(new Image(getClass().getResourceAsStream("View/img/producto.png")));
        crudProducto.setFitWidth(80);
        crudProducto.setFitHeight(80);
        btnCrudProducto.setGraphic(crudProducto);
        Tooltip ttCrudProducto = new Tooltip();
        ttCrudProducto.setText("Productos");
        btnCrudProducto.setTooltip(ttCrudProducto);
        btnCrudProducto.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new ProductoView().ventana();
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
        flowAdmin.getChildren().add(btnCrudProducto);
        
        Button btnCrudCliente = new Button("Clientes");
        btnCrudCliente.setPrefSize(240, 100);
        ImageView crudCliente = new ImageView(new Image(getClass().getResourceAsStream("View/img/cliente.png")));
        crudCliente.setFitWidth(80);
        crudCliente.setFitHeight(80);
        btnCrudCliente.setGraphic(crudCliente);
        Tooltip ttCrudCliente = new Tooltip();
        ttCrudCliente.setText("Clientes");
        btnCrudCliente.setTooltip(ttCrudCliente);
        btnCrudCliente.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new ClienteView().ventana();
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
        flowAdmin.getChildren().add(btnCrudCliente);
        
        Button btnCrudCorteCaja = new Button("Corte de Caja");
        btnCrudCorteCaja.setPrefSize(240, 100);
        ImageView corteCaja = new ImageView(new Image(getClass().getResourceAsStream("View/img/corte_caja.png")));
        corteCaja.setFitWidth(80);
        corteCaja.setFitHeight(80);
        btnCrudCorteCaja.setGraphic(corteCaja);
        Tooltip ttCorteCaja = new Tooltip();
        ttCorteCaja.setText("Corte de Caja");
        btnCrudCorteCaja.setTooltip(ttCorteCaja);
        btnCrudCorteCaja.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new CorteCajaView().ventana();
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
        flowAdmin.getChildren().add(btnCrudCorteCaja);
        
        Button btnSalir = new Button("Cerrar Sesion");
        btnSalir.setPrefSize(240, 100);
        ImageView salir = new ImageView(new Image(getClass().getResourceAsStream("View/img/logout.png")));
        salir.setFitWidth(80);
        salir.setFitHeight(80);
        btnSalir.setGraphic(salir);
        Tooltip ttSalir = new Tooltip();
        ttSalir.setText("Salir");
        btnSalir.setTooltip(ttSalir);
        btnSalir.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LoginView ventana = new LoginView();
                ventana.login(menu).show();
                primaryStage.close();
            }
        });
        flowAdmin.getChildren().add(btnSalir);
        
        return flowAdmin;
    }
    
    private FlowPane menuCajero(){
        FlowPane flowCajero = new FlowPane();
        flowCajero.setPadding(new Insets(20, 20, 20, 20));
        flowCajero.setVgap(10);
        flowCajero.setHgap(10);
        flowCajero.setPrefWrapLength(500); // preferred width allows for two columns
        flowCajero.setAlignment(Pos.CENTER); 

        Button btnFacturacion = new Button("Facturación");
        btnFacturacion.setPrefSize(240, 100);
        ImageView facturacion = new ImageView(new Image(getClass().getResourceAsStream("View/img/facturacion.png")));
        facturacion.setFitWidth(80);
        facturacion.setFitHeight(80);
        btnFacturacion.setGraphic(facturacion);
        Tooltip ttFacturacion = new Tooltip();
        ttFacturacion.setText("Facutración");
        btnFacturacion.setTooltip(ttFacturacion);
        btnFacturacion.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new FacturacionView().ventana();
                stage.initOwner(primaryStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
        flowCajero.getChildren().add(btnFacturacion);
        
        Button btnSalir = new Button("Salir");
        btnSalir.setPrefSize(240, 100);
        ImageView salir = new ImageView(new Image(getClass().getResourceAsStream("View/img/logout.png")));
        salir.setFitWidth(80);
        salir.setFitHeight(80);
        btnSalir.setGraphic(salir);
        Tooltip ttSalir = new Tooltip();
        ttSalir.setText("Salir");
        btnSalir.setTooltip(ttSalir);
        btnSalir.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LoginView ventana = new LoginView();
                ventana.login(menu).show();
                primaryStage.close();
            }
        });
        flowCajero.getChildren().add(btnSalir);
        
        return flowCajero;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
