import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    /**
     * Metodo main dell'applicazzione Server, entry point da cui avviare
     *
     * @param args indirizzo ip del server
     */
    public static void main(String[] args) {

        // settiamo l'ip hostname con l'ip della macchina che esegue questo codice, ServerCV main()
        // questa impostazione viene applicata per poter eseguire i vari programmi concorrentemente su dispositivi diversi specificando all'avvio
        //(lato client) l'indirizzo del server a cui connettersi
        System.setProperty("java.rmi.server.hostname", IpAddressServer.getServerAddress());

        //metodi di servizio
        ProgUtili.clearScreen();
        ProgUtili.stampaPresentazione();

        //crea un oggetto del server che passera al registry mettendosi in ascolto su una determinata porta
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
