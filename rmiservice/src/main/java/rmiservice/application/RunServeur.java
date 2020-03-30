package rmiservice.application;

import rmiservice.service.ServiceAlexKiddBattleOnLine;
import rmiservice.service.ServiceAlexKiddBattleOnLineImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RunServeur {
    public static final String RMI_SERVEUR = "rmi://localhost/alexkiddonline";

    public static void main(String[] args) {
        try {
            // Définition du port pour la communication RMI
            LocateRegistry.createRegistry(1099);


            // Création de l'instance du service qui va être embarqué dans le serveur RMI
            ServiceAlexKiddBattleOnLine serviceAlexKiddBattleOnLine = ServiceAlexKiddBattleOnLineImpl.creer();

            // Association de l'adresse à au service
            Naming.rebind(RMI_SERVEUR, serviceAlexKiddBattleOnLine);

            System.out.println("Serveur lancé");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
