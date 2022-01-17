package client;

import settings.Utils;

public class Client {
    static final String NAME_LOG = "src//main//java//client//file.log";

    public static void main(String[] args) {
        Utils.createFile(NAME_LOG);

        new ClientWorker().start();

//        int i = 1;
//        while (i < 3) {
//            new ClientWorker().start();
//            i++;
//        }
    }
}
