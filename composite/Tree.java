/**
 * Name: yoni
 * Reviewer: tali
 * Exercise: composite
 */

package il.co.ilrd.composite;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
    private final Folder folder;

    public Tree(String path) {
        if (!Files.isDirectory(Paths.get(path))) {
            throw new IllegalArgumentException("This directory is not exists");
        }
        folder = new Folder(path);
    }

    public void print() {
        folder.print(0);
    }

    private interface FileComponent {
        public void print(int offset);
    }

    private class Folder implements FileComponent {
        List<FileComponent> componentList;
        String folderName;

        private Folder(String path) {

            File pathFile = new File(path);
            folderName = pathFile.getName();
            componentList = new ArrayList<FileComponent>();

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

    private class FileLeaf implements FileComponent {
        String strFileName;
        private FileLeaf(String fileName) {
            strFileName = fileName;
        }

        @Override
        public void print(int offset) {

            for (int i = 1; i < offset; ++i) {
                System.out.print("\t");
            }
            if (offset != 0) {
                System.out.print("└── ");
            }

            System.out.println( Color.ANSI_GREEN + strFileName + Color.ANSI_RESET);
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
        String path = "/home/jonathan/git/jonathan.shapiro/fs/projects/src/il/co/ilrd/Concurrency";
        Tree tree = new Tree(path);
        tree.print();
    }
}
