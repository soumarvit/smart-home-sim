package cvut.fel.omo.livingentity;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.utilities.Constants;
import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * Represent a dad. Inherits from the Person class.
 */
public class Dad extends Person{

    /**
     * Constructor of the Dad class.
     *
     * @param name The name of the dad.
     */
    public Dad(String name) {
        super(name);
        this.maxRepairCapability = Constants.MAX_DAD_REPAIR_CAPABILITY;
    }

    @Override
    public void repairDevice(Device device){
        if(device.getRepairTime() <= this.maxRepairCapability){
            busyCountdown = 0;
            currentAccessory.ifPresent(accessory -> accessory.stopUsing(this));

            busyCountdown = device.getRepairTime();
            isBusy = true;

            new Activity(this.name + " fixing", this,  device.getName());
            device.getRepairedBy(this);
        } else{
            new Activity(this.name + " cant fix "  + device.getName() + " -> calling handyman", this,  device.getName());
            device.getRepairedByHandyman();
        }
    }
}
