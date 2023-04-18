/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: filetracker
 */
package il.co.ilrd.filetracker;

import il.co.ilrd.observer.Callback;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;
//the Observer
public class FileAnalyzer {
    private FileCRUD fileCRUD;
    private final String path;
    private final String backupPath;

    private Callback<WatchEvent<?>> callback;

    public FileAnalyzer(String path, String backupPath) {
        try {
            Files.copy(Paths.get(requireNonNull(path)), Paths.get(requireNonNull(backupPath)), REPLACE_EXISTING);
            fileCRUD = new FileCRUD(backupPath);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.path = path;
        this.backupPath = backupPath;
    }

    public void subscribe(FolderWatcher folderWatcher) {

        callback = new Callback<>(this::notifyUpdateFile, this :: stop);
        folderWatcher.register(callback);
    }

    private void stop() {
        callback.dispatcher.stopService();
    }

    private void notifyUpdateFile(WatchEvent<?> event) {
        //check that the event happend at the right file
        int lineNumber = 0;
        FileReader fileReader = null;
        FileReader fileReaderbackup = null;
        BufferedReader bf1 = null;
        BufferedReader bf2 = null;

        try {
            fileReader = new FileReader(path);
            fileReaderbackup = new FileReader(backupPath);
            bf1 = new BufferedReader(fileReader);
            bf2 = new BufferedReader(fileReaderbackup);
        }
        catch (FileNotFoundException e){
            System.out.println("faild");
        }

        try {
            String lineSrc =  bf1.readLine();
            String lineBackup = bf2.readLine();

            while ((lineSrc != null) && (lineBackup != null)){
                if (!lineSrc.equals(lineBackup)) {
                    fileCRUD.update(lineNumber, lineSrc);
                    lineBackup = bf2.readLine();
                }
                lineSrc =  bf1.readLine();
                lineBackup = bf2.readLine();
                lineNumber++;
            }

            while (lineSrc != null) {
                fileCRUD.create(lineSrc);
                lineSrc = bf1.readLine();
            }

            while (lineBackup != null) {
                fileCRUD.delete(lineNumber);
                lineBackup = bf2.readLine();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
                fileReaderbackup.close();
            }
            catch (IOException e){
                System.out.println("failed");
            }

        }
    }
}