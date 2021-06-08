package server;

import client.FruitoreNotizie;
import common.Editoriale;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * IMPLEMENTAZIONE del Server
 */
public class PubblicatoreImpl extends UnicastRemoteObject implements Pubblicatore {
    private static final long serialVersionUID = 1L;
    private static  ArrayList<Editoriale> raccoltaEditoriali;
    private static final int P = 5; //intervallo di tempo per ogni esecuzione delle azioni del metodo exec()


    // COSTRUTTORE
    public PubblicatoreImpl() throws RemoteException {
        raccoltaEditoriali = inizializzaEditoriali();
    }

    // IMPLEMENTAZIONE METODI
    @Override
    public synchronized boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        return false;
    }

    @Override
    public synchronized void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {

    }

    @Override
    public synchronized boolean editoriale(Editoriale e) throws RemoteException {
        return false;
    }

    private static ArrayList<Editoriale> inizializzaEditoriali() {
        ArrayList<Editoriale> editoriali = new ArrayList<>();

        for (EditorialeTipo t  : EditorialeTipo.values()) {
            editoriali.add(new Editoriale(t, null, null)); // quando l'editoriale non contiene notizie, viene messo a null
        }
        return editoriali;
    }

    // Esegue le azioni della macchina a stati (trasmette editoriale T-cancella elenco notizie di T)
    private void exec() throws InterruptedException {
        while(true){

        }
    }

}//END_Class
