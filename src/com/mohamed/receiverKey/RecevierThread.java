/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohamed.receiverKey;

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
                String charAsInt = dataInputStream.readUTF();
                System.out.println(charAsInt);
                dataInputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(RecevierThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
