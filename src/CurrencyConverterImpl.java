import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverterImpl extends UnicastRemoteObject implements CurrencyConverter{

    public static final double USD = 0.895415473;
    public static final double EUR = 1.114365;

    // COSTRUTTORE
    public CurrencyConverterImpl() throws RemoteException {
        super();
    }

    // IMPLEMENTAZIONE METODI
    @Override
    public double converti(Conversione c) throws RemoteException {
        if(c.getValuta().equals("usd")){
            return c.getValue() * EUR;
        }
        else {
            if(c.getValuta().equals("eur"))
            return c.getValue() * USD;
        }
        return 0;
    }

    // MAIN
    public static void main(String[] args) throws Exception {
        // Gestione Security manager
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }

        CurrencyConverterImpl obj = new CurrencyConverterImpl(); // oggetto locale
        Registry registry = LocateRegistry.createRegistry(1099); // registry avviato automaticamente
        registry.rebind("CurrencyConverter", obj);
        System.out.println("Server Ready");

    }//END_Main
}//END_CurrencyConverterImpl_Class
