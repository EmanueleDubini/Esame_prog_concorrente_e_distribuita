import java.util.Random;

/**
 * ProduttoreNotizie e in grado di produrre notizie da aggiungere ai vari editoriali
 */
public class ProduttoreNotizie extends Thread{

    Random random = new Random(); // generatore di numero casuale da associare a ogni notizia per renderla diversa

    /**
     * Costruttore di default del produttore di notizie
     * */
    public ProduttoreNotizie() {
        super();
    }

    /**
     * Thread che produce le notizie da aggiungere ai vari editoriali
     */
    @Override
    public void run() {
        while (true){
            EditorialeTipo tipo = EditorialeTipo.getEditorialeTipoCasuale(); // genera un editoriale casuale
            String notizia = " Notizia di " + tipo + " " + random.nextInt(10000);
            System.out.println("DEBUG notizie prodotta: " + notizia);
            PubblicatoreImpl.registraNotizia(tipo, notizia);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("ProduttoreNotizie exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }
}
