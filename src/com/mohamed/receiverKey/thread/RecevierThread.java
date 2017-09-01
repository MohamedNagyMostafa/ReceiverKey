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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author mohamednagy
 */
public class RecevierThread implements Runnable {
    
    public RecevierThread(){
        new Thread(this, "Ui Changing").start();
    }
     

    @Override
    public void run() {
        try {
            ServerSocket socketServer = new ServerSocket(8888);
            while(true){
                Socket socket = socketServer.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int read = dataInputStream.readInt();
                do{
                                    Util.println("data : " + dataInputStream.readInt());

                    PointerControl.setPointerMovingAction(dataInputStream.readInt(), 10); 
                    read = dataInputStream.readInt();
                   
                }while(read != -1);
                dataInputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(RecevierThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
