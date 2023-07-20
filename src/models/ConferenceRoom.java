package models;

import org.jetbrains.annotations.NotNull;
import services.AvailabilityChecker;
import services.ValidityChecker;

public class ConferenceRoom {

    private final String roomId;
    private final boolean[][] datesAndSlots = new boolean[30][24];

    ConferenceRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean[][] getDatesAndSlots() {
        return datesAndSlots;
    }

    public void bookSlot(Slot slot) throws Exception {

        ValidityChecker.checkValidity(slot);
        AvailabilityChecker.checkAvailability(slot, this);
        updateBookingSlots(slot);

    }

    private void updateBookingSlots(@NotNull Slot slot) {
        if (slot.getBeginDate() == slot.getEndDate()) {
            for (int hour = slot.getBeginTime(); hour < slot.getEndTime(); hour++) {
                datesAndSlots[slot.getBeginDate()][hour] = true;
            }
        } else {
            for (int hour = slot.getBeginTime(); hour < 24; hour++) {
                datesAndSlots[slot.getBeginDate()][hour] = true;
            }
            for (int hour = 0; hour < slot.getEndTime(); hour++) {
                datesAndSlots[slot.getEndDate()][hour] = true;
            }
        }
    }

    public void cancelSlot(@NotNull Slot slot) {

        if (slot.getBeginDate() == slot.getEndDate()) {
            for (int hour = slot.getBeginTime(); hour < slot.getEndTime(); hour++) {
                datesAndSlots[slot.getBeginDate()][hour] = false;
            }
        } else {
            for (int hour = slot.getBeginTime(); hour < 24; hour++) {
                datesAndSlots[slot.getBeginDate()][hour] = false;
            }
            for (int hour = 0; hour < slot.getEndTime(); hour++) {
                datesAndSlots[slot.getEndDate()][hour] = false;
            }
        }

    }
}
