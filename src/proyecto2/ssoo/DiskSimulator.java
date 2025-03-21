
package proyecto2.ssoo;

import EDD.BlockDisk;
import EDD.ListBlocks;


public class DiskSimulator {
    
    private ListBlocks listBlocks;
    private int totalBlocks;

    
    //CONSTRUCTOR.
    public DiskSimulator(int totalBlocks) {
        this.listBlocks = new ListBlocks();
        this.totalBlocks = totalBlocks;
        
        //INICIALIZANDO LOS BLOQUES DEL DISCO.
        for (int i = 0; i < totalBlocks; i++) {
            listBlocks.addBlock(new BlockDisk(i));
        }
    }
    
    //MÉTODO PARA OBTENER NÚMERO DE BLOQUES LIBRES.
    public int getFreeBlocks(){
        int freeBlocks = 0;
        BlockDisk aux = listBlocks.getHead();
        while(aux!=null){
            if(!aux.getIsOccupied()){
                freeBlocks += 1;
            }
            aux = aux.getpNext();
        }
        return freeBlocks;
    }
    
    //MÉTODO PARA ASIGNAR BLOQUES A UN ARCHIVO EN FORMA ENCADENADA.
    public ListBlocks allocateBlocks(String fileName, int size) {
        if (size > getFreeBlocks()) {
            System.out.println("No hay suficientes bloques libres");
        } else {
            ListBlocks listAllocate = new ListBlocks();
            BlockDisk aux = listBlocks.getHead();
            int blockAssigned = 0;
            while (aux != null && blockAssigned < size) {
                if (!aux.getIsOccupied()) {
                    aux.setIsOccupied(true);
                    BlockDisk blockFile = new BlockDisk(aux.getId(),aux.getIsOccupied());
                    listAllocate.addBlock(blockFile);
                    blockAssigned++;
                }
                aux = aux.getpNext();
            }
            return listAllocate;
        }
        return null;
    }

    //MÉTODO PARA LIBERAR BLOQUES.
    public void freeBlocks(ListBlocks listAllocate) {
       if (listAllocate == null || listAllocate.getHead() == null) {
           System.out.println("La lista de bloques asignados es nula o está vacía.");
           return;
       }

       BlockDisk auxAllocate = listAllocate.getHead(); // Obtener el primer bloque de la lista asignada

       // Recorrer la lista de bloques asignados
       while (auxAllocate != null) {
           // Buscar el bloque correspondiente en listBlocks y marcarlo como no ocupado
           BlockDisk auxDisk = listBlocks.getHead();
           while (auxDisk != null) {
               if (auxDisk.getId() == auxAllocate.getId()) {
                   auxDisk.setIsOccupied(false); // Marcar el bloque como no ocupado en listBlocks
                   System.out.println("Bloque " + auxDisk.getId() + " liberado.");
                   break;
               }
               auxDisk = auxDisk.getpNext();
           }
           auxAllocate = auxAllocate.getpNext(); // Mover al siguiente bloque en listAllocate
       }

       System.out.println("Bloques liberados correctamente.");
    }
        
    //MÉTODOS GET Y SET.
    public ListBlocks getListBlocks() {
        return listBlocks;
    }

    public void setListBlocks(ListBlocks listBlocks) {
        this.listBlocks = listBlocks;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }
    
    
}
