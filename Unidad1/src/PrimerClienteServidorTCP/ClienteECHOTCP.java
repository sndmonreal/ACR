/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author sndmo
 */
public class ClienteECHOTCP {

    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensaje_entrada;
    private DataOutputStream mensaje_salida;
    private int puerto_servidor;
    private String ip_servidor;

    public ClienteECHOTCP(int puerto, String ip) {
        try {
            this.puerto_servidor = puerto;
            this.ip_servidor = ip;
            cliente = new Socket(ip_servidor, puerto_servidor);
            entrada = cliente.getInputStream();
            salida = cliente.getOutputStream();
            // ENVIAR MENSAJE AL SERVIDOR
            mensaje_salida = new DataOutputStream(salida);
            mensaje_entrada = new DataInputStream(entrada);
           
            Scanner sc = new Scanner(System.in);
           
            String mensaje;
            
            while (!(mensaje = sc.nextLine()).equals("ADIOS")) {
                
                mensaje_salida.writeUTF(mensaje);
                // RECIBIR MENSAJE DEL SERVIDOR
                System.out.println(mensaje_entrada.readUTF());
                
            }
            mensaje_salida.writeUTF("ADIOS");
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] aed) {
        ClienteECHOTCP client = new ClienteECHOTCP(1234, "127.0.0.1");   
    }
}
