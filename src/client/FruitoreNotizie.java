package client;

import common.Editoriale;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * INTERFACCIA del Client
 * Anche qui serve interfaccia remota perche il server deve fare notifiche asincrone al Client
 */
public interface FruitoreNotizie extends Remote {

    public final static int PORT = 1200;

    // METODI remoti chiamati dal Server

    public void notifica(Editoriale value) throws RemoteException; // passa l'editoriale da stampare
    public void giaSottoscritto() throws RemoteException;
    public String getName() throws RemoteException; //richiesta del nome del Client

}
