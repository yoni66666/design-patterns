/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: filetracker
 */
package il.co.ilrd.filetracker;

import il.co.ilrd.observer.Callback;
import il.co.ilrd.observer.Dispatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;
import static java.util.Objects.requireNonNull;

public class FolderWatcher {
    private WatchService watcher;
    private final Dispatcher<WatchEvent<?>> dispatcher;
    private WatchKey watchKey;
    private boolean isRunning = false;
    Path myDir;

    public FolderWatcher(String folderToWatch) {
        File file = new File(requireNonNull(folderToWatch));
        if (!file.exists()){
            throw new IllegalArgumentException();
        }
        myDir = Paths.get(folderToWatch);
        try {
            watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE,
                          StandardWatchEventKinds.ENTRY_DELETE);

        } catch (IOException e) {
            throw new  RuntimeException();
        }
        dispatcher = new Dispatcher<>();
    }

    public void register(Callback<WatchEvent<?>> call) {
        if (call == null){
            throw new  IllegalArgumentException();
        }
        dispatcher.registerCallback(call);
    }

    public void start() {
        isRunning = true;
        Runnable runnable = new WatcherThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stop() {
        try {
            watcher.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isRunning = false;
    }

    private class WatcherThread implements Runnable {
        @Override
        public void run() {
            while (isRunning){
                try {
                    watchKey = watcher.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    if (event.kind() == OVERFLOW) {
                        continue;
                    }

                    if (event.kind() == ENTRY_MODIFY || event.kind() == ENTRY_CREATE){
                        dispatcher.notifyALL(event);
                    }


/*
                    if (event.kind() == ENTRY_CREATE){
                        dispatcher.notifyALL(event);
                    }

 */
                }
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
        }
    }
}

