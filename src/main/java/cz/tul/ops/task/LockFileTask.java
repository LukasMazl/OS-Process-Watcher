package cz.tul.ops.task;

import cz.tul.ops.i18n.LocalConst;
import cz.tul.ops.log.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LockFileTask implements Task {

    private static final String ROOT_FOLDER = "./lockedFiles/";
    private static final Set<String> FILE_PATHS = new HashSet<>();

    static {
        FILE_PATHS.add(ROOT_FOLDER + "lock.txt");
    }

    private List<FileOutputStream> fileOutputStreamList;
    private List<FileLock> fileLocks;

    public LockFileTask() {
        this.fileOutputStreamList = new ArrayList<>();
        this.fileLocks = new ArrayList<>();
    }

    @Override
    public void setApplicationConfig() {

    }

    private void initFiles() {
        for (String filePath : FILE_PATHS) {
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    File root = new File(ROOT_FOLDER);
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    FileLock fileLock = fileOutputStream.getChannel().lock();
                    writeStringToFile(fileOutputStream, "Guess who wrote this :)");
                    fileOutputStreamList.add(fileOutputStream);
                    fileLocks.add(fileLock);
                }
            } catch (IOException ioException) {
                Logger.error(ioException);
            }
        }
    }

    private void writeStringToFile(FileOutputStream fileOutputStream, String s) throws IOException {
        fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.FILE;
    }

    @Override
    public void run() {
        initFiles();

        while (true) {
            try {
                for (FileOutputStream fileOutputStream : fileOutputStreamList) {
                    writeStringToFile(fileOutputStream, "Tik tok");
                }
                Thread.currentThread().sleep(20);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClose() {
        Logger.debug("Closing File task.");
    }

    @Override
    public String keyName() {
        return LocalConst.FILE_NAME;
    }

    @Override
    public String keyDescription() {
        return LocalConst.FILE_DESC;
    }
}
