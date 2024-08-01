package cvut.fel.omo.accessory.devicecontroller.device.consumption.consumptionstrategy;

import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * Idle consumption strategy.
 * Part of a Strategy pattern.
 */
public class IdleConsumptionStrategy extends ConsumptionStrategy{
    public IdleConsumptionStrategy(Device device) {
        super(device);
        this.consumption = device.getIdleConsumption();
    }
}
