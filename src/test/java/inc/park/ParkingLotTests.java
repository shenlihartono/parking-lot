package inc.park;

import inc.park.models.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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
}
