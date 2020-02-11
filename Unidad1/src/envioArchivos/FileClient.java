/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envioArchivos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author sndmonreal
 */
public class FileClient {

    private int socket_port = 1234;  // se establece el puerto al que se conectará con el servidor
    private String server_ip = "127.0.0.1"; // es la dirección del servidor
    private String file_to_received = "registro(1).pdf"; //se asigna un nombre al archivo que se recibirá.
    private int file_size = 6022386; // se asigna un tamaño para el archivo
    

 public FileClient(){
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(server_ip, socket_port);
            System.out.println("Conectando...");

            byte[] mybytearray = new byte[file_size];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(file_to_received);
            bos = new BufferedOutputStream(fos);
            // lee desde el servidor y lo almacena en el arreglo de bytes
            bytesRead = is.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;// es necesario saber cuántos bytes ha leido para saber cuando termina
            // este ciclo permite leer cada paquete de bytes que envia el servidor hasta que haya terminado de 
            // enviar el archivo completo.
            do {
                bytesRead
                        = is.read(mybytearray, current, (mybytearray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            } while (bytesRead > -1);
            
            //se pasa lo del arreglo de bytes al archivo
            bos.write(mybytearray, 0, current);
            bos.flush();
            System.out.println("Archivo " + file_to_received
                    + " descargado (" + current + " bytes leidos)");
        } catch(IOException e){}finally {
            if (fos != null) {
                try{
                fos.close();
                 } catch(IOException e){}
            }
            if (bos != null) {
                try{
                bos.close();
                 } catch(IOException e){}
            }
            if (sock != null) {
                try{
                sock.close();
                 } catch(IOException e){}
            }
        }
    }
 
    public static void main(String[] args)  {
        FileClient fc = new FileClient();
    }
}
