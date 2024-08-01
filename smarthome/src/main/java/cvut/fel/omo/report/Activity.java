package cvut.fel.omo.report;

import cvut.fel.omo.livingentity.LivingEntity;
import cvut.fel.omo.utilities.AppLogger;

/**
 * Represents an activity performed by a living entity.
 */
public class Activity {
    private final String description;
    private final LivingEntity user;
    private final String usedObjectName;
    private final int duration;
    /**
     * Constructor of the activity class.
     *
     * @param description The description of the activity.
     * @param user The living entity performing the activity.
     * @param usedObjectName The name of the object used in the activity.
     */
    public Activity(String description, LivingEntity user, String usedObjectName){
        this.description = description;
        this.user = user;
        this.usedObjectName = usedObjectName;
        this.duration = user.getBusyCountdown();

        AppLogger.logger.config(this.toString());
        ActivityAndUsageReport.getReportInstance().addActivity(this);
    }
    /**
     * Returns a string representation of the activity.
     *
     * @return A string representation of the activity.
     */
    @Override
    public String toString() {
        String durationStr;
        if (duration == 0){
            durationStr = "Activity ended";
        } else {
            durationStr = Integer.toString(duration);
        }
        return "Activity: " + description + '\n' +
                "User: " + user.getName() + '\n' +
                "Used object: " + usedObjectName + '\n' +
                "Duration: " + durationStr + '\n';
    }
}
