package net;

import java.io.Closeable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DateTime extends Remote, Closeable {

    String getDate() throws RemoteException;

    String getTime() throws RemoteException;

    boolean stop() throws RemoteException;
}
