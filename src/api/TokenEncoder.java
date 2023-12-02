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
    public static String tokenSensored(int x, int y, int value){
        String token = "UMG/";
        token += pos_to_information(x, y, value);
        token += "\r";
        return token;
    }

    public static String pos_to_information(int x, int y, int value)
    {
        String v = "";
        if (value == 4) v = "h";
        else if (value == 8) v = "b";

        String information = "";
        information += v;
        information += x;
        information += ",";
        information += y;
        information += "/";
        return information;
    }

}