import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgUtili {

    /**
     * Creazione colori usati per stampare le informazioni sul terminale
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Effettua la pulizia dello schermo a console.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    /**
     * metodo di stampa di una presentazione
     */
    public static void stampaPresentazione(){
        System.out.println("********************************************************");
        System.out.println("*                                                      *");
        System.out.println("* PROGRAMMAZIONE CONCORRENTE E DISTRUBUITA AA. 2020/21 *");
        System.out.println("*                                                      *");
        System.out.println("*  CASALNOVO Giacomo    | 740003 | Como                *");
        System.out.println("*  DUBINI Emanuele      | 740954 | Como                *");
        System.out.println("*                                                      *");
        System.out.println("*  (C) UnInsubria.it                                   *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************\n");
    }
}
