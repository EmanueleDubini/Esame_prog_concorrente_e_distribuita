package common;

import java.io.Serializable;


/**
 * Oggetto condiviso Editoriale(tipo, contenuto) che deve essere Serializable
 * in quanto NON primitivo, per memorizzare dati in rete (interi e strighe)
 */
public class Editoriale implements Serializable {
    private static final long serialVersionUID = 1L;

    private final EditorialeTipo tipo;
    private String contenuto = "";

    public Editoriale(EditorialeTipo tipo){
        this.tipo = tipo;
        //this.contenuto = contenuto;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getContenuto() {
        return contenuto;
    }

    public EditorialeTipo getTipo() {
        return tipo;
    }

    public void concatenaNotizia(String contenuto) {
        this.contenuto += contenuto;
    }

    @Override
    public String toString() {
        return "Editoriale: " + tipo.toString() + " \nContenuto: " + (contenuto.equals("")? "nessun contenuto" : contenuto);
    }
}
