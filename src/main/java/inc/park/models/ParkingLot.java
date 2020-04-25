package inc.park.models;

public class ParkingLot {
    private final ParkingSlot[] parkingSlots;
    private final int size;

    public ParkingLot(int size) {
        this.parkingSlots = new ParkingSlot[size];
        this.size = size;
    }

    public String create() {
        return "Created a parking lot with " + size + " slots";
    }

    public ParkingSlot[] getAllSlots() {
        return parkingSlots;
    }
}
