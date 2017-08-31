/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey;

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
    
    
    
    public static void setPointerMovingAction(
            PointerAction.PointerMoving pointerMoving, 
            final int MOVING_SPEED){
        final int POINTER_LOCATION_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int POINTER_LOCATION_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        
        if     (pointerMoving.mCode == DEC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE);
        else if(pointerMoving.mCode == INC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE);
        else if(pointerMoving.mCode == DEC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED);
        else if(pointerMoving.mCode == INC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED);
        else if(pointerMoving.mCode == INC_X_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED);
        else if(pointerMoving.mCode == DEC_X_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED);
        else if(pointerMoving.mCode == DEC_X_INC_Y_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE - MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED);
        else if(pointerMoving.mCode == DEC_Y_INC_X_COOR)
            setPointerLocation(POINTER_LOCATION_X_COORDINATE + MOVING_SPEED, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED);
        
          
    }
   
    private static void setPointerLocation(final int X_COORDINATE, final int Y_COORDINATE){
        try {
            new Robot().mouseMove(X_COORDINATE, Y_COORDINATE);
        } catch (AWTException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
