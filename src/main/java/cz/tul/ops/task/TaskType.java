package cz.tul.ops.task;

public enum TaskType {
    MEMORY,
    SOCKET,
    FILE;

    public static TaskType getValue(String task) {
        for (TaskType taskType : values()) {
            if (taskType.toString().startsWith(task.toUpperCase())) {
                return taskType;
            }
        }
        return null;
    }
}
