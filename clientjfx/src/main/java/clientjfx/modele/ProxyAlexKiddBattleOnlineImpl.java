package clientjfx.modele;

import interfaces.dto.ScoreDTO;
import interfaces.service.ServiceAlexKiddBattleOnLine;
import interfaces.exceptions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ProxyAlexKiddBattleOnlineImpl implements ProxyAlexKiddBattleOnline {

    ServiceAlexKiddBattleOnLine serviceAlexKiddBattleOnLine;

    public ProxyAlexKiddBattleOnlineImpl() {
        System.out.println("Lancement du client");
        try {
             this.serviceAlexKiddBattleOnLine =(ServiceAlexKiddBattleOnLine) Naming.lookup(ServiceAlexKiddBattleOnLine.RMI_SERVEUR);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        System.out.println("Client initialisé");
    }

    @Override
    public String creerPartie(String joueur) {
        try {
            return this.serviceAlexKiddBattleOnLine.creerPartie(joueur);
        } catch (RemoteException e) {
           throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public void rejoindreUnePartie(String joueur, String ticket) throws TicketInvalideException, PartieDejaPleineException, TicketPerimeException {
        try {
            this.serviceAlexKiddBattleOnLine.rejoindreUnePartie(joueur,ticket);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public void choixJoueur(String joueur, String choix) throws ChoixDejaFaitException {
        try {
            this.serviceAlexKiddBattleOnLine.choixJoueur(joueur,choix);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public boolean partieTerminee(String joueur) {
        try {
            return this.serviceAlexKiddBattleOnLine.partieTerminee(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public String getVainqueur(String joueur) throws PartieNonTermineeException {
        try {
            return this.serviceAlexKiddBattleOnLine.getVainqueur(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public boolean partieCommencee(String joueur) {
        try {
            return this.serviceAlexKiddBattleOnLine.partieCommencee(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public ScoreDTO getScoreCourant(String joueur) {
        try {
            return this.serviceAlexKiddBattleOnLine.getScoreCourant(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }
    }

    @Override
    public void finDePartie(String nomJoueur) {
        try {
            this.serviceAlexKiddBattleOnLine.finDePartie(nomJoueur);
        } catch (RemoteException e) {
            throw new RuntimeException("RMI Problem !!");
        }

    }
}
