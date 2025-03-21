package proyecto2.ssoo;

import GUI.Interfaz;
import GestFile.FileSystemSimulator;

public class Main {

    public static void main(String[] args) {
        
    //INICIALIZACIÓN DEL PROYECTO EN LA CLASE PRINCIPAL.
    FileSystemSimulator simulator = new FileSystemSimulator(100); //SE DEFINE INICIALMENTE EL TAMAÑO DEL DISCO
    Interfaz interfaz = new Interfaz(simulator);
    interfaz.setVisible(true);
    
    }
}