package inc.park.process;

import inc.park.enums.ActionTypes;

public class CommandParser {
    private ParkingLot parkingLot;

    public CommandParser() {
    }

    public String process(String command) {
        ActionTypes a = ActionTypes.getActionByString(command);

        switch (a) {
            case CREATE_PARKING_LOT:
//                this.parkingLot()
                break;
            case PARK:
                break;

            default:
                return "Unknown command";
        }

        return "";
    }
}
