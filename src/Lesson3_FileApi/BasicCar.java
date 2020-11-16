package Lesson3_FileApi;

public interface BasicCar {
    void drive();
    void xenonOn();
    void xenonOff();
}

interface BasicCarRus {
    void drive();
    void basicLightOn();
    void basicLightOff();
}

class AudiA3 implements BasicCar{
    final public int light = 100;

    @Override
    public void drive() {
        System.out.println("AudiA3 rides!");
    }

    @Override
    public void xenonOn() {
        System.out.println("AudiA3 xenon on");
    }

    @Override
    public void xenonOff() {
        System.out.println("AudiA3 xenon off!");
    }
}

class AudiA3Rus implements BasicCarRus{
    final public int light = 50;

    @Override
    public void drive() {
        System.out.println("AudiA3Rus поехал!");
    }

    @Override
    public void basicLightOn() {
        System.out.println("AudiA3Rus вкл свет!");
    }

    @Override
    public void basicLightOff() {
        System.out.println("AudiA3Rus выкл свет!");
    }
}

class AdapterCar implements BasicCar{
    BasicCarRus basicCarRus;

    AdapterCar(BasicCarRus basicCarRus){
        this.basicCarRus = basicCarRus;
    }

    @Override
    public void drive() {
        basicCarRus.drive();
    }

    @Override
    public void xenonOn() {
        basicCarRus.basicLightOn();
    }

    @Override
    public void xenonOff() {
        basicCarRus.basicLightOff();
    }
}

class CentralProcessor {
    private BasicCar car;

    public CentralProcessor(BasicCar car){
        this.car = car;
    }
    public void work(){
        car.drive();
        car.xenonOn();
        car.xenonOff();
    }
}

class Example{
    public static void main(String[] args) {
        // создаем объект машины
        BasicCar audiA3 = new AudiA3();
        // создаем объект процеесор и передаем ему в управелние объект машины
        CentralProcessor cp = new CentralProcessor(audiA3);
        // процесс управляет работой машины
        cp.work();

        // создаем адаптер и перелаем в него машину Rus
        AdapterCar audiA3Rus = new AdapterCar(new AudiA3Rus());
        // создаем объект процеесор и передаем ему адаптер
        CentralProcessor cpr = new CentralProcessor(audiA3Rus);
        // процесс управляет работой машины
        cpr.work();

    }
}