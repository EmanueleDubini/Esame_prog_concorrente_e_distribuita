package server;

import client.FruitoreNotizie;
import common.Editoriale;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import static server.EditorialeTipo.politica;

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

    /**
     * hash map che per ogni FruitorNotizie registrato al server, salva come chiave il nome del Fruitore e come valore le sue informazioni.
     * Da notare che infoAbbonato contiene il riferimento costituito dall'oggetto FruitoreNozie che lo caratterizza
     */
    private final HashMap<String, InfoAbbonato> listaFruitori = new HashMap<>();


    // COSTRUTTORE
    public PubblicatoreImpl() throws RemoteException {
        raccoltaEditoriali = inizializzaEditoriali();
    }

    // IMPLEMENTAZIONE METODI remoti chiamati dal Client

    /**
     * metodo che aggiunge un FruitoreNotizie che si vuole abbonare ad un editoriale inserendolo nella lista degli abbonati ad un editoriale
     * @param tipo
     * @param fruitoreNotizia
     * @return boolean true se il metodo va a buon fine
     * @throws RemoteException
     */
    @Override
    public synchronized boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        //synchronized perchè possono essere chiamati da client multipli

        //nome del fruitore che chiama il metodo sottoscrivi
        String nomeFruitore = fruitoreNotizia.getName();

        if (listaFruitori.containsKey(nomeFruitore)) {
            //se il nome del fruitore e contenuto nella lista fruitori, quindi e gia abbonato a un editoriale

            //recupero le informazioni del fruitore gia presente nel server
            InfoAbbonato abbonato = listaFruitori.get(nomeFruitore);

            switch (tipo) {
                case politica:
                    if (abbonato.isPolitica()) {
                        //il fruitore è gia abbonato a questo editoriale
                        //avviso che il fruitore è gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.giaSottoscritto();
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setPolitica(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case attualita:
                    if (abbonato.isAttualita()) {
                        //il fruitore è gia abbonato a questo editoriale
                        //avviso che il fruitore è gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.giaSottoscritto();
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setAttualita(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case scienza:
                    if (abbonato.isScienza()) {
                        //il fruitore è gia abbonato a questo editoriale
                        //avviso che il fruitore è gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.giaSottoscritto();
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setScienza(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case sport:
                    if (abbonato.isSport()) {
                        //il fruitore è gia abbonato a questo editoriale
                        //avviso che il fruitore è gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.giaSottoscritto();
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setSport(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;
            }
            return false;

        } else {
            //eseguito se il fruitore non è gia salvato nel server, non ha richiesto ancora nessun editoriale, lo salviamo settando di che tipo di editoriale vuole ricevere notizie

            InfoAbbonato info = new InfoAbbonato(fruitoreNotizia);

            switch (tipo) {
                case politica:
                    info.setPolitica(true);
                    break;

                case attualita:
                    info.setAttualita(true);
                    break;

                case scienza:
                    info.setScienza(true);
                    break;

                case sport:
                    info.setSport(true);
                    break;
            }
            //salviamo le informazioni del fruitore nel server
            this.listaFruitori.putIfAbsent(nomeFruitore, info);
            return true;
        }
    }

    /**
     * metodo che rimuove un FruitoreNotizie che si vuole disiscrivere ad un editoriale modificando la lista degli abbonati ad un editoriale
     * @param tipo
     * @param fruitoreNotizia
     * @return boolean true se il metodo va a buon fine
     * @throws RemoteException
     */
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

        System.err.println(raccoltaEditoriali.get(0).toString());
        System.err.println(raccoltaEditoriali.get(1).toString());
        System.err.println(raccoltaEditoriali.get(2).toString());
        System.err.println(raccoltaEditoriali.get(3).toString());

        while(true){


            //Metodo synchronized: trasmetti editoriali


            Thread.sleep(5000);
        }
    }

}//END_Class
