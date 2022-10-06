package chamara.java;

class Honda extends Car {
    public Honda() {
        super(2, "vezel");
    }

    @Override
    public void startEngine() {
        System.out.println("honda is starting");
    }
}
