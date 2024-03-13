package co.edu.uniquindio.estr.jokify.controllers;

import co.edu.uniquindio.estr.jokify.model.Jokify;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.nio.charset.StandardCharsets;

import java.util.*;

public class ModelFactoryController {

    //Clase Jokify global
    static Jokify miJokify;

    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        //Se inicializan los datos
        try {
            inicializarDatos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Singleton (Garantiza instancia unica)
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private void inicializarDatos() throws InterruptedException {
//        miSubasta = new Subasta("Subastas UQ", "Carrera 15 #12N, Armenia, Quindío");
    }

    //Getters y setters de Jokify

    //-----------------------------------------------------------------------------------------------------------------------------------------
    //Funciones de Jokify para el singleton

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
