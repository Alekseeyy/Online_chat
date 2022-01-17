package server;

import settings.Utils;

import java.io.FileWriter;
import java.io.IOException;

public class Server {
    static final String NAME_LOG = "src//main//java//server//file.log";

    public static void main(String[] args) {
            Utils.createFile(NAME_LOG);
            System.out.println("Сетевой Чат");

            ServerWorker server = new ServerWorker();
            new Thread(server).start();
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
