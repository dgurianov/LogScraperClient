/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.publish;

import com.logscraperclient.entity.Event;
import com.logscraperclient.entity.EventBasket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 *
 * Stub to publish events from basket queue  to system.out
 * @author Daniel
 */
@Component
public class StdoutEventPublisher implements EventPublisher , Runnable {
    
    //Basket of Events that will be tracked.
    @Autowired
    private  EventBasket eventB ;
    
    //Regularly used variable
    Event  currentEvent ;
    
    //Logger
    Logger log = Logger.getLogger(StdoutEventPublisher.class);

    /**
     * Event publisher bean that  publish events into stdout 
     */
    public StdoutEventPublisher() {
    }
    
    /**
     *  Circular action that  checks  event basket and publish all events 
     */
//    @Scheduled(fixedDelay = 1000)
    @Override
    public void publishEvents() {
         
//        while (true){
            
            publishNextEvent();
            
//        }
        
    }

    /**
     *
     * @return 
     */
    public EventBasket getEventB() {
        return eventB;
    }

    /**
     *
     * @param eventB
     */
    public void setEventB(EventBasket eventB) {
        this.eventB = eventB;
    }

    @Override
    public void run() {
        log.info("Publisher started.");
        publishEvents();
    }

    @Override
    public void publishNextEvent() {
        currentEvent = eventB.getNext();
            if (currentEvent != null){
                log.info(String.format("Publish event id %d over object %s and line %s", currentEvent.getId(),currentEvent.getObjectName(),currentEvent.getMatchedString() ));
//                System.out.println("Publish event id : "+currentEvent.getId()+"  over object "+currentEvent.getObjectName()+" and line "+currentEvent.getMatchedString());
            }else{
                
            }
    }
    
}
