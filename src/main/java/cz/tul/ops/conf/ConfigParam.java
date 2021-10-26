package cz.tul.ops.conf;

public enum ConfigParam {
    MASTER("-master", false),
    SLAVE("-slave", false),
    SOCKET_NUMBER("-socket", false),
    FILE_NUMBER("-fileNumber", false),
    MEMORY_SIZE("-memorySize", false),
    RUN_MODE("-tasks", true),
    DEBUG_MODE("-d", false),
    USER_MODE("--user", false);

    private String param;
    private boolean isArray;

    ConfigParam(String param, boolean isArray) {
        this.param = param;
        this.isArray = isArray;
    }

    public static ConfigParam getValue(String value) {
        for(ConfigParam conParam : values()) {
            if(value.startsWith(conParam.param)) {
                return conParam;
            }
        }
        return null;
    }

    public String getParam() {
        return param;
    }

    public boolean isArray() {
        return isArray;
    }
}
