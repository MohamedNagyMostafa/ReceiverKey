/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey;

/**
 *
 * @author mohamednagy
 */
public class PointerAction{
    
    static enum PointerMoving {
        INCREASE_X_POSITION(0x001),
        INCREASE_Y_POSITION(0x002),
        DECREASE_X_POSITION(0x004),
        DECREASE_Y_POSITION(0x005),
        INCREASE_X_Y_POSITION(0x003),
        DECREASE_X_Y_POSITION(0x006),
        INCREASE_X_DECREASE_Y_POSITION(0x007),
        INCREASE_Y_DECREASE_X_POSITION(0x008);

        public final int mCode;

        private PointerMoving(final int code) {
            mCode = code;
        }
      
    }
    
    static enum PointerSPEED {
        SPEED_LEVEL_ONE(0x001),
        SPEED_LEVEL_TWO(0x002),
        SPEED_LEVEL_THREE(0x004),
        SPEED_LEVEL_FOUR(0x005),
        SPEED_LEVEL_FIVE(0x003),
        SPEED_LEVEL_SIX(0x006),
        SPEED_LEVEL_SEVEN(0x007),
        SPEED_LEVEL_EIGHT(0x008);

        public final int mCode;

        private PointerSPEED(final int code) {
            mCode = code;
        }
      
    }
    
    
    static enum PointerClick{
        SINGLE_LEFT_CLICK(0x009),
        DOUBLE_LEFT_CLICK(0x00A),
        RIGHT_CLICK(0x00B),
        RIGHT_CLICK_WITH_MOVING(0x00C);
        
        public final int mCode;
        
        private PointerClick(int code){
            mCode = code;
        }
    }
}
