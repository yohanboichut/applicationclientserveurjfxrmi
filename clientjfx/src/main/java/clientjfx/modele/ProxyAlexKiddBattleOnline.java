package clientjfx.modele;

import interfaces.dto.ScoreDTO;
import interfaces.exceptions.*;

public interface ProxyAlexKiddBattleOnline {
    String creerPartie(String joueur);

    void rejoindreUnePartie(String joueur, String ticket) throws TicketInvalideException, PartieDejaPleineException, TicketPerimeException;

    void choixJoueur(String joueur, String choix) throws ChoixDejaFaitException;

    boolean partieTerminee(String joueur);

    String getVainqueur(String joueur) throws PartieNonTermineeException;

    boolean partieCommencee(String joueur);

    ScoreDTO getScoreCourant(String joueur);

    void finDePartie(String nomJoueur);
}
