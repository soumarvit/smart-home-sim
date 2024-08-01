package cvut.fel.omo.house.config;

import cvut.fel.omo.house.Floor;

import java.util.List;

/**
 * Class that provides a data structure that can be loaded from a json config file.
 */
public class HouseConfig {
    public String name;
    public FamilyConfig family;
    public List<FloorConfig> floors;

    public GarageConfig garage;

    public boolean windSensor;
    public boolean circuitBreakerSensor;
    public boolean fireSensor;
}
