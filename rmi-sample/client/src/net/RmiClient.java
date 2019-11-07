package net;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static net.Constatns.JNDI_OBJECT_NAME;
import static net.Constatns.SERVER_PORT;

public class RmiClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_PORT);
            DateTime stub = (DateTime) registry.lookup(JNDI_OBJECT_NAME);

            System.out.println("Now is: " + stub.getDate() + ", " + stub.getTime());

        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
