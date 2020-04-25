package inc.park;

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
}
