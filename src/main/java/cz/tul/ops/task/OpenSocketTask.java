package cz.tul.ops.task;

import cz.tul.ops.conf.ApplicationConfig;
import cz.tul.ops.log.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class OpenSocketTask implements Task {

    public static int PORT_COUNTER = ApplicationConfig.getPortCounter();

    private List<ServerSocket> serverSocketList;
    private int socketCount = 3;

    public OpenSocketTask() {
    }

    public OpenSocketTask(int socketCount) {
        this.socketCount = socketCount;
    }


    @Override
    public void setApplicationConfig() {
    }

    @Override
    public void run() {
        serverSocketList = new ArrayList<>();
        for (int i = 0; i < socketCount; i++) {
                Thread thread = new Thread(() -> {
                        try {
                            ServerSocket serverSocket = new ServerSocket(PORT_COUNTER++);
                            serverSocketList.add(serverSocket);
                            serverSocket.accept();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                );
            thread.start();
        }

        while(true) {
            Logger.debug(String.format("There are %d active sockets.", socketCount));

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.SOCKET;
    }

    @Override
    public void onClose() {
        Logger.debug("Closing socket task.");
        try {
            close();
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
    }

    private void close() throws IOException {
        if(serverSocketList == null  || serverSocketList.isEmpty()) {
            return;
        }

        for (ServerSocket serverSocket : serverSocketList) {
            serverSocket.close();
        }
    }
}
