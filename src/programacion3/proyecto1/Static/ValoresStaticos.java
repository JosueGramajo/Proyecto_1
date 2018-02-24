/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Static;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import programacion3.proyecto1.Beans.Lists.AgencyList;
import programacion3.proyecto1.Beans.Lists.UserList;
import programacion3.proyecto1.Beans.Sucursal;
import programacion3.proyecto1.Beans.Usuario;
import programacion3.proyecto1.utils.JsonUtils;

/**
 *
 * @author Jose
 */
public class ValoresStaticos {
    public static int ID_USUAIRO;
    public static int ID_SUCURSAL;
    public static int TIPO_USUARIO; //1: admin, 2: cajero
    public final static String PATH = System.getProperty("user.home") + "/Documents/Tiendita";
    
    public static void MSG_ERROR(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void MSG_INFO(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void initAgencies(){
        Sucursal sucursal1 = new Sucursal(1, "Sucursal 1");
        Sucursal sucursal2 = new Sucursal(2, "Sucursal 2");
        Sucursal sucursal3 = new Sucursal(3, "Sucursal 3");
        
        ArrayList<Sucursal> list = new ArrayList<Sucursal>();
        list.add(sucursal1);
        list.add(sucursal2);
        list.add(sucursal3);
        
        JsonUtils json = new JsonUtils();
        json.writeJSON(new AgencyList(list), JsonUtils.FILE_TYPE.AGENCY);
    }
    
    public static void initUsers(){
        Usuario user = new Usuario(1, "Josue","Ciudad","22558877","jgramajo","123",1,1);
        Usuario user2 = new Usuario(2, "Kevin Vasquez","Ciudad","22558877","kvasquez","123",1,1);
        Usuario user3 = new Usuario(3, "Manuel Vega","Ciudad","22558877","mvega","123",1,1);
        Usuario user4 = new Usuario(4, "Jose Perez","Ciudad","22558877","jperez","123",1,1);
        Usuario user5 = new Usuario(4, "Jose Perez","Ciudad","22558877","cajero","123",2,1);

        ArrayList<Usuario> list = new ArrayList<Usuario>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        UserList userList = new UserList(4, list);
        
        JsonUtils json = new JsonUtils();
        json.writeJSON(userList, JsonUtils.FILE_TYPE.USER);
    }
}
