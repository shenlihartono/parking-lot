package inc.park.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActionTypes {
    CREATE_PARKING_LOT("create_parking_lot", 2),
    PARK("park", 3), LEAVE("leave", 2), STATUS("status", 1),
    PLATE_NO_WITH_COLOR("registration_numbers_for_cars_with_colour", 2),
    SLOT_NUMBER_WITH_COLOR("slot_numbers_for_cars_with_colour", 2),
    SLOT_NUMBER_WITH_PLATE("slot_number_for_registration_number", 2),
    EXIT("exit", 1);

    private final String action;
    private final int numArgs;
    private static final Map<String, ActionTypes> actions = new HashMap<>();

    static {
        for (ActionTypes at : ActionTypes.values()) {
            actions.put(at.getAction(), at);
        }
    }

    ActionTypes(String action, int numArgs) {
        this.action = action;
        this.numArgs = numArgs;
    }

    public String getAction() {
        return action;
    }

    public int getNumArgs() {
        return numArgs;
    }

    public static ActionTypes getActionByString(String a) {
        return actions.get(a);
    }
}