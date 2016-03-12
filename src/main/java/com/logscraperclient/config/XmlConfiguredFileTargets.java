/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.config;

import com.logscraperclient.entity.FileTarget;
import java.util.LinkedList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@XmlRootElement(name="configured_endpoints")
public class XmlConfiguredFileTargets {
    
    private LinkedList<FileTarget> endpoints = new LinkedList<>();

    public LinkedList<FileTarget> getEndpoints() {
        return endpoints;
    }

    @XmlElement(name="endpoint")
    public void setEndpoints(LinkedList<FileTarget> endpoints) {
        this.endpoints = endpoints;
    }
    
    
    
}
