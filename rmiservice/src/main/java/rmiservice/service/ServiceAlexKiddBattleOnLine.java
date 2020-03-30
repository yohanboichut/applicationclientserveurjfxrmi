package rmiservice.service;


import rmiservice.dto.ScoreDTO;
import interfaces.exceptions.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceAlexKiddBattleOnLine extends Remote {


    String creerPartie(String joueur) throws RemoteException;

    void rejoindreUnePartie(String joueur, String ticket) throws RemoteException, TicketInvalideException, PartieDejaPleineException, TicketPerimeException;

    void choixJoueur(String joueur, String choix) throws RemoteException, ChoixDejaFaitException;

    boolean partieTerminee(String joueur) throws RemoteException;

    String getVainqueur(String joueur) throws RemoteException, PartieNonTermineeException;

    boolean partieCommencee(String joueur) throws RemoteException;
    ScoreDTO getScoreCourant(String joueur) throws RemoteException;

    void finDePartie(String nomJoueur) throws RemoteException;
}
