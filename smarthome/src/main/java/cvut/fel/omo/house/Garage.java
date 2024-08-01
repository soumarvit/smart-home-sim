package cvut.fel.omo.house;

import cvut.fel.omo.accessory.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents a garage in a house with a list of vehicles.
 */
public class Garage extends Room{
    private final List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Constructor of the Garage class
     * @param name The name of the garage.
     */
    public Garage(String name) {
        super(name);
    }
    /**
     * Adds a vehicle to the garage.
     *
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    /**
     * Returns the vehicles, that are not currently in use by someone.
     *
     * @return The available vehicles in the garage.
     */
    public List<Vehicle> getAvailableVehicles() {
        return vehicles.stream()
                .filter(vehicle -> vehicle.isInUse())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Garage: " + vehicles.size() + " vehicles\n");
        for (Vehicle vehicle: vehicles){
            stringBuilder.append("\t").append(vehicle.getName()).append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
