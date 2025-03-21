package EDD;


public class BlockDisk {
    
    private int id; //Identificador del Bloque.
    private BlockDisk pNext; //Apuntador de al siguiente Bloque.
    private boolean isOccupied; //Ocupado o no ocupado.

    
    //CONSTRUCTOR DE LA CLASE BLOCK.
    public BlockDisk(int id) {
        this.id = id;
        this.pNext = null;
        this.isOccupied = false;
    }

    //CONSTRUCTOR PARA LAS MINI LISTAS.
    public BlockDisk(int id, boolean isOccupied) {
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

    public BlockDisk getpNext() {
        return pNext;
    }

    public void setpNext(BlockDisk pNext) {
        this.pNext = pNext;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
}
