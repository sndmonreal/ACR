/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketsNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 *
 * @author sndmonreal
 * Servicio de transferencia de archivos utilizando Java NIO
 * el servidor recibe un archivo del cliente y lo escribe
 * en el constructor recibe el nombre para crear el nuevo archivo con la información recibida.
 */
public class Servidor {

    private ByteBuffer buffer;
    private ServerSocketChannel serv;
    private SocketChannel cliente;

    public Servidor(String archivo) {
        try {
            serv = ServerSocketChannel.open();
            serv.socket().bind(new InetSocketAddress(1234));
            serv.configureBlocking(false);
            cliente = serv.accept();
            cliente.configureBlocking(false);
            System.out.println("Se conectó:  " + cliente.getRemoteAddress());
            Path path = Paths.get(archivo);
            FileChannel fileChannel = FileChannel.open(path,
                    EnumSet.of(StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING,
                            StandardOpenOption.WRITE)
            );
            buffer = ByteBuffer.allocate(1024);
            while (cliente.read(buffer) > 0) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
            fileChannel.close();
            System.out.println("Archivo recibido");
            cliente.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String arfs[]) throws IOException {
            Servidor s = new Servidor("archivo_copia.txt");
    }
}
