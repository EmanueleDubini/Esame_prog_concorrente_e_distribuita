import java.io.Serializable;

public class Conversione implements Serializable {
    private static final long serialVersionUID = 1;
    private final double valore;
    private final String valuta;

    public Conversione(double valore, String valuta){
        this.valore = valore;
        this.valuta = valuta;
    }

    // mi dice il valore della somma data
    public double getValue(){
        return valore;
    }

    public String getValuta(){
        return valuta;
    }

    // PROREPARA

}
