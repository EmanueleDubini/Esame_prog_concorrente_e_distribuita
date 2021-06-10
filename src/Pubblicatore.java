import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * INTERFACCIA del Server
 */
public interface Pubblicatore extends Remote {

    /**
     * porta su cui il server si rende disponibile
     */
    public final static int PORT = 1200;

    // METODI remoti chiamati dal Client

    /**
     * metodo che permette la sottoscrizione di un client ad un tipo specifico di editoriale, una sottoscrizione per ogni metodo invocato.
     * Una volta sottoscritto si riceveranno gli editoriali a cui si e abbonati
     *
     * @param tipo tido di editoriale a cui si desidera sottoscriversi
     * @param fruitoreNotizia riferimento all'istanza del client che invoca il metodo
     * @return true se la sottscrizione va a buon fine
     * @throws RemoteException RemoteException
     */
    public boolean sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException; // fa anche la connessione

    /**
     * metodo che permette la disiscrizione di un client ad un tipo di editoriale specificato, una disiscrizione per ogni metodo.
     * Una volta disiscritto non si riceveranno piu gli editoriali da cui si ha disdetto l'abbonamento
     *
     * @param tipo tipo di editoriale da cui si desidera disiscriversi
     * @param fruitoreNotizia riferimento all'istanza del client che invoca il metodo
     * @throws RemoteException RemoteException
     */
    public void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException;

    /**
     * metodo che restituisce a chi lo invoca un nome univoco per accedere al server
     *
     * @return String, il nome univoco
     * @throws RemoteException
     */
    public String nomeUnivoco() throws RemoteException;

}
