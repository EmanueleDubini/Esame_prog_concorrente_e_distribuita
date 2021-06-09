package server;

import client.FruitoreNotizie;
import common.Editoriale;
import common.EditorialeTipo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

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

    private static final int P = 5000; //intervallo di tempo per ogni esecuzione delle azioni del metodo exec()

    /**
     * hash map che per ogni FruitorNotizie registrato al server, salva come chiave il nome del Fruitore e come valore le sue informazioni.
     * Da notare che infoAbbonato contiene il riferimento costituito dall'oggetto FruitoreNozie che lo caratterizza
     */
    private final HashMap<String, InfoAbbonato> listaFruitori = new HashMap<>();


    /**
     * COSTRUTTORE
     */
    public PubblicatoreImpl() throws RemoteException {
        raccoltaEditoriali = inizializzaEditoriali();
    }

    private static ArrayList<Editoriale> inizializzaEditoriali() {
        ArrayList<Editoriale> editoriali = new ArrayList<>();

        for (EditorialeTipo t  : EditorialeTipo.values()) {
            editoriali.add(new Editoriale(t)); // quando l'editoriale non contiene notizie, viene messo a null
        }
        return editoriali;
    }

    private String nomeUnivoco() {
        long i = 0;
        i++;
        return "Fruitore-" + i;
    }


    // IMPLEMENTAZIONE METODI remoti chiamati dal Client

    /**
     * metodo che aggiunge un FruitoreNotizie che si vuole abbonare ad un editoriale inserendolo nella lista degli abbonati ad un editoriale
     * @param tipo tipo di editoriale a cui ci si vuole sottoscrivere
     * @param fruitoreNotizia riferimento al client che invoca il metodo
     * @return boolean true se il metodo va a buon fine
     * @throws RemoteException RemoteException
     */
    @Override
    public synchronized boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        //synchronized perchè possono essere chiamati da client multipli

        //setta il nome del fruitore che chiama il metodo sottoscrivi, il nome viene scelto dal server dato che è l'entita che conoscera tutti i client
        String nomeFruitore = nomeUnivoco();
        fruitoreNotizia.setNome(nomeFruitore);

        if (listaFruitori.containsKey(nomeFruitore)) {
            //se il nome del fruitore e contenuto nella lista fruitori, quindi e gia abbonato a un editoriale

            //recupero le informazioni del fruitore gia presente nel server
            InfoAbbonato abbonato = listaFruitori.get(nomeFruitore);

            switch (tipo) {
                case politica:
                    if (abbonato.isPolitica()) {
                        //il fruitore è gia abbonato a questo editoriale
                        //avviso che il fruitore è gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale politica");
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
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale attualita");
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
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale scienza");
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
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale sport");
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
     * @param tipo tipo di editoriale a cui ci si vuole disiscrivere
     * @param fruitoreNotizia riferimento al client che invoca il metodo
     * @throws RemoteException RemoteException
     */
    @Override
    public synchronized void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        //synchronized perchè possono essere chiamati da client multipli

        //nome del fruitore che chiama il metodo sottoscrivi
        String nomeFruitore = fruitoreNotizia.getNome();

        if (listaFruitori.containsKey(nomeFruitore)) {
            //se il nome del fruitore e contenuto nella lista fruitori, quindi e gia abbonato a un editoriale

            //recupero le informazioni del fruitore gia presente nel server
            InfoAbbonato abbonato = listaFruitori.get(nomeFruitore);

            //disiscrivo il fruitore che chiama il metodo dall'editorialea cui richiede di essere disiscritto
            switch (tipo) {
                case politica:
                    if (abbonato.isPolitica()) {
                        //il fruitore è abbonato a questo editoriale, lo disiscrivo

                        abbonato.setPolitica(false);
                    }
                    //altrimenti, il fruitore non è abbonato a questo editoriale, non succede nulla
                    break;

                case attualita:
                    if (abbonato.isAttualita()) {
                        //il fruitore è abbonato a questo editoriale, lo disiscrivo

                        abbonato.setAttualita(false);
                    }
                    //altrimenti, il fruitore non è abbonato a questo editoriale, non succede nulla
                    break;

                case scienza:
                    if (abbonato.isScienza()) {
                        //il fruitore è abbonato a questo editoriale, lo disiscrivo

                        abbonato.setScienza(false);
                    }
                    //altrimenti, il fruitore non è abbonato a questo editoriale, non succede nulla
                    break;

                case sport:
                    if (abbonato.isSport()) {
                        //il fruitore è abbonato a questo editoriale, lo disiscrivo

                        abbonato.setSport(false);
                    }
                    //altrimenti, il fruitore non è abbonato a questo editoriale, non succede nulla
                    break;
            }

            if(!abbonato.isPolitica() && !abbonato.isAttualita() && !abbonato.isScienza() && !abbonato.isSport()){
                //se un Fruitore notizie non ha piu nessuna sottoscrizione a nessun editoriale allora viene tolto dalla memoria del server
                listaFruitori.remove(nomeFruitore, abbonato);
                fruitoreNotizia.avviso("SERVER: fruitore non possiede sottoscrizioni a nessun editoriale");
            }
        }
            //eseguito se il fruitore non è gia salvato nel server, non ha richiesto ancora nessun editoriale, avvisiamo che il fruitore non e sottoscritto a nessun editoriale




    }

    // IMPLEMENTAZIONE METODI chiamati dal Thread ProduttoreNotizie

    /**
     * Inserisce la notizia generata dal produttoreNotizie all'interno dei vari editoriali in base al tipo
     * @param tipo tipo di notizia da aggiungere
     * @param notizia notizia da aggiungere
     */
    public synchronized static void registraNotizia(EditorialeTipo tipo, String notizia) {
        //raccoltaEditoriali[0] = politica, raccoltaEditoriali[0] = attualita, raccoltaEditoriali[0] = scienza, raccoltaEditoriali[0] = sport
        switch(tipo){
            case politica:
                Editoriale editorialePolitica = raccoltaEditoriali.get(0);
                editorialePolitica.concatenaNotizia(notizia + ",");
                break;

            case attualita:
                Editoriale editorialeAttualita = raccoltaEditoriali.get(1);
                editorialeAttualita.concatenaNotizia(notizia + ",");
                break;

            case scienza:
                Editoriale editorialeScienza = raccoltaEditoriali.get(2);
                editorialeScienza.concatenaNotizia(notizia + ",");
                break;

            case sport:
                Editoriale editorialeSport = raccoltaEditoriali.get(3);
                editorialeSport.concatenaNotizia(notizia + ",");
                break;
        }
    }

    /**
     * Metodo che viene chiamato ogni 5 secondi dal server che trasmette tutti gli editoriali ai feruitoriNotizie interessati,
     * dopodiche elimina le notizie trasmesse dagli editoriali per poter contenere le nuove notizie generate
     */
    public synchronized void trasmettiEditorialiAlClient() {
        ArrayList<Editoriale> editorialiCondivisi = new ArrayList<>();
        for (InfoAbbonato fruitore : this.listaFruitori.values()) {
            //raccoltaEditoriali[0] = politica, raccoltaEditoriali[0] = attualita, raccoltaEditoriali[0] = scienza, raccoltaEditoriali[0] = sport


            //controlliamo a che tipo di editoriali un fruitore e interssato
            try {
                FruitoreNotizie client = fruitore.getFruitoreRemoto();
                if (fruitore.isPolitica()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(0));

                    //client.trasmettiEditoriale(raccoltaEditoriali.get(0));
                }

                if (fruitore.isAttualita()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(1));

                    //client.trasmettiEditoriale(raccoltaEditoriali.get(1));
                }

                if (fruitore.isScienza()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(2));

                    //client.trasmettiEditoriale(raccoltaEditoriali.get(2));
                }

                if (fruitore.isSport()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(3));

                    //client.trasmettiEditoriale(raccoltaEditoriali.get(3));
                }
                //trasmissione degli editoriali al client
                client.trasmettiEditoriale(editorialiCondivisi);

            } catch (RemoteException e) {
                System.err.println("Server: Client communication failed");
            }

            //pulizia delle notizie all'interno dei vari editoriali che sono stati appena trasmessi
        }

        for(Editoriale editoriale : raccoltaEditoriali){
            editoriale.setContenuto("");
        }
    }


    /**
     * Esegue le azioni della macchina a stati (trasmette editoriale T - cancella elenco notizie di T) ogni P secondi
     *
     * Il Pubblicatore, rimane in attesa da richieste da parte dei FruitoreNotizie ed esegue ogni P secondi le seguenti operazioni, per ogni tipo T di editoriale:
     *
     * • trasmette l’editoriale di tipo T a tutti i FruitoreNotizie interessati a quel tipo di editoriale
     * • cancella l'elenco delle notizie associate all’editoriale di tipo T, in modo che la prossima notizia
     *   da un ProduttoreNotizie venga aggiunta alla stringa vuota, e quindi l’editoriale contenga solo
     *   notizie nuove.
     */
    public void exec() throws InterruptedException {



        while(true){

            Thread.sleep(P);

            trasmettiEditorialiAlClient();

            /*System.err.println(raccoltaEditoriali.get(0).toString());
            System.err.println(raccoltaEditoriali.get(1).toString());
            System.err.println(raccoltaEditoriali.get(2).toString());
            System.err.println(raccoltaEditoriali.get(3).toString());*/


        }
    }



}//END_Class
