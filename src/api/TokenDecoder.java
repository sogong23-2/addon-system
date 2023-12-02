package api;

import java.util.ArrayList;
import java.util.List;

public class TokenDecoder {
    public static String parseCmd(String data) {
        return data.substring(0, data.indexOf('/'));
    }

    public static List<String> parseToToken(String data) {
        List<String> tokens = new ArrayList<>();
        int currentIndex = 4;

        while (currentIndex < data.length()) {
            int nextIndex = data.indexOf('/', currentIndex);
            if (nextIndex != -1) {
                String substring = data.substring(currentIndex, nextIndex + 1);
                tokens.add(substring);
                currentIndex = nextIndex + 1;
            } else {
                break;
            }
        }

        return tokens;
    }
}

