package Lesson3_FileApi;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FileApi {
    public static void main(String[] args) {
        File file = new File("12");
        System.out.println(file.length());
        try {
            String filePath = file.getCanonicalPath();
            System.out.println(filePath);
        } catch (Exception e){
            e.getMessage();
        }
        System.out.println(Arrays.toString(file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("1");
            }
        })));
        System.out.println(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.length() < 233;
            }
        }));

    }
}
