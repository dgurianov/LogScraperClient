/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import java.util.ListIterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Container  to  store FileTarget objects that are in monitoring right now.
 * @author Daniel
 */
@Component
public class FilesInMonitoringMap {
    
    //Store file objects with their name as key and object reference as value 
   private   ConcurrentHashMap<String, FileTarget> container ;
   
   private final Logger logger = Logger.getLogger(FilesInMonitoringMap.class);

    public FilesInMonitoringMap() {
    }

    @PostConstruct
    public void init(){
         container = new ConcurrentHashMap<>();
     }
    
    public ConcurrentHashMap<String, FileTarget> getContainer() {
        return container;
    }
   
    //Here , duplicate files are absolutelly legal  due to updates resending, etc.
    //method has to filter out duplicates
    //
    public void put(FileTarget file ){
        
        FileTarget alreadyExist = container.get(file.getFileName());
        
        //If file is not present in container
        if (alreadyExist !=  null){
            
            if (file.getRegexBasket().size() > 1){
                
                ListIterator<RagPattern> ragPatterns = file.getRegexBasket().listIterator();
                while(ragPatterns.hasNext()){
                    //Check the case, that 2 same patterns evolve in different elements in this basket !!!!
                    //Probably need pattern comparison inside. 
                    alreadyExist.getRegexBasket().add(ragPatterns.next());
                
                    
                }
            } else if (file.getRegexBasket().size() == 1 ){
                //The only pattern in the basket
                alreadyExist.getRegexBasket().add(file.getRegexBasket().poll());
                
            }else {
                logger.warn(String.format("Broken file object %s , no regexp patterns set , discarding !!!",file.getFileName()));
            }
           
        }else {      
        
            container.put(file.getFile().getName(), file);
        }
    }
   
    //Get FileTarget if it is already monitored , or return null 
    public FileTarget get(FileTarget file ){
        
        return container.get(file.getFileName());
    }
   
   
}
