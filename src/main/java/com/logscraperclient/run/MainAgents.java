/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.run;

import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.entity.FileBasket;
import com.logscraperclient.entity.FilesInMonitoringMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Agent to unload basket of FileTarget objects .
 * Heart of the class is method run  that is scheduled by Spring 
 * @author Daniel
 */
@Component
public class MainAgents {
    
    //Basked of FileTarget objects , backed up with ConcurrentLinkedQueue 
    @Autowired
    private FileBasket fileQueue;
    
    //Observer that provides call-back method to be used by FileTarget to inform about changes 
    @Autowired
    private MainObserver headObserver;
    
    //Map of the files  that are currently monitored. Backed by ConcurrentHashMap
    @Autowired
    private FilesInMonitoringMap runningFiles ;
    
    //Get current logger 
    Logger log = Logger.getLogger(MainAgents.class.getName());

    //Temp variable 
    private FileTarget currentFile ;
    
    /**
     * Non argument constructor
     */
    public MainAgents() {
    }
    
    /**
     *  Check Basket for new FileTarget(s).
     *  If present:
     *  - perform  register observer ,
     *  - create new thread
     *  - put file into map of already monitored files
     *  - start thread.
     */
    @Scheduled(fixedDelay = 1000)
    public void run(){
            
            //Pick up next file from queue
            currentFile = fileQueue.getNext();
            
            if (currentFile != null){
                
                log.info(String.format("New  file arrived for monitoring : \nID - %d \nFilename - %s\n Pattern(s): %s ",currentFile.getId(),currentFile.getFileName(),currentFile.getRegexBasket().toString()));
                currentFile.addObserver(headObserver);
                log.info(String.format("Added observer to new file with ID %d",currentFile.getId()));
                Thread t = new Thread(currentFile);
                runningFiles.put(currentFile);
                t.start();
                
            }else {
                
                log.error("Received null instead of instance of com.logscrapper.entity.File !");
                
            }
        
    }

    /**
     *
     * @return
     */
    public FileBasket getFileQueue() {
        return fileQueue;
    }

    /**
     *
     * @param fileQueue
     */
    public void setFileQueue(FileBasket fileQueue) {
        this.fileQueue = fileQueue;
    }
    
    
}
