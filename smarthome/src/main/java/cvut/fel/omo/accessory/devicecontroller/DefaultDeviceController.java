package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Represents a default but specialized version of {@link DeviceController} with added behavior.
 * Default controller.
 */
public class DefaultDeviceController extends DeviceController {
    public DefaultDeviceController(Device device) {
        super(device);
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        new Activity("used", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        new Activity("stopped using", user, this.name);
    }
}
