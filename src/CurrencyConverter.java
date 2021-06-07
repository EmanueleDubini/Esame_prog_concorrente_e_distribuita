import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrencyConverter extends Remote {

    double converti(Conversione value) throws RemoteException;
}
