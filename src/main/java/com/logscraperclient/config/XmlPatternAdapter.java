/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.config;

import java.util.regex.Pattern;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter to Marshall \ unmarshall Pattern object to String 
 * @author Daniel
 */
public class XmlPatternAdapter extends XmlAdapter<String, Pattern>{

    @Override
    public Pattern unmarshal(String v) throws Exception {
        return Pattern.compile(v);
    }

    @Override
    public String marshal(Pattern v) throws Exception {
        return v.pattern();
    }
    
    
}
