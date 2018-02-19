/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Usuario;

/**
 *
 * @author josue
 */
public class UserList extends BaseObject{
    private int currentID;
    private ArrayList<Usuario> userList;

    public UserList(){}
    
    public UserList(int currentID, ArrayList<Usuario> userList){
        this.currentID = currentID;
        this.userList = userList;
    }

    /**
     * @return the currentID
     */
    public int getCurrentID() {
        return currentID;
    }

    /**
     * @param currentID the currentID to set
     */
    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    /**
     * @return the userList
     */
    public ArrayList<Usuario> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(ArrayList<Usuario> userList) {
        this.userList = userList;
    }
    
    
}
