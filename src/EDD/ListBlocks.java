package EDD;

public class ListBlocks {
   
    private BlockDisk head;
    private BlockDisk tail;
    private int totalBlocks;

    //CONSTRUCTOR DE LA CLASE LISTA.
    public ListBlocks() {
        this.head = null;
        this.tail = null;
        this.totalBlocks = 0;
    }

    //METODOS GET Y SET.
    public BlockDisk getHead() {
        return head;
    }

    public void setHead(BlockDisk head) {
        this.head = head;
    }

    public BlockDisk getTail() {
        return tail;
    }

    public void setTail(BlockDisk tail) {
        this.tail = tail;
    }

    public int gettotalBlocks() {
        return totalBlocks;
    }

    public void settotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }
    
    //MÉTODO PARA ENLAZAR UN BLOCK.
    public void addBlock(BlockDisk block){
        if(head == null){
            head = block;
            tail = block;
        }else{
            tail.setpNext(block);
            tail = block;
        }
        totalBlocks++;
    }
    
    public void removeBlock(BlockDisk block) {
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
        BlockDisk aux = head;
        BlockDisk aux2 = head;
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
    
    //MÉTODO PARA IMPRIMIR LA LISTA DE ASIGNACIÓN (MINI LISTA).
    public void printListAllocate(){
        BlockDisk aux = head;
        while(aux != null){
            System.out.println("Bloque asignado: "+aux.getId());
            aux = aux.getpNext();
        }
    }
}
