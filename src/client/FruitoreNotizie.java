package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * INTERFACCIA del Client
 * Anche qui serve interfaccia remota perche il server deve fare notifiche asincrone al Client
 */
public interface FruitoreNotizie extends Remote {

    public final static int PORT = 1200;

    public void richiestaTipoEditoriale(common.Editoriale value) throws RemoteException; // Client interessato a ricevere tipi editoriali
    public void stopRichiestaTipoEditoriale(common.Editoriale value) throws RemoteException; // Client NON interessato a ricevere tipi editoriali
    public String getName() throws RemoteException; //richiesta del nome del Client

    // riceve raccorta di editoriali
    //public void notifica(common.Editoriale value) throws RemoteException; //todo
}
