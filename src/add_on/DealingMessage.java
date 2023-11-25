package add_on;
import java.lang.String;
import add_on.map;

public class DealingMessage {

    public static final int SAFE = 0;
    public static final int HAZARD = 4;
    public static final int CRITICAL = 8;
    map map;

    void CheckFun(String msg){
        String[] message = msg.split("/");
        switch (message[0]){
            case "ULM":
                ULM(message);
                break;
            case "UDM":
                UDM(message);
                break;
            case "PSR":
                PSR();
                break;
            case "RSR":
                RSR();
                break;
        }
    }
    void ULM(String[] message){
        for(int i = 1; i < message.length; i++){
            String[] tmpMessage = message[i].substring(1).split(",");
            int n = Integer.parseInt(tmpMessage[0]);
            int m = Integer.parseInt(tmpMessage[1]);
            switch (message[i].charAt(0)){
                case 'm':
                    map = new map(n, m);
                    break;
                case 'r':
                    map.robotX = n;
                    map.robotY = m;
                    break;
                case 'b':
                    map.insertValue(n, m, CRITICAL);
                    break;
                case 'h':
                    map.insertValue(n, m, HAZARD);
                    break;
                default:
                    break;
            }
        }
    }
    void UDM(String[] message){
        for(int i = 1; i < message.length; i++){
            String[] tmpMessage = message[i].substring(1).split(",");
            int n = Integer.parseInt(tmpMessage[0]);
            int m = Integer.parseInt(tmpMessage[1]);
            switch (message[i].charAt(0)){
                case 'b':
                    map.insertValue(n, m, CRITICAL);
                    break;
                case 'h':
                    map.insertValue(n, m, HAZARD);
                    break;
                default:
                    break;
            }
        }
    }
    void PSR(){
        ChildThread childThread = new ChildThread();
        childThread.start();
        try {
            childThread.join(); // 자식 스레드가 종료될 때까지 기다림
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void RSR(){

    }


    public static void main(String[] args) {
        ParentThread parentThread = new ParentThread();
        parentThread.start();
    }
}


class ParentThread extends Thread {

    @Override
    public void run() {
        DealingMessage dm = new DealingMessage();
        dm.CheckFun(message);
    }
}

class ChildThread extends Thread {

    @Override
    public void run() {
        System.out.println("자식 스레드 시작");

        try {
            while(message != "RSR")
                Thread.sleep(3000); // 3초 동안 sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("자식 스레드 종료");
    }
}