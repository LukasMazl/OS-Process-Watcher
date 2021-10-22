package cz.tul.ops.master;

import java.lang.reflect.Field;

public class UnixProcessResolver implements ProcessResolver {

    @Override
    public long getPID(Process process) throws IllegalAccessException, NoSuchFieldException {
        Field field = findField(process.getClass().getDeclaredFields());
        if(field == null) {
            throw new NoSuchFieldException("No PID field found");
        }
        field.setAccessible(true);
        int pid = (int) field.get(process);
        return pid;
    }

    private Field findField(Field[] fields) {
        for(Field field : fields) {
            if(field.getName().compareTo("pid") == 0) {
                return field;
            }
        }
        return null;
    }
}
