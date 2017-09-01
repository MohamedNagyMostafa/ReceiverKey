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
import javafx.scene.shape.MoveTo;

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
            int pointerMovingValue, 
            final int MOVING_SPEED){
        final int POINTER_LOCATION_X_COORDINATE = MouseInfo.getPointerInfo().getLocation().x;
        final int POINTER_LOCATION_Y_COORDINATE = MouseInfo.getPointerInfo().getLocation().y;
        
        Thread pointerLocationThread = new Thread(new Runnable() {
            @Override
            public void run() {
        if(pointerMovingValue == DEC_X_COOR)
            PointerActionProcess.MoveMouseToLeft(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE - MOVING_SPEED);
        else if(pointerMovingValue == INC_X_COOR)
            PointerActionProcess.MoveMouseToRight(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE + MOVING_SPEED);
        else if(pointerMovingValue == DEC_Y_COOR)
            PointerActionProcess.MoveMouseToUp(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_Y_COORDINATE - MOVING_SPEED);
        else if(pointerMovingValue == INC_Y_COOR)
            PointerActionProcess.MoveMouseToDown(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_Y_COORDINATE + MOVING_SPEED);
        else if(pointerMovingValue == INC_X_Y_COOR)
            PointerActionProcess.MoveMouseRightWithDown(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE + MOVING_SPEED);
        else if(pointerMovingValue == DEC_X_Y_COOR)
            PointerActionProcess.MoveMouseLeftWithUp(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE - MOVING_SPEED);
        else if(pointerMovingValue == DEC_X_INC_Y_COOR)
            PointerActionProcess.MoveMouseLeftWithDown(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE - MOVING_SPEED);
        else if(pointerMovingValue == DEC_Y_INC_X_COOR)
            PointerActionProcess.MoveMouseRightWithUp(POINTER_LOCATION_X_COORDINATE, POINTER_LOCATION_Y_COORDINATE, POINTER_LOCATION_X_COORDINATE + MOVING_SPEED);
            }
        });
        pointerLocationThread.start();
        try {
            pointerLocationThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
}
