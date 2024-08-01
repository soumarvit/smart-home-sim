package cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy;

import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * Off consumption strategy.
 * Part of a Strategy pattern.
 */
public class OffConsumptionStrategy extends ConsumptionStrategy {

    public OffConsumptionStrategy(Device device) {
        super(device);
        this.consumption = device.getOffConsumption();
    }
}
