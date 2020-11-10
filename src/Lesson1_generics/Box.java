package Lesson1_generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> container;

    private Box(T... fruits) {
    container = new ArrayList<>(Arrays.asList(fruits));
}
    private double getWeight() {
    // return container.stream().mapToDouble(Fruit::getWeight).sum(); // Ваиант со Stream API
        if (container.size() == 0) return 0.0f;

        double w = 0.0f;
        for (int i = 0; i < container.size(); i++) {
            w += container.get(i).getWeight();
        }
        return w;
    }

    public boolean compare (Box<?> another){
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001f;
    }

    private void addFruit(T ...fruit){
        container.addAll(Arrays.asList(fruit));
    }

    private void putAll(Box<? super T> box){ // перекинуть лмбо из яблок в яблок, либо из яблок в родителя
        box.container.addAll(this.container);
        this.container.clear();
    }
}
