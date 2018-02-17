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
public class UsuarioView {
    
    private GridPane grid;
    private Scene scene;
    private Stage stage;
    
    public Stage ventana(){       
        
        grid = new GridPane();
        
        scene = new Scene(grid, 500, 500);
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
