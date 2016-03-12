/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import com.logscraperclient.exception.EventIsNull;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * FIFO basket of events backed by LinkedList 
 * @author Daniel
 */
@Component
public class EventBasket implements ObjectsBasket<Event>{
    
    
    //Basket container 
    private  Queue<Event> eventQ ;
    
    //Logger
    Logger logger = Logger.getLogger(EventBasket.class);

    /**
     * Regular constructor 
     */
    public EventBasket() {
    }
    
    
    
    /**
     *
     */
    @PostConstruct
    public void init(){
        
        this.eventQ = new ConcurrentLinkedQueue<>();
        
    }
    
    /**
     * Returns next event from queue .
     * If  queue is empty and  container returns null, wait for the next element
     * @return Event
     */
    @Override
    public Event getNext() {
        
        if (eventQ.peek() != null ){
            
            return eventQ.poll();
            
        }
        
            while(eventQ.isEmpty()){
                try {
                    //Using sleep inside the loop could  affect performance.
                    //TO-DO for improvement
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    
                    logger.error("Event basket sleep was interrupted",e);
                    
                }
            }
        
        return eventQ.poll();
    }
    
    /**
     * Put Event to the tail of the queue.
     * If event is null, throw  exception
     * @param element
     * @throws com.logscraperclient.exception.EventIsNull
     */
    @Override
    public void putLast(Event element ) throws EventIsNull{
        
        
        if (element != null ){
            
            eventQ.add(element);
            
        }else {
            
            //We do not accept empty events , do nothing and throw
            throw  new EventIsNull();
        }
        
    }

    /**
     *
     * @return
     */
    public Queue<Event> getEventQ() {
        return eventQ;
    }

    /**
     *
     * @param eventQ
     */
    public void setEventQ(Queue<Event> eventQ) {
        this.eventQ = eventQ;
    }

    @Override
    public boolean alreadyExist(Event element) {
        //This is not used for Events at this time 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event getById(int id) {
        //This is not used at this time 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event getByName(String name) {
        //This is not used at thist time 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
