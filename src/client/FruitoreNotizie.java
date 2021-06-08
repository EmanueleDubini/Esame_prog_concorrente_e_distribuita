package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FruitoreNotizie extends Remote {

    public final static int PORT = 1200;


    // riceve raccorta di editoriali
    //public void notifica(common.Editoriale value) throws RemoteException; //todo
}
