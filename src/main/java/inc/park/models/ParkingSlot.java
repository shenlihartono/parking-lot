package inc.park.models;

public class ParkingSlot {
    private Car car;
    private int slot;

    public ParkingSlot(Car car, int slot) {
        this.car = car;
        this.slot = slot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "car=" + car +
                ", slot=" + slot +
                '}';
    }
}
