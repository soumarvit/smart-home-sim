package cvut.fel.omo.accessory.devicecontroller.device;

import cvut.fel.omo.livingentity.Person;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OnConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.utilities.Constants;

/**
 * Represents a specialized version of {@link Device} with added or changed data and behavior.
 * Boiler.
 */
public class Boiler extends Device{

    public Boiler(String name){
        super(name);

        this.repairTime = Constants.BOILER_REPAIR_TIME;
        this.wearOverTime = 15;

        this.idleConsumption = new Consumption(2, 5, 2);
        this.offConsumption = new Consumption(0,0,0);
        this.onConsumption = new Consumption(5, 8, 5);

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
