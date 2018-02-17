/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Static;

import javafx.scene.control.Alert;

/**
 *
 * @author Jose
 */
public class ValoresStaticos {
    public static int ID_USUAIRO;
    public static int ID_LOCAL;
    public static int TIPO_USUARIO; //0: super_usuario, 1: admin, 2: cajero
    
    public static void msgError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void msgInfo(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
