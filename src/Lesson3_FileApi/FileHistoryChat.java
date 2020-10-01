package Lesson3_FileApi;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileHistoryChat {
    private static String msgLogChat = "История чата";
    private static File fileHistory = new File("D:\\Java\\java3\\java3_Home_Work\\file_history_chat.txt");
    public static List<String> listLogMsg = new ArrayList<String>();

    public static BufferedInputStream in;
    public static BufferedOutputStream out;

    public static void main(String[] args) throws IOException {
        wrtMsgToLogFile(msgLogChat);
        wrtHistoryMsg (fileHistory);

    }


    public static synchronized void wrtMsgToLogFile (String msg) throws IOException {
        try (FileWriter out = new FileWriter(fileHistory, true)) {
            if (fileHistory.canWrite()) {
                out.write(msg + "\n");
                out.flush();
            }
        }
    }

    public static synchronized List<String> wrtHistoryMsg(File fileHistory) throws IOException{
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileHistory));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileHistory));
            if (fileHistory.canRead()){
                int msg = in.read();
                listLogMsg.add(out.toString());
            }
        } finally {
            in.close();
            out.flush();
            out.close();
        }
        return listLogMsg;
    }

}

