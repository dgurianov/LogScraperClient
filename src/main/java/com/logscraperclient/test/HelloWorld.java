/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * @author Daniel
 */

@Component
public class HelloWorld {
    
    String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

    public HelloWorld() {
        
    }
    

    @Scheduled(fixedDelay = 1000)
    public void say(){
//            System.out.println(this.message);
}   
//    @Component
//    @Configuration
//    @EnableScheduling
//    class SomeStrangeTask{
//
//        public SomeStrangeTask() {
//        }
//        
//        
//        
////        @Scheduled(fixedDelay = 1000)
//        public void say2(){
//            System.out.println("I`m say 2 ");
//            
//            
//        }
//        
        
//    }
    
}
