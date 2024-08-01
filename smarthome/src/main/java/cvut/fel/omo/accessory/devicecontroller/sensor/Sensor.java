package cvut.fel.omo.accessory.devicecontroller.sensor;

import cvut.fel.omo.report.Event;
import cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver.SensorObservableSubject;
import cvut.fel.omo.accessory.devicecontroller.sensor.sensorobserver.SensorObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a sensor and holds all valuable information about it.
 * Implements an observable subject from observer pattern.
 */
public class Sensor implements SensorObservableSubject {

    private final List<SensorObserver> observers = new ArrayList<>();
    private final String name;

    public Sensor(String name){
        this.name = name;
    }
    @Override
    public void notifyObservers() {
        observers.forEach(blinds -> blinds.updateObserver(this));
    }

    @Override
    public void attach(SensorObserver sensorObserver) {
        observers.add(sensorObserver);
    }


    @Override
    public void detach(SensorObserver sensorObserver) {
        observers.remove(sensorObserver);
    }

    public void outsideEventDetected() {
        new Event("Outside event detected", name);
        notifyObservers();
    }


}
