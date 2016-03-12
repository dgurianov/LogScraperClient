/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logscraperclient.entity;


/**
 *  Basket based  on some kind of queue .
 * Combine Basket idiom functions (get by  id .. etc) and  underlying queue idiom (get head, put tail )
 * @author Daniel
 * @param <T>
 */
public  interface   ObjectsBasket<T> {
    
    /**
     * Get next  Element of type T from the queue.
     * If  Elements is absent, wait for it 
     * @return
     */
    public T getNext();
    
    /**
     * Put element T to the end of queue.
     * If put null , throws TIsNull exception
     * @param element
     * @throws Throwable
     */
    public void putLast(T element) throws Throwable;
    
    /**
     * Check whether element is present  in collection 
     * @param element
     * @return
     */
    public boolean alreadyExist(T element);
    
    /**
     * Get object by ID
     * @param id
     * @return
     */
    public  T getById(int id);
    
    /**
     * Get object by name
     * @param name
     * @return
     */
    public T getByName(String name);
    
}
