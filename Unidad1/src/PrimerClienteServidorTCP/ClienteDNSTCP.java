/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author sndmonreal
 * Esta es una implementación del servicio DNS muy simple con sockets
 * la relación de las direcciones IP con los dominios es ficticia
 */
public class ClienteDNSTCP {
     private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private DataInputStream mensaje_entrada;
    private DataOutputStream mensaje_salida;
    private int puerto_servidor;
    private String ip_servidor;

    public ClienteDNSTCP(int puerto, String ip) {
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
        ClienteDNSTCP client = new ClienteDNSTCP(1234, "127.0.0.1");   
    }
    
}
