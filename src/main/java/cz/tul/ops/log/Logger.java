package cz.tul.ops.log;

import cz.tul.ops.conf.ApplicationConfig;

import java.util.Date;

import static cz.tul.ops.i18n.LocalService.getResourceBundle;

public final class Logger {
    public static void debug(String msg) {
        if(ApplicationConfig.isDebug() && !ApplicationConfig.isPrintForUser()) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), msg);
        }
    }

    public static void error(Exception exception) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.err.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), exception.getLocalizedMessage());
    }

    public static void processDebug(String msg, long pid) {
        if(ApplicationConfig.isDebug() && !ApplicationConfig.isPrintForUser()) {
            System.out.printf("[%s] - [pid %d] %s\n", new Date(), pid, msg);
        }
    }

    public static void info(String msg) {
        if(!ApplicationConfig.isPrintForUser()) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            System.out.printf("[%s] - %s: %s\n", new Date(), stackTraceElements[2].toString(), msg);
        }
    }

    public static void print(String key) throws InterruptedException {
        if(ApplicationConfig.isPrintForUser()) {
            System.out.println(getResourceBundle().getString(key));
            delay(3500);
        }
    }

    private static void delay(long delay) throws InterruptedException {
        Thread.sleep(delay);
    }
}
