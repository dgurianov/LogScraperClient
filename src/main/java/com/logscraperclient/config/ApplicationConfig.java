/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.config;

/**
 *
 * @author Danylo
 */
public class ApplicationConfig {
    
    private String jmsConfigFile ;
    private String fileTargetsConfigFile;

    public ApplicationConfig() {
    }
    
    

    public String getJmsConfigFile() {
        return jmsConfigFile;
    }

    public void setJmsConfigFile(String jmsConfigFile) {
        this.jmsConfigFile = jmsConfigFile;
    }

    public String getFileTargetsConfigFile() {
        return fileTargetsConfigFile;
    }

    public void setFileTargetsConfigFile(String fileTargetsConfigFile) {
        this.fileTargetsConfigFile = fileTargetsConfigFile;
    }
    
    
    
}
