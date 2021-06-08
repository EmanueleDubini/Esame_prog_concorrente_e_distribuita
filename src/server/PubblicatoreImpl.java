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
    /**
    * Arraylist raccortaEditoriali continene i 4 tipi di editoriali possibili:
     * pos1 = politica, pos2 = attualita, pos3 = scienza, pos4 = sport
     */
    private static  ArrayList<Editoriale> raccoltaEditoriali; // contiene i 4 editoriali
    private static final int P = 5; //intervallo di tempo per ogni esecuzione delle azioni del metodo exec()


    // COSTRUTTORE
    public PubblicatoreImpl() throws RemoteException {
        raccoltaEditoriali = inizializzaEditoriali();
    }

    // IMPLEMENTAZIONE METODI remoti chiamati dal Client
    @Override
    public synchronized boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        return false;
    }

    @Override
    public synchronized void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {

    }


    private static ArrayList<Editoriale> inizializzaEditoriali() {
        ArrayList<Editoriale> editoriali = new ArrayList<>();

        for (EditorialeTipo t  : EditorialeTipo.values()) {
            editoriali.add(new Editoriale(t)); // quando l'editoriale non contiene notizie, viene messo a null
        }
        return editoriali;
    }

    /**
     * Inserisce la notizia generata dal produttoreNotizie all'interno dei vari editoriali in base al tipo
     * @param tipo tipo di notizia da aggiungere
     * @param notizia notizia da aggiungere
     */
    public synchronized static void registraNotizia(EditorialeTipo tipo, String notizia) {
        switch(tipo){
            case politica:
                Editoriale editorialePolitica = raccoltaEditoriali.get(0);
                editorialePolitica.concatenaNotizia(notizia);
                break;

            case attualita:
                Editoriale editorialeAttualita = raccoltaEditoriali.get(1);
                editorialeAttualita.concatenaNotizia(notizia);
                break;

            case scienza:
                Editoriale editorialeScienza = raccoltaEditoriali.get(2);
                editorialeScienza.concatenaNotizia(notizia);
                break;

            case sport:
                Editoriale editorialeSport = raccoltaEditoriali.get(3);
                editorialeSport.concatenaNotizia(notizia);
                break;
        }
    }

    // Esegue le azioni della macchina a stati (trasmette editoriale T-cancella elenco notizie di T)
    public void exec() throws InterruptedException {
        while(true){
            Thread.sleep(3000);
            System.err.println(raccoltaEditoriali.get(0).toString());
            System.err.println(raccoltaEditoriali.get(1).toString());
            System.err.println(raccoltaEditoriali.get(2).toString());
            System.err.println(raccoltaEditoriali.get(3).toString());
        }
    }

}//END_Class
