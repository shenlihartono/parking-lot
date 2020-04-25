package inc.park.models;

import java.util.Map;

public class ParkingLot {
    private Map<Integer, ParkingSlot> parkSlots;
    private final int size;

    public ParkingLot(int size) {
        this.size = size;
    }

    public String create() {
        return "Created a parking lot with " + size + " slots";
    }
}
