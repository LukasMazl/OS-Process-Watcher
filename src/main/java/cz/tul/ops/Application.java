package cz.tul.ops;

import cz.tul.ops.conf.ApplicationConfig;
import cz.tul.ops.i18n.LocalConst;
import cz.tul.ops.log.Logger;
import cz.tul.ops.master.ProcessLauncher;
import cz.tul.ops.master.SimpleProcessLauncher;
import cz.tul.ops.task.TaskType;
import cz.tul.ops.task.launcher.SimpleTaskLauncher;
import cz.tul.ops.task.launcher.TaskLauncher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static int killed_processed = 0;
    private static final List<TaskType> TASK_TYPE_LIST = Arrays.asList(TaskType.FILE, TaskType.MEMORY, TaskType.SOCKET);

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
        Logger.print(LocalConst.WELCOME_MESSAGE);
        Logger.print(LocalConst.WHAT_TO_DO);
        Logger.print(LocalConst.PROGRAM_DESC);

        callNextTask();
    }

    private static void callNextTask() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        if(killed_processed >= TASK_TYPE_LIST.size()) {
            return;
        }

        ProcessLauncher processLauncher = new SimpleProcessLauncher();
        TaskType taskType = TASK_TYPE_LIST.get(killed_processed);
        processLauncher.launch(Arrays.asList(taskType), Application::onProcessEnd);
        Logger.print(taskType.getName());
        Logger.print(taskType.getDesc());
    }

    public static void onProcessEnd() {
        killed_processed++;
        try {
            Logger.print(LocalConst.PREFIX_PROCESS_KILLED_ + killed_processed);
            callNextTask();
        } catch (Exception e) {
            Logger.error(e);
        }
    }
}
