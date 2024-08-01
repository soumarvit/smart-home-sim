package cvut.fel.omo.house;

import cvut.fel.omo.accessory.devicecontroller.device.Blinds;
import cvut.fel.omo.accessory.devicecontroller.BlindsDeviceController;
import cvut.fel.omo.accessory.devicecontroller.DeviceController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents a room in a house with a list of windows and device controllers.
 */
public class Room {
    private final String name;
    private final List<Window> windows = new ArrayList<>();
    private final List<DeviceController> deviceControllers = new ArrayList<>();

    /**
     * Constructor of the Room class
     * @param name The name of the
     */
    public Room(String name){
        this.name = name;
    }

    /**
     * Adds a window with a blinds device controller to the room.
     */
    public void addWindow(){
        String windowDescription = this.name + " window " +  windows.size();
        Window window = new Window(new BlindsDeviceController(new Blinds(windowDescription + " blinds")));
        windows.add(window);
    }
    /**
     * Adds a device controller to the room.
     *
     * @param deviceController The device controller to add.
     */
    public void addDeviceController(DeviceController deviceController){
        deviceControllers.add(deviceController);
    }

    public String getName() {
        return name;
    }
    public List<Window> getWindows(){
        return  windows;
    }

    public List<DeviceController> getDeviceControllers() {
        return deviceControllers;
    }
    /**
     * Returns the device controllers in the room that are not currently in use.
     *
     * @return The device controllers currently not in use.
     */
    public List<DeviceController> getAvailableDeviceControllers() {
        return deviceControllers.stream()
                .filter(deviceController -> !deviceController.isInUse() && !deviceController.isForAnimal())
                .collect(Collectors.toList());
    }
    /**
     * Returns the device controllers for animals in the room that are not currently in use.
     *
     * @return The device controllers for animals currently not in use.
     */
    public List<DeviceController> getAvailableAnimalDeviceControllers() {
        return deviceControllers.stream()
                .filter(deviceController -> !deviceController.isInUse() && deviceController.isForAnimal())
                .collect(Collectors.toList());
    }
}
