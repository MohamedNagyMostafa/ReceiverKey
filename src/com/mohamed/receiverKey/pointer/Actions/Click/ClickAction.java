/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.pointer.Actions.Click;

import com.mohamed.receiverKey.producer.ClickTask;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamednagy
 */
public class ClickAction {
    
    
    public static void setMouseAction(final ClickTask ACTION_TYPE, final Robot MOUSE_SYSTEM_CONTROL){
        switch (ACTION_TYPE.getTaskType()){
            case ClickTask.LEFT_CLICK:
                    MOUSE_SYSTEM_CONTROL.mousePress(InputEvent.BUTTON1_MASK);
                    MOUSE_SYSTEM_CONTROL.mouseRelease(InputEvent.BUTTON1_MASK);
                    
                    break;
                case ClickTask.RIGHT_CLICK:
                    MOUSE_SYSTEM_CONTROL.mousePress(InputEvent.BUTTON3_MASK);
                    MOUSE_SYSTEM_CONTROL.mouseRelease(InputEvent.BUTTON3_MASK);
                    break;
                case ClickTask.LEFT_CLICK_SELECT:
                    { // must 5 ms after release to callback again .. (select)
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ClickAction.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    MOUSE_SYSTEM_CONTROL.mousePress(InputEvent.BUTTON1_MASK);
                    break;
                case ClickTask.LEFT_CLICK_SELECT_RELEASE:
                    MOUSE_SYSTEM_CONTROL.mouseRelease(InputEvent.BUTTON1_MASK);
                    Util.println("release select");

                    break;

        }
        
        
    }
}
