package client;

import server.Server;
import settings.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client {
    public static final String NAME_LOG = "src//main//java//client//file.log";

    public static void main(String[] args) throws IOException {
        final String HOST = Utils.getSettings().getHost();
        final int PORT = Utils.getSettings().getPort();

        Server.createFile(NAME_LOG);

        InetSocketAddress socketAddress = new InetSocketAddress(HOST, PORT);
        final SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(socketAddress);

        new ClientWorker(socketChannel).start();

//        int i = 1;
//        while (i < 3) {
//            new ClientWorker(socketChannel).start();
//            i++;
//        }
    }
}
