package ru.gb.javatwo.network;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketThread extends Thread implements Closeable {

    private final SocketThreadListener listener;
    private final Socket socket;
    private DataOutputStream out;
    private DataOutputStream in;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public SocketThread(SocketThreadListener listener, String name, Socket socket) {
        super(name);
        this.socket = socket;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {

        try(DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())){
            listener.onSocketStart(this, socket);
            this.out = out;
            listener.onSocketReady(this, socket);
            while (!isInterrupted()){
                String msg = in.readUTF();
                listener.onReceiveString(this, socket, msg);
            }
        } catch (IOException e){
            listener.onSocketException(this, e);
        } finally {
            close();
            listener.onSocketStop(this);
        }

    }
    public synchronized boolean sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
            return true;
        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
            return false;
        }
    }

    public synchronized void wrtMsgToLogFile (String msg) {
        try (FileWriter out = new FileWriter("log.txt", true)) {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void close() {
        interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
    }
}
