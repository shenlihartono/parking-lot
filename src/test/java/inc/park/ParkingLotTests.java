package inc.park;

import inc.park.models.Car;
import inc.park.models.ParkingLot;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ParkingLotTests {
    @Test
    public void createParkingLot() {
        ParkingLot parkingLot = new ParkingLot(6);
        String result = parkingLot.create();
        assertThat(result, is("Created a parking lot with 6 slots"));

        parkingLot = new ParkingLot(5);
        result = parkingLot.create();
        assertThat(result, is("Created a parking lot with 5 slots"));
    }

    @Test
    public void getParkingSlots() {
        ParkingLot parkingLot = new ParkingLot(6);
        parkingLot.create();
        Map slots = parkingLot.getAllSlots();
        assertThat(slots.size(), equalTo(6));

        parkingLot = new ParkingLot(5);
        parkingLot.create();
        slots = parkingLot.getAllSlots();
        assertThat(slots.size(), equalTo(5));
    }

    @Test
    public void getAvailableSlots() {
        ParkingLot parkingLot = new ParkingLot(6);
        parkingLot.create();
        Set<Integer> slots = parkingLot.getAvailableSLots();
        assertThat(slots.size(), equalTo(6));

        parkingLot = new ParkingLot(5);
        parkingLot.create();
        slots = parkingLot.getAvailableSLots();
        assertThat(slots.size(), equalTo(5));
    }

    @Test
    public void parking() {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.create();

        // when parking: KA-01-HH-1234 White
        Car car = new Car("KA-01-HH-1234", "White");
        String result = parkingLot.park(car);
        assertThat(result, is("Allocated slot number: 1"));

        // when parking another car: KA-01-HH-9999 White
        car = new Car("KA-01-HH-9999", "White");
        result = parkingLot.park(car);
        assertThat(result, is("Allocated slot number: 2"));

        // park: KA-01-BB-0001 Black
        car = new Car("KA-01-BB-0001", "Black");
        result = parkingLot.park(car);
        assertThat(result, is("Allocated slot number: 3"));

        // park another car, result = full
        car = new Car("KA-01-HH-7777", "Red");
        result = parkingLot.park(car);
        assertThat(result, is("Sorry, parking lot is full"));
    }

    @Test
    public void leaveParkingLot() {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.create();

        parkingLot.park(new Car());
        String result = parkingLot.leave(1);
        assertThat(result, is("Slot number 1 is free"));

        //when slot still free (no car)
        result = parkingLot.leave(2);
        assertThat(result, is("No car found in slot 2"));
        result = parkingLot.leave(3);
        assertThat(result, is("No car found in slot 3"));

        //when slot number is outside of range
        result = parkingLot.leave(4);
        assertThat(result, is("Slot number outside of range"));
        result = parkingLot.leave(-5);
        assertThat(result, is("Slot number outside of range"));

        result = parkingLot.park(new Car());
        String slotNo = result.substring(result.indexOf(":") + 1);
        int slot = Integer.parseInt(slotNo.trim());
        result = parkingLot.leave(slot);
        assertThat(result, is("Slot number " + slot + " is free"));
    }

    @Test
    public void getParkedCar() {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.create();

        // when no car park initially
        Car car = parkingLot.getCarParkedAt(1);
        assertThat(car, equalTo(null));

        // park 1st car, and get the car parked at that location
        Car myCar = new Car("KA-01-HH-1234", "White");
        String result = parkingLot.park(myCar);

        String slotNo = result.substring(result.indexOf(":") + 1);
        int slot = Integer.parseInt(slotNo.trim());

        Car myParkedCar = parkingLot.getCarParkedAt(slot);
        assertThat(myParkedCar, equalTo(myCar));

        // when slot number is outside of range
        Car mysteriousCar = parkingLot.getCarParkedAt(4);
        assertThat(mysteriousCar, equalTo(null));

        mysteriousCar = parkingLot.getCarParkedAt(-5);
        assertThat(mysteriousCar, equalTo(null));

        // park 2nd car, and get the car parked at that location
        Car anotherCar = new Car("KA-01-HH-9999", "Black");
        result = parkingLot.park(anotherCar);

        slotNo = result.substring(result.indexOf(":") + 1);
        slot = Integer.parseInt(slotNo.trim());

        Car anotherParkedCar = parkingLot.getCarParkedAt(slot);
        assertThat(anotherParkedCar, equalTo(anotherCar));

        // leave car slot#1 and get parked car at slot#1
        parkingLot.leave(1);
        Car targetCar = parkingLot.getCarParkedAt(1);
        assertThat(targetCar, equalTo(null));

        //do the same to car slot#2
        parkingLot.leave(2);
        targetCar = parkingLot.getCarParkedAt(2);
        assertThat(targetCar, equalTo(null));

        Map<Integer, Car> allSlots = parkingLot.getAllSlots();
        assertThat(allSlots.size(), equalTo(3));

        Set<Integer> avSlots = parkingLot.getAvailableSLots();
        assertThat(avSlots.size(), equalTo(3));
    }

}
