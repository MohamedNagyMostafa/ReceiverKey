/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer.Actions.Moving;

import com.mohamed.receiverKey.pointer.Actions.Click.ClickAction;
import com.mohamed.receiverKey.producer.ClickTask;
import com.mohamed.receiverKey.producer.MovingTask;
import com.mohamed.receiverKey.producer.ProducerCoordinate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamednagy
 */
public class PointerLocationThread extends Thread {
    private Queue<Object> tasksQueue = null;
    
    private static class PointerLocationThreadHolder{
        private static final PointerLocationThread POINTER_LOCATION_THREAD = new PointerLocationThread();
    }
    
    public static PointerLocationThread getInstance(){
        return PointerLocationThreadHolder.POINTER_LOCATION_THREAD;
    }
    
    private PointerLocationThread(){
        tasksQueue = new LinkedList<>();
    }
    
    public void addTask(Object task){
        tasksQueue.add(task);
       
    }

    @Override
    public void run() {
        try {
            final Robot MOUSE_SYSTEM_CONTROL  = new Robot();
            ProducerCoordinate producerCoordinate = ProducerCoordinate.getInstance();
            producerCoordinate.setMouse(MOUSE_SYSTEM_CONTROL);
            
            while(true){
            if(tasksQueue.size() > 0){
                final Object TASK = tasksQueue.poll();
                if(TASK instanceof MovingTask){
                    producerCoordinate.addTask((MovingTask) TASK);
                    producerCoordinate.run();

                    while(producerCoordinate.isRunning());
                }else if(TASK instanceof  ClickTask){
                    ClickAction.setMouseAction((ClickTask) TASK, MOUSE_SYSTEM_CONTROL);
                }
            }
        }
        } catch (AWTException ex) {
            Logger.getLogger(PointerLocationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
