package server;

import settings.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Server {
    public static final String NAME_LOG = "src//main//java//server//file.log";

    public static void main(String[] args) throws IOException {
        final String HOST = Utils.getSettings().getHost();
        final int PORT = Utils.getSettings().getPort();

        try {
            createFile(NAME_LOG);
            System.out.println("Сетевой Чат");

            final ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(HOST, PORT));

            ServerWorker server = new ServerWorker(serverChannel);
            new Thread(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String NAME) {
        String newLog = "Файл file.log успешно создан";

        File logFile = new File(NAME);
        try {
            if (logFile.createNewFile()) {
                System.out.println(newLog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recordingMessage(String NAME, String str) {
        try (FileWriter writer = new FileWriter(NAME, true)) {
            writer.write(str + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
