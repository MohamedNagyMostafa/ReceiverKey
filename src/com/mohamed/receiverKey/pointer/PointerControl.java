/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

import com.mohamed.receiverKey.pointer.Actions.Moving.PointerLocationThread;
import com.mohamed.receiverKey.producer.ClickTask;
import com.mohamed.receiverKey.producer.MovingTask;

/**
 *
 * @author mohamednagy
 */
public class PointerControl {    
    /**
     * Handling with mouse moving.
     * Takes both x and y coordinates and start producer for each
     * coordinate if there are values for them.
     * if producer is not started yet, start it and pass moving task.
     * Otherwise pass task directly to tasks queue.
     * @param X_COORDINATE_DISTANCE_CHANGED the distance which happened in
     *                                      X-coordinate if it was positive value
     *                                      then this coordinate have increased otherwise
     *                                      have decreased.
     * @param Y_COORDINATE_DISTANCE_CHANGED the distance which happened in
     *                                      Y-coordinate if it was positive value
     *                                      then this coordinate have increased otherwise
     *                                      have decreased.
     */
    public static void setPointerMovingAction(Integer ... points){
      
        PointerLocationThread pointerLocationThread = PointerLocationThread.getInstance();
        
        if(X_COORDINATE_DISTANCE_CHANGED != 0 || Y_COORDINATE_DISTANCE_CHANGED != 0)
            pointerLocationThread.addTask(new MovingTask(X_COORDINATE_DISTANCE_CHANGED, Y_COORDINATE_DISTANCE_CHANGED));
        if(!pointerLocationThread.isAlive())
            pointerLocationThread.start(); 
    } 
    /**
     * Handling with mouse click event.
     * Takes type of mouse event and set it to queue tasks as 
     * Click Task.
     * if producer is not started yet, start it and pass moving task.
     * Otherwise pass task directly to tasks queue. 
     * @param ACTION_TYPE  hold the action which happened. 
     *                     (CLICK RIGHT - CLICK LEFT - SCROLL).
     */
    public static void setPointerClickAction(final int ACTION_TYPE){
            
        PointerLocationThread pointerLocationThread = PointerLocationThread.getInstance();

        pointerLocationThread.addTask(new ClickTask(ACTION_TYPE));
        
        if(!pointerLocationThread.isAlive())
            pointerLocationThread.start(); 
    }
}
