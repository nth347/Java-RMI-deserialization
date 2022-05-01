package nth347.rmiserver;

import nth347.common.RMIInterface;
import nth347.common.RMIInterfaceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "0.0.0.0");
        System.setProperty("java.rmi.server.logCalls", "true");

        RMIInterface rmiInterface = new RMIInterfaceImpl();
        LocateRegistry.createRegistry(1099);

        // rmi://0.0.0.0:1099/RMI
        Naming.bind("RMI", rmiInterface);

        System.out.println("[i] RMI Server has started with JDK " + System.getProperty("java.version"));
    }
}
