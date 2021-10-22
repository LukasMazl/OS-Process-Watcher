package cz.tul.ops.task;

import java.io.IOException;

public interface OnProgramClose {

    void onClose() throws IOException;
}
