package cz.tul.ops.master;

public class Window32ProcessResolver implements ProcessResolver {

    @Override
    public long getPID(Process process) {
        System.out.println(process.getClass().getName());
        return 0;
    }
}
