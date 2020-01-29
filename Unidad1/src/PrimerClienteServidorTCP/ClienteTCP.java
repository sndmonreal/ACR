/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;

import java.io.*;
import java.net.*;

/**
 * @author sndmo
 */
public class ClienteTCP {

    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensaje_entrada;
    private DataOutputStream mensaje_salida;
    private int puerto_servidor;
    private String ip_servidor;

    public ClienteTCP(int puerto, String ip) {
        try {
            this.puerto_servidor = puerto;
            this.ip_servidor = ip;
            cliente = new Socket(ip_servidor, puerto_servidor);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            mensaje_entrada = new DataInputStream(entrada);
            System.out.println(mensaje_entrada.readUTF());
            
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String []aed){
        ClienteTCP client = new ClienteTCP(1234,"127.0.0.1");
        ClienteTCP client2 = new ClienteTCP(1234,"127.0.0.1");    
    }
}
