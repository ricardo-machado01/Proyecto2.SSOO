package GestFile;

import EDD.ListDirectory;
import EDD.ListFile;



public class Directory {
    
    private String name;
    private ListFile files;
    private ListDirectory subdirectories;
    private Directory parent;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.files = new ListFile();
        this.subdirectories = new ListDirectory();
        this.parent = parent;
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

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

}