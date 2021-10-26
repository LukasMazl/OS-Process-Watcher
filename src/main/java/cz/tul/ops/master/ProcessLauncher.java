package cz.tul.ops.master;

import cz.tul.ops.task.TaskType;

import java.io.IOException;
import java.util.List;

public interface ProcessLauncher {

    /**
     * Launch process and returns PID
     *
     * @param taskTypes
     * @return Process ID
     */
    long launch(List<TaskType> taskTypes, Runnable onEnd) throws IOException, NoSuchFieldException, IllegalAccessException;
}
