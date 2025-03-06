package EDD;

import GestFile.File;

public class ListFile {
   
    private NodeFile head;
    private NodeFile tail;

    //CONSTRUCTOR DE LA CLASE LISTA.
    public ListFile(){
        this.head = null;
        this.tail = null;
    }

    //METODOS GET Y SET.
    public NodeFile getHead() {
        return head;
    }

    public void setHead(NodeFile head) {
        this.head = head;
    }

    public NodeFile getTail() {
        return tail;
    }

    public void setTail(NodeFile tail) {
        this.tail = tail;
    }

    
    //MÉTODO PARA ENCOLAR UN NODO.
    public void addFile(File file){
        NodeFile node = new NodeFile(file);
        if(head == null){
            head = node;
            tail = node;
        }else{
            tail.setPnext(node);
            tail = node;
        }
    }
    
    //MÉTODO PARA ELIMINAR UN NODO.
    public void deleteFile(File file) {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }

        // Caso especial: el bloque a eliminar es el head
        if (head.getFile().getName().equals(file.getName())) {
            head = head.getPnext();
            if (head == null) {
                tail = null; // Si la lista queda vacía, tail también debe ser null
            }
            return;
        }

        //Buscar el bloque a eliminar
        NodeFile aux = head;
        NodeFile aux2 = head;
        while (aux != null) {
            if (aux.getFile().getName().equals(file.getName())) {
                aux2.setPnext(aux.getPnext());
                if (aux == tail) {
                    tail = aux2; // Si el bloque a eliminar es el tail, actualizar tail
                }
                break;
            }
            aux2 = aux;
            aux = aux.getPnext();
        }
    }
    
    //MÉTODO PARA IMPRIMIR LA LISTA DE ARCHIVOS.
    public void printListFile(){
        NodeFile aux = head;
        while(aux != null){
            System.out.println("Archivo: "+aux.getFile().getName());
            aux = aux.getPnext();
        }
    }
}
