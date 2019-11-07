package net;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static net.Constatns.JNDI_OBJECT_NAME;
import static net.Constatns.SERVER_PORT;

public class RmiServer {
    public static void main(String[] args) {
        try (RmiInterface stub = new ServerObject()) {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.bind(JNDI_OBJECT_NAME, stub);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
