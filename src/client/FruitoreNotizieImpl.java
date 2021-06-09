package client;

import common.Editoriale;
import common.EditorialeTipo;
import common.ProgUtili;
import server.Pubblicatore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * IMPLEMENTAZIONE del Client
 */
public class FruitoreNotizieImpl extends UnicastRemoteObject implements FruitoreNotizie{
    private String nome;
    private final Pubblicatore stub;

    /**
     * Contiene tutti gli editoriali a cui un FruitoreNotizie e sottoscritto
      */
    ArrayList<EditorialeTipo> tipiIscrizione = new ArrayList<>();

    public FruitoreNotizieImpl(Pubblicatore server) throws RemoteException {
        super();
        this.stub = server;
    }



    @Override
    public void trasmettiEditoriale(ArrayList<Editoriale> editorialiCondivisi) throws RemoteException {
        for(Editoriale editoriale : editorialiCondivisi){
            System.out.println(editoriale.toString());

        }
        System.out.println("------------------------------------------------------------------------");
    }

    @Override
    public void avviso(String avviso) throws RemoteException{
        System.out.println(avviso);
    }

    @Override
    public String getNome() throws RemoteException {
        return nome;
    }

    @Override
    public void setNome(String nome) throws RemoteException {
        this.nome = nome;
    }

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

    public void sottoscrizioneCasuale() throws RemoteException {
        EditorialeTipo tipo = EditorialeTipo.getEditorialeTipoCasuale();
        if(!tipiIscrizione.contains(tipo)) {
            System.out.println(ProgUtili.ANSI_GREEN + "mi sottoscrivo all'editoriale di tipo " + tipo + ProgUtili.ANSI_RESET);

            //aggiorniamo le iscrizioni del FruitoreNotizie, localmente e remotamente
            tipiIscrizione.add(tipo);
            stub.sottoscrivi(tipo, this);
        }
    }

    public void disiscrizioneCasuale() throws RemoteException {
        int indice = (int)(Math.random() * tipiIscrizione.size());

        //scelta di una posizione a caso dell'arraylist che contiene i tipi di sottoscrizioni effettuati dal client
        if(!tipiIscrizione.isEmpty()){
            EditorialeTipo tipologia = tipiIscrizione.get(indice);

            System.out.println(ProgUtili.ANSI_CYAN  + "mi disiscrivo all'editoriale di tipo " + tipologia + ProgUtili.ANSI_RESET);

            tipiIscrizione.remove(tipologia);
            stub.disiscrivi(tipologia, this);
        }

    }

    public static void main(String[] args)  {

        ProgUtili.clearScreen();
        System.out.println("il client e stato implementato in modo che esegua all'infinito");

        try {
            String host = args.length >= 1 ? args[0] : null;
            Registry registry = LocateRegistry.getRegistry(host, FruitoreNotizie.PORT);
            Pubblicatore stub = (Pubblicatore) registry.lookup("Pubblicatore");


            FruitoreNotizieImpl me = new FruitoreNotizieImpl(stub);



            /*stub.sottoscrivi(EditorialeTipo.politica, me);
            stub.sottoscrivi(EditorialeTipo.attualita, me);
            stub.sottoscrivi(EditorialeTipo.scienza, me);
            stub.sottoscrivi(EditorialeTipo.sport, me);*/

            me.exec();

        }catch(Exception e){
            System.err.println("SERVER NOT STARTED");
        }



    }

}