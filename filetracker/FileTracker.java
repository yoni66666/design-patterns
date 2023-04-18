/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: filetracker
 */
package il.co.ilrd.filetracker;

import java.io.File;
import java.nio.file.NotDirectoryException;

import static java.util.Objects.requireNonNull;

public class FileTracker {
    private  FolderWatcher watcher;
    private  FileAnalyzer analyzer;

    public FileTracker(String path, String backupPath) {

        File file = new File(requireNonNull(path));

        if (!file.exists()){
            throw new IllegalArgumentException();
        }
        watcher = new FolderWatcher(file.getParent());
        analyzer = new FileAnalyzer(path, requireNonNull(backupPath));
    }

    public void startTracking() {
        analyzer.subscribe(watcher);
        watcher.start();
    }

    public void stopTracking() {
        watcher.stop();
        //may add unSubscribe
        //analyzer.unSubscribe(watcher);
    }
}
