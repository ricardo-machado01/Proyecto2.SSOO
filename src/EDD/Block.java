package EDD;


public class Block {
    
    private int id;
    private Block pNext;
    private Boolean isOccupied;

    
    //CONSTRUCTOR DE LA CLASE BLOCK.
    public Block(int id) {
        this.id = id;
        this.pNext = null;
        this.isOccupied = false;
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
