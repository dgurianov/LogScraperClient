/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.run;

import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.entity.RagPattern;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Resembles main Observer bean to receive updates over the elements
 * monitored.
 * Calls Event Generator  to create events
 * @author Daniel
 */
@Component
public class MainObserver  implements Observer {
    
    //Getting logger
    Logger log = Logger.getLogger(MainObserver.class.getName()); 
    
    //Objects to hold temporary values and  type  conversion .
    private FileTarget currentFile ;
    private String line ;

    //Event processor  to process events 
    @Autowired
    EventProcessor eventP ;

    /**
     * No argument constructor 
     */
    public MainObserver() {
    }
    
    /**
     * Runs when  respective Observable is changed.
     * Gets Observable which called  this method and Object - contents of the change.
     * If change (String) , matches to one of the  regexps, publish Event with Event publisher.
     */
    @Override
    public void update(Observable o, Object arg) {
        
        if (arg instanceof  String){
            
            currentFile = (FileTarget ) o;
            line = (String) arg ;
            
            log.info(String.format("Received new line  %s  in  file  %s",line,currentFile.getFile()));
            
            for ( RagPattern ragPattern : currentFile.getRegexBasket()){
                
                if (ragPattern.getPattern().matcher(line).matches()) {
                    
                    log.info(String.format("Got match   %s with  pattern %s and ragAlert %d",
                                            line,
                                            ragPattern.getPattern().pattern(),
                                            ragPattern.getRagFlag()));
                    eventP.processEvent(currentFile, line,ragPattern.getRagFlag());
                     
                }else {
                    
                    log.debug(String.format("Not matched %s with %s ",line,ragPattern.getPattern().pattern()));
                    
                }
                
            }
            
        }else {
            
            log.info("Contents change is not a string.");
        
        }
        
    }

    /**
     * 
     * @return 
     */
    public EventProcessor getEventP() {
        return eventP;
    }

    /**
     *
     * @param eventP
     */
    public void setEventP(EventProcessor eventP) {
        this.eventP = eventP;
    }
    
}
