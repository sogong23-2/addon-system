package add_on;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DealingMessage {
    public static final int SAFE = 0;
    public static final int HAZARD = 4;
    public static final int CRITICAL = 8;
    public static map map;

    void ULM(String[] message) {
        for (int i = 1; i < message.length; i++) {
            String[] tmpMessage = message[i].substring(1).split(",");
            int n = Integer.parseInt(tmpMessage[0]);
            int m = Integer.parseInt(tmpMessage[1]);
            switch (message[i].charAt(0)) {
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

    void UDM(String[] message) {
        for (int i = 1; i < message.length; i++) {
            String[] tmpMessage = message[i].substring(1).split(",");
            int n = Integer.parseInt(tmpMessage[0]);
            int m = Integer.parseInt(tmpMessage[1]);
            switch (message[i].charAt(0)) {
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

    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public void addMessage(String message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startListening() {
        Thread listenerThread = new Thread(() -> {
            while (true) {
                String[] message = getMessage().split("/");

                switch (message[0]) {
                    case "ULM":
                        ULM(message);
                        break;
                    case "UDM":
                        UDM(message);
                        break;
                    case "PSR":
                        System.out.println("PSR 메시지를 받았습니다. 프로그램을 잠시 정지합니다.");
                        ChildThread childThread = new ChildThread(this); // DealingMessage 인스턴스를 넘겨줌
                        childThread.start();
                        break;
                    default:
                        break;
                }
            }
        });
        listenerThread.start();
    }

    public static void main(String[] args) {
        DealingMessage dm = new DealingMessage();
        dm.startListening();

        // 다른 시스템에서 메시지를 받아서 큐에 넣는 로직을 구현해야 한다.
        // 소켓 통신을 이용하여 메시지를 받고 addMessage 메서드를 호출하여 큐에 메시지를 넣어야 한다.
    }
}

class ChildThread extends Thread {
    private DealingMessage dealingMessage;

    // 생성자를 통해 DealingMessage 인스턴스를 받음
    public ChildThread(DealingMessage dealingMessage) {
        this.dealingMessage = dealingMessage;
    }

    @Override
    public void run() {
        System.out.println("자식 스레드가 실행됩니다.");

        while (true) {
            String message = dealingMessage.getMessage(); // RSR 메시지를 받기 위해 큐에서 메시지 확인
            if (message != null && message.equals("RSR")) {
                System.out.println("RSR 메시지를 받았습니다. 자식 스레드를 종료합니다.");
                break;
            }
        }
    }
}