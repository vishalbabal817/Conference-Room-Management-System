package services;

import models.Slot;

public class ValidityChecker {

    public static void checkValidity(Slot slot) throws Exception {
        int hours = 0;
        if (slot.getBeginDate() == slot.getEndDate()) {
            hours = slot.getEndTime() - slot.getBeginTime();
        }
        else {
            hours += 24 - slot.getBeginTime();
            hours += slot.getEndTime();
        }

        if (hours > 12) throw new Exception("The slot you are trying to book is beyond booking duration of 12 hours.");
    }

}
