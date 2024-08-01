package cvut.fel.omo;

import com.google.gson.Gson;
import cvut.fel.omo.accessory.Accessory;
import cvut.fel.omo.house.*;
import cvut.fel.omo.house.config.HouseConfig;
import cvut.fel.omo.house.config.HouseConfigLoader;
import cvut.fel.omo.livingentity.*;
import cvut.fel.omo.report.ActivityAndUsageReport;
import cvut.fel.omo.report.ConsumptionReport;
import cvut.fel.omo.report.EventReport;
import cvut.fel.omo.report.HouseConfigReport;
import cvut.fel.omo.accessory.devicecontroller.*;
import cvut.fel.omo.accessory.devicecontroller.device.*;
import cvut.fel.omo.accessory.devicecontroller.sensor.Sensor;
import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.utilities.Constants;
import cvut.fel.omo.accessory.vehicle.Vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


/**
 * Simulates the behavior of a smart house and its occupants over a specified time period.
 */
public class Simulation {

    /**
     * simulation duration in ticks (1 tick == 1 hour)
     */
    private int simTime = 500;

    /**
     * config file to load house from
     */
    private final String configFile = "config2.json";
    /*
    * probability 1/outsideEventProbability
    */
    private final int outsideEventProbability = 12;

    /*
     * probability 1/beingActiveProbability
     */
    private final int beingActiveProbability = 2;


    /**
     * Runs the simulation, modeling the behavior of the smart house and its occupants.
     */
    public void run(){

        AppLogger.initLogger(Constants.LOGGER_CONFIG);
        AppLogger.logger.info("running simulation");

        HouseConfigLoader houseLoader = new HouseConfigLoader();

        House house = houseLoader.buildFromConfig(configFile);
        if (house == null){
            AppLogger.logger.severe("Error loading house from config");
            return;
        }

        HouseConfigReport.getReportInstance(house).generateReport();

        for (int i = 0; i < simTime; i++){
            simulateOutsideEvent(house);
            simulateDevices(house);
            simulatePeople(house);
            simulateAnimals(house);
        }

        HouseConfigReport.getReportInstance(house).generateReport();
        ConsumptionReport.getReportInstance(house).generateReport();
        EventReport.getReportInstance().generateReport();
        ActivityAndUsageReport.getReportInstance().generateReport();
    }

    private void simulateOutsideEvent(House house){
        Random rand = new Random();

        //generate outside event with 1/outsideEventProbability
        if(rand.nextInt(outsideEventProbability) == 0){
            List<Sensor> sensors = house.getSensors();
            if (sensors != null){
                int randSensorIdx = rand.nextInt(sensors.size());
                sensors.get(randSensorIdx).outsideEventDetected();
            }

        }
    }

    private void simulateDevices(House house){
        house.getDeviceControllers().forEach(DeviceController::simulationUpdate);
    }

    private void simulateAnimals(House house){

        for (Animal animal : house.getAnimals()) {
            animal.simulationUpdate();
            if(animal.isBusy()){
                continue;
            }

            Room randRoom = getRandomRoom(house);
            animal.goToRoom(randRoom);

            if(!randRoom.getAvailableAnimalDeviceControllers().isEmpty()){
                animal.useAccessory(getRandomAnimalAccessoryFromRoom(randRoom));
            }
        }
    }

    private Room getRandomRoom(House house){
        Random rand = new Random();
        int randomRoomIndex = rand.nextInt(house.getRooms().size());
        return house.getRooms().get(randomRoomIndex);
    }

    private Accessory getRandomAccessoryFromRoom(Room room){
        Random rand = new Random();
        int randomDeviceIndex = rand.nextInt(room.getAvailableDeviceControllers().size());
        return room.getAvailableDeviceControllers().get(randomDeviceIndex);
    }

    private Accessory getRandomAnimalAccessoryFromRoom(Room room){
        Random rand = new Random();
        int randomDeviceIndex = rand.nextInt(room.getAvailableAnimalDeviceControllers().size());
        return room.getAvailableAnimalDeviceControllers().get(randomDeviceIndex);
    }

    private void simulatePeople(House house){
        Random rand = new Random();

        for (Person person: house.getFamily()){
            person.simulationUpdate();
            if (person.isBusy()){
                continue;
            }

            //be active with 1/beingActiveProbability
            if (rand.nextInt(beingActiveProbability) == 1){
                simulatePersonVehicleUsage(house, person);
            } else {
                simulatePersonDeviceUsage(house, person);
            }
        }
    }

    private void simulatePersonDeviceUsage(House house, Person person){
        Random rand = new Random();

        Room randRoom = getRandomRoom(house);
        person.goToRoom(randRoom);

        int timeToUse = rand.nextInt(Constants.MAX_USETIME) + 1;

        if(!randRoom.getAvailableDeviceControllers().isEmpty()){
            person.useObject(getRandomAccessoryFromRoom(randRoom), timeToUse);
        }
    }

    private void simulatePersonVehicleUsage(House house, Person person){
        Random rand = new Random();
        int timeToUse = rand.nextInt(Constants.MAX_USETIME) + 1;

        person.goToRoom(house.getGarage());
        List<Vehicle> availableVehicles = house.getGarage().getAvailableVehicles();
        if(!availableVehicles.isEmpty()){
            int randomVehicleIndex = rand.nextInt(availableVehicles.size());
            person.useObject(availableVehicles.get(randomVehicleIndex), timeToUse);
        }
    }
}
