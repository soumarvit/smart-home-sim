package cvut.fel.omo.accessory.devicecontroller.device;

import cvut.fel.omo.livingentity.Person;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy.OnConsumptionStrategy;
import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.utilities.Constants;

/**
 * Represents a specialized version of {@link Device} with added or changed data and behavior.
 * Fridge.
 */
public class Fridge extends Device{

    public Fridge(String name){
        super(name);

        this.wearOverTime = 5;
        this.repairTime = Constants.FRIDGE_REPAIR_TIME;

        this.idleConsumption = new Consumption(0, 0,0);
        this.onConsumption = new Consumption(1, .5, 1);
        this.offConsumption = new Consumption(0, 0, 0);

        setStrategy(new OnConsumptionStrategy(this));
    }

    @Override
    public void goIdle() {
        System.out.println("error Fridge cannot go idle");
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
