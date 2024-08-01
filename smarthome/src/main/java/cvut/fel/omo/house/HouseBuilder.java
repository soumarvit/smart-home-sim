package cvut.fel.omo.house;

import com.fasterxml.jackson.databind.ObjectMapper;
import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.accessory.devicecontroller.DeviceController;
import cvut.fel.omo.accessory.vehicle.Vehicle;
import cvut.fel.omo.utilities.Constants;

import java.io.FileWriter;
import java.util.List;
/**
 * Represents a house builder that uses the Builder design pattern to construct a house.
 */
public class HouseBuilder {
    private final House house;

    /**
     * Constructor of the HouseBuilder class
     */
    public HouseBuilder(){
        house = new House();
    }
    /**
     * Adds a floor to the house.
     *
     * @return The current house builder instance.
     */
    public HouseBuilder addFloor(){
        AppLogger.logger.config("House builder: added floor");
        house.addFloor();
        return this;
    }
    /**
     * Adds a room to the house.
     *
     * @param roomName The name of the room to add.
     * @return The current house builder instance.
     */
    public HouseBuilder addRoom(String roomName){
        AppLogger.logger.config("House builder: added room");
        List<Floor> floors = house.getFloors();
        if (!floors.isEmpty()){
            Room room = new Room(roomName);
            floors.get(floors.size() - 1).addRoom(room);
        }
        return this;
    }
    /**
     * Adds a garage to the house.
     *
     * @param garageName The name of the garage to add.
     * @return The current house builder instance.
     */
    public HouseBuilder addGarage(String garageName){
        AppLogger.logger.config("House builder: added garage");
        house.addGarage(new Garage(garageName));
        return this;
    }
    /**
     * Adds a vehicle to the garage of the house.
     *
     * @param vehicle The vehicle to add.
     * @return The current house builder instance.
     */
    public HouseBuilder addVehicle(Vehicle vehicle){
        AppLogger.logger.config("House builder: added vehicle");
        Garage garage = house.getGarage();
        if (garage != null){
            garage.addVehicle(vehicle);
        }
        return this;
    }

    public HouseBuilder addWindows(int numWindows){
        for (int i = 0; i < numWindows; i++){
            this.addWindow();
        }
        return this;
    }
    /**
     * Adds a window to the last added room of the house.
     *
     * @return The current house builder instance.
     */
    public HouseBuilder addWindow(){
        AppLogger.logger.config("House builder: added window");
        List<Floor> floors = house.getFloors();
        if (!floors.isEmpty()){
            List<Room> rooms = floors.get(floors.size() - 1).getRooms();
            if (!rooms.isEmpty()){
                rooms.get(rooms.size() - 1).addWindow();
            }
        }
        return this;
    }
    /**
     * Adds the specified device controller to the last added room of the house.
     *
     * @param deviceController The device controller to add.
     * @return The current house builder instance.
     */
    public HouseBuilder addDeviceController(DeviceController deviceController){
        AppLogger.logger.config("House builder: added device controller");
        List<Floor> floors = house.getFloors();
        if (!floors.isEmpty()){
            List<Room> rooms = floors.get(floors.size() - 1).getRooms();
            if (!rooms.isEmpty()){
                rooms.get(rooms.size() - 1).addDeviceController(deviceController);
            }
        }
        return this;
    }
    /**
     * Builds and returns the constructed house.
     *
     * @return The constructed house.
     */
    public House build(){
        AppLogger.logger.config("House builder: build");
        return house;
    }


}
