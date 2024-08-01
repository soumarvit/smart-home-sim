package cvut.fel.omo.livingentity;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.Accessory;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.accessory.devicecontroller.device.deviceobserver.DeviceObserver;

import java.util.Optional;
/**
 * Represents an animal living in the house, inheriting from the LivingEntity class.
 * Implements the DeviceObserver and RepairChainOfResponsibilityHandler interfaces
 */
public abstract class Person extends LivingEntity implements DeviceObserver, RepairChainOfResponsibilityHandler {

    protected int maxRepairCapability;
    private RepairChainOfResponsibilityHandler nextRepairHandler;

    /**
     * Constructor of the Person class.
     * @param name Name of the person
     */
    public Person(String name) {
        super(name);
    }
    /**
     * Sets and uses the specified accessory for a certain duration.
     *
     * @param accessory The accessory to be used.
     * @param useTime The duration for which the accessory will be used.
     */
    public void useObject(Accessory accessory, int useTime){
        busyCountdown = useTime;
        isBusy = true;
        currentAccessory = Optional.of(accessory);
        currentAccessory.ifPresent(accessory1 -> accessory1.use(this));
    }

    public void updateObserver(Device device) {
        repairDevice(device);
    }

    public void setNextRepairHandler(RepairChainOfResponsibilityHandler handler){
        this.nextRepairHandler = handler;
    }

    public void repairDevice(Device device) {
        if(device.getRepairTime() <= this.maxRepairCapability){

            busyCountdown = 0;
            currentAccessory.ifPresent(accessory -> accessory.stopUsing(this));

            busyCountdown = device.getRepairTime();
            isBusy = true;

            new Activity(this.name + " fixing", this,  device.getName());
            device.getRepairedBy(this);
        } else{
            this.nextRepairHandler.repairDevice(device);
        }
    }
}
