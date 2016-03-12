/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.run;

import com.logscraperclient.config.XmlConfigParser;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Daniel
 */

public class MainStart {
    public static void main(String[] args) {
        
        Logger log = Logger.getLogger(MainStart.class.getName());
        
        //Create context  
        
        ApplicationContext apctx = new ClassPathXmlApplicationContext("services.xml");
        
        //Parse config files
        //This operation  creates File objects that will be observed.
        //This operation puts all  created File objects to  queue  to wait  for observers .
        
        apctx.getBean(XmlConfigParser.class).parseConfig();
        
        //At this stage, each Observer traces file and   publih events as soon as something found.
        
    }
    
}
