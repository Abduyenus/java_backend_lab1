package ChatExceptions;
import java.io.*;
import java.util.logging.*;
//general exception handler
public class SystemExceptionHandler {
    private static final String SYS_LOG = "system.log";

    //thread-safe logging
    private static final Logger logger = Logger.getLogger(SystemExceptionHandler.class.getName());

    public static void systemException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SYS_LOG, true))) {
            writer.println(e.getMessage());
        } catch (IOException exception) {
            logger.severe("An error occurred: " + exception.getMessage());
            logger.severe("Stack trace: ");
            for (StackTraceElement element : exception.getStackTrace()) {
                logger.severe(element.toString());
            }
        }
    }
}


