package settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final String FILE_NAME = "settings.txt";

    private static final Gson gson = createGson();
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    public static Settings getSettings() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return gson.fromJson(reader.readLine(), Settings.class);
        }
    }

    public static String getTime() {
        final LocalDateTime time = LocalDateTime.now();
        return time.format(DATE_TIME_FORMATTER);
    }
}
