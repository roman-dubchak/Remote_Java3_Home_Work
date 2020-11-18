package Lesson3_FileApi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileApi {
    public static void main(String[] args) throws Exception {

        Book book = new Book("Jungle Book Path I");
        Student st1 = new Student(1, "Bob", book);
        Student st2 = new Student(2, "Bob", book);

       ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("student.ser"));
//        book.title = "Jungle Path II";
       os.writeObject(st1);
       book.title = "Jungle Path II"; // название не изенится, т.к. уже записана в st1
        os.reset(); // сбрасывает галочки в схеме, перезапишет book с новым названием
       os.writeObject(st2);
       os.close();

       ObjectInputStream is = new ObjectInputStream(new FileInputStream("student.ser"));
       Student s1 = (Student)is.readObject();
       Student s2 = (Student)is.readObject();
       s1.info();
       s2.info();
       is.close();

       System.out.printf("%s\n%s\n", s1.book.title, s2.book.title);
    }

    private static File getFileExample() {
        File file = new File("12");
        System.out.println(file.length());
        try {
            String filePath = file.getCanonicalPath();
            System.out.println(filePath);
        } catch (Exception e){
            e.getMessage();
        }
        return file;
    }

    private static void filterExample(File file) {
        System.out.println(Arrays.toString(file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("1");
            }
        })));

        System.out.println(Arrays.toString(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.length() < 233;
            }
        })));
    }

    private static void ISRExample() {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("1.txt"), StandardCharsets.UTF_8)) {
            int x;
            while ((x = isr.read()) != -1){
                System.out.print((char) x);
            }
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public static void FOSExample() {
        String str = "World\n";
        byte [] strByte = str.getBytes();
        try (FileOutputStream out = new FileOutputStream("1.txt", true) ){
            for (int i = 0; i < 20; i++) {
                out.write(strByte);
            }
        } catch (IOException e){
            e.getStackTrace();
        }
    }
}
