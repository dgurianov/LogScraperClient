/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.update;

/**
 * Update receiver object.
 * Collect updates by different sources
 * @author Daniel
 */
public interface UpdateReceiver {
    
    /**
     *  Start continuous process of updates receiving 
     */
    public void startReceivingUpdates();
    
    /**
     * Update monitoring FileBasket with one file 
     * @param  fileName
     * @param regexp
     */
    public void update(String fileName , String regexp );
        
    /**
     * Check whether such file is already monitored.
     * Used while  adding  additional regexp expression to already monitored file.
     * @param fileName
     * @return
     */
    boolean alreadyExist(String fileName);
}
