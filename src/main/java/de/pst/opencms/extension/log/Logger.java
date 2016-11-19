package de.pst.opencms.extension.log;

import org.apache.commons.logging.Log;
import org.opencms.main.CmsLog;

public class Logger {
    private static Log logTarget;


    public static void forClass(Class clazz) {
        logTarget = CmsLog.getLog(clazz);

    }

    public static void debug(String message) {
        logTarget.debug(message);
    }

    public static void info(String message) {
        logTarget.info(message);
    }

    public static void warn(String message) {
        logTarget.warn(message);
    }

    public static void error(String message) {
        logTarget.error(message);
    }

    public static void fatal(String message) {
        logTarget.fatal(message);
    }

}
