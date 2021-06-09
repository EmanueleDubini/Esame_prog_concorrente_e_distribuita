package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgUtili {

    /**
     * Effettua la pulizia dello schermo a console.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Stampa la data odierna.
     * @return La data in formato gg-mm-aaaa.
     */
    public static String stampaData() {
        Date oggi = new Date(); // Data di oggi
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return sdf.format(oggi);
    }
}
