package GestFile;

import EDD.ListDirectory;
import EDD.ListFile;



public class Directory {
    
    private String name;
    private ListFile files;
    private ListDirectory subdirectories;

    public Directory(String name) {
        this.name = name;
        this.files = new ListFile();
        this.subdirectories = new ListDirectory();
    }

    //MÉTODO GET Y SET.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListFile getFiles() {
        return files;
    }

    public void setFiles(ListFile files) {
        this.files = files;
    }

    public ListDirectory getSubdirectories() {
        return subdirectories;
    }

    public void setSubdirectories(ListDirectory subdirectories) {
        this.subdirectories = subdirectories;
    }    
    
}