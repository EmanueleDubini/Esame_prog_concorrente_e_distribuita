package client;

import common.Editoriale;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * INTERFACCIA del Client
 * Anche qui serve interfaccia remota perche il server deve fare notifiche asincrone al Client
 */
public interface FruitoreNotizie extends Remote {

    public final static int PORT = 1200;

    // METODI remoti chiamati dal Server

    public void trasmettiEditoriale(ArrayList<Editoriale> editorialiCondivisi) throws RemoteException; // passa l'editoriale da stampare
    public void avviso(String avviso) throws RemoteException;
    public String getNome() throws RemoteException; //richiesta del nome del Client
    public void setNome(String nome) throws RemoteException; //setta il nome del client

}
