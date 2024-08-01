package cvut.fel.omo.house.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cvut.fel.omo.accessory.devicecontroller.*;
import cvut.fel.omo.accessory.devicecontroller.device.*;
import cvut.fel.omo.accessory.vehicle.Vehicle;
import cvut.fel.omo.house.House;
import cvut.fel.omo.house.HouseBuilder;
import cvut.fel.omo.livingentity.*;
import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.utilities.Constants;

import java.io.File;
import java.io.IOException;


/**
 * Class that provides a bridge between json house config file and the actual House class,
 * so it is possible to load a config from json straight to a House class.
 */
public class HouseConfigLoader {

    /**
     * Build a house from a json config file.
     * @param filename Config file name (.json).
     * @return A new House object.
     */
    public House buildFromConfig(String filename){
        HouseConfig houseConfig = loadConfig(filename);

        if (houseConfig == null){
            AppLogger.logger.severe("Unable to load house from config");
            return null;

        } else {
            HouseBuilder builder = new HouseBuilder();

            Dad dad = new Dad(houseConfig.family.dad);
            Mum mum = new Mum(houseConfig.family.mum);

            addFloorsRoomsDevices(builder, houseConfig, dad, mum);
            addGarage(builder, houseConfig);

            House house = builder.build();

            addSensors(house, houseConfig);
            addFamilyMembers(house, houseConfig, dad, mum);

            return house;
        }
    }


    private HouseConfig loadConfig(String filename) {
        String filepath = Constants.CONFIG_FILE_LOCATION + filename;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), HouseConfig.class);
        } catch (IOException e) {
            AppLogger.logger.severe("Unable to open file " + filename);
            return null;
        }
    }

    private void addFamilyMembers(House house, HouseConfig houseConfig, Dad dad, Mum mum){
        house.addPerson(dad);
        house.addPerson(mum);

        for (String name: houseConfig.family.sons){
            Son son = new Son(name);
            house.addPerson(son);
        }

        for (String name: houseConfig.family.daughters){
            Daughter daughter = new Daughter(name);
            house.addPerson(daughter);
        }

        for (String name: houseConfig.family.animals){
            Animal animal = new Animal(name);
            house.addAnimal(animal);
        }
    }

    private void addSensors(House house, HouseConfig houseConfig){
        if (houseConfig.circuitBreakerSensor){
            house.addCircuitBreakerSensor();
        }
        if (houseConfig.windSensor){
            house.addWindSensor();
        }
    }

    private void addGarage(HouseBuilder builder, HouseConfig houseConfig){
        builder.addGarage(houseConfig.garage.name);
        for (String vehicleName: houseConfig.garage.vehicles){
            Vehicle vehicle = new Vehicle(vehicleName);
            builder.addVehicle(vehicle);
        }
    }

    private void addFloorsRoomsDevices(HouseBuilder builder, HouseConfig houseConfig, Dad dad, Mum mum){
        for (FloorConfig floor: houseConfig.floors){
            builder.addFloor();
            for (RoomConfig room: floor.rooms){
                builder.addRoom(room.name);
                builder.addWindows(room.windows);
                for (DeviceConfig device: room.devices){
                    switch (device.type){
                        case DeviceEnum.TV -> {
                            Television television = new Television(device.name);
                            television.attach(dad);
                            TvDeviceController tvDeviceController = new TvDeviceController(television);
                            builder.addDeviceController(tvDeviceController);
                        }
                        case DeviceEnum.BOILER -> {
                            Boiler boiler = new Boiler(device.name);
                            boiler.attach(dad);
                            BoilerDeviceController boilerDeviceController = new BoilerDeviceController(boiler);
                            builder.addDeviceController(boilerDeviceController);
                        }
                        case DeviceEnum.FRIDGE -> {
                            Fridge fridge = new Fridge(device.name);
                            fridge.attach(mum);
                            FridgeDeviceController fridgeDeviceController = new FridgeDeviceController(fridge);
                            builder.addDeviceController(fridgeDeviceController);
                        }
                        case DeviceEnum.ANIMAL_FEEDER -> {
                            SmartFeeder feeder = new SmartFeeder(device.name);
                            feeder.attach(mum);
                            SmartFeederController smartFeederController = new SmartFeederController(feeder);
                            builder.addDeviceController(smartFeederController);
                        }
                    }
                }
            }
        }
    }
}
