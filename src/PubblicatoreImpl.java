import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * IMPLEMENTAZIONE del Server
 */
public class PubblicatoreImpl extends UnicastRemoteObject implements Pubblicatore {
    /**
     * serialVersionUID per la comunicazione seriale
     */
    private static final long serialVersionUID = 1L;

    /**
     * intervallo di tempo per ogni esecuzione delle azioni del metodo exec()
     */
    private static final int P = 5000;

    /**
     * variabile per generare identificatori del client
     */
    long i = 0;

    /**
    * Arraylist raccortaEditoriali continene i 4 tipi di editoriali possibili:
     * pos1 = politica, pos2 = attualita, pos3 = scienza, pos4 = sport
     */
    private static  ArrayList<Editoriale> raccoltaEditoriali; // contiene i 4 editoriali

    /**
     * hash map che per ogni FruitorNotizie registrato al server, salva come chiave il nome del Fruitore e come valore le sue informazioni.
     * Da notare che infoAbbonato contiene il riferimento costituito dall'oggetto FruitoreNozie che lo caratterizza
     */
    private final HashMap<String, InfoAbbonato> listaFruitori = new HashMap<>();

    /**
     * Arraylist editorialiCondividi utilizzato nel metodo "trasmettiEditorialiAlClient()" per trasmettere ai vari fruitoriNotizie gli editoriali a cui sono abbonati
     */
    ArrayList<Editoriale> editorialiCondivisi = new ArrayList<>();


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

    // IMPLEMENTAZIONE METODI remoti chiamati dal Client

    /**
     * metodo che quando viene invocato restituisce un nome per il client che desidera colegarsi al server, per essere identificato
     * @return String l'identificatore
     * @throws RemoteException
     */
    @Override
    public String nomeUnivoco() throws RemoteException {
        i++;
        return "Fruitore-" + i;
    }


    /**
     * metodo che aggiunge un FruitoreNotizie che si vuole abbonare ad un editoriale inserendolo nella lista degli abbonati ad un editoriale
     * @param tipo tipo di editoriale a cui ci si vuole sottoscrivere
     * @param fruitoreNotizia riferimento al client che invoca il metodo
     * @return boolean true se il metodo va a buon fine
     * @throws RemoteException RemoteException
     */
    @Override
    public synchronized boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException {
        //synchronized perche possono essere chiamati da client multipli

        //setta il nome del fruitore che chiama il metodo sottoscrivi, il nome viene scelto dal server dato che e l'entita che conoscera tutti i client
        String nomeFruitore = fruitoreNotizia.getNome();

        if (listaFruitori.containsKey(nomeFruitore)) {
            //se il nome del fruitore e contenuto nella lista fruitori, quindi e gia abbonato a un editoriale

            //recupero le informazioni del fruitore gia presente nel server
            InfoAbbonato abbonato = listaFruitori.get(nomeFruitore);

            switch (tipo) {
                case politica:
                    if (abbonato.isPolitica()) {
                        //il fruitore e gia abbonato a questo editoriale
                        //avviso che il fruitore e gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale politica");
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setPolitica(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case attualita:
                    if (abbonato.isAttualita()) {
                        //il fruitore e gia abbonato a questo editoriale
                        //avviso che il fruitore e gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale attualita");
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setAttualita(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case scienza:
                    if (abbonato.isScienza()) {
                        //il fruitore e gia abbonato a questo editoriale
                        //avviso che il fruitore e gia sottoscritto all'editoriale richiesto
                        fruitoreNotizia.avviso("SERVER: fruitore gia sottoscritto all'editoriale scienza");
                    }else {
                        //abbono il fruitore al nuovo editoriale che ha richiesto
                        abbonato.setScienza(true);
                        listaFruitori.replace(nomeFruitore, abbonato);
                    }
                    break;

                case sport:
                    if (abbonato.isSport()) {
                        //il fruitore e gia abbonato a questo editoriale
                        //avviso che il fruitore e gia sottoscritto all'editoriale richiesto
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
            //eseguito se il fruitore non e gia salvato nel server, non ha richiesto ancora nessun editoriale, lo salviamo settando di che tipo di editoriale vuole ricevere notizie

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
        //synchronized perche possono essere chiamati da client multipli

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
                        //il fruitore e abbonato a questo editoriale, lo disiscrivo

                        abbonato.setPolitica(false);
                    }
                    //altrimenti, il fruitore non e abbonato a questo editoriale, non succede nulla
                    break;

                case attualita:
                    if (abbonato.isAttualita()) {
                        //il fruitore e abbonato a questo editoriale, lo disiscrivo

                        abbonato.setAttualita(false);
                    }
                    //altrimenti, il fruitore non e abbonato a questo editoriale, non succede nulla
                    break;

                case scienza:
                    if (abbonato.isScienza()) {
                        //il fruitore e abbonato a questo editoriale, lo disiscrivo

                        abbonato.setScienza(false);
                    }
                    //altrimenti, il fruitore non e abbonato a questo editoriale, non succede nulla
                    break;

                case sport:
                    if (abbonato.isSport()) {
                        //il fruitore e abbonato a questo editoriale, lo disiscrivo

                        abbonato.setSport(false);
                    }
                    //altrimenti, il fruitore non e abbonato a questo editoriale, non succede nulla
                    break;
            }

            if(!abbonato.isPolitica() && !abbonato.isAttualita() && !abbonato.isScienza() && !abbonato.isSport()){
                //se un Fruitore notizie non ha piu nessuna sottoscrizione a nessun editoriale allora viene tolto dalla memoria del server
                listaFruitori.remove(nomeFruitore, abbonato);
                //listaFruitori.remove(nomeFruitore);
                fruitoreNotizia.avviso("SERVER: fruitore non possiede sottoscrizioni a nessun editoriale");
            }
        }
    }


    // IMPLEMENTAZIONE METODI chiamati dal Thread ProduttoreNotizie

    /**
     * Inserisce la notizia generata dal produttoreNotizie all'interno dei vari editoriali in base al tipo
     * @param tipo tipo di notizia da aggiungere
     * @param notizia notizia da aggiungere
     */
    public synchronized static void registraNotizia(EditorialeTipo tipo, String notizia) {
        //synchronized perche possono essere chiamati da client multipli

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
        //leggiamo ogni fruitore abbonato ad almeno un editoriale e ne iniviamo gli editoriali
        for (InfoAbbonato fruitore : this.listaFruitori.values()) {
            //raccoltaEditoriali[0] = politica, raccoltaEditoriali[0] = attualita, raccoltaEditoriali[0] = scienza, raccoltaEditoriali[0] = sport


            //controlliamo a che tipo di editoriali un fruitore e interssato
            try {
                FruitoreNotizie client = fruitore.getFruitoreRemoto();
                if (fruitore.isPolitica()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(0));
                }

                if (fruitore.isAttualita()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(1));
                }

                if (fruitore.isScienza()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(2));
                }

                if (fruitore.isSport()) {
                    //trasmetto al client l'editoriale
                    editorialiCondivisi.add(raccoltaEditoriali.get(3));
                }
                //trasmissione degli editoriali al client
                client.trasmettiEditoriale(editorialiCondivisi);

                //puliamo l'arraylist prima che lo usi il fruitore successivo
                editorialiCondivisi.clear();

            } catch (RemoteException e) {
                //se si raggiunge questo punto del codice vuol dire che un client si e disconnesso
            }
        }

        //pulizia delle notizie all'interno dei vari editoriali che sono stati appena trasmessi
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
        //metodo che non ha bisogno di essere synchronized e che permette l'esecuzione ciclica del metodo di stampa degli editoriali
        //se mettessimo anche questo metodo synchronized ogni volta che viene eseguito questo (e magari contiene piu istruzioni della singola " trasmettiEditorialiAlClient(); "), gli altri metodi del server non potrebbero venire eseguiti in concorrenza da qualche client e il thread del server avrebbe il lock dell'oggetto
        //anche se questa cosa avviene solo durante il cambio di stato del server


        while(true){

            Thread.sleep(P); //quando e in sleep e sarebbe un metodo synchronized manterrebbe il lock e quindi non permetterebbe ai thread di usufruire dei metodi remoti del server

            //trasmissione editoriali ai fruitoriNotizie
            trasmettiEditorialiAlClient();

        }
    }



}//END_Class
