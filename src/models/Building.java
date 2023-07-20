package models;

import java.util.ArrayList;

public class Building {

    private final String name;
    private final ArrayList<Floor> floors = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public String getName() {
        return name;
    }

    public void listAvailableConferenceRoomsInTheBuilding(int date, int hour) {

        for (Floor floor : floors) {
            System.out.print("Available rooms on floor " + floor.getLevel() + ": ");
            for (ConferenceRoom conferenceRoom : floor.getConferenceRooms()) {
                if (!conferenceRoom.getDatesAndSlots()[date][hour]) System.out.print(conferenceRoom.getRoomId() + " ");
            }
            System.out.println();
        }

    }

    public void addFloor(String level) {

        floors.add(new Floor(Integer.parseInt(level)));

    }

    public Floor getFloor(String floorLevel) throws Exception {

        for (Floor floor : floors) {
            if (floor.getLevel() == Integer.parseInt(floorLevel)) return floor;
        }
        throw new Exception("No floor found by the level: " + floorLevel);

    }

}
