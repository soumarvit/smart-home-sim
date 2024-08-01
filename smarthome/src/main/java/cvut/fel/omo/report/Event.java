package cvut.fel.omo.report;

import cvut.fel.omo.report.EventReport;
import cvut.fel.omo.utilities.AppLogger;
/**
 * Represents an event that occured.
 * Holds a description and associated device name.
 */
public class Event {
    private final String description;
    private final String deviceName;

    /**
     * Constructor of the Event class.
     *
     * @param description The description of the event.
     * @param deviceName The name of the device associated with the event.
     */
    public Event(String description, String deviceName) {
        this.description = description;
        this.deviceName = deviceName;

        AppLogger.logger.config(this.toString());
        EventReport.getReportInstance().addEvent(this);
    }
    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "Event: " + description + '\n' +
                "Device: " + deviceName + '\n';
    }
}
