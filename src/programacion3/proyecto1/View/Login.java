/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jose
 */
public class Login {
    
    public Stage login(){
        Label lbUsuario = new Label("Usuario");
        TextField txtUsuario = new TextField();
        
        Label lbPassword = new Label("Password");
        PasswordField txtPassword = new PasswordField();
        
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPrefSize(100, 10);
        btnAceptar.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        grid.add(lbUsuario, 0, 0); 
        grid.add(txtUsuario, 1, 0); 
        grid.add(lbPassword, 0, 1); 
        grid.add(txtPassword, 1, 1); 
        grid.add(btnAceptar, 0, 2, 2,1); 
        grid.setAlignment(Pos.CENTER); 
        
        Scene scene = new Scene(grid, 300, 250);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/facturacion.png")));
        stage.setTitle("Login");
        stage.setScene(scene);
        return stage;
        
    }
    
}
