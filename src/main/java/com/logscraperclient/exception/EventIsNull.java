/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.exception;

/**
 *
 * @author Daniel
 */
public class EventIsNull extends Exception{

    
    

    @Override
    public String getMessage() {
        return "Event  object operated is null.";
    }
    
    
    
}
