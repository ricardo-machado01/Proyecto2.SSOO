
package proyecto2.ssoo;

import EDD.Block;
import EDD.List;


public class DiskSimulator {
    
    private List listBlocks;
    private int totalBlocks;

    
    //CONSTRUCTOR.
    public DiskSimulator(int totalBlocks) {
        this.listBlocks = new List();
        this.totalBlocks = totalBlocks;
        
        //INICIALIZANDO LOS BLOQUES DEL DISCO.
        for (int i = 0; i < totalBlocks; i++) {
            listBlocks.addBlock(new Block(i));
        }
    }
    
    //MÉTODO PARA OBTENER NÚMERO DE BLOQUES LIBRES.
    public int getFreeBlocks(){
        int freeBlocks = 0;
        Block aux = listBlocks.getHead();
        while(aux!=null){
            if(!aux.getIsOccupied()){
                freeBlocks += 1;
            }
            aux = aux.getpNext();
        }
        return freeBlocks;
    }
    
    //MÉTODO PARA VERIFICAR SI EL DISCO ESTÁ LLENO.
    public boolean isDiskFull(){
        return getFreeBlocks() == 0;
    }
    
    //MÉTODO PARA ASIGNAR BLOQUES A UN ARCHIVO EN FORMA ENCADENADA.
    public List allocateBlocks(String fileName, int size){
        if(isDiskFull()){
            System.out.println("El disco está lleno");
        }else if(size > getFreeBlocks()){
            System.out.println("No hay suficientes bloques libres");
        }else{
            List listAllocate = new List();
            Block aux = listBlocks.getHead();
            int blockAssigned = 0;
            while(aux != null && blockAssigned < size){
                if(!aux.getIsOccupied()){
                    aux.setIsOccupied(true);
                    listAllocate.addBlock(aux);
                    blockAssigned += 1;
                }
                aux = aux.getpNext();
            }
            return listAllocate;
        }
        return null;
    }

    //MÉTODO PARA LIBERAR BLOQUES.
    public void freeBlocks(List listAllocate) {
        if (listAllocate == null) {
            System.out.println("La lista de bloques asignados es nula.");
        }

        Block auxAllocate = listAllocate.getHead(); // Obtener el primer bloque de la lista asignada

        // Recorrer la lista de bloques asignados
        while (auxAllocate != null) {
            // Buscar el bloque correspondiente en listBlocks y marcarlo como no ocupado
            Block auxDisk = listBlocks.getHead();
            while (auxDisk != null) {
                if (auxDisk.getId() == auxAllocate.getId()) {
                    auxDisk.setIsOccupied(false); // Marcar el bloque como no ocupado en listBlocks
                    break;
                }
                auxDisk = auxDisk.getpNext();
            }
            auxAllocate = auxAllocate.getpNext(); // Mover al siguiente bloque en listAllocate
        }

        System.out.println("Bloques liberados correctamente.");
    }
        
    //MÉTODOS GET Y SET.
    public List getListBlocks() {
        return listBlocks;
    }

    public void setListBlocks(List listBlocks) {
        this.listBlocks = listBlocks;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }
    
    
}
