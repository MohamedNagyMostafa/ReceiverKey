/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

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
public class PointerControl {
    
    public static final int DEC_X_COOR = PointerAction.PointerMoving.DECREASE_X_POSITION.mCode;
    public static final int DEC_Y_COOR = PointerAction.PointerMoving.DECREASE_Y_POSITION.mCode;
    public static final int INC_X_COOR = PointerAction.PointerMoving.INCREASE_X_POSITION.mCode;
    public static final int INC_Y_COOR = PointerAction.PointerMoving.INCREASE_Y_POSITION.mCode;
    public static final int DEC_X_Y_COOR = PointerAction.PointerMoving.DECREASE_X_Y_POSITION.mCode;
    public static final int INC_X_Y_COOR = PointerAction.PointerMoving.INCREASE_X_Y_POSITION.mCode;
    public static final int DEC_X_INC_Y_COOR = PointerAction.PointerMoving.INCREASE_Y_DECREASE_X_POSITION.mCode;
    public static final int DEC_Y_INC_X_COOR = PointerAction.PointerMoving.INCREASE_X_DECREASE_Y_POSITION.mCode;
    
    public static final int COORDINATE_INCREASING  = 0x001;
    public static final int COORDINATE_DECREASING  = 0x002;
    public static final int COORDINATE_NO_CHANGING = 0x003;
    
    public static void setPointerMovingAction(
            int pointerMovingValue, 
            final int MOVING_SPEED){
        final int POINTER_LOCATION_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int POINTER_LOCATION_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        
        Thread pointerLocationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if     (pointerMovingValue == DEC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING, POINTER_LOCATION_Y_COORDINATE, COORDINATE_NO_CHANGING);
        else if(pointerMovingValue == INC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING, POINTER_LOCATION_Y_COORDINATE, COORDINATE_NO_CHANGING);
        else if(pointerMovingValue == DEC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE,  COORDINATE_NO_CHANGING, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING);
        else if(pointerMovingValue == INC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE,  COORDINATE_NO_CHANGING, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING);
        else if(pointerMovingValue == INC_X_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING);
        else if(pointerMovingValue == DEC_X_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING);
        else if(pointerMovingValue == DEC_X_INC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING);
        else if(pointerMovingValue == DEC_Y_INC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, COORDINATE_INCREASING, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED, COORDINATE_DECREASING);
            }
        });
        
        pointerLocationThread.start();
        try {
            pointerLocationThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    private static void setPointerLocation(final int POINTER_LOCATION_X_COORDINATE, final int LOCATION_CHANGING_X_COORDINATE_STATE, 
            final int POINTER_LOCATION_Y_COORDINATE, final int LOCATION_CHANGING_Y_COORDINATE_STATE){
        
        final Thread COORDINATE_X_CHANGING_THREAD = new Thread(new Runnable() {
            @Override
            public void run() {
                switch(LOCATION_CHANGING_X_COORDINATE_STATE){
                    case COORDINATE_INCREASING:
                        Util.println("start");
                        increase_X_coordinate(POINTER_LOCATION_X_COORDINATE);
                                                Util.println("end");

                        break;
                    case COORDINATE_DECREASING:
                        decrease_X_coordinate(POINTER_LOCATION_X_COORDINATE);
                        
                        break;
                    case COORDINATE_NO_CHANGING:
                        // Do nothing.
                        break;
                }
            }
        });
        
        final Thread COORDINATE_Y_CHANGING_THREAD = new Thread(new Runnable() {
            @Override
            public void run() {
                switch(LOCATION_CHANGING_Y_COORDINATE_STATE){
                    case COORDINATE_INCREASING:
                        increase_Y_coordinate(POINTER_LOCATION_Y_COORDINATE);
                        break;
                    case COORDINATE_DECREASING:
                        decrease_Y_coordinate(POINTER_LOCATION_Y_COORDINATE);
                        break;
                    case COORDINATE_NO_CHANGING:
                        // Do nothing.
                        break;
                }
            }
        });
        
        COORDINATE_X_CHANGING_THREAD.start();
        try {
            COORDINATE_X_CHANGING_THREAD.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        COORDINATE_Y_CHANGING_THREAD.start();
    }
   
    private synchronized static void increase_X_coordinate(final int NEW_X_COORDINATE){
        final int PREVIOUS_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int PREVIOUS_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        try {
                        Util.println("inital x = " + PREVIOUS_X_COORDINATE);
            Robot robot = new Robot();
            for(int coordinateMoving = PREVIOUS_X_COORDINATE + 1; coordinateMoving <= NEW_X_COORDINATE; coordinateMoving++ ){
                robot.mouseMove(coordinateMoving, PREVIOUS_Y_COORDINATE);
                                        Util.println("next x = " + coordinateMoving);

                                                        Thread.sleep(10);


            }
            } catch (AWTException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    private synchronized static void increase_Y_coordinate(final int NEW_Y_COORDINATE){      
        final int PREVIOUS_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int PREVIOUS_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        Util.println("initial y = " + PREVIOUS_Y_COORDINATE);
        try {
            Robot robot = new Robot();
            for(int coordinateMoving = PREVIOUS_Y_COORDINATE + 1; coordinateMoving <= NEW_Y_COORDINATE; coordinateMoving++ ){
                robot.mouseMove(PREVIOUS_X_COORDINATE, coordinateMoving);
                        Util.println("next y = " + coordinateMoving);
                                                                Thread.sleep(10);

            }
            } catch (AWTException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized static void decrease_X_coordinate(final int NEW_X_COORDINATE){
        final int PREVIOUS_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int PREVIOUS_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
                Util.println("initial x = " + PREVIOUS_X_COORDINATE);

        try {
            Robot robot = new Robot();
            for(int coordinateMoving = PREVIOUS_X_COORDINATE -1 ; coordinateMoving >= NEW_X_COORDINATE; coordinateMoving-- ){
                robot.mouseMove(coordinateMoving, PREVIOUS_Y_COORDINATE);
                                        Util.println("next x = " + coordinateMoving);
                                        Thread.sleep(10);
            }
            } catch (AWTException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized static void decrease_Y_coordinate(final int NEW_Y_COORDINATE){
        final int PREVIOUS_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int PREVIOUS_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        try {
            Robot robot = new Robot();
            Util.println("inital y = " + PREVIOUS_Y_COORDINATE);
            for(int coordinateMoving = PREVIOUS_Y_COORDINATE - 1; coordinateMoving >= NEW_Y_COORDINATE; coordinateMoving-- ){
                robot.mouseMove(PREVIOUS_X_COORDINATE, coordinateMoving);
                            Util.println("next y = " + coordinateMoving);
                                                                    Thread.sleep(10);


            }
            } catch (AWTException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
