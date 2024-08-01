package cvut.fel.omo.house;

import cvut.fel.omo.accessory.devicecontroller.BlindsDeviceController;
import cvut.fel.omo.accessory.devicecontroller.DeviceController;
import cvut.fel.omo.livingentity.Animal;
import cvut.fel.omo.livingentity.LivingEntity;
import cvut.fel.omo.livingentity.Person;
import cvut.fel.omo.accessory.devicecontroller.sensor.Sensor;
import cvut.fel.omo.accessory.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the house where the people, animals live.
 */
public class House {
    private Garage garage;
    private final List<Floor> floors = new ArrayList<>();
    private final List<Person> family = new ArrayList<>();
    private final List<Animal> animals = new ArrayList<>();
    private final List<Sensor> sensors = new ArrayList<>();

    /**
     * Adds a new floor to the house.
     */
    public void addFloor(){
        Floor floor = new Floor();
        floors.add(floor);
    }
    /**
     * Adds a garage to the house if it doesn't already exist.
     *
     * @param garage The garage to add.
     */
    public void addGarage(Garage garage){
        if (this.garage == null){
            this.garage = garage;
        }
    }
    /**
     * Adds a wind sensor and attaches it to the blinds controllers.
     */
    public void addWindSensor(){
        Sensor windSensor = new Sensor("Wind Sensor");
        this.addSensor(windSensor);
        this.getBlindsControllers()
                .forEach(blindsDeviceController -> windSensor.attach(blindsDeviceController));
    }
    /**
     * Adds a circuit breaker sensor and attaches it to the device controllers.
     */
    public void addCircuitBreakerSensor(){
        Sensor circuitBreakerSensor = new Sensor("Circuit breaker sensor");
        this.addSensor(circuitBreakerSensor);
        this.getDeviceControllers()
                .forEach(deviceController -> circuitBreakerSensor.attach(deviceController));
    }


    private void addSensor(Sensor sensor){
        this.sensors.add(sensor);
    }

    public Garage getGarage() {
        return this.garage;
    }

    public List<Floor> getFloors() {
        return floors;
    }
    /**
     * Returns all device controllers in the house.
     *
     * @return The device controllers in the house.
     */
    public List<DeviceController> getDeviceControllers() {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getDeviceControllers().stream())
                .collect(Collectors.toList());
    }
    /**
     * Returns the blinds controllers in the house.
     *
     * @return The blinds controllers in the house.
     */
    public List<BlindsDeviceController> getBlindsControllers() {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getWindows().stream())
                .map(window -> window.getBlindsDeviceController())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehicles(){
        return garage.getVehicles();
    }

    public List<Room> getRooms(){
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .collect(Collectors.toList());
    }

    public List<LivingEntity> getLivingEntities() {
        List<LivingEntity> livingEntities = new ArrayList<>(family);
        livingEntities.addAll(animals);
        return livingEntities;
    }

    public List<Person> getFamily() {
        return family;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    /**
     * Adds a person to the family and sets the repair chain of responsibility.
     *
     * @param person The person to add.
     */
    public void addPerson(Person person){
        family.add(person);
        setRepairChainOfResponsibility();
    }

    private void setRepairChainOfResponsibility(){
        for (int i = 0; i < family.size(); i++) {
            if (i == family.size() - 1){
                family.get(i).setNextRepairHandler(family.get(0));
            } else {
                family.get(i).setNextRepairHandler(family.get(i + 1));
            }
        }
    }
    /**
     * Adds an animal to the house.
     *
     * @param animal The animal to add.
     */
    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
}
