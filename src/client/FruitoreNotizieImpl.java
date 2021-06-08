package client;

import common.Editoriale;
import server.Pubblicatore;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * IMPLEMENTAZIONE del Client
 */
public class FruitoreNotizieImpl {
    public static void main(String[] args) throws Exception {

        //System.setSecurityManager(new SecurityManager());

        String host = args.length >= 1 ? args[0] : null;
        Registry registry = LocateRegistry.getRegistry(host);
        Pubblicatore stub = (Pubblicatore) registry.lookup("Pubblicatore");


    }
}