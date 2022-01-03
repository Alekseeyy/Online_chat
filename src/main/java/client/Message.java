package client;

import settings.Utils;

public class Message {
    private final String author;
    private final String content;

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Utils.getTime() + " " + author + ": " + content;
    }
}
