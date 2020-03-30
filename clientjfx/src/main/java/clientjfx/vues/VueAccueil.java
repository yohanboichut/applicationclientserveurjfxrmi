package clientjfx.vues;

import clientjfx.controleur.Controleur;
import interfaces.exceptions.PartieDejaPleineException;
import interfaces.exceptions.TicketInvalideException;
import interfaces.exceptions.TicketPerimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class VueAccueil implements Vue {


    @FXML
    TextField pseudo;

    @FXML
    Button go;

    private Stage stage;
    private Controleur controleur;


    private void setScene(Scene scene) {
        this.scene = scene;
    }

    private Scene scene;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public static VueAccueil creer(Stage stage) {
        URL location = VueAccueil.class.getResource("accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueAccueil vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    private void initialiserBouton() {
        this.go.setOnAction(e -> connexion());
    }

    private void connexion() {
        String pseudoJoueur = this.pseudo.getText();
        if (Objects.isNull(pseudoJoueur) || pseudoJoueur.length()<2) {
            this.showError("Vous devez renseigner un pseudo d'au moins 2 caractères !");
        }
        else {
            controleur.connexion(pseudoJoueur);
        }
    }

    @Override
    public void chargerDonnees() {

    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,message,ButtonType.OK);
        alert.setTitle("Connexion");
        alert.showAndWait();
    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void rejoindrePartie(ActionEvent actionEvent) {
        TextInputDialog token = new TextInputDialog();
        token.setTitle("Ticket d'invitation");
        token.setHeaderText("Entrez votre ticket");
        String pseudo = this.pseudo.getText();

        Optional<String> resultat = token.showAndWait();
        if (resultat.isPresent() && !(Objects.isNull(pseudo)) && pseudo.length()>2) {
            try {
                this.controleur.rejoindrePartie(pseudo,resultat.get());
            } catch (PartieDejaPleineException e) {
                this.showError("Partie déjà en cours !!");
            } catch (TicketInvalideException e) {
                this.showError("Ticket non valide !");
            } catch (TicketPerimeException e) {
                this.showError("Ticket périmé !");
            }
        }
        else {
            this.showError("Vous devez saisir un pseudo et un ticket !");
        }
    }
}
