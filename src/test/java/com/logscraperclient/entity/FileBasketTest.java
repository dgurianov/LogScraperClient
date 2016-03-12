/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;

import com.logscraperclient.entity.FileTarget;
import com.logscraperclient.entity.FileBasket;
import com.logscraperclient.exception.FileIsNull;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniel
 */
public class FileBasketTest {
    
    @Test
    public void fileBasketDoesNotReturnNull() throws FileIsNull{
        
        FileBasket fb = new FileBasket();
        fb.init();
        try {
            fb.putLast(new FileTarget(1, "c:\\Test", "Test",1));
        }catch (Throwable e){
            e.printStackTrace();
        }
        
        
        Assert.assertNotNull(fb.getNext());
        
    }
    
    @Test(expected = FileIsNull.class)
    public void fileBasketExceptsWhileAcceptsNull() throws FileIsNull{
        FileBasket fb = new FileBasket();
        fb.init();
        
        fb.putLast(null);
        
        
    }
}
