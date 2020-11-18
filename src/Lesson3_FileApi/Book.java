package Lesson3_FileApi;

import java.io.Serializable;

public class Book implements Serializable {
    String title;

    public Book(String title) {
        this.title = title;
    }
}
