package cvut.fel.omo.report;

import cvut.fel.omo.utilities.AppLogger;
import cvut.fel.omo.utilities.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages and generates event reports.
 */
public class EventReport implements Report {

    private static List<Event> events;
    private static EventReport reportInstance;

    private EventReport() {}

    /**
     * Returns the singleton instance of the EventReport class.
     *
     * @return instance of the EventReport
     */
    public static EventReport getReportInstance() {
        if (reportInstance == null) {
            reportInstance = new EventReport();
            events = new ArrayList<>();
        }
        return reportInstance;
    }

    /**
     * Generates the event report and writes it to a file called "EventReport.txt".
     */
    @Override
    public void generateReport() {
        try (FileWriter writer = new FileWriter(Constants.REPORT_FILE_LOCATION + "EventReport.txt")) {
            writer.write("Event Report\n\n");

            if (events.isEmpty()) {
                writer.write("Event Report\nno events occurred\n");
            } else {
                for (Event event : events) {
                    writer.write(event.toString() + '\n');
                }
            }

            AppLogger.logger.info("Event report generated successfully!");

        } catch (IOException e) {
            AppLogger.logger.severe("Error writing to file EventReport.txt\n error message: " + e.getMessage());
        }
    }

    /**
     * Adds an event to the event report.
     *
     * @param event The event to be added to the report.
     */
    public void addEvent(Event event) {
        events.add(event);
    }
}