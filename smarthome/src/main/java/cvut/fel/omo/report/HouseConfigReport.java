package cvut.fel.omo.report;
import cvut.fel.omo.house.Floor;
import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.house.House;
import cvut.fel.omo.utilities.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Generates a report detailing the configuration of a house.
 */
public class HouseConfigReport implements Report{

    private House house;
    private static HouseConfigReport reportInstance;


    private HouseConfigReport(){};
    private HouseConfigReport(House house){
        this.house = house;
    }
    /**
     * Returns the singleton instance of the HouseConfigReport class.
     *
     * @return instance of the HouseConfigReport
     */
    public static HouseConfigReport getReportInstance(){
        if(reportInstance == null){
            reportInstance = new HouseConfigReport();
        }
        return reportInstance;
    }
    /**
     * Returns the singleton instance of the HouseConfigReport class.
     *
     * @param house The house to generate the report for.
     * @return instance of the HouseConfigReport
     */
    public static HouseConfigReport getReportInstance(House house){
        if(reportInstance == null){
            reportInstance = new HouseConfigReport(house);
        }
        return reportInstance;
    }
    /**
     * Generates the house configuration report and writes it to a file called "HouseConfigReport.txt".
     */
    @Override
    public void generateReport(){
        if(house == null){
            AppLogger.logger.severe("House not set, can't generate HouseConfigReport");
            return;
        }

        try (FileWriter writer = new FileWriter(Constants.REPORT_FILE_LOCATION + "HouseConfigurationReport.txt")) {
            writer.write("House Configuration Report\n\n");

            String familyStr = house.getFamily().stream()
                    .map(member -> "\t" + member.getName())
                    .collect(Collectors.joining("\n"));
            int familySize = house.getFamily().size();

            writer.write("Family: " + familySize + " members\n");
            writer.write(familyStr + "\n\n");

            int numGarage = (house.getGarage() == null) ? 0 : 1;

            writer.write("House: " + house.getFloors().size() + " floors, " + numGarage +" garage\n\n");
            writer.write(house.getGarage().toString());

            for(Floor floor: house.getFloors()) {
                writer.write("Floor " + (house.getFloors().indexOf(floor) + 1) + ": " + floor.getRooms().size() + " rooms\n");

                String roomDetails = floor.getRooms().stream()
                        .map(room -> "\t" + room.getName() + ": " + room.getWindows().size() + " windows\n\t\tDevices: "
                                + room.getDeviceControllers().stream().
                                map(deviceController -> deviceController.getDeviceName())
                                .collect(Collectors.joining(", ")))
                        .collect(Collectors.joining("\n"));

                writer.write(roomDetails + "\n\n");
            }
            AppLogger.logger.info("House config report generated successfully!");

        } catch (IOException e) {
            AppLogger.logger.severe("Error writing to file HouseConfigurationReport.txt\n error message: " + e.getMessage());
        }
    }
    /**
     * Sets the house for which the report will be generated.
     *
     * @param house The house to set.
     */
    public void setHouse(House house) {
        this.house = house;
    }
}


















