/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleDatagrama;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author sndmonreal
 */
public class Servidor {
    private DatagramSocket servidor;
    private DatagramPacket recibe,envia;
    private byte []enviar, recibir;
    private int port=1234;
    
    public Servidor(){
        try{
            servidor = new DatagramSocket(port);
            recibir = new byte[100];
            
            recibe = new DatagramPacket(recibir,recibir.length);
            
            servidor.receive(recibe);
            System.out.println("datagrama: "+new String(recibe.getData()));
            System.out.println("IP:"+recibe.getAddress().getHostName()+
                    " puerto: "+recibe.getPort());
            
            envia = new DatagramPacket(recibir,recibir.length,recibe.getAddress(),recibe.getPort());
            servidor.send(envia);
            
            servidor.close();
        }catch(SocketException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
   public static void main(String ae[]){
       Servidor s = new Servidor();
   } 
}
