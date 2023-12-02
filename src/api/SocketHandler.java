package api;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class SocketHandler {

    public static int[][] apiResolver(String data) throws InterruptedException {
        String cmd = TokenDecoder.parseCmd(data);
        List<String> tokens = TokenDecoder.parseToToken(data);

        int[][] ret = new int[50][3];

        //TODO 핸들링 코드 작성
        // System.out 코드들 'HANDLER(tokens)'로 대체할 것
        switch (cmd) {
            //TODO Pause 할 때 이 스레드가 아닌 메인 스레드가 Pause 할 수 있도록 다뤄야함
            case "PSR":
                //tokens = []
                System.out.println("PSR");
                ret[0][0] = -1;
                break;
            case "RSR":
                //tokens = []
                ret[0][0] = -2;
                System.out.println("RSR");
                break;
            case "ULM":
                int cnt = 1;
                for (String token : tokens) {
                    //System.out.println(token);
                    if (token.charAt(0) == 'm') {
                        ret[cnt][0] = token.charAt(1) - '0';
                        ret[cnt][1] = token.charAt(3) - '0';
                        ret[cnt][2] = 'm';
                    }
                    else if (token.charAt(0) == 'r') {
                        ret[cnt][0] = token.charAt(1) - '0';
                        ret[cnt][1] = token.charAt(3) - '0';
                        ret[cnt][2] = 'r';
                    }
                    else if (token.charAt(0) == 'b') {
                        ret[cnt][0] = token.charAt(1) - '0';
                        ret[cnt][1] = token.charAt(3) - '0';
                        ret[cnt][2] = 'b';
                    }
                    else if (token.charAt(0) == 'h') {
                        ret[cnt][0] = token.charAt(1) - '0';
                        ret[cnt][1] = token.charAt(3) - '0';
                        ret[cnt][2] = 'h';
                    }
                    else if (token.charAt(0) == 't') {
                        ret[cnt][0] = token.charAt(1) - '0';
                        ret[cnt][1] = token.charAt(3) - '0';
                        ret[cnt][2] = 't';

                    }
                    //System.out.println(ret[cnt][0] + " " + ret[cnt][1] + " " + ret[cnt][2]);
                    cnt++;
                }
                //System.out.println("ULM" + cnt);
                ret[0][0] = cnt;
                break;
            case "UDM":
                //tokens = ["h4,2/"]
                System.out.println("UDM");
                System.out.println(tokens);
                ret[1][0] = tokens.get(0).charAt(1) - '0';
                ret[1][1] = tokens.get(0).charAt(3) - '0';
                switch (tokens.get(0).charAt(0)) {
                    case 'b':
                        ret[1][2] = 8;
                        break;
                    case 'h':
                        ret[1][2] = 4;
                        break;
                    default:
                        break;
                }
                break;
            default:
                //invalid handling
                break;
        }
        return ret;
    }
}