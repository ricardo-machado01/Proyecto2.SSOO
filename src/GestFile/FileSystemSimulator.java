package GestFile;;


import proyecto2.ssoo.DiskSimulator;
import EDD.ListBlocks;
import EDD.ListDirectory;
import EDD.ListFile;
import EDD.NodeDirectory;
import EDD.NodeFile;

public class FileSystemSimulator {

    private DiskSimulator diskSimulator; // Simulador del disco
    private Directory rootDirectory;     // Directorio raíz del sistema de archivos
    private Directory currentDirectory; // Directorio actual (para navegación)
    private boolean isAdminMode;

    // Constructor
    public FileSystemSimulator(int totalBlocks) {
        this.diskSimulator = new DiskSimulator(totalBlocks);
        this.rootDirectory = new Directory("root");
        this.currentDirectory = rootDirectory; // Inicialmente, el directorio actual es el raíz
        this.isAdminMode = true; //por defecto administrador.
    }

    //MÉTODO PARA CAMBIAR DE MODO.
    public void setIsAdminMode(boolean isAdminMode) {
        this.isAdminMode = isAdminMode;
        System.out.println("Estás en modo: " + this.isAdminMode);
    }

    // Métodos CRUD y de gestión del sistema de archivos
    //MÉTODO PARA CREAR UN ARCHIVO.
    public void createFile(String fileName, int size) {
        if(!isAdminMode){
            System.out.println("Acceso denegado: Solo el administrador puede crear archivos.");
            return;
        }
        if (size <= 0) {
            System.out.println("El tamaño del archivo debe ser mayor que 0.");
            return;
        }

        // Verificar si ya existe un archivo con el mismo nombre en el directorio actual
        ListFile files = currentDirectory.getFiles();
        NodeFile aux = files.getHead();
        while (aux != null) {
            if (aux.getFile().getName().equals(fileName)) {
                System.out.println("Ya existe un archivo con el nombre: " + fileName);
                return;
            }
            aux = aux.getPnext();
        }

        // Asignar bloques al archivo
        ListBlocks allocatedBlocks = diskSimulator.allocateBlocks(fileName, size);
        if (allocatedBlocks == null) {
            System.out.println("No hay suficientes bloques libres para crear el archivo.");
            return;
        }

        // Crear el archivo y agregarlo al directorio actual
        File newFile = new File(fileName, size);
        newFile.setListAllocate(allocatedBlocks);
        currentDirectory.getFiles().addFile(newFile);
        System.out.println("Archivo '" + fileName + "' creado exitosamente.");
    }
    
    //MÉTODO PARA ELIMINAR UN ARCHIVO.
    public void deleteFile(String fileName) {
        if(!isAdminMode){
            System.out.println("Acceso denegado: Solo el administrador puede eliminar archivos.");
        }
        ListFile files = currentDirectory.getFiles();
        NodeFile aux = files.getHead();
        NodeFile prev = null;

        while (aux != null) {
            if (aux.getFile().getName().equals(fileName)) {
                // Liberar los bloques asignados al archivo
                diskSimulator.freeBlocks(aux.getFile().getListAllocate());

                // Eliminar el archivo de la lista
                if (prev == null) {
                    files.setHead(aux.getPnext()); // El archivo a eliminar es el head
                } else {
                    prev.setPnext(aux.getPnext());
                }

                System.out.println("Archivo '" + fileName + "' eliminado exitosamente.");
                return;
            }
            prev = aux;
            aux = aux.getPnext();
        }

        System.out.println("No se encontró el archivo: " + fileName);
    }

    //MÉTODO PARA CREAR UN SUBDIRECTORIO.
    public void createDirectory(String dirName) {
        if(!isAdminMode){
            System.out.println("Acceso denegado: Solo el administrador puede crear directorios.");
            return;
        }
        // Verificar si ya existe un directorio con el mismo nombre en el directorio actual
        ListDirectory subdirectories = currentDirectory.getSubdirectories();
        NodeDirectory aux = subdirectories.getHead();
        while (aux != null) {
            if (aux.getDirectory().getName().equals(dirName)) {
                System.out.println("Ya existe un directorio con el nombre: " + dirName);
                return;
            }
            aux = aux.getpNext();
        }

        // Crear el directorio y agregarlo al directorio actual
        Directory newDirectory = new Directory(dirName);
        currentDirectory.getSubdirectories().addDirectory(newDirectory);
        System.out.println("Directorio '" + dirName + "' creado exitosamente.");
    }

    
    //REVISAR
    public void deleteDirectory(String dirName) {
        ListDirectory subdirectories = currentDirectory.getSubdirectories();
        NodeDirectory aux = subdirectories.getHead();
        NodeDirectory prev = null;

        while (aux != null) {
            if (aux.getDirectory().getName().equals(dirName)) {
                // Eliminar todos los archivos y subdirectorios del directorio (recursivo)
                deleteDirectoryRecursive(aux.getDirectory());

                // Eliminar el directorio de la lista
                if (prev == null) {
                    subdirectories.setHead(aux.getpNext()); // El directorio a eliminar es el head
                } else {
                    prev.setpNext(aux.getpNext());
                }

                System.out.println("Directorio '" + dirName + "' eliminado exitosamente.");
                return;
            }
            prev = aux;
            aux = aux.getpNext();
        }

        System.out.println("No se encontró el directorio: " + dirName);
    }

    //REVISAR
    private void deleteDirectoryRecursive(Directory directory) {
        // Eliminar todos los archivos del directorio
        ListFile files = directory.getFiles();
        NodeFile fileAux = files.getHead();
        while (fileAux != null) {
            diskSimulator.freeBlocks(fileAux.getFile().getListAllocate());
            fileAux = fileAux.getPnext();
        }

        // Eliminar todos los subdirectorios del directorio (recursivo)
        ListDirectory subdirectories = directory.getSubdirectories();
        NodeDirectory dirAux = subdirectories.getHead();
        while (dirAux != null) {
            deleteDirectoryRecursive(dirAux.getDirectory());
            dirAux = dirAux.getpNext();
        }
    }

    //MÉTODO PARA LISTAR ARCHIVOS Y DIRECTORIOS. EN EL DIRECTORIO ACTUAL.
    public void listContents() {
        System.out.println("Contenido del directorio '" + currentDirectory.getName() + "':");

        // Listar archivos
        System.out.println("Archivos:");
        ListFile files = currentDirectory.getFiles();
        NodeFile fileAux = files.getHead();
        while (fileAux != null) {
            System.out.println("- " + fileAux.getFile().getName());
            fileAux = fileAux.getPnext();
        }

        // Listar subdirectorios
        System.out.println("Directorios:");
        ListDirectory subdirectories = currentDirectory.getSubdirectories();
        NodeDirectory dirAux = subdirectories.getHead();
        while (dirAux != null) {
            System.out.println("- " + dirAux.getDirectory().getName());
            dirAux = dirAux.getpNext();
        }
    }

    //Cambiar de directorio (navegación) //REVISAR
    public void changeDirectory(String dirName) {
        if (dirName.equals("..")) {
            // Retroceder al directorio padre (si no es el raíz)
            if (currentDirectory != rootDirectory) {
                // Lógica para retroceder al directorio padre (requiere implementación adicional)
                System.out.println("Cambiando al directorio padre.");
            } else {
                System.out.println("Ya estás en el directorio raíz.");
            }
        } else {
            // Buscar el directorio en el directorio actual
            ListDirectory subdirectories = currentDirectory.getSubdirectories();
            NodeDirectory aux = subdirectories.getHead();
            while (aux != null) {
                if (aux.getDirectory().getName().equals(dirName)) {
                    currentDirectory = aux.getDirectory();
                    System.out.println("Cambiando al directorio: " + dirName);
                    return;
                }
                aux = aux.getpNext();
            }
            System.out.println("No se encontró el directorio: " + dirName);
        }
    }

    //MÉTODOS GET Y SET.
    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public int getFreeSpace() {
        return diskSimulator.getFreeBlocks();
    }

    public DiskSimulator getDiskSimulator() {
        return diskSimulator;
    }

    public void setDiskSimulator(DiskSimulator diskSimulator) {
        this.diskSimulator = diskSimulator;
    }

    public Directory getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(Directory rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
    
        public boolean isIsAdminMode() {
        return isAdminMode;
    }
}