package cvut.fel.omo.house.config;

import java.util.List;

/**
 * Class that provides a data structure that can be loaded from a json config file.
 */
public class RoomConfig {
    public String name;
    public List<DeviceConfig> devices;
    public int windows;
}
