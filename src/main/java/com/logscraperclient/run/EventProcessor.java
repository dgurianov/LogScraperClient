/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.run;

import com.logscraperclient.entity.Event;
import com.logscraperclient.entity.EventBasket;
import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.exception.EventIsNull;
import com.logscraperclient.publish.EventPublisher;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Been to generate and process  Events
 * Uses Event publisher  to publish events somewhere
 * @author Daniel
 */
@Component
public class EventProcessor {
    
    private static int id = 0;
    @Autowired
    private  EventBasket eventQ ;
    @Autowired
    private  EventPublisher eventP;
    
    //Setup logging
    Logger log = Logger.getLogger(EventProcessor.class);
    
    /**
     * Event  processor bean  constructor . 
     */
    public EventProcessor(){
    }
    
    
    //Takes  FileTarget and String, that has triggered matcher 
    //       and create Event. Puts it to event backet

    /**
     *
     * @param file
     * @param eventLine
     * @param alertValue
     */
        public void processEvent(FileTarget file , String eventLine , Integer alertValue){
        
        Event event = new  Event(++id, file.getFile().getPath(), eventLine, Calendar.getInstance().getTime(), alertValue);
        
        log.debug(String.format("Successfully created event with id %d", event.getId()));
        
//        System.out.println("Event get into backet "+event.getId());
        try {
            eventQ.putLast(event);
            log.debug(String.format("New event with id %d got into basket .", event.getId()));
        }catch (EventIsNull e){
            log.warn(String.format("Failed to  put event with id %d  into basket, event is null .", event.getId()));
//            System.out.println("Failed to add event into the basket, event is null.");
        }
        
        
        
    }

    /**
     *
     * @return
     */
    public static int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public static void setId(int id) {
        EventProcessor.id = id;
    }

    /**
     *
     * @return
     */
    public EventBasket getEventQ() {
        return eventQ;
    }

    /**
     *
     * @param eventQ
     */
    public void setEventQ(EventBasket eventQ) {
        this.eventQ = eventQ;
    }

    /**
     *
     * @return
     */
    public EventPublisher getEventP() {
        return eventP;
    }

    /**
     *
     * @param eventP
     */
    public void setEventP(EventPublisher eventP) {
        this.eventP = eventP;
    }
    
    
}
