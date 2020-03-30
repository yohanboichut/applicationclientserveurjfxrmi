package clientjfx.controleur;

import rmiservice.dto.ScoreDTO;
import javafx.stage.Stage;
import interfaces.exceptions.*;
import clientjfx.modele.ProxyAlexKiddBattleOnline;
import clientjfx.modele.ProxyAlexKiddBattleOnlineImpl;
import clientjfx.vues.*;

public class Controleur {

    private ProxyAlexKiddBattleOnline facadeAlexKiddBattle;
    private VueAccueil vueAccueil;
    private VueResultat vueResultat;
    private VueCombat vueCombat;
    private VueAttenteJoueur vueAttenteJoueur;
    private String choixAdversaire;


    public Controleur(Stage stage) {
        facadeAlexKiddBattle = new ProxyAlexKiddBattleOnlineImpl();
        vueAccueil = VueAccueil.creer(stage);
        vueAccueil.initialiserControleur(this);
        vueResultat = VueResultat.creer(stage);
        vueResultat.initialiserControleur(this);
        vueCombat = VueCombat.creer(stage);
        vueCombat.initialiserControleur(this);
        vueAttenteJoueur = VueAttenteJoueur.creer(stage);
        vueAttenteJoueur.initialiserControleur(this);

    }

    private String token;

    private String nomJoueur;

    public void run() {
        vueAccueil.show();
    }

    public String getToken() {
        return token;
    }

    public void connexion(String pseudoJoueur) {
        this.nomJoueur = pseudoJoueur;
        this.token = this.facadeAlexKiddBattle.creerPartie(pseudoJoueur);
        vueAttenteJoueur.chargerDonnees();
        vueAttenteJoueur.show();
    }

    public String getVainqueur() throws PartieNonTermineeException {
        return this.facadeAlexKiddBattle.getVainqueur(this.nomJoueur);
    }

    public void goToMenu() {
        this.finDePartie();
        this.vueAccueil.show();

    }

    public String getNomJoueur() {
        return this.nomJoueur;
    }

    public ScoreDTO getScore() {
        return this.facadeAlexKiddBattle.getScoreCourant(nomJoueur);
    }

    public void jouer(String choix) throws ChoixDejaFaitException {
        int idNumeroOperationCourante = this.getScore().getNumeroOperation();
        this.facadeAlexKiddBattle.choixJoueur(nomJoueur,choix);
        this.vueCombat.attendreChoixAdversaire(idNumeroOperationCourante);
    }

    public String getChoixAlexKidd() {
        return this.choixAdversaire;
    }

    public void rejoindrePartie(String joueur, String token) throws PartieDejaPleineException, TicketInvalideException, TicketPerimeException {
        this.nomJoueur = joueur;
        this.token = token;
        this.facadeAlexKiddBattle.rejoindreUnePartie(this.nomJoueur,this.token);
        this.goToPartie();
    }

    public boolean partieCommencee() {
        return this.facadeAlexKiddBattle.partieCommencee(this.nomJoueur);
    }

    public void goToPartie() {
        System.out.println("GOto partie pour "+this.nomJoueur);
        vueCombat.chargerDonnees();
        vueCombat.show();
    }

    public void majScore() {

        this.vueCombat.majApresAlexKidd();
        if (this.facadeAlexKiddBattle.partieTerminee(nomJoueur)) {
            this.vueResultat.chargerDonnees();
            this.vueResultat.show();
        }
    }



    public void finDePartie(){
        this.facadeAlexKiddBattle.finDePartie(this.nomJoueur);
    }
}
