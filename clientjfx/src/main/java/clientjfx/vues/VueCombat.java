package clientjfx.vues;

import clientjfx.controleur.Controleur;
import interfaces.exceptions.ChoixDejaFaitException;
import interfaces.type.Score;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import rmiservice.dto.ScoreDTO;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static interfaces.facade.FacadeAlexKiddBattleOnLine.PIERRE;
import static interfaces.facade.FacadeAlexKiddBattleOnLine.CISEAUX;
import static interfaces.facade.FacadeAlexKiddBattleOnLine.FEUILLE;

public class VueCombat implements Vue {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;


    @FXML
    Label pseudo;

    @FXML
    Label nomJoueur;


    @FXML
    RadioButton radioPierre;


    @FXML
    RadioButton radioCiseaux;

    @FXML
    RadioButton radioFeuille;


    @FXML
    Label scorePseudo;

    @FXML
    Label scoreAlexKidd;



    @FXML
    ImageView rien;

    @FXML
    ImageView pierre;

    @FXML
    ImageView ciseaux;

    @FXML
    ImageView feuille;

    @FXML
    VBox vBoxImages;

    @FXML
    Button jouer;

    @FXML
    Label nomAdversaire;


    @FXML
    Label pseudoAdversaire;

    private ImageView imageCourante;
    private ToggleGroup toggleGroup;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private void setScene(Scene scene) {
        this.scene = scene;
    }

    public static VueCombat creer(Stage stage) {
        URL location = VueCombat.class.getResource("combat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueCombat vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserRadioBoutons();
        vue.initialiserImages();
        return vue;
    }

    private void initialiserRadioBoutons() {
        this.toggleGroup = new ToggleGroup();
        this.radioCiseaux.setToggleGroup(toggleGroup);
        this.radioFeuille.setToggleGroup(toggleGroup);
        this.radioPierre.setToggleGroup(toggleGroup);
    }



    private void initialiserImages() {
        this.imageCourante = rien;
        this.vBoxImages.getChildren().removeAll(pierre,ciseaux,feuille);
    }


    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }





    @Override
    public void chargerDonnees() {
        String nom = controleur.getNomJoueur();

        this.nomJoueur.setText(nom);
        this.pseudo.setText(nom);
        this.initialiserScore();
    }




    private void initialiserScore() {
        ScoreDTO score = this.controleur.getScore();
        this.scorePseudo.setText(score.getJoueur1().equals(this.controleur.getNomJoueur())?Integer.toString(score.getScoreJoueur1()):Integer.toString(score.getScoreJoueur2()));
        this.scoreAlexKidd.setText(score.getJoueur1().equals(this.controleur.getNomJoueur())?Integer.toString(score.getScoreJoueur2()):Integer.toString(score.getScoreJoueur1()));
        this.pseudoAdversaire.setText(score.getJoueur1().equals(this.controleur.getNomJoueur())?score.getJoueur2():score.getJoueur1());
        this.nomAdversaire.setText(score.getJoueur1().equals(this.controleur.getNomJoueur())?score.getJoueur2():score.getJoueur1());

    }






    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void jouer(ActionEvent actionEvent) {

        RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (Objects.isNull(radioButton)) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Vous devez choisir entre Pierre/Ciseaux/Feuille !!!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            String choix = radioButton.getText();
            try {
                this.controleur.jouer(choix);
            } catch (ChoixDejaFaitException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez choisir entre Pierre/Ciseaux/Feuille !!!", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    public void majApresAlexKidd() {
        ScoreDTO scoreDTO = this.controleur.getScore();
        String choixAlexKidd = scoreDTO.getJoueur1().equals(this.controleur.getNomJoueur())?scoreDTO.getChoixJoueur2():scoreDTO.getChoixJoueur1();
        this.initialiserScore();

        if (PIERRE.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = pierre;
            this.vBoxImages.getChildren().add(pierre);
        }
        if (CISEAUX.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = ciseaux;
            this.vBoxImages.getChildren().add(ciseaux);
        }

        if (FEUILLE.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = feuille;
            this.vBoxImages.getChildren().add(feuille);
        }

        this.jouer.setDisable(false);
        this.show();


    }

    /**
     * Désactiver le bouton de validation du choix
     * Mettre à jour l'image avec Alex Kidd only
     * Définition de la tâche de fond qui tourne tant que
     * l'identifiant d'opération n'évolue pas
     * Appel du contrôleur majscore qui permettra la mise à jour de la vue à la fin de l'attente
     *
     * @param idNumeroOperationCourante
     */


    public void attendreChoixAdversaire(int idNumeroOperationCourante) {




    }

















    /*
    public void attendreChoixAdversaire(int idNumeroOperationCourante) {

        this.jouer.setDisable(true);
        this.vBoxImages.getChildren().remove(imageCourante);
        this.imageCourante = rien;
        this.vBoxImages.getChildren().add(rien);
        this.show();
        Task<Boolean> attendre = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ScoreDTO scoreDTO = null;
                do {
                     scoreDTO = controleur.getScore();
                }
                while (scoreDTO.getNumeroOperation() == idNumeroOperationCourante);
                return true;
            }
        };
        attendre.addEventFilter(WorkerStateEvent.WORKER_STATE_SUCCEEDED,e ->{controleur.majScore();});
        Thread thread = new Thread(attendre);
        thread.start();
    }
     */

}
