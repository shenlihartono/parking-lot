package inc.park.models;

import java.util.Map;

public class ParkingLot {
    private Map<Integer, ParkingSlot> parkSlots;

    public String create(int size) {
        return "Created a parking lot with " + size + " slots";
    }
}
