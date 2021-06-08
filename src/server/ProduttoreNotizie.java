package server;

import java.util.Random;

/**
 * ProduttoreNotizie e in grado di produrre notizie da aggiungere ai vari editoriali
 */
public class ProduttoreNotizie extends Thread{

    Random random = new Random(); // generatore di numero casuale da associare a ogni notizia per renderla diversa

    public ProduttoreNotizie() {
    }

    @Override
    public void run() {
        EditorialeTipo tipo = EditorialeTipo.getEditorialeTipoCasuale();
        String notizia = "Notizia di " + tipo + " " + random.nextInt(10000);

        // todo
        // scrittura delle notize nell'arraylist
    }
}
