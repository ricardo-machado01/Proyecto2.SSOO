package EDD;

import GestFile.Directory;
import GestFile.File;

public class ListDirectory {
   
    private NodeDirectory head;
    private NodeDirectory tail;

    //CONSTRUCTOR DE LA CLASE LISTA.
    public ListDirectory() {
        this.head = null;
        this.tail = null;
    }

    //METODOS GET Y SET.

    public NodeDirectory getHead() {
        return head;
    }

    public void setHead(NodeDirectory head) {
        this.head = head;
    }

    public NodeDirectory getTail() {
        return tail;
    }

    public void setTail(NodeDirectory tail) {
        this.tail = tail;
    }
    

    //MÉTODO PARA ENCOLAR UN NODO.
    public void addDirectory(Directory directory){
        NodeDirectory node = new NodeDirectory(directory);
        if(head == null){
            head = node;
            tail = node;
        }else{
            tail.setpNext(node);
            tail = node;
        }
    }
    
    //MÉTODO PARA ELIMINAR UN NODO. ESTA ES LA FUNCIÓN RECURSIVA. SE TIENE QUE ELABORAR.
    public void deleteDirectory(File file) {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }

        // Caso especial: el bloque a eliminar es el head
        if (head.getDirectory().getName().equals(file.getName())) {
            head = head.getpNext();
            if (head == null) {
                tail = null; // Si la lista queda vacía, tail también debe ser null
            }
            return;
        }

        //Buscar el bloque a eliminar
        NodeDirectory aux = head;
        NodeDirectory aux2 = head;
        while (aux != null) {
            if (aux.getDirectory().getName().equals(file.getName())) {
                aux2.setpNext(aux.getpNext());
                if (aux == tail) {
                    tail = aux2; // Si el bloque a eliminar es el tail, actualizar tail
                }
                break;
            }
            aux2 = aux;
            aux = aux.getpNext();
        }
    }
    
    //MÉTODO PARA IMPRIMIR LA LISTA DE ARCHIVOS.
    public void printListDirectory(){
        NodeDirectory aux = head;
        while(aux != null){
            System.out.println("Directorio: "+aux.getDirectory().getName());
            aux = aux.getpNext();
        }
    }
}