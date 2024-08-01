package cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver;


/**
 * Observer pattern interface for observable subject.
 * Designed specifically for {@link cvut.fel.omo.accessory.devicecontroller.DeviceController}
 * to observe {@link cvut.fel.omo.accessory.devicecontroller.sensor.Sensor}
 */
public interface SensorObservableSubject {

    /**
     * Notifies all attached observers.
     */
    void notifyObservers();

    /**
     * Attaches an observer to sensor.
     * @param sensorObserver Observer to attach.
     */
    void attach(SensorObserver sensorObserver);

    /**
     * Detaches an observer to sensor.
     * @param sensorObserver Observer to attach.
     */
    void detach(SensorObserver sensorObserver);
}
