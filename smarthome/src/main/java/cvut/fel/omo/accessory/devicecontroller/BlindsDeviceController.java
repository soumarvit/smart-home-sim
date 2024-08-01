package cvut.fel.omo.accessory.devicecontroller;

import cvut.fel.omo.report.Activity;
import cvut.fel.omo.report.Event;
import cvut.fel.omo.accessory.devicecontroller.device.Device;
import cvut.fel.omo.livingentity.LivingEntity;
import cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver.SensorObservableSubject;

/**
 * Represents a specialized version of {@link DeviceController} with added behavior.
 * Blinds controller.
 */
public class BlindsDeviceController extends DeviceController {

    public BlindsDeviceController(Device device) {
        super(device);
    }
    @Override
    public void updateObserver(SensorObservableSubject sensor){
        new Event(this.getDeviceName() + " were drawn up", this.getDeviceName());
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        this.turnOn();
        new Activity("drawn down", user, this.name);
    }

    @Override
    public void stopUsing(LivingEntity user) {
        super.stopUsing(user);
        this.turnOff();
        new Activity("drawn up", user, this.name);
    }
}
