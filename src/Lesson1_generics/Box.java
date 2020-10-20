package Lesson1_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> fruits;

    private Box() {
    fruits = new ArrayList<>();
}

    private List<T> getFruits() {
        return fruits;
    }
    private double getWeight() {
    return fruits.stream().mapToDouble(Fruit::getWeight).sum();
    }

    private void clearBox(){
        fruits.clear();
    }

    private void putBox(T ...fruit){
        fruits.addAll(Arrays.asList(fruit));
    }

    private void putAll(Box<T> box){
        fruits.addAll(box.getFruits());
    }

    public <E extends Fruit> boolean compare (Box<E> box){
        return getWeight() == box.getWeight();

    }
//    boolean find (List<Apple> listApple, Fruit fruits){
//        for(Fruit f : listApple){
//            if (f.)
//        }
//    }
}
