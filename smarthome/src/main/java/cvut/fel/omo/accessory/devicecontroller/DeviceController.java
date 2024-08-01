package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Event;
import cvut.fel.omo.accessory.Accessory;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.ConsumptionData;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;
import cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver.SensorObservableSubject;
import cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver.SensorObserver;

import java.util.Optional;

/**
 * Class that provides an API for controlling a device. (Uses Facade pattern).
 * Extends the functionality of {@link Accessory}.
 * Implements an observer from observer pattern.
 */
public abstract class DeviceController extends Accessory implements SensorObserver {
    protected final Device device;
    protected boolean isForAnimal = false;
    public DeviceController(Device device){
        this.device = device;
        this.name = device.getName();
        device.setDeviceController(this);
    }

    /**
     * Provided function to turn on this controllers {@link Device}.
     */
    public void turnOn(){
        device.turnOn();
        new Event(getDeviceName() + " turned on", getDeviceName());
    }

    /**
     * Provided function to turn off this controllers {@link Device}.
     */
    public void turnOff(){
        device.turnOff();
        new Event(getDeviceName() + " turned off", getDeviceName());
    }

    /**
     * Provided function to se this controllers {@link Device} to idle state.
     */
    public void goIdle(){
        device.goIdle();
        new Event(getDeviceName() + " went idle", getDeviceName());
    }

    public void use(LivingEntity user){
        this.user = Optional.of(user);
        this.isInUse = true;
    }

    public void stopUsing(LivingEntity user){
        this.user = Optional.empty();
        this.isInUse = false;
    }

    /**
     * Method for simulating a run of a device.
     * The device consumption and lifespan get updated.
     */
    public void simulationUpdate(){
        device.updateLifespan();
        device.updateConsumption();
    }

    public void updateObserver(SensorObservableSubject sensor){
        turnOff();
        this.isInUse = false;
    }

    public ConsumptionData getConsumptionData(){
        return device.getConsumptionData();
    }
    public String getDeviceName(){
        return device.getName();
    }

    public boolean isForAnimal() {
        return this.isForAnimal;
    }
}
