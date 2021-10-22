package cz.tul.ops.master;

import cz.tul.ops.Application;
import cz.tul.ops.conf.ApplicationConfig;
import cz.tul.ops.log.Logger;
import cz.tul.ops.master.process.ProcessWatcher;
import cz.tul.ops.task.TaskType;

import java.io.IOException;
import java.util.List;

public class SimpleProcessLauncher implements ProcessLauncher {

    private static final String LAUNCHER_COMMAND = "java -jar ";

    private ProcessResolver processResolver;
    private Process currentProcess;

    public SimpleProcessLauncher() {
        this.processResolver = ProcessResolverFactory.getInstance();
    }

    @Override
    public long launch(List<TaskType> taskTypes) throws IOException, NoSuchFieldException, IllegalAccessException {
        if (currentProcess != null && currentProcess.isAlive()) {
            throw new IOException("Current process already running!");
        }
        String preparedCommand = prepareCommand(taskTypes);
        Logger.debug(String.format("Prepared command: %s", preparedCommand));
        currentProcess = Runtime.getRuntime().exec(preparedCommand);
        long pid = processResolver.getPID(currentProcess);
        registerNewProcess(pid, currentProcess, taskTypes);
        return pid;
    }

    private void registerNewProcess(long pid, Process currentProcess, List<TaskType> taskTypes) {
        Logger.debug(String.format("Registering new process with PID %d", pid));
        ProcessWatcher.registerWatchableProcess(pid, currentProcess, taskTypes);
        Logger.debug(String.format("Process with PID %d is registered", pid));
    }

    private String prepareCommand(List<TaskType> taskTypes) {
        StringBuilder stringCommandBuilder = new StringBuilder();
        addNewCommand(stringCommandBuilder, "java -jar ./target/OperationSystems-1.0-SNAPSHOT.jar");
        addNewCommand(stringCommandBuilder,"-slave");
        if(ApplicationConfig.isDebug()) {
            addNewCommand(stringCommandBuilder,"-d");
        }

        String prepareTaskTypeCommand = buildTaskTypeCommand(taskTypes);
        addNewCommand(stringCommandBuilder, prepareTaskTypeCommand);

        return stringCommandBuilder.toString();
    }

    private String buildTaskTypeCommand(List<TaskType> taskTypes) {
        StringBuilder taskTypeBuilder = new StringBuilder();
        taskTypeBuilder.append("-tasks");
        boolean first = true;
        for(TaskType taskType : taskTypes) {
            if(first) {
                taskTypeBuilder.append(taskType.toString());
                first = false;
            } else {
                taskTypeBuilder.append(",");
                taskTypeBuilder.append(taskType.toString());
            }
        }
        return taskTypeBuilder.toString();
    }

    private void addNewCommand(StringBuilder stringBuilder, String command) {
        stringBuilder.append(command);
        stringBuilder.append(" ");
    }

    @Override
    protected void finalize() throws Throwable {
        if (currentProcess != null) {
            Logger.debug("Killing process with: " + processResolver.getPID(currentProcess));
            currentProcess.destroy();
        }
    }
}
