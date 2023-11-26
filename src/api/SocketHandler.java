package api;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class SocketHandler {

    public static void apiResolver(String data) throws InterruptedException {
        String cmd = TokenDecoder.parseCmd(data);
        List<String> tokens = TokenDecoder.parseToToken(data);

        //TODO 핸들링 코드 작성
        // System.out 코드들 'HANDLER(tokens)'로 대체할 것
        switch (cmd) {
            //TODO Pause 할 때 이 스레드가 아닌 메인 스레드가 Pause 할 수 있도록 다뤄야함
            case "PSR":
                //tokens = []
                System.out.println("PSR");
                break;
            case "RSR":
                //tokens = []
                System.out.println("RSR");
                break;
            case "ULM":
                //tokens = m5,5/  r0,0/  b1,2/  h2,3/  t4,0/  t3,3/
                System.out.println("ULM");
                System.out.println(tokens);
                break;
            case "UDM":
                //tokens = ["h4,2/"]
                System.out.println("UDM");
                System.out.println(tokens);
                break;
            default:
                //invalid handling
                break;
        }
    }
}
