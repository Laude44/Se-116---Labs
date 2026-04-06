public class Vehicle {
    private String plateNumber;
    private double dailyRate;

    public Vehicle(String plateNumber, double dailyRate) {
        this.plateNumber = plateNumber;
        this.dailyRate = dailyRate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double calculateRental(int days){
        return (days*dailyRate);
    }

    public void safetyCheck() {
        System.out.println(plateNumber + " - Basic diagnostic: Brakes and lights are functional.");
    }

    public static void main(String[] args) {
        Vehicle[] fleet = new Vehicle[3];

        fleet[0] = new Vehicle("35 ABC 12", 500);
        fleet[1] = new Truck("35 DEF 34", 800, 35);
        fleet[2] = new HeavyDutyTruck("35 GHI 56", 1200, 50, 6);
        fleet[0].calculateRental(5);
        fleet[1].calculateRental(5);
        fleet[2].calculateRental(5);
        System.out.println("//////////");
        fleet[0].safetyCheck();
        fleet[1].safetyCheck();
        fleet[2].safetyCheck();

    }




}
