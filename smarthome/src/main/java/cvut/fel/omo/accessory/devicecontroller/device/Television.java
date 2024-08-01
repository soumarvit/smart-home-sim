package cvut.fel.omo.accessory.devicecontroller.device;

import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OffConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.utilities.Constants;

/**
 * Represents a specialized version of {@link Device} with added or changed data and behavior.
 * Television.
 */
public class Television extends Device{

    public Television(String name){
        super(name);

        this.repairTime = Constants.TV_REPAIR_TIME;
        this.wearOverTime = 4;

        this.idleConsumption = new Consumption(0, 0, 5);
        this.offConsumption = new Consumption(0, 0, 0);
        this.onConsumption = new Consumption(0, 0, 15);

        setStrategy(new OffConsumptionStrategy(this));
    }

}
