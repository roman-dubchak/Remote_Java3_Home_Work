package Lesson1_generics;

public abstract class Fruit {
    private double weight;

    public double getWeight(){
        return this.weight;
    }

    public Fruit(double weight) {
        this.weight = weight;
    }
}
