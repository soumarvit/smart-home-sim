package cvut.fel.omo.accessory.devicecontroller.device;

import cvut.fel.omo.livingentity.Person;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OnConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.utilities.Constants;

/**
 * Represents a specialized version of {@link Device} with added or changed data and behavior.
 * Smart animal feeder.
 */
public class SmartFeeder extends Device{

    public SmartFeeder(String name) {
        super(name);

        this.repairTime = Constants.SMART_FEEDER_REPAIR_TIME;
        this.wearOverTime = 4;

        this.idleConsumption = new Consumption(0.1, 0, 0.1);
        this.onConsumption = new Consumption(1, 0, 1);
        this.offConsumption = new Consumption(0, 0, 0);

        setStrategy(new OnConsumptionStrategy(this));
    }
    @Override
    public void getRepairedBy(Person person) {
        super.getRepairedBy(person);
        turnOn();
    }

    @Override
    public void getRepairedByHandyman() {
        super.getRepairedByHandyman();
        turnOn();
    }
}
