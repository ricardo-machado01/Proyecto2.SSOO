package proyecto2.ssoo;

import EDD.List;


public class Main {


    public static void main(String[] args) {
        // Crear un disco con 10 bloques
        DiskSimulator disk = new DiskSimulator(10);

        // Asignar 3 bloques a un archivo llamado "file1"
        List file1Blocks = disk.allocateBlocks("file1", 10);
        if (file1Blocks != null) {
            System.out.println("Bloques asignados a 'file1'.");
        }

        // Verificar bloques libres antes de liberar
        System.out.println("Bloques libres antes de liberar: " + disk.getFreeBlocks());
        // Liberar los bloques de "file1"
        disk.freeBlocks(file1Blocks);
        System.out.println("Bloques de 'file1' liberados.");

        // Verificar bloques libres después de liberar
        System.out.println("Bloques libres después de liberar: " + disk.getFreeBlocks());
    }
    
}
