package server;

import client.FruitoreNotizie;
import common.EditorialeTipo;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * INTERFACCIA del Server
 */
public interface Pubblicatore extends Remote {

    public final static int PORT = 1200;

    // METODI remoti chiamati dal Client

    // gli passiamo il tipo di editoriale di cui fa richiesta, e un oggetto Client
    // Boolena perche ci serve sapere se la sottoscrizione è andata a buon fine o no
    public boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException; // fa anche la connessione

    // tipo void perche se il Client si disconnette il server non ci puo fare nulla, senza controllo
    public void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException;

}
