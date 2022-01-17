package client;

import server.Server;
import settings.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientWorker extends Thread {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        try {
            final String host = Utils.getSettings().getHost();
            final int port = Utils.getSettings().getPort();

            InetSocketAddress socketAddress = new InetSocketAddress(host, port);
            final SocketChannel socketChannel = SocketChannel.open();

            try (scanner; socketChannel) {
                socketChannel.connect(socketAddress);

                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                String name = getClientName();
                String newClient = name + ": вошёл(а) в чат";
                System.out.println(newClient);
                Server.recordingMessage(Client.NAME_LOG, newClient);

                while (true) {
                    System.out.println(name + ", введите сообщение или введите команду '/exit' для выхода:");
                    String newMessage = scanner.nextLine();
                    if ("/exit".equals(newMessage)) {
                        String exit = name + ": покинул(а) чат";
                        System.out.println(exit);
                        Server.recordingMessage(Client.NAME_LOG, exit);
                        break;
                    }
                    Message message = new Message(name, newMessage);
                    String message21 = message.toString();
                    socketChannel.write(ByteBuffer.wrap(message21.getBytes(StandardCharsets.UTF_8)));
                    Server.recordingMessage(Client.NAME_LOG, message21);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int bytesCount = socketChannel.read(inputBuffer);
                    System.out.println(new String(inputBuffer.array(), 0, bytesCount,
                            StandardCharsets.UTF_8));
                    inputBuffer.clear();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Server.recordingMessage(Client.NAME_LOG, e.getMessage());
        }
    }

    private String getClientName() {
        System.out.println("Добро пожаловать! Введите имя:");
        return scanner.nextLine();
    }
}
