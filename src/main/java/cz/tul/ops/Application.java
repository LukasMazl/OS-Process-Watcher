package cz.tul.ops;

import cz.tul.ops.conf.ApplicationConfig;
import cz.tul.ops.log.Logger;
import cz.tul.ops.master.ProcessLauncher;
import cz.tul.ops.master.SimpleProcessLauncher;
import cz.tul.ops.task.TaskType;
import cz.tul.ops.task.launcher.SimpleTaskLauncher;
import cz.tul.ops.task.launcher.TaskLauncher;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationConfig.init(args);
        if (ApplicationConfig.isMaster()) {
            runMasterMode();
        } else {
            runSlaveMode();
        }
    }

    private static void runSlaveMode() {
        List<TaskType> taskTypes = ApplicationConfig.getTaskTypes();
        Logger.debug(String.format("Slave active with mode %s", taskTypes));
        TaskLauncher taskLauncher = new SimpleTaskLauncher();
        taskLauncher.launch(ApplicationConfig.getTaskTypes());
    }

    public static void runMasterMode() throws Exception {
        Logger.debug("Master mode started!");
        for(TaskType taskType : ApplicationConfig.getTaskTypes()) {
            ProcessLauncher processLauncher = new SimpleProcessLauncher();
            processLauncher.launch(Arrays.asList(taskType));
        }
    }

}
