package net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote, AutoCloseable {
    int sum(int x, int y) throws RemoteException;
}
