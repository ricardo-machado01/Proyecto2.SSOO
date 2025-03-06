package EDD;
import GestFile.Directory;


public class NodeDirectory {
    
    private Directory directory;
    private NodeDirectory pNext;

    //CONSTRUCTOR.
    public NodeDirectory(Directory directory){
        this.directory = directory;
        this.pNext = null;
    }

    //MÉTODO GET Y SET.
    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public NodeDirectory getpNext() {
        return pNext;
    }

    public void setpNext(NodeDirectory pNext) {
        this.pNext = pNext;
    }
    
    
}
