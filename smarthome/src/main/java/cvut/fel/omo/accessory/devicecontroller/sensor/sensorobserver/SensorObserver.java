package cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver;

import cvut.fel.omo.accessory.devicecontroller.device.Device;

/**
 * Observer pattern interface for an observer.
 * Designed specifically for {@link cvut.fel.omo.accessory.devicecontroller.DeviceController}
 * to observe {@link cvut.fel.omo.accessory.devicecontroller.sensor.Sensor}
 */
public interface SensorObserver {

    /**
     * React to a notification from an observable subject ({@link SensorObservableSubject}).
     * @param sensor The observable subject.
     */
    void updateObserver(SensorObservableSubject sensor);

}
