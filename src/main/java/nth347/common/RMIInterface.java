package nth347.common;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    String helloRMI(String from) throws RemoteException;

    int primitiveMethod(int i) throws RemoteException;

    Object attackClient() throws RemoteException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void attackServer(Object object) throws RemoteException;
}
