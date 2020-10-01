package Lesson3_FileApi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileHistoryChat {
    private static String msgLogChat = "История чата";
    private static File fileHistory = new File("D:\\Java\\java3\\java3_Home_Work\\file_history_chat.txt");
    private static File fileLog = new File("D:\\Java\\java3\\java3_Home_Work\\file_log_chat.txt");
    public static List<String> listLogMsg = new ArrayList<String>(100);

    public static BufferedInputStream in;
    public static BufferedOutputStream out;

    public static void main(String[] args) throws IOException {
//        wrtMsgToLogFile(msgLogChat);
        wrtHistoryMsg (fileHistory);
    }

    public static synchronized void wrtMsgToLogFile (String msg) throws IOException {
        try (FileWriter out = new FileWriter(fileHistory, true)) {
            if (fileHistory.canWrite()) {
                out.write(msg + "\n");
                out.flush();
                out.close();
                System.out.println("запись в файл");
            }
        }
    }

    public static synchronized List<String> wrtHistoryMsg(File fileHistory) throws IOException{
        try {
            BufferedReader reader = new BufferedReader (new FileReader(fileHistory));
            BufferedWriter writer = new BufferedWriter (new FileWriter(fileLog));
            if (fileHistory.canRead()){
                while (reader.ready()) {
                    listLogMsg.add(reader.readLine());
                    System.out.println(listLogMsg);
                }
            }
            if (fileLog.canWrite()){
                for (int i = 0; i < listLogMsg.size(); i++) {
                    if (i >= 100) continue;
                    writer.write(listLogMsg.toString());
                }
            }
            reader.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLogMsg;
    }

}

