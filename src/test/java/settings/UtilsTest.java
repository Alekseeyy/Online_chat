package settings;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getSettings() throws IOException {
        String result1 = "127.0.0.1";
        int result2 = 25333;

        String actual1 = Utils.getSettings().getHost();
        int actual2 = Utils.getSettings().getPort();

        assertEquals(result1, actual1);
        assertEquals(result2, actual2);
    }

    @Test
    void getTime() {
        LocalDateTime time = LocalDateTime.now();
        String result = time.format(Utils.DATE_TIME_FORMATTER);

        String actual = Utils.getTime();

        assertEquals(result, actual);
    }
}