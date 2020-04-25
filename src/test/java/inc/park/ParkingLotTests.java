package inc.park;

import inc.park.models.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParkingLotTests {
    private final ParkingLot parkingLot = new ParkingLot();

    @Test
    public void createParkingLot() {
        String result = parkingLot.create(6);
        assertThat(result, is("Created a parking lot with 6 slots"));

        result = parkingLot.create(5);
        assertThat(result, is("Created a parking lot with 5 slots"));
    }
}
