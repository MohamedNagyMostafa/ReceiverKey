/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.producer;

import com.mohamed.receiverKey.pointer.MousePoint;
import com.mohamed.receiverKey.pointer.Task;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamednagy
 */
public class ProducerCoordinate {
    // The end touch position for both coordinate
    private static int END_POINT_X_COORDINATE;
    private static int END_POINT_Y_COORDINATE;
    // Distance Moving of both x and y coordinate.
    private int X_COORDINATE_DISTANCE;
    private int Y_COORDINATE_DISTANCE;
    // State of both coordinates producers. they take two values.
    // #INCREASING_COORDINATE #DECREASING_COORDIANTE #NO_CHANGE_COORDINATE
    private static int X_PRODUCER_STATE;
    private static int Y_PRODUCER_STATE;
    // Types of improving in coordinate. 
    // #INCREASING_COORDINATE : indicates to increasing by one in the coordinate.
    // #DECREASING_COORDIANTE : indicates to decreasing by one in the coordinate.
    // #NO_CHANGE_COORDINATE  : indicates to no changing in the coordinate.
    public static final int INCREASING_COORDINATE = 0x00B1;
    public static final int DECREASING_COORDIANTE = 0x00B2;
    public static final int NO_CHANGE_COORDINATE  = 0x00B3;
    // Idintfy if for both x, y coordinates producer.
    private static final int X_COORDINATE_PRODUCER = 0x000;
    private static final int Y_COORDINATE_PRODUCER = 0x001;
    // Reflector for producers.
    private static final int REFLECTION = 2;
    // Produces threads to do increasing in x and y coordinate synchronize.
    private Thread X_ProducerThread;
    private Thread Y_ProducerThread;
    // Determine the producer which run now.
    private int currentProducer;
   
    
    private static class ProducerCoordinateHolder{
        private static final ProducerCoordinate PRODUCER_COORDINATE = new ProducerCoordinate();
    }
    
    public static ProducerCoordinate getInstance(){  
        return ProducerCoordinateHolder.PRODUCER_COORDINATE; 
    }
    /**
     * To add new task for coordinate producer which hold coordinates data.
     * @param task  the new task
     */
    public void addTask(Task task){
        END_POINT_X_COORDINATE = task.get_X_endPoint();
        END_POINT_Y_COORDINATE = task.get_Y_endPoint();
        X_PRODUCER_STATE = task.get_X_state();
        Y_PRODUCER_STATE = task.get_Y_state();
        X_COORDINATE_DISTANCE = task.get_X_distance();
        Y_COORDINATE_DISTANCE = task.get_Y_distance();
    }
    /**
     * To start coordinate producer.
     */
    public void run(){
        init();
    }
    
    private void init(){
        final MousePoint mouserPoint = new MousePoint();
        
        X_ProducerThread = new Thread(()->{
                        
            while(mouserPoint.getX() != END_POINT_X_COORDINATE){
                Util.println("thread x  current value : " + mouserPoint.getX() + " wanted " + END_POINT_X_COORDINATE);
                producerThreadProcess(mouserPoint, X_COORDINATE_PRODUCER);
                Util.println("thread x end current value : " + mouserPoint.getX() + " wanted " + END_POINT_X_COORDINATE);

            }
        });
        
        Y_ProducerThread = new Thread(()->{
                        
            while(mouserPoint.getY() != END_POINT_Y_COORDINATE){
                Util.println("thread y  current value : " + mouserPoint.getY() + " wanted " + END_POINT_Y_COORDINATE);
                producerThreadProcess(mouserPoint, Y_COORDINATE_PRODUCER);
                Util.println("thread y end current value : " + mouserPoint.getY() + " wanted " + END_POINT_Y_COORDINATE);
            }
        });
        
        startPoint(mouserPoint);
    }
    
    private synchronized void producerThreadProcess(MousePoint mousePoint, final int PRODUCRER_ID){
        // Waiting.
        while(currentProducer != PRODUCRER_ID && anotherProducerWaiting(PRODUCRER_ID)){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducerCoordinate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Start.
        startProcess(PRODUCRER_ID, mousePoint);
        mousePoint.updateMouse(PRODUCRER_ID);
        
        switcher(PRODUCRER_ID, mousePoint);
        
        if(anotherProducerWaiting(PRODUCRER_ID))
            notify();
       
    }
    /**
     * Determine inWhich producer the process will be.
     * @param producerId    producer id.
     * @param mousePoint    the holder of mouse coordinates.
     */
    private void startProcess(int producerId, MousePoint mousePoint){
        switch(producerId){
            case X_COORDINATE_PRODUCER:
                mousePoint.setX(doProcess(mousePoint.getX(), X_PRODUCER_STATE));
                break;
            case Y_COORDINATE_PRODUCER:
                mousePoint.setY(doProcess(mousePoint.getY(), Y_PRODUCER_STATE));
                break;
        }
    }
    /**
     * Do process of (increasing - decreasing - no changing) on point.
     * @param coordinatePoint   point coordinate.
     * @param STATE             process state.
     * @return 
     */
    private int doProcess(int coordinatePoint, final int STATE){
        switch(STATE){
            case INCREASING_COORDINATE:
                return ++coordinatePoint;
            case DECREASING_COORDIANTE:
                return --coordinatePoint;
            case NO_CHANGE_COORDINATE:
            default:
                return coordinatePoint;      
        }
    }
    /**
     * Switch between producers upon it's current state and it's idle or not.
     * @param PRODUCER_ID producer id.
     */
    private void switcher(final int PRODUCER_ID, MousePoint mousePoint){
        final int PRODUCER_CONTROLLER = (PRODUCER_ID + REFLECTION + 1) % REFLECTION;
        
        if(!isIdle(mousePoint, PRODUCER_CONTROLLER))
            currentProducer = PRODUCER_CONTROLLER;
        else
            currentProducer = PRODUCER_ID;    
    }
    
    private boolean isIdle(final MousePoint MOUSE_POINT, final int PRODUCER_ID){
        return (PRODUCER_ID == X_COORDINATE_PRODUCER)? (MOUSE_POINT.getX() == END_POINT_X_COORDINATE) :
                (MOUSE_POINT.getY() == END_POINT_Y_COORDINATE);
    }
    /**
     * Determine which producer is going to start first.
     * Determine the next producer will run also or not.
     * Determine the end producer for update mouse.
     * @param MOUSE_POINT Mouse System.
     */
    private void startPoint(final MousePoint MOUSE_POINT){
        if(Math.abs(X_COORDINATE_DISTANCE) > Math.abs(Y_COORDINATE_DISTANCE)){
            currentProducer = X_COORDINATE_PRODUCER;   
            Util.println("start point x");
            
            if(Y_PRODUCER_STATE == NO_CHANGE_COORDINATE){
                Util.println("start point only x");
                X_ProducerThread.start();
            }else{ 
                Util.println("start point x - >y");
                X_ProducerThread.start();
                Y_ProducerThread.start();
            }
        }else{
            currentProducer = Y_COORDINATE_PRODUCER;
            Util.println("start point y");
            if(X_PRODUCER_STATE == NO_CHANGE_COORDINATE){
                Util.println("start point only y");
                Y_ProducerThread.start();

            }else{
                Util.println("start point y - >x");
                Y_ProducerThread.start();
                X_ProducerThread.start();
            }
        }
    }
    /**
     * Check there is another producer is waiting or not. 
     * @param PRODUCER_ID   current running producer id
     * @return  true if there is another producer is waiting otherwise false
     */
    private boolean anotherProducerWaiting(final int PRODUCER_ID){
        final int ANOTHER_PRODUCER_ID = (PRODUCER_ID + REFLECTION + 1) % REFLECTION;
        
        return (ANOTHER_PRODUCER_ID == X_COORDINATE_PRODUCER)? 
                (X_PRODUCER_STATE != NO_CHANGE_COORDINATE && X_ProducerThread.isAlive()):
                (Y_PRODUCER_STATE  != NO_CHANGE_COORDINATE && Y_ProducerThread.isAlive());
    }
    /**
     * Check the state of coordinate producer is running or not upon the
     * x producer and y producer is running or not.
     * @return true if both x producer and y producer is not running if
     *         there is at least one running then return false.
     */
    public boolean isRunning(){
        return (X_ProducerThread != null && Y_ProducerThread != null)?
                (X_ProducerThread.isAlive() || Y_ProducerThread.isAlive()):false;
    }
}
