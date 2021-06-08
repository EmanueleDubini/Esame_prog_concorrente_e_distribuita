package server;

import client.FruitoreNotizie;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pubblicatore extends Remote {

    public final static int PORT = 1200;


    public void sottoscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException;
    public void disiscrivi(EditorialeTipo tipo, FruitoreNotizie fruitoreNotizia) throws RemoteException;

}
