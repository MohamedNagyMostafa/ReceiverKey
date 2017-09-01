/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamednagy
 */
public class PointerActionProcess {
    
    private static final int SLEEPING_DURATION = 2;
    // Called whenever increasing in x coordinate is happened.
    @CoordinateAnnotation
    public static void MoveMouseToRight(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION ){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION + 1 ; 
                    coordinateCounterChanger <= NEW_X_COORDINATE_POSITION; coordinateCounterChanger++){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(coordinateCounterChanger, INITIAL_Y_COORDINATE_POSITION);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever decreasing in x coordinate is happened.
    @CoordinateAnnotation
    public static void MoveMouseToLeft(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION ){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION - 1 ; 
                    coordinateCounterChanger >= NEW_X_COORDINATE_POSITION; coordinateCounterChanger--){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(coordinateCounterChanger, INITIAL_Y_COORDINATE_POSITION);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    // Called whenever decreasing in y coordinate is happened.
    @CoordinateAnnotation
    public static void MoveMouseToUp(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_Y_COORDINATE_POSITION ){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION - 1 ; 
                    coordinateCounterChanger >= NEW_Y_COORDINATE_POSITION; coordinateCounterChanger--){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(INITIAL_X_COORDINATE_POSITION, coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever increasing in y coordinate is happened.
    @CoordinateAnnotation
    public static void MoveMouseToDown(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_Y_COORDINATE_POSITION ){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION + 1 ; 
                    coordinateCounterChanger <= NEW_Y_COORDINATE_POSITION; coordinateCounterChanger++){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(INITIAL_X_COORDINATE_POSITION, coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever increasing in both y and x coordinates is happened.
    @CoordinateAnnotation
    public static void MoveMouseRightWithDown(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int x_coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION + 1 ,
                    y_coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION + 1;
                    x_coordinateCounterChanger <= NEW_X_COORDINATE_POSITION;
                    x_coordinateCounterChanger++, y_coordinateCounterChanger++){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(x_coordinateCounterChanger, y_coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever decreasing in both y and x coordinates is happened.
    @CoordinateAnnotation
    public static void MoveMouseLeftWithUp(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int x_coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION - 1 ,
                    y_coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION - 1;
                    x_coordinateCounterChanger >= NEW_X_COORDINATE_POSITION;
                    x_coordinateCounterChanger--, y_coordinateCounterChanger--){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(x_coordinateCounterChanger, y_coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever decreasing in y and increasing in x coordinates are happened.
    @CoordinateAnnotation
    public static void MoveMouseRightWithUp(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int x_coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION + 1 ,
                    y_coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION - 1;
                    x_coordinateCounterChanger <= NEW_X_COORDINATE_POSITION;
                    x_coordinateCounterChanger++, y_coordinateCounterChanger--){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(x_coordinateCounterChanger, y_coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Called whenever decreasing in x and increasing in y coordinates are happened.
    @CoordinateAnnotation
    public static void MoveMouseLeftWithDown(final int INITIAL_X_COORDINATE_POSITION,
            final int INITIAL_Y_COORDINATE_POSITION, final int NEW_X_COORDINATE_POSITION){
        try {
            Robot SYSTEM_MOUSE_CONTROLLER = new Robot();
            for(int x_coordinateCounterChanger = INITIAL_X_COORDINATE_POSITION - 1 ,
                    y_coordinateCounterChanger = INITIAL_Y_COORDINATE_POSITION + 1;
                    x_coordinateCounterChanger >= NEW_X_COORDINATE_POSITION;
                    x_coordinateCounterChanger--, y_coordinateCounterChanger++){
                SYSTEM_MOUSE_CONTROLLER.mouseMove(x_coordinateCounterChanger, y_coordinateCounterChanger);
                Thread.sleep(SLEEPING_DURATION);
            }
        } catch (AWTException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PointerActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
