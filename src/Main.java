import models.Building;
import models.ConferenceRoom;
import models.Floor;
import models.Slot;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static final ArrayList<Building> buildings = new ArrayList<>();

    private static void listAllBookings() {

        System.out.println();

        for (Building building : buildings) {
            System.out.println("Building " + building.getName() + ": ");
            listAllBookingsOfTheBuilding(building);
            System.out.println();
        }

    }

    private static void listAllBookingsOfTheBuilding(@NotNull Building building) {

        for (Floor floor : building.getFloors()) {
            listAllBookingsOfTheFloor(floor);
        }

    }

    private static void listAllBookingsOfTheFloor(@NotNull Floor floor) {

        for (ConferenceRoom conferenceRoom : floor.getConferenceRooms()) {
            System.out.print("Floor " + floor.getLevel() + " & Conference Room " + conferenceRoom.getRoomId() + ": ");
            listAllBookingsOfTheConferenceRoom(conferenceRoom);
            System.out.println();
        }

    }

    private static void listAllBookingsOfTheConferenceRoom(@NotNull ConferenceRoom conferenceRoom) {

        int dayOfMonth = 0;
        for (boolean[] date : conferenceRoom.getDatesAndSlots()) {
            System.out.print(dayOfMonth + "(");
            listAllBookingsOnTheDay(date);
            System.out.print(")");
            dayOfMonth++;
        }

    }

    private static void listAllBookingsOnTheDay(boolean @NotNull [] date) {

        int beginHour = 0;
        int endHour = 0;
        for (boolean hour : date) {
            if (!hour) {
                if (beginHour != endHour) System.out.print(beginHour + "-" + endHour + " ");
                beginHour = endHour + 1;
            }
            endHour++;
        }

    }

    private static void addBuilding(String name) {

        buildings.add(new Building(name));

    }

    private static @NotNull Building getBuildingFromName(String buildingName) throws Exception {

        for (Building building : buildings) {
            if (building.getName().equals(buildingName)) return building;
        }
        throw new Exception("No building found by the name of " + buildingName);

    }

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to the Conference Room Management System!!");
        System.out.println();
        System.out.println("List of Commands you can use: ");
        System.out.println("1. ADD BUILDING <name>: to add a building in the system.");
        System.out.println("2. ADD FLOOR <building> <level>: to add a floor in a building.");
        System.out.println("3. ADD CONFERENCE ROOM <building> <level> <room's ID>: to add a floor on a particular level of a building.");
        System.out.println("4. LIST AVAILABLE ROOMS <building> <date> <hour>: " +
                "to get the list of all available rooms at a particular hour of a day in a particular building.");
        System.out.println("5. LIST ALL BOOKINGS: to get the list of all the room bookings of all the buildings");
        System.out.println("6. BOOK SLOT <building> <floor> <conference room's Id> <begin date> <begin time> <end date> <end time>: " +
                "to book a particular conference room.");
        System.out.println("7. CANCEL SLOT <building> <floor> <conference room's Id> <begin date> <begin time> <end date> <end time>: " +
                "to cancel the mentioned slot.");
        System.out.println("8. EXIT : to get out of the application.");
        System.out.println("NOTE: You can only book a room for max of 12 hours.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            try {
                System.out.print("Enter the command: ");
                String line = reader.readLine();
                if (line.startsWith("ADD BUILDING")) {
                    String buildingName = line.substring(13);
                    addBuilding(buildingName);
                } else if (line.startsWith("ADD FLOOR")) {
                    String command = line.substring(10);
                    String[] arguments = command.split(" ");
                    String buildingName = arguments[0];
                    String floorLevel = arguments[1];

                    Building building = getBuildingFromName(buildingName);
                    building.addFloor(floorLevel);
                } else if (line.startsWith("ADD CONFERENCE ROOM")) {
                    String command = line.substring(20);
                    String[] arguments = command.split(" ");
                    String buildingName = arguments[0];
                    String floorLevel = arguments[1];
                    String roomId = arguments[2];

                    Building building = getBuildingFromName(buildingName);
                    Floor floor = building.getFloor(floorLevel);
                    floor.addConferenceRoom(roomId);
                } else if (line.startsWith("LIST AVAILABLE ROOMS")) {
                    String command = line.substring(21);
                    String[] arguments = command.split(" ");
                    String buildingName = arguments[0];
                    String date = arguments[1];
                    String hour = arguments[2];

                    Building building = getBuildingFromName(buildingName);
                    building.listAvailableConferenceRoomsInTheBuilding(Integer.parseInt(date), Integer.parseInt(hour));
                } else if (line.startsWith("LIST ALL BOOKINGS")) {

                    listAllBookings();

                } else if (line.startsWith("BOOK SLOT")) {
                    String command = line.substring(10);
                    String[] arguments = command.split(" ");
                    String buildingName = arguments[0];
                    String floorLevel = arguments[1];
                    String roomId = arguments[2];
                    Slot slot = new Slot(
                            Integer.parseInt(arguments[3]),
                            Integer.parseInt(arguments[4]),
                            Integer.parseInt(arguments[5]),
                            Integer.parseInt(arguments[6]));

                    Building building = getBuildingFromName(buildingName);
                    Floor floor = building.getFloor(floorLevel);
                    ConferenceRoom conferenceRoom = floor.getConferenceRoom(roomId);
                    conferenceRoom.bookSlot(slot);
                } else if (line.startsWith("CANCEL SLOT")) {
                    String command = line.substring(12);
                    String[] arguments = command.split(" ");
                    String buildingName = arguments[0];
                    String floorLevel = arguments[1];
                    String roomId = arguments[2];
                    Slot slot = new Slot(
                            Integer.parseInt(arguments[3]),
                            Integer.parseInt(arguments[4]),
                            Integer.parseInt(arguments[5]),
                            Integer.parseInt(arguments[6]));

                    Building building = getBuildingFromName(buildingName);
                    Floor floor = building.getFloor(floorLevel);
                    ConferenceRoom conferenceRoom = floor.getConferenceRoom(roomId);
                    conferenceRoom.cancelSlot(slot);
                } else if (line.startsWith("EXIT")) {
                    System.out.println("Thanks for using the Conference Room Management System!");
                    break;
                } else {
                    System.out.println("Command Invalid! Please try again...");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Try again...");
            }

        }

    }
}