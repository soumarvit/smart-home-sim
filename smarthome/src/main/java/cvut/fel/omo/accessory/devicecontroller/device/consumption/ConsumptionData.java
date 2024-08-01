package cvut.fel.omo.accessory.devicecontroller.device.consumption;

/**
 * Container to store total water, gas and water usage with the ability to updates these values.
 */
public class ConsumptionData {
    private double waterUsage = 0;
    private double electricityUsage = 0;
    private double gasUsage = 0;

    /**
     * Update the total water, gas and electricity usage by adding the data from {@param consumption} to it.
     * @param consumption Current water, gas and electricity consumption that will be added up to the total usage.
     */
    public void update(Consumption consumption){
        waterUsage += consumption.getWaterConsumption();
        electricityUsage += consumption.getElectricityConsumption();
        gasUsage += consumption.getGasConsumption();
    }

    public double getWaterUsage() {
        return waterUsage;
    }

    public double getElectricityUsage() {
        return electricityUsage;
    }

    public double getGasUsage() {
        return gasUsage;
    }

    @Override
    public String toString() {
        return "Electricity usage: " + electricityUsage + " kWh\n" +
                "Water usage: " + waterUsage + " m^3\n" +
                "Gas usage: " + gasUsage + " kWh\n";
    }
}
