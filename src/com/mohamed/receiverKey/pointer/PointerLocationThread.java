/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

import com.mohamed.receiverKey.producer.ProducerCoordinate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author mohamednagy
 */
public class PointerLocationThread extends Thread {
    private Queue<Task> tasksQueue = null;
    
    private static class PointerLocationThreadHolder{
        private static final PointerLocationThread POINTER_LOCATION_THREAD = new PointerLocationThread();
    }
    
    public static PointerLocationThread getInstance(){
        return PointerLocationThreadHolder.POINTER_LOCATION_THREAD;
    }
    
    private PointerLocationThread(){
        tasksQueue = new LinkedList<>();
    }
    
    public void addTask(Task task){
        tasksQueue.add(task);
       
    }

    @Override
    public void run() {
        ProducerCoordinate producerCoordinate = ProducerCoordinate.getInstance();
        while(true){
            if(tasksQueue.size() > 0){
               producerCoordinate.addTask(tasksQueue.poll());
                Util.println("run");
               producerCoordinate.run();
                Util.println("wait");

               while(producerCoordinate.isRunning());
            }
        }
    }
}
