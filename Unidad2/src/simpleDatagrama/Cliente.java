/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleDatagrama;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author sndmonreal
 */
public class Cliente {

    private DatagramSocket cliente;
    private DatagramPacket recibe, envia;
    private byte[] enviar, recibir;
    private int port = 1234;
    private InetAddress ip_serv;

    public Cliente(String mensaje) {
        try {
             ip_serv= InetAddress.getByName("localhost");
            cliente = new DatagramSocket();
            enviar = mensaje.getBytes();
            
            envia = new DatagramPacket(enviar,enviar.length,ip_serv,port);
            
            cliente.send(envia);
            
            recibe = new DatagramPacket(enviar,enviar.length);
            cliente.receive(recibe);
            System.out.println("datagrama: "+new String(recibe.getData()));
            
            
            cliente.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String aer[]){
        Cliente c = new Cliente("Hola que hace?");
    }
}
