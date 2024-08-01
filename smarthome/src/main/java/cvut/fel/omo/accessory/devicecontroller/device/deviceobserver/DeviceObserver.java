package cvut.fel.omo.accessory.devicecontroller.device.deviceobserver;

import cvut.fel.omo.accessory.devicecontroller.device.Device;


/**
 * Observer pattern interface for an observer.
 * Designed specifically for {@link cvut.fel.omo.livingentity.Person} to observe {@link cvut.fel.omo.accessory.devicecontroller.device.Device}
 */
public interface DeviceObserver {

    /**
     * React to a notification from an observable subject ({@link DeviceObservableSubject}).
     * @param device The observable subject.
     */
    void updateObserver(Device device);

}
