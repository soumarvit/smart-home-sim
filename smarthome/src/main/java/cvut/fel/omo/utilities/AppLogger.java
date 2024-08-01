package cvut.fel.omo.utilities;

import java.text.SimpleDateFormat;
import java.util.logging.*;

/**
 * Utility class for using the application logger.
 */
public class AppLogger {
    /**
     * The static logger instance for the application.
     */
    public static final Logger logger= Logger.getLogger("Main logger");

    private AppLogger(){
    }

    public static void initLogger(int loggerLevel){

        switch (loggerLevel){
            case Constants.LOGGER_FINEST -> logger.setLevel(Level.FINEST);
            case Constants.LOGGER_CONFIG -> logger.setLevel(Level.CONFIG);
            case Constants.LOGGER_INFO -> logger.setLevel(Level.INFO);
            case Constants.LOGGER_SEVERE -> logger.setLevel(Level.SEVERE);
            case Constants.LOGGER_OFF -> logger.setLevel(Level.OFF);
        }


        for (Handler handler : logger.getHandlers()) {
            handler.setFormatter(new CustomFormatter());
            logger.addHandler(handler);
        }

        CustomFormatter formatter = new CustomFormatter();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(formatter);

        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
    }


    private static class CustomFormatter extends Formatter {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd yyyy, h:mm:ss a");

        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder();
            //builder.append(dateFormat.format(new Date(record.getMillis()))).append(" ");
            //builder.append(record.getLevel()).append(": ");
            builder.append(record.getMessage()).append("\n");
            return builder.toString();
        }
    }
}
