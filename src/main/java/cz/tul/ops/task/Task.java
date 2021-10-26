package cz.tul.ops.task;

public interface Task extends Runnable, OnProgramClose, Configurable {

    TaskType getTaskType();

    String keyName();

    String keyDescription();

}
