package cz.tul.ops.task;

import cz.tul.ops.i18n.LocalConst;
import cz.tul.ops.log.Logger;

import java.util.*;


public class BigAmountOfMemoryTask implements Task {

    private static final long JVM_MEM_SIZE = 13 * 1024 * 1024;

    private final List<Double> numbers = new ArrayList<>();
    private final long limitSize;
    private volatile boolean stopped;

    public BigAmountOfMemoryTask() {
        this(300 * 1024 * 1024);
    }

    public BigAmountOfMemoryTask(long limitSize) {
        this.limitSize = limitSize;
        this.stopped = false;
    }

    @Override
    public void setApplicationConfig() {

    }

    @Override
    public TaskType getTaskType() {
        return TaskType.MEMORY;
    }

    @Override
    public void run() {
        while (!stopped) {
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - JVM_MEM_SIZE;
            if(usedMemory > limitSize) {
                if(!numbers.isEmpty()) {
                    numbers.remove(0);
                }
            } else {
                numbers.add(Math.random());
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String keyName() {
        return LocalConst.MEMORY_NAME;
    }

    @Override
    public String keyDescription() {
        return LocalConst.MEMORY_DESC;
    }

    @Override
    public void onClose() {
        Logger.debug("Closing memory task.");
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
