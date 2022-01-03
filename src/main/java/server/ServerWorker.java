package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerWorker implements Runnable {
    private final ServerSocketChannel serverChannel;
    private final String exit = "Отключился...";
    private final String startServera = "Сервер запустился...";

    public ServerWorker(ServerSocketChannel serverChannel) {
        this.serverChannel = serverChannel;
    }

    @Override
    public void run() {
        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                System.out.println(startServera);
                Server.recordingMessage(Server.NAME_LOG, startServera);

                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) {
                        System.out.println(exit);
                        Server.recordingMessage(Server.NAME_LOG, exit);
                        break;
                    }
                    final String msg = new String(inputBuffer.array(), 0, bytesCount,
                            StandardCharsets.UTF_8);
                    inputBuffer.clear();
                    Server.recordingMessage(Server.NAME_LOG, msg);
                    socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
