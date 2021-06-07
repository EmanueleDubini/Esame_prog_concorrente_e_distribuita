import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CurrencyConverterClient {
    public static void main(String[] args) throws Exception {

        double valoreDollari = 5;
        double valoreEuro = 5;
        String usd = "usd";
        String eur = "eur";

        System.setSecurityManager(new SecurityManager());

        String host = args.length >= 1 ? args[0] : null;
        Registry registry = LocateRegistry.getRegistry(host);
        CurrencyConverter stub = (CurrencyConverter) registry.lookup("CurrencyConverter");

        Conversione dollari = new Conversione(valoreDollari, usd);
        double ris = stub.converti(dollari);
        System.out.println("\n\n" + dollari.getValue() + " dollari " + " valgono " + ris + " EURO");

        Conversione euro = new Conversione(valoreEuro, eur);
        ris = stub.converti(euro);
        System.out.println(euro.getValue() + " euro " + " valgono " + ris + " DOLLARI\n\n");

    }
}