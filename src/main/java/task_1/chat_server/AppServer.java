package task_1.chat_server;

import java.io.IOException;
import java.net.ServerSocket;

public class AppServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1300);
            Server server = new Server(serverSocket);
            server.runServer();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}

