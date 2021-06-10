import java.io.Serializable;


/**
 * Oggetto condiviso Editoriale(tipo, contenuto) che deve essere Serializable
 * in quanto NON primitivo, per memorizzare dati in rete (interi e strighe)
 */
public class Editoriale implements Serializable {
    /**
     * serialVersionUID per la comunicazione seriale
     */
    private static final long serialVersionUID = 1L;

    /**
     * tipo dell'editoriale
     */
    private final EditorialeTipo tipo;

    /**
     * contenuto dell'editoriale
     */
    private String contenuto = "";

    /**
     * Costruttore
     *
     * @param tipo tipo dell'editoriale da istanziare
     */
    public Editoriale(EditorialeTipo tipo){
        this.tipo = tipo;
        //this.contenuto = contenuto;
    }

    /**
     * restituisce SerialVersionUID
     *
     * @return SerialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * setta il contenuto di una notizia
     *
     * @param contenuto contenuto
     */
    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    /**
     * restituisce il contenuto di un editoriale
     *
     * @return String il contenuto restituito
     */
    public String getContenuto() {
        return contenuto;
    }

    /**
     * restituisce il tipo di un editoriale
     *
     * @return String il tipo restituito
     */
    public EditorialeTipo getTipo() {
        return tipo;
    }

    /**
     * Metodo che concatena le notizie gia presenti nell'editoriale ad una nuova notizia inserita al suo interno
     *
     * @param contenuto la notizia da inserire
     */
    public void concatenaNotizia(String contenuto) {
        this.contenuto += contenuto;
    }

    /**
     * metodo di toString
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Editoriale: " + tipo.toString() + " \nContenuto: " + (contenuto.equals("")? "nessun contenuto" : contenuto);
    }
}
