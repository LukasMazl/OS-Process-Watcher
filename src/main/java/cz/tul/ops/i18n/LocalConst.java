package cz.tul.ops.i18n;

public class LocalConst {

    public static final String WELCOME_MESSAGE = "welcome.message";

    public static final String WHAT_TO_DO = "what.to.do";

    public static final String PROGRAM_DESC = "program.desc";

    public static final String FILE_NAME = "file.name";

    public static final String FILE_DESC = "file.desc";

    public static final String MEMORY_NAME = "memory.name";

    public static final String MEMORY_DESC = "memory.desc";

    public static final String SOCKET_NAME = "socket.name";

    public static final String SOCKET_DESC = "socket.desc";

    public static final String PREFIX_PROCESS_KILLED_ = "process.kill.";

    public static final String PROCESS_KILLED_1 = PREFIX_PROCESS_KILLED_ + "1";

    public static final String PROCESS_KILLED_2 = PREFIX_PROCESS_KILLED_ + "2";

    public static final String PROCESS_KILLED_3 = PREFIX_PROCESS_KILLED_ + "3";

    private LocalConst() throws IllegalAccessException {
        throw new IllegalAccessException("Unsupported operation");
    }
}
