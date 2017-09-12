/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer.Actions.Moving;

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
public class MousePoint {
    private int mX;
    private int mY;
    private Robot mMouseControllRobot;
    
    public MousePoint(final Robot MOUSE_SYSTEM_CONTROL){
        mX = MouseInfo.getPointerInfo().getLocation().x;
        mY = MouseInfo.getPointerInfo().getLocation().y;
        
        mMouseControllRobot = MOUSE_SYSTEM_CONTROL;
      
    }
   
    
    public void setX(int x){
        mX = x;
    }
    
    public void setY(int y){
        mY = y;
    }
    
    public int getX(){
        return mX;
    }
    
    public int getY(){
        return mY;
    }
    
    public void updateMouse(int producer){
        mMouseControllRobot.mouseMove(mX, mY);
    }
}
