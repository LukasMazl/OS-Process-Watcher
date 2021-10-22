package cz.tul.ops.master.process;

import cz.tul.ops.task.TaskType;

import java.util.List;

public class TaskProcessWrapper {
    private long pid;
    private Process process;
    private List<TaskType> taskTypes;

    public TaskProcessWrapper(long pid, Process process, List<TaskType> taskTypes) {
        this.pid = pid;
        this.process = process;
        this.taskTypes = taskTypes;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public List<TaskType> getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(List<TaskType> taskTypes) {
        this.taskTypes = taskTypes;
    }
}
