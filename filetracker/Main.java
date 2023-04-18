/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: filetracker
 */
package il.co.ilrd.filetracker;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String path = "/home/jonathan/Desktop/change/write.txt";
        String backupPath = "/home/jonathan/Desktop/test/test.txt";

        FileTracker fileTracker = new FileTracker(path, backupPath);

        fileTracker.startTracking();

        //dosent work...
       // fileTracker.stopTracking();
       // fileTracker.startTracking();

    }
}
