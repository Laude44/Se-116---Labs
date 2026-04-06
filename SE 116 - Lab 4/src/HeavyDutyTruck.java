public class HeavyDutyTruck extends Truck{
    private int axleCount;

    public HeavyDutyTruck(String plateNumber,double dailyRate,int cargoCapacity,int axleCount) {
        super(plateNumber,dailyRate,cargoCapacity);
        this.axleCount = axleCount;
    }

    public int getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(int axleCount) {
        this.axleCount = axleCount;
    }
    @Override
    public void safetyCheck(){
        if (getCargoCapacity() < 40) {
            System.out.println( " Cargo is safe");
        } else {
            System.out.println( " Cargo is not safe!!!");
        }
    }
    @Override
    public double calculateRental(int days){
        return (days*getDailyRate()*axleCount);
    }


}
