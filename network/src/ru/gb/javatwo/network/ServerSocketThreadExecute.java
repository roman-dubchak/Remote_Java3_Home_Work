package ru.gb.javatwo.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketThreadExecute {
    ExecutorService executorService;
    ServerSocketThread serverSocketThread;

    public ServerSocketThreadExecute (){
        executorService = Executors.newFixedThreadPool(10);
        System.out.println("ServerSocketThreadExecute is start");
        executorService.execute(serverSocketThread);
    }

    public void ServerSocketThreadExecuteStop(ExecutorService executorService){
        executorService.shutdown();
        System.out.println("ServerSocketThreadExecute is shutdown");
    }
}
