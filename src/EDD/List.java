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
    
}
