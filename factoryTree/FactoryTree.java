/**
 * Name: yoni
 * Reviewer: Eliraz
 * Exercise: factoryTree
 */

package il.co.ilrd.factoryTree;
import il.co.ilrd.factory.Factory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FactoryTree {
    Factory <String, FileComponent,String> factory;
    private final FileComponent folder;

    public FactoryTree(String path) {
        if (!Files.isDirectory(Paths.get(path))) {
            throw new IllegalArgumentException("This directory is not exists");
        }

        factory = new Factory<>();
        factory.add("Folder", (a) -> new Folder(a));
        factory.add("FileLeaf", (a) -> new FileLeaf(a));
        folder =  factory.create("Folder", path);
    }

    public void print() {
        folder.print(0);
    }

    private interface FileComponent {
        public void print(int offset);
    }

    private class Folder implements FileComponent{

        List <FileComponent> componentList;
        String folderName;
        public Folder(String path){
            componentList = new ArrayList<>();
            File pathFile = new File(path);
            folderName = pathFile.getName();

            File[] filesList = pathFile.listFiles();

            Arrays.sort(filesList);

            for (File component : filesList) {
                if (component.isDirectory()) {
                    componentList.add(new Folder(component.getAbsolutePath()));
                } else {
                    componentList.add(new FileLeaf(component.getName()));
                }
            }
        }

        @Override
        public void print(int offset) {

            for (int i = 1; i < offset; ++i) {
                System.out.print("\t");
            }
            if (offset != 0) {
                System.out.print("└── ");
            }

            System.out.println(Color.ANSI_BLUE + folderName + Color.ANSI_RESET);

            for (FileComponent component : componentList){
                component.print(offset + 1);
            }
        }
    }

    private class FileLeaf implements FileComponent{
        String fileName;
        public FileLeaf(String fileName){
            this.fileName = fileName;
        }

        @Override
        public void print(int offset) {

            for (int i = 1; i < offset; ++i) {
                System.out.print("\t");
            }
            if (offset != 0) {
                System.out.print("└── ");
            }

            System.out.println( Color.ANSI_GREEN + fileName + Color.ANSI_RESET);
        }
    }
    static class Color {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_BLUE = "\u001B[34m";
    }
}
class Main{
    public static void main(String[] args) throws IllegalAccessException {
        String path = "/home/jonathan/git/jonathan.shapiro/fs/projects/src/il/co/ilrd";
        FactoryTree tree = new FactoryTree(path);
        tree.print();
    }
}

