/**
 * per ogni FruitoreNotizie che si registra, il server crea un oggetto IndoAbbonato e ci inserire il reference remoto del fruitore e gli editoriali a cui è iscritto
 */
public class InfoAbbonato {
    private final FruitoreNotizie fruitoreRemoto;
    private boolean politica = false;
    private boolean attualita = false;
    private boolean scienza = false;
    private boolean sport = false;

    /**
     * Costruttore
     */
    public InfoAbbonato(FruitoreNotizie f) {
        this.fruitoreRemoto = f;
    }

    public FruitoreNotizie getFruitoreRemoto() {
        return fruitoreRemoto;
    }

    public boolean isPolitica() {
        return politica;
    }

    public void setPolitica(boolean politica) {
        this.politica = politica;
    }

    public boolean isAttualita() {
        return attualita;
    }

    public void setAttualita(boolean attualita) {
        this.attualita = attualita;
    }

    public boolean isScienza() {
        return scienza;
    }

    public void setScienza(boolean scienza) {
        this.scienza = scienza;
    }

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }
}
