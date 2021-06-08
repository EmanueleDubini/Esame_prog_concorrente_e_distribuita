package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    // MAIN
    public static void main(String[] args) throws Exception {

        /*
        // Gestione Security manager
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        */


        PubblicatoreImpl obj = new PubblicatoreImpl(); // oggetto locale
        Registry registry = LocateRegistry.createRegistry(Pubblicatore.PORT); // registry avviato automaticamente
        registry.rebind("Pubblicatore", obj);
        System.out.println("Server Pubblicatore Pronto...");



    }//END_Main
}
