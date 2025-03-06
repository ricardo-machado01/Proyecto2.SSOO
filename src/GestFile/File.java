package GestFile;

import EDD.BlockDisk;
import EDD.ListBlocks;

public class File {

    private String name; // Nombre del archivo.
    private int size; // cantidad de bloques que ocupa.
    private ListBlocks listAllocate; // Lista de bloques asignados para el archivo.

    // CONSTRUCTOR.
    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.listAllocate = new ListBlocks(); // Inicializar la lista de bloques asignados
    }

    // MÉTODO GET Y SET.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ListBlocks getListAllocate() {
        return listAllocate;
    }

    public void setListAllocate(ListBlocks listAllocate) {
        this.listAllocate = listAllocate;
    }
    
}