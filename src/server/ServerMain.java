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

        // Esecuzione
        ProduttoreNotizie pn1 = new ProduttoreNotizie(); pn1.start();
        ProduttoreNotizie pn2 = new ProduttoreNotizie(); pn2.start();
        ProduttoreNotizie pn3 = new ProduttoreNotizie(); pn3.start();
        ProduttoreNotizie pn4 = new ProduttoreNotizie(); pn4.start();
        ProduttoreNotizie pn5 = new ProduttoreNotizie(); pn5.start();

    }//END_Main

}//END_ServerMain_Class