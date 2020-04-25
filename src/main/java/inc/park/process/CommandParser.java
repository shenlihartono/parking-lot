package inc.park.process;

import inc.park.enums.ActionTypes;
import inc.park.models.Car;
import inc.park.models.ParkingLotStatus;

import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {
    private final ParkingLot parkingLot;

    public CommandParser() {
        parkingLot = new ParkingLot();
    }

    public void process(String command) {
        String[] inputs = command.split(" ");
        ActionTypes action = ActionTypes.getActionByString(inputs[0]);
        if (action.getNumArgs() != inputs.length) {
            System.out.println("Unknown command");
        } else {
            switch (action) {
                case CREATE_PARKING_LOT:
                    int size = Integer.parseInt(inputs[1]);
                    String result = this.parkingLot.create(size);
                    System.out.println(result);
                    break;
                case PARK:
                    Car car = new Car(inputs[1], inputs[2]);
                    result = this.parkingLot.park(car);
                    System.out.println(result);
                    break;
                case LEAVE:
                    int slot = Integer.parseInt(inputs[1]);
                    result = this.parkingLot.leave(slot);
                    System.out.println(result);
                    break;
                case STATUS:
                    List<ParkingLotStatus> status = this.parkingLot.getStatus();
                    this.printStatus(status);
                    break;
                case PLATE_NO_WITH_COLOR:
                    List<String> plates = this.parkingLot.getLicensePlatesFromColor(inputs[1]);
                    this.printLicensePlates(plates);
                    break;
                case SLOT_NUMBER_WITH_COLOR:
                    List<Integer> nums = this.parkingLot.getSlotNumberFromColor(inputs[1]);
                    this.printSlotNumbers(nums);
                    break;
                case SLOT_NUMBER_WITH_PLATE:
                    int res = this.parkingLot.getSlotNumberFromPlate(inputs[1]);
                    System.out.println(res == 0 ? "Not found" : res);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private void printStatus(List<ParkingLotStatus> status) {
        if (status != null && !status.isEmpty()) {
            System.out.println("Slot No.\tRegistration No.\tColor");
            for (ParkingLotStatus s : status) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.getSlot());
                sb.append("\t\t\t");
                sb.append(s.getCar().getPlate());
                sb.append("\t\t");
                sb.append(s.getCar().getColor());
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("No vehicle in parking");
        }
    }

    private void printLicensePlates(List<String> plates) {
        if (plates != null && !plates.isEmpty()) {
            String joinedString = plates.stream().
                    map(Object::toString).
                    collect(Collectors.joining(", "));
            System.out.println(joinedString);
        } else {
            System.out.println("No vehicle in parking");
        }
    }

    private void printSlotNumbers(List<Integer> slots) {
        if (slots != null && !slots.isEmpty()) {
            String joinedString = slots.stream().
                    map(Object::toString).
                    collect(Collectors.joining(", "));
            System.out.println(joinedString);
        } else {
            System.out.println("No vehicle in parking");
        }
    }
}
