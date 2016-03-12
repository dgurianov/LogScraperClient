/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.update;

import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.entity.FileBasket;
import com.logscraperclient.entity.FilesInMonitoringMap;
import com.logscraperclient.entity.IdGenerator;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is a stub to emulate updates 
 * @author Daniel
 */
@Component
public class StubUpdatesReceiver implements UpdateReceiver {
    
    //Uniq id generated for each file
    @Autowired
    private IdGenerator id  ;
    
    @Autowired
    private FileBasket fileQ ;
    @Autowired 
    private FilesInMonitoringMap filesInMonitoring ;
    
    //Logging
    Logger log = Logger.getLogger(StubUpdatesReceiver.class);
    

    
    
    public StubUpdatesReceiver() {
        
    }

    @PostConstruct
    public void init(){
        //Get latest id;
        try {
            //Files setup has been moved to config
//            System.out.println("No files are there ");
//            fileQ.putLast(new FileTarget(id.getId(),"C:\\!disk\\output_file2.txt" , "WARN"));
//            fileQ.putLast(new FileTarget(id.getId(),"C:\\!disk\\output_file3.txt" , "INFO"));
        }catch (Throwable e){
            log.error("Filed to add file to filebasket, file is null");
//            System.out.println("Failed to add file to filebasket, file is null");
        }
    }
    
    @Override
    public void update(String fileName, String regexp) {
        FileTarget f = new FileTarget(id.getId(),"C:\\!disk\\output_file2.txt" , "WARN",1);
        filesInMonitoring.put( f);
    }
    
    
    @Override
    public boolean alreadyExist(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void startReceivingUpdates() {
//        System.out.println(fileQ.getNext().getFile());
    }

    public FileBasket getFileQ() {
        return fileQ;
    }

    public void setFileQ(FileBasket fileQ) {
        this.fileQ = fileQ;
    }

    public IdGenerator getId() {
        return id;
    }

    public void setId(IdGenerator id) {
        this.id = id;
    }

    

    
    
    
    
    
}
