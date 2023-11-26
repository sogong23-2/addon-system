package api;
import java.util.Arrays;
import java.util.List;

public class SocketHandler {

    public static void apiResolver(String data) {
        String cmd = TokenDecoder.parseCmd(data);
        List<String> tokens = TokenDecoder.parseToToken(data);

        switch (cmd) {
            case "PSR":
                System.out.println("PSR");
                break;
            case "UMG":
                //
                break;
            default:
                //invalid handling
                break;
        }
    }
}
