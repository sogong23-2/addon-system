package api;
import java.util.Arrays;
import java.util.List;

public class SocketHandler {

    public static void apiResolver(String data) {
        //String cmd = TokenDecoder.parseCmd(data);
        String cmd = "PSR";
        //List<String> tokens = TokenDecoder.parseToToken(data);
        List<String> tokens = Arrays.asList();

        switch (cmd) {
            case "PSR":
                //
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
