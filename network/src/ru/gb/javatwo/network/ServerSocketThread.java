package ru.gb.javatwo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerSocketThread extends Thread{
    private final int port;
    private final int timeout;

    // HW4 Execute var 1
    ExecutorService executorService = Executors.newCachedThreadPool();

    private final ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.port = port;
        this.timeout = timeout;
        this.listener = listener;
        start();
        // HW4 Execute var 1
        ServerSocketThread socketThread = this;

        // HW4 Execute var 1
        executorService.execute(new Runnable() {
        @Override
        public void run () {
            listener.onServerStart(socketThread);
            try (ServerSocket server = new ServerSocket(port)) {
                server.setSoTimeout(timeout); // устанавливаем таймаут
                listener.onServerSocketCreated(socketThread, server);
                while (!isInterrupted()) {
                    Socket client;
                    try {
                        client = server.accept();
                    } catch (SocketTimeoutException e) {
                        listener.onServerTimeout(socketThread, server);
                        continue; // возврат в управляющую конструкцию while
                    }
                    listener.onSocketAccepted(socketThread, server, client);
                }
            } catch (IOException e) {
                listener.onServerException(socketThread, e);
            } finally {
                listener.onServerStop(socketThread);
            }
        }
        });
    }
}