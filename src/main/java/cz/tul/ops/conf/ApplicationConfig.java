package cz.tul.ops.conf;

import cz.tul.ops.task.TaskType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationConfig {

    private static Map<ConfigParam, Object> configParamObjectMap;
    private static ConfigurationParser configurationParser;

    static {
        configParamObjectMap = new HashMap<>();
        configurationParser = new SimpleConfigurationParser();
    }

    public static void init(String[] args) {
        configParamObjectMap = configurationParser.parse(args);
    }

    public static boolean isMaster() {
        return configParamObjectMap.get(ConfigParam.MASTER) != null;
    }

    public static List<TaskType> getTaskTypes() {
        List<TaskType> taskTypes = new ArrayList<>();
        String[] tasks = (String[]) configParamObjectMap.get(ConfigParam.RUN_MODE);
        if(tasks == null)
            return taskTypes;

        for(String task : tasks) {
            TaskType taskType = TaskType.getValue(task);
            taskTypes.add(taskType);
        }
        return taskTypes;
    }

    public static boolean isDebug() {
        return configParamObjectMap.get(ConfigParam.DEBUG_MODE) != null;
    }

    public static int getPortCounter() {
        return 4000;
    }
}
