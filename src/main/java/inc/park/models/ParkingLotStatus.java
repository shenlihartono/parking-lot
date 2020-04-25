package inc.park.models;

public class ParkingLotStatus {
    private Car car;
    private int slot;

    public ParkingLotStatus(int slot, Car car) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParkingLotStatus compared = (ParkingLotStatus) o;
        return slot == compared.slot && car == compared.car;
    }

    @Override
    public String toString() {
        return "ParkingLotStatus{" +
                "car=" + car +
                ", slot=" + slot +
                '}';
    }
}
