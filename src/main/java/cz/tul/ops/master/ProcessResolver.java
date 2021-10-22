package cz.tul.ops.master;

public interface ProcessResolver {

    long getPID(Process process) throws NoSuchFieldException, IllegalAccessException;
}
