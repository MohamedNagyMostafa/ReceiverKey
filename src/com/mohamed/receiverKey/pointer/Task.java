/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

import com.mohamed.receiverKey.producer.ProducerCoordinate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.MouseInfo;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author mohamednagy
 */
public class Task {
    private final int X_COORDINATE_DISTANCE;
    private final int Y_COORIDNATE_DISTANCE;
    
    private int X_COORDINATE_STATE;
    private int Y_COORDINATE_STATE;
    
    
    public Task(int x_distance, int y_distance){
     
        X_COORDINATE_DISTANCE = x_distance;
        Y_COORIDNATE_DISTANCE = y_distance;
        X_COORDINATE_STATE = getState(x_distance);
        Y_COORDINATE_STATE = getState(y_distance);
        
    }
    

    
    public int get_X_endPoint(){
        return MouseInfo.getPointerInfo().getLocation().x + X_COORDINATE_DISTANCE;
    }
    
    public int get_Y_endPoint(){
        return MouseInfo.getPointerInfo().getLocation().y + Y_COORIDNATE_DISTANCE;
    }
    
    public int get_X_state(){
        return X_COORDINATE_STATE;
    }
    
    public int get_Y_state(){
        return Y_COORDINATE_STATE;
    }
    
    public int get_X_distance(){
        return X_COORDINATE_DISTANCE;
    }
    
    public int get_Y_distance(){
        return Y_COORIDNATE_DISTANCE;
    }
    
    private static int getState(int distance){
        return (distance == 0)? ProducerCoordinate.NO_CHANGE_COORDINATE : 
                (distance > 0)? ProducerCoordinate.INCREASING_COORDINATE : 
                ProducerCoordinate.DECREASING_COORDIANTE;
    }
}

