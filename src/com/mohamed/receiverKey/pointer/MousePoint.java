/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer;

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
    private Robot mouseControllRobot;
    
    public MousePoint(){
        mX = MouseInfo.getPointerInfo().getLocation().x;
        mY = MouseInfo.getPointerInfo().getLocation().y;
        try {
            mouseControllRobot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MousePoint.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        mouseControllRobot.mouseMove(mX, mY);
       
        
    }
}
