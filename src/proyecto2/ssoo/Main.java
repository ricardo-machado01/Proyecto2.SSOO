package proyecto2.ssoo;

import GestFile.Directory;
import GestFile.File;
import GestFile.FileSystemSimulator;

public class Main {

    public static void main(String[] args) {
        
        /*Crear un simulador.
        FileSystemSimulator simulator = new FileSystemSimulator(10);
        //Creación de archivo de 3 bloques.
        simulator.createFile("file1.txt", 3);
        //Comprobamos si se crea el archivo en el directorio.
        //System.out.println("Archivos de este directorio: "+ simulator.getCurrentDirectory().getFiles().getHead().getFile().getName());
        //Comprobamos que se marcan como ocupado los bloques del disco.
        //System.out.println("Cantidad de bloques libres: "+ simulator.getDiskSimulator().getFreeBlocks());
        
        
        //Eliminar archivo de un directotio.
        //simulator.deleteFile("file1.txt");
        
        //Comprobar si libera los espacios del disco.
        //System.out.println("Bloques de disco libres: " +simulator.getDiskSimulator().getFreeBlocks());
        
        //Comprobar que se elimina el archivo del directorio.
        //System.out.println("Lista de archivos del directorio: " + simulator.getCurrentDirectory().getFiles().getHead());
       
        //Comprobar que se elimina el archivo del directorio.
        simulator.getCurrentDirectory().getFiles().printListFile();
        
        //Comprobamos que se crea un subdirectorio de un directorio.
        simulator.createDirectory("Documentos");
        
        //Comprobamos que el directorio documento pertenece a root.
        simulator.getCurrentDirectory().getSubdirectories().printListDirectory();
        
        simulator.listContents();*/
        //PRUEBA DE MODO USUARIO Y MODO ADMIN
    // Crear una instancia del sistema de archivos con 100 bloques de disco
    FileSystemSimulator fs = new FileSystemSimulator(100);

    // Crear un archivo y un directorio en modo administrador
    System.out.println("Modo actual: " + fs.isIsAdminMode());
    fs.createFile("file1.txt", 10); // Crear un archivo
    fs.createDirectory("dir1"); // Crear un directorio
    fs.listContents(); // Listar contenido

    // Cambiar a modo usuario
    System.out.println("\nCambiando a modo usuario...");
    fs.setIsAdminMode(false);
    System.out.println("Modo actual: " + fs.isIsAdminMode());

    // Intentar crear un archivo en modo usuario (debe fallar)
    fs.createFile("file2.txt", 5); // Esto debería mostrar un mensaje de acceso denegado

    // Listar contenido en modo usuario (debe funcionar)
    fs.listContents();
    }
}