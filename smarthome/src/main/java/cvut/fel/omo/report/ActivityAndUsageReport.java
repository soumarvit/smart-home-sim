package cvut.fel.omo.report;

import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.utilities.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Generates a report detailing the activities and device usages of living entities.
 */
public class ActivityAndUsageReport implements Report{
    private static List<Activity> activities;
    private static ActivityAndUsageReport reportInstance;

    private ActivityAndUsageReport(){}
    /**
     * Returns the singleton instance of the ActivityAndUsageReport class.
     *
     * @return instance of the ActivityAndUsageReport
     */
    public static ActivityAndUsageReport getReportInstance(){
        if (reportInstance == null){
            reportInstance = new ActivityAndUsageReport();
            activities = new ArrayList<>();
        }
        return reportInstance;
    }

    /**
     * Generates the activity and usage report and writes to a file calles "ActivityAndUsageReport.txt"
     */
    @Override
    public void generateReport() {

        try (FileWriter writer = new FileWriter(Constants.REPORT_FILE_LOCATION + "ActivityAndUsageReport.txt")) {
            writer.write("Activity and Usage Report\n\n");
            if(activities.isEmpty()){
                writer.write("Activity and Usage Report\nno activities happened\n");
            } else {
                for (Activity activity : activities) {
                    writer.write(activity.toString() + '\n');
                }
            }

            AppLogger.logger.info("Activity and Usage report generated successfully!");

        } catch (IOException e) {
            AppLogger.logger.severe("Error writing to file ActivityAndUsageReport.txt\n error message: " + e.getMessage());
        }
    }
    /**
     * Adds an activity to the activity and usage report.
     *
     * @param activity The activity to be added to the report.
     */
    public void addActivity(Activity activity){
        activities.add(activity);
    }
}
