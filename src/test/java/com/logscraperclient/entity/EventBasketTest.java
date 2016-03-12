/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import com.logscraperclient.entity.EventBasket;
import com.logscraperclient.entity.Event;
import com.logscraperclient.exception.EventIsNull;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniel
 */
public class EventBasketTest {
    
    @Test
    public void eventBasketDoesNotReturnNull() throws EventIsNull{
        EventBasket eb = new EventBasket();
        eb.init();
        try {
            eb.putLast(new Event(1, "Test", "Test",Calendar.getInstance().getTime(),1));
        }catch (Throwable e){
            e.printStackTrace();
        }
        
        //Fail if null returned 
        Assert.assertNotNull(eb.getNext());
    }
    
    
    @Test(expected = EventIsNull.class)
    public void eventBasketExceptsWhileAcceptNull() throws EventIsNull{
        
        EventBasket eb = new EventBasket();
        eb.putLast(null);
        
    }
    
    
}
