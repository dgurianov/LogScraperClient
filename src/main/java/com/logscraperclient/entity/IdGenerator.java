/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import org.springframework.stereotype.Component;

/**
 * To keep and generate uniq ids for objects
 * @author Daniel
 */
@Component
public class IdGenerator {
    
    private  int id = 0 ;

    public IdGenerator() {
    }

    public  int getId() {
        return ++id;
    }

    public  void setId(int id) {
        this.id = id;
    }
    
    
    
}
