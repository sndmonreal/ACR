/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author sndmonreal
 * lectura de un archivo de texto utilizando Channels y Buffers de Java NIO
 * 
 */
public class LectorArchivo {

    private FileInputStream fis;
    private FileChannel canal;// permite tanto lectura como escritura en el archivo
    private ByteBuffer buffer;// ejecuta operaciones de lectura y escritura de datos sobre el canal.
    private String archivo;

    public LectorArchivo() {
        try {
            archivo = "datos.txt";
            fis = new FileInputStream(archivo);
            canal = fis.getChannel();
            buffer = ByteBuffer.allocate(48);
            int bytesleidos = canal.read(buffer);//lee una cantidad de bytes directamente del archivo
            while(bytesleidos != -1){// se hace el ciclo mientras haya datos que leer del archivo
                buffer.flip();// se prepaa el buffer para la lectura
                // en cada lectura, se imprime el contenido del buffer elemento por elemento
                while(buffer.hasRemaining()){
                    System.out.print((char)buffer.get());
                }
                buffer.clear();// se limpia el buffer para volver a llenarlo (escritura)
                bytesleidos = canal.read(buffer);
            }
            fis.close();
            System.out.println();
        } catch (IOException e) {

        }
    }
    
    public static void main(String ards[]){
        LectorArchivo lee = new LectorArchivo();
    }

}
