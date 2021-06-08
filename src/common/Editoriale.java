package common;

import server.EditorialeTipo;
import java.io.Serializable;

public class Editoriale implements Serializable {
    private static final long serialVersionUID = 1;
    private final String contenuto;
    private final EditorialeTipo tipo;

    public Editoriale(EditorialeTipo tipo, String contenuto){
        this.contenuto = contenuto;
        this.tipo = tipo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public EditorialeTipo getTipo() {
        return tipo;
    }

}
