/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

import com.mohamed.receiverKey.producer.ProducerCoordinate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.MouseInfo;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamednagy
 */
public class PointerControl {    
    
    public static void setPointerMovingAction(final int X_COORDINATE_DISTANCE_CHANGED, final int Y_COORDINATE_DISTANCE_CHANGED){
      
        PointerLocationThread pointerLocationThread = PointerLocationThread.getInstance();
        
        if(X_COORDINATE_DISTANCE_CHANGED != 0 || Y_COORDINATE_DISTANCE_CHANGED != 0)
            pointerLocationThread.addTask(new Task(X_COORDINATE_DISTANCE_CHANGED, Y_COORDINATE_DISTANCE_CHANGED));
        if(!pointerLocationThread.isAlive())
            pointerLocationThread.start();
       
    } 
    
}
