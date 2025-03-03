package GestFile;

import EDD.Block;
import EDD.List;

public class Directory {

    private String name;
    private List listFile;       // Lista de archivos en el directorio
    private List subdirectories; // Lista de subdirectorios

    // CONSTRUCTOR.
    public Directory(String name) {
        this.name = name;
        this.listFile = new List();       // Inicializar la lista de archivos
        this.subdirectories = new List(); // Inicializar la lista de subdirectorios
    }

    // Métodos para agregar y eliminar archivos
    public void addFile(File file) {
        listFile.addBlock(new Block(file.getListAllocate().getHead().getId())); // Agregar el primer bloque del archivo
    }

    public void removeFile(File file) {
        listFile.removeBlock(new Block(file.getListAllocate().getHead().getId())); // Eliminar el primer bloque del archivo
    }

    // Métodos para agregar y eliminar subdirectorios
    public void addSubdirectory(Directory directory) {
        subdirectories.addBlock(new Block(directory.hashCode())); // Usar un identificador único para el directorio
    }

    public void removeSubdirectory(Directory directory) {
        subdirectories.removeBlock(new Block(directory.hashCode())); // Eliminar el directorio
    }

    // MÉTODOS GET Y SET.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getListFile() {
        return listFile;
    }

    public void setListFile(List listFile) {
        this.listFile = listFile;
    }

    public List getSubdirectories() {
        return subdirectories;
    }

    public void setSubdirectories(List subdirectories) {
        this.subdirectories = subdirectories;
    }
}