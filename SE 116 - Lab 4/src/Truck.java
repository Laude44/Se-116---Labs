public class Truck extends Vehicle{
    private int cargoCapacity;

    public Truck(String plateNumber, double dailyRate, int cargoCapacity) {
        super(plateNumber, dailyRate);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
    @Override
    public void safetyCheck(){
        if (cargoCapacity < 40) {
            System.out.println( " Cargo is safe");
        } else {
            System.out.println( " Cargo is not safe!!!");
        }
    }
}
