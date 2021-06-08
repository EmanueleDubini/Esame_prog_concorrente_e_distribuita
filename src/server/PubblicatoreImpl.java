package server;

import client.FruitoreNotizie;
import common.Editoriale;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PubblicatoreImpl extends UnicastRemoteObject implements Pubblicatore {

    private static  ArrayList<Editoriale> raccoltaEditoriali;


    // COSTRUTTORE
    public PubblicatoreImpl() throws RemoteException {
        raccoltaEditoriali = inizializzaEditoriali();
    }

    // IMPLEMENTAZIONE METODI
    @Override
    public synchronized void sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {

    }

    @Override
    public synchronized void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {

    }

    private static ArrayList<Editoriale> inizializzaEditoriali() {
        ArrayList<Editoriale> editoriali = new ArrayList<>();

        for (EditorialeTipo t  : EditorialeTipo.values()) {
            editoriali.add(new Editoriale(t, null)); // quando l'editoriale non contiene notizie, viene messo a null
        }
        return editoriali;
    }

}//END_Class
