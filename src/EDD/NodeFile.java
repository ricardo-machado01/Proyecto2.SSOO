package EDD;

import GestFile.File;


public class NodeFile {
    
    private File file;
    private NodeFile Pnext;

    
    //CONSTRUCTOR.
    public NodeFile(File file) {
        this.file = file;
        this.Pnext = null;
    }
    
    //MÉTODO GET Y SET.
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public NodeFile getPnext() {
        return Pnext;
    }

    public void setPnext(NodeFile Pnext) {
        this.Pnext = Pnext;
    }
    
    
}
