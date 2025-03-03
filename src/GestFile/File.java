package GestFile;

import EDD.Block;
import EDD.List;

public class File {

    private String name; // Nombre del archivo.
    private int size; // Unidad de medida, cantidad de bloques.
    private List listAllocate; // Lista de bloques asignados para el archivo.

    // CONSTRUCTOR.
    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.listAllocate = new List(); // Inicializar la lista de bloques asignados
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

    public List getListAllocate() {
        return listAllocate;
    }

    public void setListAllocate(List listAllocate) {
        this.listAllocate = listAllocate;
    }
    
}