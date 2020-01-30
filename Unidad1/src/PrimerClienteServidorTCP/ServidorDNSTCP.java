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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author sndmonreal
 * Esta es una implementación del servicio DNS muy simple con sockets
 * la relación de las direcciones IP con los dominios es ficticia
 */
public class ServidorDNSTCP {
    private ServerSocket servidor;
    private Socket cliente;
    private InputStream entrada;
    private OutputStream salida;
    private String ip, protocolo;
    private int puerto;
    private DataInputStream mensajeEntrada;
    private DataOutputStream mensajeSalida;
    private ArrayList<String> mapa;
    
        public ServidorDNSTCP() {
            mapa = new ArrayList();
            mapa.add("127.0.0.1 - localhost");
            mapa.add("145.78.0.1 - www.google.com");
            mapa.add("221.23.43.18 - www.facebook.com");
            mapa.add("156.8.6.67 - www.zacatecas.ipn.mx");
            mapa.add("192.68.33.10 - www.ecosia.com");
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
                    // BUSCAR EN EL MAPA
                        mensaje = buscar(mensaje);
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
      
        public String buscar(String dato){
            String busqueda="";
                for(int i=0;i<mapa.size();i++){
                    busqueda = mapa.get(i);
                    if(busqueda.startsWith(dato)){// debe devolver el dominio
                        busqueda = busqueda.substring(dato.length()+3);
                        break;
                    }else if(busqueda.contains(dato)){ // debe devolver la ip
                        busqueda= busqueda.substring(0,(busqueda.length()-dato.length())-3);
                            break;
                    }else
                        busqueda="no se encontro";
                }
           
            return busqueda;
        }

    public static void main(String[] aed) {
        ServidorDNSTCP serv = new ServidorDNSTCP();
    }
}
