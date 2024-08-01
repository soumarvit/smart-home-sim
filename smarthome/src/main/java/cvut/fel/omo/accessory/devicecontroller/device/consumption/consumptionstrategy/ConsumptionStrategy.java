package cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy;

import cvut.fel.omo.accessory.devicecontroller.device.consumption.Consumption;
import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * Implementation of a Strategy pattern.
 * Designed specifically to keep {@link Consumption} in {@link Device} up to date.
 */
public abstract class ConsumptionStrategy {

    protected Device device;
    protected Consumption consumption;

    public ConsumptionStrategy(Device device){
        consumption = new Consumption(0,0,0);
        this.device = device;
    }

    /**
     * @return Consumption object with current water, gas and electricity usage.
     */
    public Consumption getCurrentConsumption(){
        return this.consumption;
    };
}
