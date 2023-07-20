package services;

import models.ConferenceRoom;
import models.Slot;
import org.jetbrains.annotations.NotNull;

public class AvailabilityChecker {

    public static void checkAvailability(@NotNull Slot slot, @NotNull ConferenceRoom conferenceRoom) throws Exception {
        boolean[][] datesAndSlots = conferenceRoom.getDatesAndSlots();

        if (slot.getBeginDate() == slot.getEndDate()) {
            for (int hour = slot.getBeginTime(); hour < slot.getEndTime(); hour++) {
                if (datesAndSlots[slot.getBeginDate()][hour])
                    throw new Exception("The slot you are booking is either fully booked or partially booked.");
            }
        } else {
            for (int hour = slot.getBeginTime(); hour < 24; hour++) {
                if (datesAndSlots[slot.getBeginDate()][hour])
                    throw new Exception("The slot you are booking is either fully booked or partially booked.");
            }
            for (int hour = 0; hour < slot.getEndTime(); hour++) {
                if (datesAndSlots[slot.getEndDate()][hour])
                    throw new Exception("The slot you are booking is either fully booked or partially booked.");
            }
        }
    }

}
