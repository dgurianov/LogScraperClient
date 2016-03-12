/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.config;

import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.entity.FileBasket;
import com.logscraperclient.entity.IdGenerator;
import com.logscraperclient.entity.RagPattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Parse  xml file config and create beans of type FileTarget. 
 * Put beans inside FileBasket
 * @author Daniel
 */
@Component
public class XmlConfigParser implements ConfigParser{
    
    //Generator of uniq IDs for aech FileTarget
    @Autowired
    private IdGenerator idGenerator ;

    //Basked of FileTarget objects , backed up with ConcurrentLinkedQueue 
    @Autowired
    private FileBasket fileQueue ;
    
    //Logger
    private final Logger log ;
    
    //Config file
    private final java.io.File configFileTargets; 

    public XmlConfigParser() {
        
        log  = Logger.getLogger(XmlConfigParser.class);
        
        configFileTargets = new java.io.File("C:\\!disk\\NB_Workspace\\LogScraperClient\\src\\main\\java\\com\\logscraperclient\\dummyconfigfiles\\SampleApplicationConfig.xml");
                                     
    }
    
    
    //Method to run from external to parse config.
    @Override
    public void parseConfig() {
        
        try {
            
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlConfiguredFileTargets.class,FileTarget.class,XmlPatternAdapter.class,RagPattern.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //Unmarshal configured endpoints
            XmlConfiguredFileTargets listOfEndpoints = (XmlConfiguredFileTargets) unmarshaller.unmarshal(configFileTargets);
            
            //Checking that config file has any content configured 
            if(listOfEndpoints.getEndpoints().isEmpty()){
                
                log.error(String.format("Config file %s is empty , exiting",configFileTargets.getAbsolutePath()));
                System.exit(0);
                
            }
            
            //Start to process endpoints into FileTarget elements 
            for (FileTarget f : listOfEndpoints.getEndpoints()){
                
                //Set new if  for the file 
                f.setId(idGenerator.getId());
                //Put FileTarget object into the fileQueue basket.    
                fileQueue.putLast(f);
                    
                log.info(String.format("New File %s went to basket. Queue is %d long now.", f.getFileName(),fileQueue.getFileQ().size()));
            }
            
        }catch (Exception e){
            
            log.error("Unmarshal of configuration failed ",e);
            
        }
       
    }
    
}
