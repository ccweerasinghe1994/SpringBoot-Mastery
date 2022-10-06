package chamara.java;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(12,"car");
        System.out.println(car.getName());
        System.out.println(car.getCylinders());

        car.startEngine();
        car.accelerating();
        car.braking();

        Civic civic = new Civic();
        civic.startEngine();
        civic.accelerating();
        civic.braking();
    }
}