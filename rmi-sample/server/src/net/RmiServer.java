package net;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Callable;

import static net.Constatns.JNDI_OBJECT_NAME;
import static net.Constatns.SERVER_PORT;

public class RmiServer {
    public static void main(String[] args) {
        try (DateTimeImpl stub = new DateTimeImpl()) {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.bind(JNDI_OBJECT_NAME, stub);
            stub.addStopCallback(stopCallback(registry));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static Callable<Void> stopCallback(Registry registry) {
        return () -> {
            registry.unbind(JNDI_OBJECT_NAME);
            return null;
        };
    }
}
