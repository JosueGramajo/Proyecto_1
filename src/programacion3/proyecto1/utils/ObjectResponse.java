/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion3.proyecto1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import programacion3.proyecto1.Beans.BaseObject;

/**
 *
 * @author josue
 */
public class ObjectResponse {
    private ObjectMapper object;
    private boolean success;
    private String error;

    public ObjectResponse(){}
    
    public ObjectResponse(ObjectMapper object, boolean success, String error){
        this.object = object;
        this.success = success;
        this.error = error;
    }
    /**
     * @return the object
     */
    public ObjectMapper getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(ObjectMapper object) {
        this.object = object;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
