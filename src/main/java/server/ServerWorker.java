package server;

import settings.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerWorker implements Runnable {
    private final String EXIT = "Отключился...";
    private final String START_SERVERA = "Сервер запустился...";

    @Override
    public void run() {
        try {
            final String host = Utils.getSettings().getHost();
            final int port = Utils.getSettings().getPort();

            final ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(host, port));

            while (true) {
                try (SocketChannel socketChannel = serverChannel.accept()) {
                    final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                    System.out.println(START_SERVERA);
                    Server.recordingMessage(Server.NAME_LOG, START_SERVERA);

                    while (socketChannel.isConnected()) {
                        int bytesCount = socketChannel.read(inputBuffer);
                        if (bytesCount == -1) {
                            System.out.println(EXIT);
                            Server.recordingMessage(Server.NAME_LOG, EXIT);
                            break;
                        }
                        final String msg = new String(inputBuffer.array(), 0, bytesCount,
                                StandardCharsets.UTF_8);
                        inputBuffer.clear();
                        Server.recordingMessage(Server.NAME_LOG, msg);
                        socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Server.recordingMessage(Server.NAME_LOG, e.getMessage());
        }
    }
}
