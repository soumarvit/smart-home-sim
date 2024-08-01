package cvut.fel.omo.accessory.devicecontroller.device.consumption;

/**
 * Container to store water, gas and electricity consumption.
 */
public class Consumption {
    /**
     * water consumption in m^3 per time unit
     */
    private final double waterConsumption;

    /**
     * gas consumption in kWh per time unit
     */
    private final double gasConsumption;

    /**
     * electricity consumption in kWh per time unit
     */
    private final double electricityConsumption;

    public Consumption(double waterConsumption, double gasConsumption, double electricityConsumption) {
        this.waterConsumption = waterConsumption;
        this.gasConsumption = gasConsumption;
        this.electricityConsumption = electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public double getGasConsumption() {
        return gasConsumption;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

}
