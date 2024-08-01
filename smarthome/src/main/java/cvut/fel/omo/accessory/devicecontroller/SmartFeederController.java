package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Represents a specialized version of {@link DeviceController} with added behavior.
 * Smart animal feeder controller.
 */
public class SmartFeederController extends DeviceController{

    public SmartFeederController(Device device) {
        super(device);
        this.isForAnimal = true;
    }
    public void use(LivingEntity user) {
        super.use(user);
        new Activity("eating from Smart Feeder", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        new Activity("eating from Smart Feeder ended", user, this.name);
    }
}
