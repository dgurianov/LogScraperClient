/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import java.util.Date;

/**
 * Class that embodies match of the regexp towards  String that has changed in 
 * observed File .
 * @author Daniel
 */
public class Event {
    
    //Event has its uniq ID
    private final  int id ;
    
    //Object name event is related to
    private final String objectName;
    
    //String that was matched by regexp
    private final String matchedString ;
    
    //Calendar date of the  event
    private final Date time ;
    
    //Alert RAG classification number 
    private final Integer regexpRagFlag; 

    //Constructor 
    public Event(int id ,
                String objectName,
                String matchedString,
                Date time ,
                Integer alertValue) {
        
        this.id = id;
        this.objectName = objectName;
        this.matchedString = matchedString;
        this.time = time;
        this.regexpRagFlag = alertValue;
    }

    
    //Setters and getters 
    public int getId() {
        return id;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getMatchedString() {
        return matchedString;
    }

    public Date getTime() {
        return time;
    }

    public Integer getRegexpRagFlag() {
        return regexpRagFlag;
    }
    
    
            
            
            
    
}
