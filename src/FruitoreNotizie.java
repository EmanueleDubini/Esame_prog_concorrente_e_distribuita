import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * INTERFACCIA del Client
 * Anche qui serve interfaccia remota perche il server deve fare notifiche asincrone al Client (callback)
 */
public interface FruitoreNotizie extends Remote {

    /**
     * porta su cui il server si rende disponibile
     */
    public final static int PORT = 1200;

    // METODI remoti chiamati dal Server

    /**
     * metodo chiamato remotamente dal server come callback per condividere al client gli editoriali che verranno poi stampati
     *
     * @param editorialiCondivisi editoriali condivisi
     * @throws RemoteException RemoteException
     */
    public void trasmettiEditoriale(ArrayList<Editoriale> editorialiCondivisi) throws RemoteException;

    /**
     * metodo chiamato remotamente dal server come callback per trasmettere un avviso al client
     *
     * @param avviso l'avviso da trasmettere
     * @throws RemoteException RemoteException
     */
    public void avviso(String avviso) throws RemoteException;

    /**
     * metodo chiamato remotamente dal server come callback per ottenere il nome di un istanza del client
     *
     * @return String nome dell'istanza
     * @throws RemoteException RemoteException
     */
    public String getNome() throws RemoteException;

    /**
     * metodo chiamato remotamente dal server come callback per settare il nome di un client
     *
     * @param nome nome da impostare
     * @throws RemoteException RemoteException
     */
    public void setNome(String nome) throws RemoteException;

}
