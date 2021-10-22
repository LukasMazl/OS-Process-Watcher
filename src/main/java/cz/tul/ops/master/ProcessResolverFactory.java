package cz.tul.ops.master;

public final class ProcessResolverFactory {

    private static final ProcessResolver PROCESS_RESOLVER_INSTANCE;

    static {
        if (isWindows()) {
            PROCESS_RESOLVER_INSTANCE = new Window32ProcessResolver();
        } else if (isUnix() || isMacOsX()) {
            PROCESS_RESOLVER_INSTANCE = new UnixProcessResolver();
        } else {
            throw new IllegalArgumentException("Illegal OS");
        }
    }

    private ProcessResolverFactory() {
        throw new RuntimeException("This constructor is not supported!");
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix() {
        return getOsName().startsWith("Unix");
    }

    private static boolean isMacOsX() {
        return getOsName().startsWith("Mac OS X");
    }

    public static ProcessResolver getInstance() {
        return PROCESS_RESOLVER_INSTANCE;
    }
}
