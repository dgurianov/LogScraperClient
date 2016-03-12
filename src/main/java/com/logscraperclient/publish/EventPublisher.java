/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.publish;


/**
 * Publish events in any way.
 * @author Daniel
 */
public interface EventPublisher {
    
    /**
     * Start  automated routine of publishing events
     */
    public void publishEvents();
    
    /**
     * Publish only one , next event
     */
    public void publishNextEvent();
    
}
