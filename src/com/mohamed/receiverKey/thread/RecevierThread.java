/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey.thread;

import com.mohamed.receiverKey.pointer.PointerControl;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.Button;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.IntPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author mohamednagy
 */
public class RecevierThread implements Runnable {
    
    private static final String SPLITING_STRING = "_";
    private static final int PORT_SERVER = 8888;
    
    public RecevierThread(){
        new Thread(this, "Ui Changing").start();
    }

    @Override
    public void run() {
            Socket socket = null;
            ServerSocket socketServer = null;
        try {
            
            socketServer = new ServerSocket(PORT_SERVER);
            
            while(true){
                socket = socketServer.accept();
                try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
                    String dataAsUTFString = dataInputStream.readUTF();
                    do{
                        if(dataAsUTFString.contains(SPLITING_STRING)){
                            int[] coordinatesAsInt = decodeDataAsMovingEvent(dataAsUTFString);
                            PointerControl.setPointerMovingAction(coordinatesAsInt[0], coordinatesAsInt[1]);
                        }else{
                            int clickEventAsInt = decodeDataAsClickEvent(dataAsUTFString);
                            PointerControl.setPointerClickAction(clickEventAsInt);

                        }
                        dataAsUTFString = dataInputStream.readUTF();
                        
                    }while(!dataAsUTFString.isEmpty());
                }
            }
           
        } catch (IOException ex) {
            Logger.getLogger(RecevierThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    if(socket != null )
                        socket.close();
                    if(socketServer != null)
                        socketServer.close();
                } catch (IOException ex) {
                    Logger.getLogger(RecevierThread.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    private int[] decodeDataAsMovingEvent (String dataAsString){
        String[] splitData = dataAsString.split(SPLITING_STRING);
        return new int[]{Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1])};
    }
    
    private int decodeDataAsClickEvent (String dataAsString){
        return Integer.parseInt(dataAsString);
    }
    
}
