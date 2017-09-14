/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.producer;

import com.mohamed.receiverKey.producer.ProducerCoordinate;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.MouseInfo;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author mohamednagy
 */
public class MovingTask {
    private Integer[] movingPoints = null;
    
    
    public MovingTask encodePointsAsTask(Integer... points){
        movingPoints = points;
        
        return this;
    }
}

