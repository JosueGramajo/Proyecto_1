/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.Beans.Lists;

import java.util.ArrayList;
import programacion3.proyecto1.Beans.BaseObject;
import programacion3.proyecto1.Beans.Cliente;

/**
 *
 * @author josue
 */
public class ClientList extends BaseObject{
    private ArrayList<Cliente> clients;
    
    public ClientList(){}
    
    public ClientList(ArrayList<Cliente> clients){
        this.clients = clients;
    }

    /**
     * @return the clients
     */
    public ArrayList<Cliente> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(ArrayList<Cliente> clients) {
        this.clients = clients;
    }
    
}
