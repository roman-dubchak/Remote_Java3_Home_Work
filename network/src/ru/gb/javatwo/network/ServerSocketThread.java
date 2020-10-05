package ru.gb.javatwo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class ServerSocketThread extends Thread{
    private final int port;
    private final int timeout;

    private final ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.port = port;
        this.timeout = timeout;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        listener.onServerStart(this);
        try (ServerSocket server = new ServerSocket(port)) {
            server.setSoTimeout(timeout); // устанавливаем таймаут
            listener.onServerSocketCreated(this, server);
            while (!isInterrupted()) {
                Socket client;
                try {
                     client = server.accept();
                }catch (SocketTimeoutException e){
                    listener.onServerTimeout(this, server);
                    continue; // возврат в управляющую конструкцию while
                }
                listener.onSocketAccepted(this, server, client);
            }
        } catch (IOException e){
            listener.onServerException(this, e);
        } finally {
            listener.onServerStop(this);
        }
    }
}