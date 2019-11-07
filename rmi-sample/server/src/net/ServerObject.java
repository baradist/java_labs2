package net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerObject extends UnicastRemoteObject implements RmiInterface {
    protected ServerObject() throws RemoteException {
        System.out.println("ServerObject created");
    }

    @Override
    public int sum(int x, int y) throws RemoteException {
        return x + y;
    }

    @Override
    public void close() throws Exception {
        System.out.println("ServerObject closed");
    }
}
