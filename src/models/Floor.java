package models;

import java.util.ArrayList;

public class Floor {

    private final int level;
    private final ArrayList<ConferenceRoom> conferenceRooms = new ArrayList<>();

    public Floor(int level) {
        this.level = level;
    }

    public ArrayList<ConferenceRoom> getConferenceRooms() {
        return conferenceRooms;
    }

    public int getLevel() {
        return level;
    }

    public void addConferenceRoom(String roomId) {

        conferenceRooms.add(new ConferenceRoom(roomId));

    }

    public ConferenceRoom getConferenceRoom(String roomId) throws Exception {

        for (ConferenceRoom conferenceRoom: conferenceRooms) {
            if (conferenceRoom.getRoomId().equals(roomId)) return conferenceRoom;
        }
        throw new Exception("No conference room by room Id: " + roomId);

    }

}
