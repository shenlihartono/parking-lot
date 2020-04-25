package inc.park;

import inc.park.models.Car;
import inc.park.models.ParkingLot;
import inc.park.models.ParkingLotStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void status() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.create();

        // when no cars parked at all
        List<ParkingLotStatus> status = parkingLot.getStatus();
        assertThat(status, equalTo(null));

        // park 5 cars, remove slot#2 and slot#4, then get the status
        Car car1 = new Car("ABC", "White");
        parkingLot.park(car1);

        List<ParkingLotStatus> expStatus = new ArrayList<>();
        expStatus.add(new ParkingLotStatus(1, car1));

        status = parkingLot.getStatus();
        assertThat(status, equalTo(expStatus));

        Car car2 = new Car("", "");
        parkingLot.park(car2);

        Car car3 = new Car("XYZ", "Red");
        parkingLot.park(car3);

        Car car4 = new Car("", "");
        parkingLot.park(car4);

        Car car5 = new Car("DEF", "Black");
        parkingLot.park(car5);

        parkingLot.leave(2);
        parkingLot.leave(4);

        expStatus = new ArrayList<>();
        expStatus.add(new ParkingLotStatus(1, car1));
        expStatus.add(new ParkingLotStatus(3, car3));
        expStatus.add(new ParkingLotStatus(5, car5));

        List<ParkingLotStatus> result = parkingLot.getStatus();
        assertThat(result, equalTo(expStatus));
    }

    @Test
    public void getLicenseFromColor() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.create();

        // when no cars parked at all
        List<String> plates = parkingLot.getLicensePlatesFromColor("White");
        assertThat(plates, equalTo(null));

        // when parking: KA-01-HH-1234 White
        Car car = new Car("KA-01-HH-1234", "White");
        parkingLot.park(car);

        // when parking another car: KA-01-HH-9999 White
        car = new Car("KA-01-HH-9999", "White");
        parkingLot.park(car);

        // park: KA-01-BB-0001 Black
        car = new Car("KA-01-BB-0001", "Black");
        parkingLot.park(car);

        // park another car
        car = new Car("KA-01-HH-7777", "Red");
        parkingLot.park(car);

        // test white plates
        List<String> whitePlates = new ArrayList<>();
        whitePlates.add("KA-01-HH-1234");
        whitePlates.add("KA-01-HH-9999");
        plates = parkingLot.getLicensePlatesFromColor("White");
        assertThat(plates, equalTo(whitePlates));

        // test black plates
        List<String> blackPlates = new ArrayList<>();
        blackPlates.add("KA-01-BB-0001");
        plates = parkingLot.getLicensePlatesFromColor("Black");
        assertThat(plates, equalTo(blackPlates));

        // test red plates
        List<String> redPlates = new ArrayList<>();
        redPlates.add("KA-01-HH-7777");
        plates = parkingLot.getLicensePlatesFromColor("Red");
        assertThat(plates, equalTo(redPlates));

        // test pink plates
        List<String> pinkPlates = new ArrayList<>();
        plates = parkingLot.getLicensePlatesFromColor("Pink");
        assertThat(plates, equalTo(pinkPlates));
    }

    @Test
    public void getSlotNumberFromColor() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.create();

        // when no cars parked at all
        List<Integer> number = parkingLot.getSlotNumberFromColor("White");
        assertThat(number, equalTo(null));

        // when parking: KA-01-HH-1234 White
        Car car = new Car("KA-01-HH-1234", "White");
        String result = parkingLot.park(car);
        String slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo1 = Integer.parseInt(slotNo.trim());

        // when parking another car: KA-01-HH-9999 White
        car = new Car("KA-01-HH-9999", "White");
        result = parkingLot.park(car);
        slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo2 = Integer.parseInt(slotNo.trim());

        // park: KA-01-BB-0001 Black
        car = new Car("KA-01-BB-0001", "Black");
        result = parkingLot.park(car);
        slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo3 = Integer.parseInt(slotNo.trim());

        // park another car
        car = new Car("KA-01-HH-7777", "Red");
        result = parkingLot.park(car);
        slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo4 = Integer.parseInt(slotNo.trim());

        // test white plates
        List<Integer> whitePlates = new ArrayList<>();
        whitePlates.add(slotNo1);
        whitePlates.add(slotNo2);
        number = parkingLot.getSlotNumberFromColor("White");
        assertThat(number, equalTo(whitePlates));

        // test black plates
        List<Integer> blackPlates = new ArrayList<>();
        blackPlates.add(slotNo3);
        number = parkingLot.getSlotNumberFromColor("Black");
        assertThat(number, equalTo(blackPlates));

        // test red plates
        List<Integer> redPlates = new ArrayList<>();
        redPlates.add(slotNo4);
        number = parkingLot.getSlotNumberFromColor("Red");
        assertThat(number, equalTo(redPlates));

        // test pink plates
        List<Integer> pinkPlates = new ArrayList<>();
        number = parkingLot.getSlotNumberFromColor("Pink");
        assertThat(number, equalTo(pinkPlates));
    }

    @Test
    public void getSlotNumberFromPlate() {
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.create();

        // when no cars parked at all
        int number = parkingLot.getSlotNumberFromPlate("KA-01-HH-1234");
        assertThat(number, is(0));

        // when parking: KA-01-HH-1234 White
        Car car = new Car("KA-01-HH-1234", "White");
        String result = parkingLot.park(car);
        String slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo1 = Integer.parseInt(slotNo.trim());

        // when parking another car: KA-01-HH-9999 White
        car = new Car("KA-01-HH-9999", "White");
        result = parkingLot.park(car);
        slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo2 = Integer.parseInt(slotNo.trim());

        // park: KA-01-BB-0001 Black
        car = new Car("KA-01-BB-0001", "Black");
        result = parkingLot.park(car);
        slotNo = result.substring(result.indexOf(":") + 1);
        int slotNo3 = Integer.parseInt(slotNo.trim());

        // test "KA-01-HH-1234" plate
        int actualSlot = parkingLot.getSlotNumberFromPlate("KA-01-HH-1234");
        assertThat(actualSlot, equalTo(slotNo1));

        // test "KA-01-HH-9999" plates
        actualSlot = parkingLot.getSlotNumberFromPlate("KA-01-HH-9999");
        assertThat(actualSlot, equalTo(slotNo2));

        // test "KA-01-BB-0001" plates
        actualSlot = parkingLot.getSlotNumberFromPlate("KA-01-BB-0001");
        assertThat(actualSlot, equalTo(slotNo3));

        // test "KA-01-HH-7777" plate
        actualSlot = parkingLot.getSlotNumberFromPlate("KA-01-HH-7777");
        assertThat(actualSlot, equalTo(0));
    }
}
