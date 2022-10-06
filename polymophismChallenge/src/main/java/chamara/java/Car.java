package chamara.java;

public class Car {
    private int wheels;
    private int cylinders;
    private boolean engine;

    private String name;

    public Car(int cylinders, String name) {
        this.wheels = 4;
        this.cylinders = cylinders;
        this.engine = true;
        this.name = name;
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }

    public void startEngine(){
        System.out.println("Car() -> vehicle is starting");
    }

    public void accelerating(){
        System.out.println("Car() -> accelerating");
    }

    public void braking(){
        System.out.println("Car() -> braking");
    }
}

