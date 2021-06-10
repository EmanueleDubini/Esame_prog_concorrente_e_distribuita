/**
 * per ogni FruitoreNotizie che si registra, il server crea un oggetto IndoAbbonato e ci inserire il reference
 * remoto del fruitore e gli editoriali a cui è iscritto
 */
public class InfoAbbonato {
    /**
     * riferimento al client
     */
    private final FruitoreNotizie fruitoreRemoto;

    /**
     * settato a true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     */
    private boolean politica = false;

    /**
     * settato a true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     */
    private boolean attualita = false;

    /**
     * settato a true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     */
    private boolean scienza = false;

    /**
     * settato a true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     */
    private boolean sport = false;

    /**
     * Costruttore
     */
    public InfoAbbonato(FruitoreNotizie f) {
        this.fruitoreRemoto = f;
    }


    /**
     * restituisce il riferimento al FruitoreRemotot
     *
     * @return String il FruitoreRemoto
     */
    public FruitoreNotizie getFruitoreRemoto() {
        return fruitoreRemoto;
    }

    /**
     * restiuisce true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @return true se abbonato
     */
    public boolean isPolitica() {
        return politica;
    }

    /**
     * setta se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @param politica true se si abbona
     */
    public void setPolitica(boolean politica) {
        this.politica = politica;
    }

    /**
     * restiuisce true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @return true se abbonato
     */
    public boolean isAttualita() {
        return attualita;
    }

    /**
     * setta se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @param attualita true se si abbona
     */
    public void setAttualita(boolean attualita) {
        this.attualita = attualita;
    }

    /**
     * restiuisce true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @return true se abbonato
     */
    public boolean isScienza() {
        return scienza;
    }

    /**
     * setta se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @param scienza true se si abbona
     */
    public void setScienza(boolean scienza) {
        this.scienza = scienza;
    }

    /**
     * restiuisce true se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @return true se abbonato
     */
    public boolean isSport() {
        return sport;
    }

    /**
     * setta se il FruitoreNotizie rappresentato dall'istanza InfoAbbonato e sottoscritto a questo tipo di editoriale
     *
     * @param sport true se si abbona
     */
    public void setSport(boolean sport) {
        this.sport = sport;
    }
}
