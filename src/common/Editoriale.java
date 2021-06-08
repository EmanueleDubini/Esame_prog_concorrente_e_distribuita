package common;

import server.EditorialeTipo;
import java.io.Serializable;

/**
 * Oggetto condiviso Editoriale(tipo, contenuto) che deve essere Serializable
 * in quanto NON primitivo, per memorizzare dati in rete (interi e strighe)
 */
public class Editoriale implements Serializable {
    private static final long serialVersionUID = 1L;

    private final EditorialeTipo tipo;
    private final String contenuto;
    private final String ClientName; // nome del Client che si collega, cosi sappiamo chi si collega

    public Editoriale(EditorialeTipo tipo, String contenuto, String clientName){
        this.tipo = tipo;
        this.contenuto = contenuto;
        ClientName = clientName;
    }

    public String getContenuto() {
        return contenuto;
    }

    public EditorialeTipo getTipo() {
        return tipo;
    }

    public String getClientName() { return ClientName; }
}
