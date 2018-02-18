/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import programacion3.proyecto1.Handlers.UserHandler;
import programacion3.proyecto1.ProgramacionIIIProyecto1;
import programacion3.proyecto1.Static.ValoresStaticos;
import programacion3.proyecto1.utils.StatusResponse;

/**
 *
 * @author Jose
 */
public class LoginView {
    
    public Application main;
    public Stage primaryStage;
    
    private Label lbUsuario, lbPassword;
    private TextField txtUsuario;
    private PasswordField txtPassword;
    private Button btnAceptar;
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    public Stage login(Stage primaryStage, Application main){
        
        this.main = main;
        this.primaryStage = primaryStage;
        
        lbUsuario = new Label("Usuario");
        txtUsuario = new TextField();
        
        lbPassword = new Label("Password");
        txtPassword = new PasswordField();
        
        btnAceptar = new Button("Aceptar");
        btnAceptar.setPrefSize(100, 10);
        btnAceptar.setAlignment(Pos.CENTER);

        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        grid.add(lbUsuario, 0, 0); 
        grid.add(txtUsuario, 1, 0); 
        grid.add(lbPassword, 0, 1); 
        grid.add(txtPassword, 1, 1); 
        grid.add(btnAceptar, 0, 2, 2,1); 
        grid.setAlignment(Pos.CENTER); 
        
        scene = new Scene(grid, 300, 250);
        stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/facturacion.png")));
        stage.setTitle("Login");
        stage.setScene(scene);
        
        eventos();
        return stage;
    }
    
    public void eventos(){
        btnAceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserHandler usrHandler = new UserHandler();
                
                StatusResponse response = usrHandler.doLogin(txtUsuario.getText(), txtPassword.getText());
                if(response.isSuccess()){
                    //aqui se hace la validacion de el Login
                    ValoresStaticos.TIPO_USUARIO=1;
                    ((ProgramacionIIIProyecto1)main).menuPrincipal(primaryStage);
                    stage.close(); 
                }else{
                    System.out.println(response.getStatus());
                }
            }
        });
    }
    
}
