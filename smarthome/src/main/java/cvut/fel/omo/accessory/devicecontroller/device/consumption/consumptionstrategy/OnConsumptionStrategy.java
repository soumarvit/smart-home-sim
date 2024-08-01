package cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy;

import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * On consumption strategy.
 * Part of a Strategy pattern.
 */
public class OnConsumptionStrategy extends ConsumptionStrategy{

    public OnConsumptionStrategy(Device device) {
        super(device);
        this.consumption = device.getOnConsumption();
    }
}
