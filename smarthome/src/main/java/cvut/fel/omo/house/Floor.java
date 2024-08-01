package cvut.fel.omo.house;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a floor in a house with a list of rooms on the floor.
 */
public class Floor {
    private final List<Room> rooms = new ArrayList<>();
    /**
     * Adds a room to the floor.
     *
     * @param room The room to add.
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }
    /**
     * Returns the rooms on the floor.
     *
     * @return The rooms on the floor.
     */
    public List<Room> getRooms() {
        return rooms;
    }
}
