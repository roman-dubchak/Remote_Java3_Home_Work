package Lesson3_FileApi;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void info (){
        System.out.println(id + " " +name);
    }
}
