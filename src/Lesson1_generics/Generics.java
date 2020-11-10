package Lesson1_generics;

import java.util.ArrayList;
import java.util.Arrays;

public class Generics {
    public static void main(String[] args) {
        String [] arrStr = {"hello", "world"};
        System.out.println("before: " + arrStr[0] + " ; " + arrStr[1]);
        arrChange(arrStr);
        System.out.println("after: " + arrStr[0] + " ; " + arrStr[1]);

        Integer[] arrIn = {1, 2};
        System.out.println("before: " + arrIn[0] + " ; " + arrIn[1]);
        arrChange(arrIn);
        System.out.println("after: " + arrIn[0] + " ; " + arrIn[1]);

        arrToArrayList(arrStr);
        arrToArrayList(arrIn);
    }

    static <T> void arrChange (T[] array){
        for (int i = 0; i < 1; i++) {
            T obj = array[i];
            array [i] = array [array.length - 1];
            array [array.length - 1] = obj;
            // можно через метод swap
        }
    }

    // можно без дженериков
    static void arrChangeObj (Object[] array){
        for (int i = 0; i < 1; i++) {
            Object obj = array[i];
            array [i] = array [array.length - 1];
            array [array.length - 1] = obj;
            // позже нашел метод swap, который повторяет тот, что я написал))
        }
    }

    static <T> ArrayList arrToArrayList (T[] array) {
//        ArrayList <T> arrayList = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            arrayList.add(array[i]);
//            System.out.println("Arraylist #" + i + " : " + arrayList); // to check
//        }
//        return arrayList;
        return new ArrayList<T>(Arrays.asList(array));
    }
}