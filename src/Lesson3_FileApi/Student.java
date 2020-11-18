package Lesson3_FileApi;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 2134134819347L;

    int id;
    String name;Book book;

    public Student(int id, String name, Book book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }

    public Student() {
    }

    public void info (){
        System.out.println(id + " " +name + " " + book.title);
    }
}
