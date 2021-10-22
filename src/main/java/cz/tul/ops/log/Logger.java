package cz.tul.ops.log;

import cz.tul.ops.conf.ApplicationConfig;

import java.io.IOException;
import java.util.Date;

public final class Logger {
    public static void debug(String msg) {
        if(ApplicationConfig.isDebug()) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), msg);
        }
    }

    public static void error(IOException ioException) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.err.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), ioException.getLocalizedMessage());
    }

    public static void processDebug(String msg, long pid) {
        if(ApplicationConfig.isDebug()) {
            System.out.printf("[%s] - [pid %d] %s\n", new Date(), pid, msg);
        }
    }

    public static void info(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), msg);
    }
}
