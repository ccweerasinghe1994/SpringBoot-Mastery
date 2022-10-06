package chamara.java;

class Civic extends Car{
    public Civic() {
        super(10, "civic");
    }
    @Override
    public void startEngine() {
        System.out.println("civic is starting");
    }

    @Override
    public void accelerating() {
        System.out.println("civic is accelerating");
    }

    @Override
    public void braking() {
        System.out.println("civic is braking");
    }
}