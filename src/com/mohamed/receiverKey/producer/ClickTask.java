/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.producer;

/**
 *
 * @author mohamednagy
 */
public class ClickTask {
    public static final int RIGHT_CLICK = 0x00B1;
    public static final int LEFT_CLICK = 0x00C2;
    public static final int LEFT_CLICK_SELECT = 0x00C3;
    public static final int LEFT_CLICK_SELECT_RELEASE = 0x00C4;
    
    private final int TASK_TYPE;
    
    public ClickTask(int taskType){
        TASK_TYPE = taskType;
    }
    
    public int getTaskType(){
        return TASK_TYPE;
    }
}
