package inc.park.models;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParkingLot {
    private Map<Integer, Car> parkingSlots;
    private final int size;
    private TreeSet<Integer> availableSlots;

    public ParkingLot(int size) {
        this.parkingSlots = new TreeMap<>();
        this.availableSlots = new TreeSet<>();
        this.size = size;
    }

    public String create() {
        for (int i = 1; i <= size; i++) {
            parkingSlots.put(i, new Car());
            availableSlots.add(i);
        }
        return "Created a parking lot with " + size + " slots";
    }

    public Map<Integer, Car> getAllSlots() {
        return parkingSlots;
    }

    public Set<Integer> getAvailableSLots() {
        return availableSlots;
    }

    public String park(Car car) {
        boolean empty = this.availableSlots.isEmpty();
        if (empty) {
            return "Sorry, parking lot is full";
        }

        Integer slot = this.availableSlots.first();
        this.availableSlots.remove(slot);

        return "Allocated slot number: " + slot;
    }
}
