/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import com.logscraperclient.exception.FileIsNull;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * FileTarget basket backed by concurrent queue
 * @author Daniel
 */
@Component
public class FileBasket implements ObjectsBasket<FileTarget>{
    
    private  ConcurrentLinkedQueue<FileTarget> fileQ ;
    
    Logger log = Logger.getLogger(FileBasket.class);

    public FileBasket() {
    }
    
    @PostConstruct
    public void init(){
        log.debug("File basket constructed successfully .");
        fileQ = new ConcurrentLinkedQueue<>();
    }

    @Override
    public FileTarget getNext() {
        if (fileQ.peek() != null){
            return fileQ.poll();
        }
        while (fileQ.isEmpty()){
            try {
                //Using sleep inside the loop could  affect performance.
                //TO-DO for improvement
                Thread.sleep(10);
            } catch (Exception e) {
                log.error("File basket sleep interrupted while  waiting for the next file.");
            }
        }
        return fileQ.poll();
    }

    /**
     * Put new FileTarget into end of the queue.
     * Throw FileIsNull if null tries to get there 
     * @param element
     * @throws FileIsNull
     */
    @Override
    public void putLast(FileTarget element) throws FileIsNull {
        if (element != null ){
            fileQ.add(element);
        }else{
            throw new FileIsNull();
        }
    }

    public ConcurrentLinkedQueue<FileTarget> getFileQ() {
        return fileQ;
    }

    public void setFileQ(ConcurrentLinkedQueue<FileTarget> fileQ) {
        this.fileQ = fileQ;
    }

    @Override
    public boolean alreadyExist(FileTarget element) {
        
        //If null  , exit at once with false 
        if (element == null ){ return false; }
        
        FileTarget tempFile ;
        
        Iterator<FileTarget> iFile = fileQ.iterator();
        
        //Check each FileTarget object  whether it is monitored or not 
        while (iFile.hasNext()){
            tempFile = iFile.next();
            if ( tempFile.getFile().getName().matches(
                                                        element.getFile().getName()
                                                        )
                ){
                    return true;
                }
        }
        
        //If we got here , element is not monitored yet 
        return false ;
    }

    @Override
    public FileTarget getById(int id) {
        
        FileTarget tempFile ;
        
        Iterator<FileTarget> iFile = fileQ.iterator();
        
        //Check each FileTarget object  whether it is monitored or not 
        while (iFile.hasNext()){
            tempFile = iFile.next();
            if ( tempFile.getId() == id ){
                    return tempFile;
                }
        }
        
        //If we got here , element is not monitored yet 
        return null ;
    }
        
    

    @Override
    public FileTarget getByName(String name) {
        //If null  , exit at once with null 
        if (name == null ){ return null; }
        
        FileTarget tempFile ;
        
        Iterator<FileTarget> iFile = fileQ.iterator();
        
        //Check each FileTarget object  whether it is monitored or not 
        while (iFile.hasNext()){
            tempFile = iFile.next();
            if ( tempFile.getFile().getName().matches(name)){
                    return tempFile;
                }
        }
        
        //If we got here , element is not monitored yet 
        return null ;
        
    }
    
}
