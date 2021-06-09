import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    // MAIN
    public static void main(String[] args) {

        // settiamo l'ip hostname con l'ip della macchina che esegue questo codice, ServerCV main()
        System.setProperty("java.rmi.server.hostname", IpAddressServer.getServerAddress());

        ProgUtili.clearScreen();
        ProgUtili.stampaPresentazione();

        try {
            PubblicatoreImpl obj = new PubblicatoreImpl(); // oggetto locale
            Registry registry = LocateRegistry.createRegistry(Pubblicatore.PORT); // registry avviato automaticamente
            registry.rebind("Pubblicatore", obj);

            System.out.println("Server Pubblicatore: " + IpAddressServer.getServerAddress() + " Pronto...");
            System.out.println("Thread Produttori Notizie partiti");
            //per permettere una corretta lettura delle stampe sulla console si inserisce una sleep, in modo tale di eseguire le due stampe e poi schedulare i thread ProduttoreNotizia
            Thread.sleep(3000);
            creazioneNotizie();



            obj.exec();
        } catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }//END_Main

    private static void creazioneNotizie(){

        ProduttoreNotizie pn1 = new ProduttoreNotizie(); pn1.start();
        ProduttoreNotizie pn2 = new ProduttoreNotizie(); pn2.start();
        ProduttoreNotizie pn3 = new ProduttoreNotizie(); pn3.start();
        ProduttoreNotizie pn4 = new ProduttoreNotizie(); pn4.start();
        ProduttoreNotizie pn5 = new ProduttoreNotizie(); pn5.start();
    }

}//END_ServerMain_Class
