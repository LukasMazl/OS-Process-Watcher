package cz.tul.ops.master.process;

import cz.tul.ops.log.Logger;
import cz.tul.ops.task.TaskType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProcessWatcher {

    protected static final Map<Long, TaskProcessWrapper> REGISTERED_PROCESS;
    private static WatcherThread watcherThread;
    static {
        REGISTERED_PROCESS = Collections.synchronizedMap(new HashMap<>());

        watcherThread = new WatcherThread();
        initWatcher();
    }

    public static void registerWatchableProcess(long pid, Process process, List<TaskType> taskTypes) {
        REGISTERED_PROCESS.put(pid, new TaskProcessWrapper(pid, process, taskTypes));
    }

    public static void unregisterWatchableProcess(long pid) {
        synchronized (ProcessWatcher.class) {
            REGISTERED_PROCESS.remove(pid);
            if (REGISTERED_PROCESS.isEmpty()) {
                watcherThread.setStopped(true);
            }
        }
    }

    public static void initWatcher() {
        if(watcherThread != null) {
            watcherThread.start();
        } else {
            Logger.debug("No thread watcher found!");
        }
    }
}
