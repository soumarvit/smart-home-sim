package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;

/**
 * Represents a specialized version of {@link DeviceController} with added behavior.
 * Television controller.
 */
public class TvDeviceController extends DeviceController{
    public TvDeviceController(Device device) {
        super(device);
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        turnOn();
        new Activity("Watching tv", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        turnOff();
        new Activity("Stopped watching tv", user, this.name);
    }
}
