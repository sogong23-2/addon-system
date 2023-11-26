package api;

public class TokenEncoder {
    //TODO input 자료형은 System에서 쓰는 형태로 바꾸기
    String tokenMoveRobot(Integer x, Integer y) {
        String token = "URG/";
        token += x.toString();
        token += ",";
        token += y.toString();
        token += "\r";
        return token;
    }


    //TODO input 자료형은 System에서 쓰는 형태로 바꾸기
    String tokenSensored(String infomation){
        String token = "UDG/";
        token += infomation;
        token += "\r";
        return token;
    }

}
