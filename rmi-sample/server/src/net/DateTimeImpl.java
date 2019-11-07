package net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeImpl extends UnicastRemoteObject implements DateTime {
    DateTimeImpl() throws RemoteException {
        System.out.println("DateTimeImpl created");
    }


    @Override
    public void close() {
        System.out.println("DateTimeImpl closed");
    }

    @Override
    public String getDate() throws RemoteException {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String getTime() throws RemoteException {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
    }

    @Override
    public boolean stop() throws RemoteException {
        return false; // TODO: stop rmi-server, remove itself from the registry
    }
}
