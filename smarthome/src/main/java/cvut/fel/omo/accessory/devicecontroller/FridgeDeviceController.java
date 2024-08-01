package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Represents a specialized version of {@link DeviceController} with added behavior.
 * Fridge controller.
 */
public class FridgeDeviceController extends DeviceController {
    public FridgeDeviceController(Device device) {
        super(device);
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        new Activity("opened fridge", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        new Activity("closed fridge", user, this.name);
    }
}
