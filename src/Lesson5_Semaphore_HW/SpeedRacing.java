package Lesson5_Semaphore_HW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.*;

public class SpeedRacing {
    private static final Logger logger = Logger.getLogger(SpeedRacing.class.getName());
    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier clb = new CyclicBarrier(CARS_COUNT);
    public static final CountDownLatch startLatch = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch finishLatch = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args)  throws IOException  {

        logger.setLevel(Level.ALL);

        Handler h = new FileHandler("racing_log.log");
        h.setFormatter(new SimpleFormatter());
        h.setLevel(Level.ALL);
        h.setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                if (record.getMessage().startsWith("ВАЖНОЕ ОБЪЯВЛЕНИЕ"))
                    return true;
                return false;
            }
        });
        logger.addHandler(h);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        logger.log(Level.CONFIG, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), clb, startLatch, finishLatch);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            startLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            logger.log(Level.CONFIG, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            finishLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            logger.log(Level.CONFIG, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Car implements Runnable {
    private static int CARS_COUNT; //
    private static CyclicBarrier cbr;
    private static CountDownLatch startLatch;
    private static CountDownLatch finishLatch;

    private static boolean win = true;
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cbr, CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.race = race;
        this.speed = speed;
        this.cbr = cbr;
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            startLatch.countDown();
            cbr.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if(i == race.getStages().size() - 1 && win){
                win = false;
                System.out.println(this.name + " WIN");
            }
        }
        finishLatch.countDown();
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    private static final Semaphore smp = new Semaphore(2);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
