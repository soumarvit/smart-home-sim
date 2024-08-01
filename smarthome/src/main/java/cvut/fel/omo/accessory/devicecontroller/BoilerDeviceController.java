package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Represents a specialized version of {@link DeviceController} with added behavior.
 * Boiler controller.
 */
public class BoilerDeviceController extends DeviceController {
    public BoilerDeviceController(Device device) {
        super(device);
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        new Activity("getting hot water", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        new Activity("stopped getting hot water", user, this.name);
    }
}
