package inc.park.models;

import java.util.Set;
import java.util.TreeSet;

public class ParkingLot {
    private final ParkingSlot[] parkingSlots;
    private final int size;
    private final Set<Integer> availableSlots;

    public ParkingLot(int size) {
        this.parkingSlots = new ParkingSlot[size];
        this.availableSlots = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            availableSlots.add(i);
        }

        this.size = size;
    }

    public String create() {
        return "Created a parking lot with " + size + " slots";
    }

    public ParkingSlot[] getAllSlots() {
        return parkingSlots;
    }

    public Set<Integer> getAvailableSLots() {
        return availableSlots;
    }
}
