package EDD;


public class Block {
    
    private int id;
    private Block pNext;
    private boolean isOccupied;

    
    //CONSTRUCTOR DE LA CLASE BLOCK.
    public Block(int id) {
        this.id = id;
        this.pNext = null;
        this.isOccupied = false;
    }

    //CONSTRUCTOR PARA LAS MINI LISTAS.
    public Block(int id, boolean isOccupied) {
        this.id = id;
        this.pNext = null;
        this.isOccupied = isOccupied;
    }
    
    //METODOS GET Y SET.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Block getpNext() {
        return pNext;
    }

    public void setpNext(Block pNext) {
        this.pNext = pNext;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
     
}
