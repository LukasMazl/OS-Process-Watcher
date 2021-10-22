package cz.tul.ops.master.process;

import cz.tul.ops.log.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WatcherThread extends Thread {
    private volatile boolean stopped;

    @Override
    public void run() {
        stopped = false;
        while (!stopped) {
            Map<Long, TaskProcessWrapper> registeredProcess;
            synchronized (WatcherThread.class) {
                registeredProcess = makeCopyMap(ProcessWatcher.REGISTERED_PROCESS);
            }

            for (Map.Entry<Long, TaskProcessWrapper> entry : registeredProcess.entrySet()) {
                TaskProcessWrapper taskProcessWrapper = entry.getValue();

                if (taskProcessWrapper.getProcess().isAlive()) {
                    printInputFromProcess(taskProcessWrapper);
                } else {
                    unregisterProcess(taskProcessWrapper);
                }
            }

            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private Map<Long, TaskProcessWrapper> makeCopyMap(Map<Long, TaskProcessWrapper> registeredProcess) {
        Map<Long, TaskProcessWrapper> clone = new HashMap<>();
        for (Map.Entry<Long, TaskProcessWrapper> entry : registeredProcess.entrySet()) {
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone;
    }

    private void unregisterProcess(TaskProcessWrapper taskProcessWrapper) {
        ProcessWatcher.unregisterWatchableProcess(taskProcessWrapper.getPid());
        Logger.info(String.format("Process with PID %d was killed.", taskProcessWrapper.getPid()));
    }

    private void printInputFromProcess(TaskProcessWrapper taskProcessWrapper) {
        Scanner scanner = new Scanner(taskProcessWrapper.getProcess().getInputStream());
        while (scanner.hasNextLine()) {
            Logger.processDebug(scanner.nextLine(), taskProcessWrapper.getPid());
        }
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
