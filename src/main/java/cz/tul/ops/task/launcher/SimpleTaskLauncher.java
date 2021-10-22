package cz.tul.ops.task.launcher;

import cz.tul.ops.task.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleTaskLauncher implements TaskLauncher {

    private static final Map<TaskType, Task> TASK_TYPE_MAP;
    static {
        TASK_TYPE_MAP = new HashMap<>();
        TASK_TYPE_MAP.put(TaskType.FILE, new LockFileTask());
        TASK_TYPE_MAP.put(TaskType.SOCKET, new OpenSocketTask());
        TASK_TYPE_MAP.put(TaskType.MEMORY, new BigAmountOfMemoryTask());
    }


    @Override
    public void launch(List<TaskType> tasks) {
        for(Map.Entry<TaskType, Task> entry : TASK_TYPE_MAP.entrySet()) {
            Task task = entry.getValue();
            if(tasks.contains(task.getTaskType())) {
                task.setApplicationConfig();
                Thread launcher = new Thread(task);
                launcher.start();
            }
        }
    }
}
