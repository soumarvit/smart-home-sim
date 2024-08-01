package cvut.fel.omo.accessory.devicecontroller.device.deviceobserver;

/**
 * Observer pattern interface for observable subject.
 * Designed specifically for {@link cvut.fel.omo.livingentity.Person} to observe {@link cvut.fel.omo.accessory.devicecontroller.device.Device}
 */
public interface DeviceObservableSubject{

    /**
     * Notifies all attached observers.
     */
    void notifyObservers();

    /**
     * Attaches an observer to device.
     * @param deviceObserver Observer to attach.
     */
    void attach(DeviceObserver deviceObserver);

    /**
     * Detaches an observer from device.
     * @param deviceObserver Observer to detach.
     */
    void detach(DeviceObserver deviceObserver);
}
