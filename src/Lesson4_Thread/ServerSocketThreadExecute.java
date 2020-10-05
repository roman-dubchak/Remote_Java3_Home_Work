package Lesson4_Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketThreadExecute {
    ExecutorService executorService;
//    ServerSocketThread serverSocketThread; // Закоментил чтобы все классы Сокетов не импортировать

    public ServerSocketThreadExecute (){
        executorService = Executors.newFixedThreadPool(10);
        System.out.println("ServerSocketThreadExecute is start");
//        executorService.execute(serverSocketThread); // Закоментил чтобы все классы Сокетов не импортировать
    }

    public void ServerSocketThreadExecuteStop(ExecutorService executorService){
        executorService.shutdown();
        System.out.println("ServerSocketThreadExecute is shutdown");
    }
}

