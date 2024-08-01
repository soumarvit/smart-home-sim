package cvut.fel.omo.report;

import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.utilities.Constants;
import cvut.fel.omo.house.House;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Generates a report detailing the consumption of devices.
 */
public class ConsumptionReport implements Report{
    private House house;
    private static ConsumptionReport reportInstance;
    private ConsumptionReport(){};
    private ConsumptionReport(House house){
        this.house = house;
    };
    /**
     * Returns the singleton instance of the ConsumptionReport class.
     *
     * @return instance of the ConsumptionReport
     */
    public static ConsumptionReport getReportInstance(){
        if (reportInstance == null){
            reportInstance = new ConsumptionReport();
        }
        return reportInstance;
    }
    /**
     * Returns the singleton instance of the ConsumptionReport class.
     *
     * @param house The house to generate the report for.
     * @return instance of the ConsumptionReport
     */
    public static ConsumptionReport getReportInstance(House house){
        if (reportInstance == null){
            reportInstance = new ConsumptionReport(house);
        }
        return reportInstance;
    }
    /**
     * Generates the consumption report and writes it to a file called "ConsumptionReport.txt".
     */
    public void generateReport(){
        if(house == null){
            AppLogger.logger.severe("House not set, can't generate ConsumptionReport");
            return;
        }

        try(FileWriter writer = new FileWriter(Constants.REPORT_FILE_LOCATION + "ConsumptionReport.txt")) {
            writer.write("Consumption Report\n\n");

            String consumptionDetails = house.getDeviceControllers().stream()
                    .map(deviceController -> deviceController.getDeviceName() + "\n" +
                            deviceController.getConsumptionData().toString() + '\n')
                    .collect(Collectors.joining());

            writer.write(consumptionDetails);

            String totalConsumption = "electricity total: " + calculateTotalElectricityConsumption() + " kWh\n" +
                    "water total: " + calculateTotalWaterConsumption() + " m^3\n" +
                    "gas total: " + calculateTotalGasConsumption() + " kWh\n\n";

            writer.write("Total consumption\n" + totalConsumption);
            writer.write("Total cost: " + calculateCost() + " Kč\n");

            AppLogger.logger.info("Consumption report generated successfully!");

        }  catch (IOException e) {
            AppLogger.logger.severe("Error writing to file ConsumptionReport.txt\n error message: " + e.getMessage());
        }
    }

    private double calculateTotalGasConsumption(){
        return house.getDeviceControllers().stream()
                .mapToDouble(deviceController -> deviceController.getConsumptionData().getGasUsage())
                .sum();
    }
    private double calculateTotalWaterConsumption(){
        return house.getDeviceControllers().stream()
                .mapToDouble(deviceController -> deviceController.getConsumptionData().getWaterUsage())
                .sum();
    }
    private double calculateTotalElectricityConsumption(){
        return house.getDeviceControllers().stream()
                .mapToDouble(deviceController -> deviceController.getConsumptionData().getElectricityUsage())
                .sum();
    }

    private double calculateCost(){
        return Constants.ELECTRICITY_PRICE * calculateTotalElectricityConsumption() +
                Constants.GAS_PRICE * calculateTotalGasConsumption() +
                Constants.WATER_PRICE * calculateTotalWaterConsumption();
    }
}
