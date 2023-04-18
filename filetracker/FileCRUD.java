/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: filetracker
 */
package il.co.ilrd.filetracker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileCRUD implements CRUD<Integer, String>{
    private final String filePathName;
    private ArrayList<String> listOfLines;

    public FileCRUD(String path) throws IOException {
        File file = new File(path);
        filePathName = path;

        if(!file.exists()){
            listOfLines = new ArrayList<String>();
            file.createNewFile();
        }
        else {
            listOfLines = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        }
    }

    @Override
    public Integer create(String data) {
        listOfLines.add(data);

        Path currentPath = Paths.get(filePathName);
        try {
            Files.write(currentPath, (data + "\n").getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return (listOfLines.size() - 1);
    }

    @Override
    public String read(Integer line) {
        if (line > listOfLines.size()){
            return null;
        }
        return listOfLines.get(line);
    }

    @Override
    public void update(Integer line, String data) {
        listOfLines.set(line,data);
        writeListToFile();
    }

    @Override
    public void delete(Integer line) {
        listOfLines.remove(line.intValue());
        writeListToFile();
    }

    private void writeListToFile()  {
        Path file = Paths.get(filePathName);
        try {
            Files.write(file,listOfLines);
        }
        catch (IOException e){
            System.out.println("update failed");
        }
    }
}
