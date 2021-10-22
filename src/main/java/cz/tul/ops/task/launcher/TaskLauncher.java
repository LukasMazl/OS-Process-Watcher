package cz.tul.ops.task.launcher;

import cz.tul.ops.task.TaskType;

import java.util.List;

public interface TaskLauncher {

    void launch(List<TaskType> tasks);
}
