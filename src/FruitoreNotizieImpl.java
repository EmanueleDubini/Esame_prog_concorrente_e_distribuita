import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * IMPLEMENTAZIONE del Client
 * il client e stato implementato in modo che esegua all'infinito
 */
public class FruitoreNotizieImpl extends UnicastRemoteObject implements FruitoreNotizie{
    /**
     * serialVersionUID per la comunicazione seriale
     */
    private static final long serialVersionUID = 1L;

    /**
     * nome dell'istanza del client
     */
    private String nome;

    /**
     * riferimento all'oggetto remoto server
     */
    private final Pubblicatore stub;

    /**
     * Contiene tutti gli editoriali a cui un FruitoreNotizie e sottoscritto
     */
    ArrayList<EditorialeTipo> tipiIscrizione = new ArrayList<>();

    /**
     * Costruttore
     *
     * @param nome nome da assegnare all'oggetto creato
     * @param server riferimento remoto al server
     * @throws RemoteException RemoteException
     */
    public FruitoreNotizieImpl(String nome, Pubblicatore server) throws RemoteException {
        super();
        this.nome = nome;
        this.stub = server;
    }


    /**
     * Metodo che trasmette un Arraylist con cli editoriali da stampare e poi lo svuota quando
     * sono stati trasmessi
     *
     * @param editorialiCondivisi lista di editoriali ricevuti
     * @throws RemoteException RemoteException
     */
    @Override
    public void trasmettiEditoriale(ArrayList<Editoriale> editorialiCondivisi) throws RemoteException {
        for(Editoriale editoriale : editorialiCondivisi){
            System.out.println(editoriale.toString());
            System.out.println("\n");
            System.out.flush();

        }
        System.out.println("------------------------------------------------------------------------");
        System.out.flush();
    }

    /**
     * Trasmette un avviso al client il quale lo stampa
     */
    @Override
    public void avviso(String avviso) throws RemoteException{
        System.out.println(avviso);
    }


    /**
     * Restituisce il nome dell'istanza del client
     *
     * @return nome dell'istanza
     * @throws RemoteException RemoteException
     */
    @Override
    public String getNome() throws RemoteException {
        return nome;
    }

    /**
     * Setta il nome dell'istanza del client
     *
     * @param nome nome da impostare
     * @throws RemoteException RemoteException
     */
    @Override
    public void setNome(String nome) throws RemoteException {
        this.nome = nome;
    }

    /**
     * Esecuzione del Client
     *
     * @throws RemoteException RemoteException
     */
    private void exec() throws RemoteException {
        sottoscrizioneCasuale();

        while (true) {
            //il client si sottoscrive ad un editoriale casualmente tra i 4 disponibili

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                System.err.println("Questo punto del programma non dovrebbe mai essere raggiunto");
            }

            int scelta = ThreadLocalRandom.current().nextInt(0, 10);

            if (scelta <= 5) {
                //se scelta e uguale a 1,2,3,4 o 5 si sottoscrive ad un altro editoriale
                sottoscrizioneCasuale();
            } else if (scelta <= 7) {
                //se scelta e uguale a 6 o 7 si disiscrive da un editoriale a cui è iscritto
                disiscrizioneCasuale();
            }
            //altrimenti non viene fatta nessuna azione
        }
    }

    /**
     * Metodo che in automatico sottoscrive il Client a un editoriale casuale
     *
     * @throws RemoteException RemoteException
     */
    public void sottoscrizioneCasuale() throws RemoteException {
        EditorialeTipo tipo = EditorialeTipo.getEditorialeTipoCasuale();
        if(!tipiIscrizione.contains(tipo)) {
            System.out.println(ProgUtili.ANSI_GREEN + nome + ": mi sottoscrivo all'editoriale di tipo " + tipo + ProgUtili.ANSI_RESET);

            //aggiorniamo le iscrizioni del FruitoreNotizie, localmente e remotamente
            tipiIscrizione.add(tipo);
            stub.sottoscrivi(tipo, this);
        }
    }

    /**
     * Metodo che in automatico disicrive il Client da un editoriale casuale
     *
     * @throws RemoteException RemoteException
     */
    public void disiscrizioneCasuale() throws RemoteException {
        int indice = (int)(Math.random() * tipiIscrizione.size());

        //scelta di una posizione a caso dell'arraylist che contiene i tipi di sottoscrizioni effettuati dal client
        if(!tipiIscrizione.isEmpty()){
            EditorialeTipo tipologia = tipiIscrizione.get(indice);

            System.out.println(ProgUtili.ANSI_CYAN + nome + ": mi disiscrivo all'editoriale di tipo " + tipologia + ProgUtili.ANSI_RESET);

            tipiIscrizione.remove(tipologia);
            stub.disiscrivi(tipologia, this);
        }

    }


    /**
     * Metodo main dell'applicazzione Client, entry point da cui avviare
     *
     * @param args indirizzo ip del server a cui si desidera connettersi
     */
    public static void main(String[] args)  {

        ProgUtili.clearScreen(); // pulisce lo schermo
        System.out.println("***************************");
        System.out.println("** FRUITORE-NOTIZIE-IMPL **");
        System.out.println("***************************\n");


        try {
            String host = args.length >= 1 ? args[0] : null;    //argomenti della linea di comando (ip_address / localhost)
            Registry registry = LocateRegistry.getRegistry(host, FruitoreNotizie.PORT);
            Pubblicatore stub = (Pubblicatore) registry.lookup("Pubblicatore");

            FruitoreNotizieImpl me = new FruitoreNotizieImpl(stub.nomeUnivoco(), stub);

            /*stub.sottoscrivi(EditorialeTipo.politica, me);
            stub.sottoscrivi(EditorialeTipo.attualita, me);
            stub.sottoscrivi(EditorialeTipo.scienza, me);
            stub.sottoscrivi(EditorialeTipo.sport, me);*/

            me.exec();

        }catch(Exception e){
            System.err.println("SERVER NOT STARTED"); // gestione dell'eccezione se il Client parte prima del Server
        }



    }

}