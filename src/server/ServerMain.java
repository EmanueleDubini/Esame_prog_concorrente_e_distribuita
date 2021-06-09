package server;

import common.IpAddressServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    // MAIN
    public static void main(String[] args) {

        presentazione();
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

    private static void presentazione() {
        System.out.println("********************************************************\n" +
                "*\n" +
                "* PROGRAMMAZIONE CONCORRENTE E DISTRUBUITA AA. 2020/21 *\n" +
                "*\n" +
                "* CASALNOVO Giacomo | 740003 | Como\n" +
                "* DUBINI Emanuele | 740954 | Como\n" +
                "*\n" +
                "* (C) UnInsubria.it\n" +
                "*\n" +
                "*******************************************************");
    }

    private static void creazioneNotizie(){

        ProduttoreNotizie pn1 = new ProduttoreNotizie(); pn1.start();
        ProduttoreNotizie pn2 = new ProduttoreNotizie(); pn2.start();
        ProduttoreNotizie pn3 = new ProduttoreNotizie(); pn3.start();
        ProduttoreNotizie pn4 = new ProduttoreNotizie(); pn4.start();
        ProduttoreNotizie pn5 = new ProduttoreNotizie(); pn5.start();
    }

}//END_ServerMain_Class
