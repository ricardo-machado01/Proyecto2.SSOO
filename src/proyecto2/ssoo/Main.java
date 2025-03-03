package proyecto2.ssoo;

import EDD.List;
import GestFile.Directory;
import GestFile.File;

public class Main {

    public static void main(String[] args) {
        // 1. Crear un disco con 10 bloques
        DiskSimulator disk = new DiskSimulator(10);
        System.out.println("Disco creado con 10 bloques.");

        // 2. Crear el directorio raíz
        Directory root = new Directory("root");
        System.out.println("Directorio raíz 'root' creado.");

        // 3. Crear un archivo llamado "archivo1.txt" de 3 bloques
        File file1 = new File("archivo1.txt", 3);
        List allocatedBlocks1 = disk.allocateBlocks(file1.getName(), file1.getSize());
        if (allocatedBlocks1 != null) {
            file1.setListAllocate(allocatedBlocks1); // Asignar bloques al archivo
            root.addFile(file1); // Agregar el archivo al directorio raíz
            System.out.println("Archivo 'archivo1.txt' creado con 3 bloques asignados.");
        }

        // 4. Crear un subdirectorio llamado "Documentos"
        Directory documentos = new Directory("Documentos");
        root.addSubdirectory(documentos); // Agregar el subdirectorio al directorio raíz
        System.out.println("Subdirectorio 'Documentos' creado.");

        // 5. Crear un archivo llamado "documento1.docx" de 2 bloques en el subdirectorio "Documentos"
        File file2 = new File("documento1.docx", 2);
        List allocatedBlocks2 = disk.allocateBlocks(file2.getName(), file2.getSize());
        if (allocatedBlocks2 != null) {
            file2.setListAllocate(allocatedBlocks2); // Asignar bloques al archivo
            documentos.addFile(file2); // Agregar el archivo al subdirectorio "Documentos"
            System.out.println("Archivo 'documento1.docx' creado con 2 bloques asignados en el subdirectorio 'Documentos'.");
        }

        // 6. Verificar el número de bloques libres
        System.out.println("Bloques libres después de crear archivos: " + disk.getFreeBlocks());

        // 7. Eliminar el archivo "archivo1.txt" del directorio raíz
        root.removeFile(file1);
        disk.freeBlocks(file1.getListAllocate()); // Liberar los bloques asignados al archivo
        System.out.println("Archivo 'archivo1.txt' eliminado y bloques liberados.");

        // 8. Verificar el número de bloques libres después de eliminar el archivo
        System.out.println("Bloques libres después de eliminar 'archivo1.txt': " + disk.getFreeBlocks());

        // 9. Eliminar el subdirectorio "Documentos" (esto también eliminará el archivo "documento1.docx")
        root.removeSubdirectory(documentos);
        //TIENES QUE LIBERAR TODOS LOS BLOQUES DE UN DIRECTORIO.
        disk.freeBlocks(file2.getListAllocate()); // Liberar los bloques asignados al archivo
        System.out.println("Subdirectorio 'Documentos' eliminado y bloques liberados.");

        // 10. Verificar el número de bloques libres después de eliminar el subdirectorio
        System.out.println("Bloques libres después de eliminar 'Documentos': " + disk.getFreeBlocks());
    }
}