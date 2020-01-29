/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;

import java.io.*;
import java.net.*;

/**
 *
 * @author sndmo
 */
public class ServidorTCP {

    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;

    public ServidorTCP() {
        try {
            puerto = 1234;
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor a la escucha...");
            while (true) {
                cliente = servidor.accept();
                InetAddress ip_cliente = cliente.getInetAddress();
                System.out.println("Se conecto: " + ip_cliente.getHostAddress()+":"+cliente.getPort());
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();
                // enviar mensaje al cliente
                mensajeSalida = new DataOutputStream(salida);
                mensajeSalida.writeUTF("Bienvenido cliente");
                // cerrar socket cliente
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    public static void main(String[] aed) {
        ServidorTCP serv = new ServidorTCP();
        //ClienteTCP cliente = new ClienteTCP(1234,"127.0.0.1");
    }
}
