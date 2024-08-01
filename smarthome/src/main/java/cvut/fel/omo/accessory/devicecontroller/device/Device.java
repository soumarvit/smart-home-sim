package cvut.fel.omo.accessory.devicecontroller.device;

import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.ConsumptionData;
import cvut.fel.omo.utilities.Constants;
import cvut.fel.omo.accessory.devicecontroller.DeviceController;
import cvut.fel.omo.accessory.devicecontroller.device.deviceobserver.DeviceObservableSubject;
import cvut.fel.omo.report.Event;
import cvut.fel.omo.accessory.devicecontroller.device.deviceobserver.DeviceObserver;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.ConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.IdleConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OffConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OnConsumptionStrategy;
import cvut.fel.omo.livingentity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a device and holds all valuable information about it.
 * Implements an observable subject from observer pattern.
 */
public abstract class Device implements DeviceObservableSubject {
    protected String name;
    protected double lifespan = Constants.MAX_LIFESPAN;
    protected int repairTime = Constants.DEFAULT_REPAIR_TIME;
    protected double wearOverTime;
    private DeviceController deviceController;
    protected ConsumptionStrategy strategy;
    protected final ConsumptionData consumptionData = new ConsumptionData();
    protected Consumption idleConsumption;
    protected Consumption offConsumption;
    protected Consumption onConsumption;
    protected List<DeviceObserver> observers = new ArrayList<>();


    public Device(String name){
        this.name = name;
    }
    protected void setStrategy(ConsumptionStrategy consumptionStrategy){
        this.strategy = consumptionStrategy;
    }

    /**
     * Turns the device on by setting the consumption strategy to on.
     */
    public void turnOn() {
        setStrategy(new OnConsumptionStrategy(this));
    }

    /**
     * Turns the device off by setting the consumption strategy to off.
     */
    public void turnOff(){
        setStrategy(new OffConsumptionStrategy(this));
    }

    /**
     * Sets the device to idle state by setting the consumption to idle.
     */
    public void goIdle(){
        setStrategy(new IdleConsumptionStrategy(this));
    }


    public ConsumptionData getConsumptionData(){
        return this.consumptionData;
    }

    /**
     * Decrements the device lifespan in each call.
     * If lifespan gets to 0, the device stops working (stopWorking method gets called).
     */
    public void updateLifespan(){
        this.lifespan -= wearOverTime;
        if (lifespan <= 0){
            stopWorking();
        }
    }

    /**
     * Depending on current device state (on, off, idle) the total consumption of the device is updated
     */
    public void updateConsumption(){
        Consumption current = strategy.getCurrentConsumption();
        consumptionData.update(current);
    }

    public Consumption getIdleConsumption() {
        return idleConsumption;
    }

    public Consumption getOffConsumption() {
        return offConsumption;
    }

    public Consumption getOnConsumption() {
        return onConsumption;
    }

    public String getName() {
        return name;
    }

    /**
     * The device is turned off, event saying that the device stopped working is created.
     * All observers of the device get notified that it stopped working.
     */
    public void stopWorking(){
        setStrategy(new OffConsumptionStrategy(this));

        new Event(name + " stopped working", this.getName());
        notifyObservers();
    }

    /**
     * The devices lifespan is set back to MAX_LIFESPAN (defined in {@link Constants}).
     * Event saying the device got repaired by @param person is created.
     * @param person The person who repaired the device.
     */
    public void getRepairedBy(Person person){
        this.lifespan = Constants.MAX_LIFESPAN;
        new Event(name + " got repaired by " + person.getName(), this.getName());
    }

    /**
     * The devices lifespan is set back to MAX_LIFESPAN (defined in {@link Constants}).
     * Event saying the device got repaired by a handyman is created.
     */
    public void getRepairedByHandyman(){
        this.lifespan = Constants.MAX_LIFESPAN;
        new Event(name + " got repaired by handyman", this.getName());
    }


    @Override
    public void notifyObservers(){
        observers.forEach(user -> user.updateObserver(this));
    }

    @Override
    public void attach(DeviceObserver deviceObserver){
        observers.add(deviceObserver);
    }

    @Override
    public void detach(DeviceObserver deviceObserver){
        observers.remove(deviceObserver);
    }


    public int getRepairTime() {
        return repairTime;
    }

    public void setDeviceController(DeviceController deviceController) {
        this.deviceController = deviceController;
    }

    public DeviceController getDeviceController() {
        return deviceController;
    }
}
