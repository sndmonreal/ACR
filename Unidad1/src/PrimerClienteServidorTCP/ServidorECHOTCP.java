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
public class ServidorECHOTCP {

    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;

    public ServidorECHOTCP() {
        try {
            puerto = 1234;
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor a la escucha...");
            while (true) {
                cliente = servidor.accept();
                InetAddress ip_cliente = cliente.getInetAddress();
                System.out.println("Se conecto: " + ip_cliente.getHostAddress() + ":" + cliente.getPort());
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();
                mensajeEntrada = new DataInputStream(entrada);
                mensajeSalida = new DataOutputStream(salida);
                String mensaje;
                while (!(mensaje = mensajeEntrada.readUTF()).equals("ADIOS")) {
                    // RECIBIR MENSAJE DEL CLIENTE
                    System.out.println(mensaje);
                    // enviar mensaje al cliente
                    mensajeSalida.writeUTF(mensaje);
                }
                System.out.println("Se fue " + ip_cliente.getHostAddress() + ":" + cliente.getPort());
                // cerrar socket cliente
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] aed) {
        ServidorECHOTCP serv = new ServidorECHOTCP();
    }
}
