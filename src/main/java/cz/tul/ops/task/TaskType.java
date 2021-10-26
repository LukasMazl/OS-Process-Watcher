package cz.tul.ops.task;

public enum TaskType {
    MEMORY("memory.name", "memory.desc"),
    SOCKET("socket.name", "socket.desc"),
    FILE("file.name", "file.desc");

    private String name;
    private String desc;

    TaskType(String name, String key) {
        this.name = name;
        this.desc = key;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static TaskType getValue(String task) {
        for (TaskType taskType : values()) {
            if (taskType.toString().startsWith(task.toUpperCase())) {
                return taskType;
            }
        }
        return null;
    }
}
