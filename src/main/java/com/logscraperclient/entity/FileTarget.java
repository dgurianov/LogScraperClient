/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Observable;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.log4j.Logger;

/**
 * FileTarget object that is  runnable inside the thread and can be observable from  Observer 
 * @author Daniel
 */
@XmlType(propOrder={"id","file","regexBasket"})
public class FileTarget extends Observable implements  Runnable{
    
    //Uniq ID of the object
    private  int id ;
    
    //File on filesystem that will be monitored
    private  java.io.File  file;
    
    //List of regexp patterns to be used towards file
    private  LinkedList<RagPattern> regexBasket;
    
    Logger log = Logger.getLogger(FileTarget.class);

    //No args constructor
    public FileTarget() {}  
    
    //Constructor to create FileTarget manually
    public FileTarget(int id , String file, String regex, Integer ragFlag) {
        
        this.id = id;
        this.file = new java.io.File(file);
        this.regexBasket = new LinkedList<>();
        
        if (regex != null){
            RagPattern rp = new RagPattern(Pattern.compile(regex), ragFlag);
            this.regexBasket.add(rp);
        }
        
    }

    @Override
    public void run() {
        
        
        //Temporary value
        String line ;
        
        //These has to be closed !
	final BufferedReader br;
        final InputStreamReader isr;
        
        try {
            
            isr = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(isr);
            
            while (true) {
                
                    //Do sleep a bit to not to overload CPU
                    Thread.sleep(1);
                    
                    //Wait for the changes in file
                    line  = br.readLine();
                    
                    if (line != null){
                        
                        //Inform observer that some new changes arrived.
                        setChanged();
                        //Why not compare with regexp  at this time ???????
                        //At this time , It looks like any change in the file will trigger at least 2 lines in log.
                        //Which means that  for 1G  file that is  being tracket , we will have 2G log file in DEBUG.
                        notifyObservers(line);
                    }
            }
            
        }catch (Exception e){
            
            log.error("BufferedReader.readline() crashed due to some reason.",e);
            
        }
        
        
        
    }
    
    //Getters, setters 

    public int getId() {
        return id;
    }

    public java.io.File getFile() {
        return file;
    }

    public LinkedList<RagPattern> getRegexBasket() {
        return regexBasket;
    }
    
    public String getFileName(){
        return file.getName();
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="file")
    public void setFile(java.io.File file) {
        this.file = file;
    }

    @XmlElement(name="regexp")
    //@XmlJavaTypeAdapter(XmlPatternAdapter.class)
    public void setRegexBasket(LinkedList<RagPattern> regexBasket) {
        this.regexBasket = regexBasket;
    }
    
    
    
}
