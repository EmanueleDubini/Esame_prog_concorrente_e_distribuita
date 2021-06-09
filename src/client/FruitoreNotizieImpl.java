package client;

import common.Editoriale;
import server.EditorialeTipo;
import server.Pubblicatore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * IMPLEMENTAZIONE del Client
 */
public class FruitoreNotizieImpl extends UnicastRemoteObject implements FruitoreNotizie{
    private final String name;

    public FruitoreNotizieImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public void trasmettiEditoriale(Editoriale editoriale) throws RemoteException {
        System.out.println(editoriale);
    }

    @Override
    public void avviso(String avviso) throws RemoteException{
        System.out.println(avviso);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    private void exec() {
        while (true);
    }

    public static void main(String[] args) throws Exception {

        //System.setSecurityManager(new SecurityManager());

        String host = args.length >= 1 ? args[0] : null;
        Registry registry = LocateRegistry.getRegistry(host, FruitoreNotizie.PORT);
        Pubblicatore stub = (Pubblicatore) registry.lookup("Pubblicatore");

        FruitoreNotizieImpl me = new FruitoreNotizieImpl("Pippo");//todo definire i nomi dei client dal server
        stub.sottoscrivi(EditorialeTipo.politica, me);
        stub.sottoscrivi(EditorialeTipo.attualita, me);
        stub.sottoscrivi(EditorialeTipo.scienza, me);
        stub.sottoscrivi(EditorialeTipo.sport, me);
        me.exec();

        //todo gestire quando viene avviato prima il client del server

    }

}