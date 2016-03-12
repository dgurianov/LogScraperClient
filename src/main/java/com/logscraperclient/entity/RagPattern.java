/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import com.logscraperclient.config.XmlPatternAdapter;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Class that holds Pattern instance and adds RAG(RED AMBER GREEN) flag to it.
 * @author Danylo
 */
//@XmlType(propOrder = {"pattern","ragFlag"})
public class RagPattern {
    
    //Regex pattern 
    private  Pattern pattern ;
    
    //RAG flag (0,1,2... etc.)
    private  Integer ragFlag ;

    public RagPattern() {
    }
    
    

    public RagPattern(Pattern pattern , Integer ragFlag) {
        
        this.pattern = null;
        this.ragFlag = null;
        
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Integer getRagFlag() {
        return ragFlag;
    }

    @XmlElement(name = "pattern")
    @XmlJavaTypeAdapter(XmlPatternAdapter.class)
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

        
    @XmlElement(name = "regexpragflag")
    public void setRagFlag(Integer ragFlag) {
        this.ragFlag = ragFlag;
    }
    
    
    
    
    
}
