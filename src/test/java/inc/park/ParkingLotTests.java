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
}
