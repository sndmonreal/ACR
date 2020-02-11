/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envioArchivos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sndmonreal
 */
public class FileServer {

    private ServerSocket servidor;
    private Socket cliente;
    private OutputStream salida;
    private int puerto=1234;
    private String file_to_send; 
    private FileInputStream fis ;
    private  BufferedInputStream bis;
    
    public FileServer(String file) {
       file_to_send = file;
        try {
            servidor = new ServerSocket(puerto);
            while (true) {
                System.out.println("Servidor esperando...");
                try {
                    cliente = servidor.accept();
                    System.out.println("Acepta conexion de: " + cliente);
                    // crear objeto file para crear flujo de lectura
                    File myFile = new File(file_to_send);
                    byte[] mybytearray = new byte[(int) myFile.length()]; //calcular tamaño del archivo a enviar
                    fis = new FileInputStream(myFile); // crear objeto para abrir archivo
                    bis = new BufferedInputStream(fis);// crear objeto para leer archivo
                    bis.read(mybytearray, 0, mybytearray.length);
                    salida = cliente.getOutputStream();
                    System.out.println("Enviando " + file_to_send + "(" + mybytearray.length + " bytes)");
                    salida.write(mybytearray, 0, mybytearray.length);
                    salida.flush();
                    System.out.println("Listoo.");
                }catch(IOException e){}
                finally {
                    if (bis != null) {
                        bis.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                    if (cliente != null) {
                        cliente.close();
                    }
                }
            }
        }catch(IOException e){} finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException ex) {
                }
            }
        }
    }
    
    public static void main(String args[]){
        FileServer serv = new FileServer("registro.pdf");
        
    }
}
