package client;

import common.Editoriale;
import server.Pubblicatore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * IMPLEMENTAZIONE del Client
 */
public class FruitoreNotizieImpl implements FruitoreNotizie{
    private final String name;

    public FruitoreNotizieImpl(String name) {
        this.name = name;
    }

    @Override
    public void notifica(Editoriale value) throws RemoteException {

    }

    @Override
    public void giaSottoscritto() throws RemoteException{
        System.out.println("sottoscrizione gia effettuata all'editoriale rischiesto");
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    public static void main(String[] args) throws Exception {

        //System.setSecurityManager(new SecurityManager());

        String host = args.length >= 1 ? args[0] : null;
        Registry registry = LocateRegistry.getRegistry(host);
        Pubblicatore stub = (Pubblicatore) registry.lookup("Pubblicatore");


    }
}