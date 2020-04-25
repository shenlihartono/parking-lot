package inc.park.models;

import java.util.*;

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
            parkingSlots.put(i, null);
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
        parkingSlots.put(slot, car);
        this.availableSlots.remove(slot);

        return "Allocated slot number: " + slot;
    }

    public String leave(int i) {
        if (i > size || i < 1) {
            return "Slot number outside of range";
        }

        boolean isAvailableSlot = this.availableSlots.contains(i);
        if (isAvailableSlot) {
            return "No car found in slot " + i;
        }

        this.parkingSlots.put(i, null);
        this.availableSlots.add(i);
        return "Slot number " + i + " is free";
    }

    public Car getCarParkedAt(int i) {
        boolean isAvailableSlot = this.availableSlots.contains(i);
        if (isAvailableSlot) {
            return null;
        }

        return parkingSlots.get(i);
    }

    public List<ParkingLotStatus> getStatus() {
        if (availableSlots.size() == size) {
            return null;
        }

        List<ParkingLotStatus> status = new ArrayList<>();
        this.parkingSlots.forEach((k, v) -> {
                    if (v != null) status.add(new ParkingLotStatus(k, v));
                }
        );

        return status;
    }

    public List<String> getLicensePlatesFromColor(String color) {
        if (availableSlots.size() == size) {
            return null;
        }

        List<String> plates = new ArrayList<>();
        parkingSlots.entrySet().stream()
                .filter(p -> p.getValue() != null && p.getValue().getColor().equals(color))
                .forEach((entry) -> plates.add(entry.getValue().getPlate()));

        return plates;
    }

    public List<Integer> getSlotNumberFromColor(String color) {
        if (availableSlots.size() == size) {
            return null;
        }

        List<Integer> plates = new ArrayList<>();
        parkingSlots.entrySet().stream()
                .filter(p -> p.getValue() != null && p.getValue().getColor().equals(color))
                .forEach((entry) -> plates.add(entry.getKey()));

        return plates;
    }

    public int getSlotNumberFromPlate(String plate) {
        if (availableSlots.size() == size) {
            return 0;
        }

        for (Map.Entry<Integer, Car> entry : parkingSlots.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getPlate().equals(plate)) {
                return entry.getKey();
            }
        }

        return 0;
    }
}
