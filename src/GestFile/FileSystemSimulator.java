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
        this.rootDirectory = new Directory("root",null);
        this.currentDirectory = rootDirectory; // Inicialmente, el directorio actual es el raíz
        this.isAdminMode = true; //por defecto administrador.
    }

    //MÉTODO PARA CAMBIAR DE MODO.
    public void setIsAdminMode(boolean isAdminMode) {
        this.isAdminMode = isAdminMode;
        System.out.println("Estás en modo: " + this.isAdminMode);
    }
    
    //MÉTODO PARA BUSCAR A UN ARCHIVO.
    public File findFile(String nameFile){
        File found_file = null;
        ListFile listfile = currentDirectory.getFiles();
        NodeFile nodefile = listfile.getHead();
        while(nodefile != null){
            if(nodefile.getFile().getName().equals(nameFile)){
                found_file = nodefile.getFile();
                return found_file;
            }
            nodefile = nodefile.getPnext();
        }
        return null;
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
        Directory newDirectory = new Directory(dirName,currentDirectory);
        currentDirectory.getSubdirectories().addDirectory(newDirectory);
        System.out.println("Directorio '" + dirName + "' creado exitosamente.");
        currentDirectory = newDirectory;
    }   

    
    //REVISAR
    public void deleteDirectory(String dirName) {
        if (!isAdminMode) {
            System.out.println("Acceso denegado: Solo el administrador puede eliminar directorios.");
            return;
        }

        ListDirectory subdirectories = currentDirectory.getSubdirectories();
        NodeDirectory aux = subdirectories.getHead();
        NodeDirectory prev = null;

        while (aux != null) {
            if (aux.getDirectory().getName().equals(dirName)) {
                Directory targetDir = aux.getDirectory(); // Guardamos el directorio a eliminar

                System.out.println("Eliminando directorio: " + dirName);

                // Desvincular el directorio de su padre
                if (prev == null) {
                    subdirectories.setHead(aux.getpNext()); // Si es el primer elemento
                } else {
                    prev.setpNext(aux.getpNext()); // Quitar de la lista de subdirectorios
                }

                // Llamar a la eliminación recursiva
                deleteDirectoryRecursive(targetDir);

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
        // Eliminar todos los archivos del directorio y liberar bloques
        ListFile files = directory.getFiles();
        NodeFile currentFileNode = files.getHead();

        while (currentFileNode != null) {
            File file = currentFileNode.getFile();
            System.out.println("Eliminando archivo: " + file.getName());
            diskSimulator.freeBlocks(file.getListAllocate()); // Liberar bloques del archivo
            currentFileNode = currentFileNode.getPnext(); // Avanzar al siguiente archivo
        }

        // Eliminar todos los subdirectorios del directorio de forma recursiva
        ListDirectory subdirectories = directory.getSubdirectories();
        NodeDirectory currentSubdirNode = subdirectories.getHead();

        while (currentSubdirNode != null) {
            Directory subDir = currentSubdirNode.getDirectory();
            System.out.println("Eliminando subdirectorio: " + subDir.getName());
            deleteDirectoryRecursive(subDir); // Llamada recursiva para eliminar subdirectorios
            currentSubdirNode = currentSubdirNode.getpNext(); // Avanzar al siguiente subdirectorio
        }

        // Finalmente, desvincular el directorio de su padre
        if (directory.getParent() != null) {
            ListDirectory parentSubdirs = directory.getParent().getSubdirectories();
            NodeDirectory aux = parentSubdirs.getHead();
            NodeDirectory prev = null;

            while (aux != null) {
                if (aux.getDirectory() == directory) {
                    if (prev == null) {
                        parentSubdirs.setHead(aux.getpNext()); // Eliminar si es el primero
                    } else {
                        prev.setpNext(aux.getpNext()); // Eliminar referencia en la lista
                    }
                    System.out.println("Desvinculando directorio: " + directory.getName());
                    break;
                }
                prev = aux;
                aux = aux.getpNext();
            }
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

    //MÉTODO PARA CAMBIAR DE DIRECTORIO. (NAVEGACIÓN)
    public void changeDirectory(String dirName) {
        if (dirName.equals("root")) {
            currentDirectory = rootDirectory;
            System.out.println("Cambiando al directorio raíz.");
            return;
        }

        // Buscar el directorio en los subdirectorios del directorio actual
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

        // Si no se encuentra en los subdirectorios, verificar si es el directorio padre
        if (currentDirectory.getParent() != null && currentDirectory.getParent().getName().equals(dirName)) {
            currentDirectory = currentDirectory.getParent();
            System.out.println("Cambiando al directorio padre: " + dirName);
        }
    }
    
    //MÉTODO PARA RENOMBRAR UN ARCHIVO.
    public void renameFile(String oldName, String newName) {
    if (!isAdminMode) {
        System.out.println("Acceso denegado: Solo el administrador puede modificar archivos.");
        return;
    }

    ListFile files = currentDirectory.getFiles();
    NodeFile aux = files.getHead();

    // Verificar si ya existe un archivo con el nuevo nombre
    while (aux != null) {
        if (aux.getFile().getName().equals(newName)) {
            System.out.println("Ya existe un archivo con el nombre: " + newName);
            return;
        }
        aux = aux.getPnext();
    }

    // Buscar el archivo con el nombre antiguo y cambiar su nombre
    aux = files.getHead();
    while (aux != null) {
        if (aux.getFile().getName().equals(oldName)) {
            aux.getFile().setName(newName);
            System.out.println("Archivo '" + oldName + "' renombrado a '" + newName + "'.");
            return;
        }
        aux = aux.getPnext();
    }

    System.out.println("No se encontró el archivo: " + oldName);
}

    // Método para modificar el nombre de un directorio
    public void renameDirectory(String oldName, String newName) {
        if (!isAdminMode) {
            System.out.println("Acceso denegado: Solo el administrador puede modificar directorios.");
            return;
        }
        
        ListDirectory subdirectories = currentDirectory.getSubdirectories();
        NodeDirectory aux = subdirectories.getHead();

        // Verificar si ya existe un directorio con el nuevo nombre
        while (aux != null) {
            if (aux.getDirectory().getName().equals(newName)) {
                System.out.println("Ya existe un directorio con el nombre: " + newName);
                return;
            }
            aux = aux.getpNext();
        }

        // Buscar el directorio con el nombre antiguo y cambiar su nombre
        aux = subdirectories.getHead();
        while (aux != null) {
            if (aux.getDirectory().getName().equals(oldName)) {
                aux.getDirectory().setName(newName); // Cambiar el nombre en el sistema de archivos               
                System.out.println("Directorio '" + oldName + "' renombrado a '" + newName + "'.");
                return;
            }
            aux = aux.getpNext();
        }

        System.out.println("No se encontró el directorio: " + oldName);
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

    public void setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
    }
}