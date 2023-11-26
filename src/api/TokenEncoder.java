package api;

public class TokenEncoder {
    //TODO input 자료형은 System에서 쓰는 형태로 바꾸기
    public static String tokenMoveRobot(Integer x, Integer y) {
        String token = "MRG/r";
        token += x.toString();
        token += ",";
        token += y.toString();
        token += "/\r";
        return token;
    }


    //TODO input 자료형은 System에서 쓰는 형태로 바꾸기
    String tokenSensored(String infomation){
        String token = "UMG/";
        token += infomation;
        token += "\r";
        return token;
    }

}
