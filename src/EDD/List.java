package EDD;

public class List {
   
    private Block head;
    private Block tail;
    private int totalBlocks;

    //CONSTRUCTOR DE LA CLASE LISTA.
    public List() {
        this.head = null;
        this.tail = null;
        this.totalBlocks = 0;
    }

    //METODOS GET Y SET.
    public Block getHead() {
        return head;
    }

    public void setHead(Block head) {
        this.head = head;
    }

    public Block getTail() {
        return tail;
    }

    public void setTail(Block tail) {
        this.tail = tail;
    }

    public int gettotalBlocks() {
        return totalBlocks;
    }

    public void settotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }
    
    //MÉTODO PARA ENLAZAR UN BLOCK.
    public void addBlock(Block block){
        if(head == null){
            head = block;
            tail = block;
        }else{
            tail.setpNext(block);
            tail = block;
        }
        totalBlocks++;
    }
    
    public void removeBlock(Block block) {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }

        // Caso especial: el bloque a eliminar es el head
        if (head.getId() == block.getId()) {
            head = head.getpNext();
            if (head == null) {
                tail = null; // Si la lista queda vacía, tail también debe ser null
            }
            totalBlocks--;
            return;
        }

        //Buscar el bloque a eliminar
        Block aux = head;
        Block aux2 = head;
        while (aux != null) {
            if (aux.getId() == block.getId()) {
                aux2.setpNext(aux.getpNext());
                if (aux == tail) {
                    tail = aux2; // Si el bloque a eliminar es el tail, actualizar tail
                }
                totalBlocks--;
                break;
            }
            aux2 = aux;
            aux = aux.getpNext();
        }
    }
    
    //MÉTODO PARA IMPRIMIR LA LISTA DE ASIGNACIÓN
    public void printListAllocate(){
        Block aux = head;
        while(aux != null){
            System.out.println("Bloque asignado: "+aux.getId());
            aux = aux.getpNext();
        }
    }
}
