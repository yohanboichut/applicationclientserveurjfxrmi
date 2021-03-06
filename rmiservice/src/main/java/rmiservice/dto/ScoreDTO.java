package rmiservice.dto;

import interfaces.type.Score;

import java.io.Serializable;

public class ScoreDTO implements Serializable {

    private int numeroOperation;
    private int scoreJoueur1;
    private int scoreJoueur2;
    private String joueur1;
    private String joueur2;
    private String choixJoueur1;
    private String choixJoueur2;


    private ScoreDTO(int numeroOperation, int scoreJoueur1, int scoreJoueur2, String joueur1, String joueur2, String choixJoueur1, String choixJoueur2) {
        this.numeroOperation = numeroOperation;
        this.scoreJoueur1 = scoreJoueur1;
        this.scoreJoueur2 = scoreJoueur2;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.choixJoueur1 = choixJoueur1;
        this.choixJoueur2 = choixJoueur2;
    }


    public static ScoreDTO creer(Score score) {
        return new ScoreDTO(score.getNumeroOperation(),score.getScoreJoueur1(),score.getScoreJoueur2(),score.getJoueur1(),score.getJoueur2(),score.getChoixJoueur1(),score.getChoixJoueur2());
    }

    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    public String getJoueur1() {
        return joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public String getChoixJoueur1() {
        return choixJoueur1;
    }

    public String getChoixJoueur2() {
        return choixJoueur2;
    }

    public int getNumeroOperation() {
        return numeroOperation;
    }
}
