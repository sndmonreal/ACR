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
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author sndmonreal Servicio de transferencia de archivos utilizando Java NIO
 * El cliente enviara un archivo al servidor en el constructor se recibe el
 * nombre del archivo a enviar
 */
public class Cliente {

    private ByteBuffer buffer;
    private SocketChannel cliente;
    private FileChannel channel;
    private Path path;
    private String host = "127.0.0.1";
    private int puerto = 1234;
    private InetSocketAddress addr;

    public Cliente(String archivo) {
        try {
            addr = new InetSocketAddress(host, puerto);
            cliente = SocketChannel.open();
            cliente.configureBlocking(false);

            cliente.connect(addr);
            buffer = ByteBuffer.allocate(1024);
            path = Paths.get(archivo);
            channel = FileChannel.open(path);

            while (channel.read(buffer) > 0) {
                buffer.flip();
                cliente.write(buffer);
                buffer.clear();
            }
            channel.close();
            System.out.println("Archivo enviado");
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        Cliente c = new Cliente("archivo.txt");

    }
}
