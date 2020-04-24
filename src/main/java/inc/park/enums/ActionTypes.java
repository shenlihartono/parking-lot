package inc.park.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActionTypes {
    PARK("park"), LEAVE("leave"), STATUS("status"),
    PLATE_NO_WITH_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBER_WITH_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_WITH_PLATE("slot_number_for_registration_number");

    private final String action;
    private static final Map<String, ActionTypes> actions = new HashMap<>();

    static {
        for (ActionTypes at : ActionTypes.values()) {
            actions.put(at.getAction(), at);
        }
    }

    ActionTypes(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public static ActionTypes getActionByString(String a) {
        return actions.get(a);
    }
}