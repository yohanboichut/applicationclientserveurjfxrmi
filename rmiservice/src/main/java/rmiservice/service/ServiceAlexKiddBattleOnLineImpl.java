package rmiservice.service;

import interfaces.service.ServiceAlexKiddBattleOnLine;
import interfaces.dto.ScoreDTO;
import interfaces.facade.FacadeAlexKiddBattleOnLine;
import modele.FacadeAlexKiddBattleOnLineImpl;
import interfaces.exceptions.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceAlexKiddBattleOnLineImpl extends UnicastRemoteObject implements ServiceAlexKiddBattleOnLine {
    FacadeAlexKiddBattleOnLine facadeAlexKiddBattleOnLine;

    protected ServiceAlexKiddBattleOnLineImpl() throws RemoteException {
        super();
        this.facadeAlexKiddBattleOnLine = new FacadeAlexKiddBattleOnLineImpl();
    }

    public static ServiceAlexKiddBattleOnLine creer() throws RemoteException {
        return new ServiceAlexKiddBattleOnLineImpl();
    }

    @Override
    public String creerPartie(String joueur) throws RemoteException {
        return this.facadeAlexKiddBattleOnLine.creerPartie(joueur);
    }

    @Override
    public void rejoindreUnePartie(String joueur, String ticket) throws RemoteException, TicketInvalideException, PartieDejaPleineException, TicketPerimeException {
        this.facadeAlexKiddBattleOnLine.rejoindreUnePartie(joueur,ticket);
    }

    @Override
    public void choixJoueur(String joueur, String choix) throws RemoteException, ChoixDejaFaitException {
        this.facadeAlexKiddBattleOnLine.choixJoueur(joueur,choix);
    }

    @Override
    public boolean partieTerminee(String joueur) throws RemoteException {
        return this.facadeAlexKiddBattleOnLine.partieTerminee(joueur);
    }

    @Override
    public String getVainqueur(String joueur) throws RemoteException, PartieNonTermineeException {
        return this.facadeAlexKiddBattleOnLine.getVainqueur(joueur);
    }

    @Override
    public boolean partieCommencee(String joueur) throws RemoteException {
        return this.facadeAlexKiddBattleOnLine.partieCommencee(joueur);
    }

    @Override
    public ScoreDTO getScoreCourant(String joueur) throws RemoteException {

        return ScoreDTO.creer(this.facadeAlexKiddBattleOnLine.getScoreCourant(joueur));
    }

    @Override
    public void finDePartie(String nomJoueur) throws RemoteException {
        this.facadeAlexKiddBattleOnLine.finDePartie(nomJoueur);
    }
}
