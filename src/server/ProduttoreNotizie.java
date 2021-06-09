package server;

import common.Editoriale;

import java.util.ArrayList;
import java.util.Random;

/**
 * ProduttoreNotizie e in grado di produrre notizie da aggiungere ai vari editoriali
 */
public class ProduttoreNotizie extends Thread{

    Random random = new Random(); // generatore di numero casuale da associare a ogni notizia per renderla diversa

    public ProduttoreNotizie() {
        super();
    }

    @Override
    public void run() {
        //System.out.println("ProduttoreNotizie " + currentThread().getName() + " running...");

        while (true){
            EditorialeTipo tipo = EditorialeTipo.getEditorialeTipoCasuale();
            String notizia = " Notizia di " + tipo + " " + random.nextInt(10000);
            System.out.println("DEBUG notizie prodotta: " + notizia);
            PubblicatoreImpl.registraNotizia(tipo, notizia);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("ProduttoreNotizie exception: " + e.toString());
                e.printStackTrace();
                //todo se avanza tempo usare Timer
            }
        }
    }
}
