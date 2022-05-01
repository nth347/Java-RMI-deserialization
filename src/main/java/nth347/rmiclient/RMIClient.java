package nth347.rmiclient;

import nth347.common.RMIInterface;

import java.rmi.Naming;
import java.rmi.registry.Registry;

import static java.rmi.registry.LocateRegistry.getRegistry;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        RMIInterface rmiInterface = (RMIInterface) Naming.lookup("rmi://127.0.0.1:1099/RMI");

        //Registry registry = getRegistry("rmi://127.0.0.1:1099");
        //RMIInterface rmiInterface = (RMIInterface) registry.lookup("RMI");

        String ret = rmiInterface.helloRMI("nth347");

        System.out.println(ret);
    }
}
