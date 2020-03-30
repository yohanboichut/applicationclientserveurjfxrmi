package clientjfx.vues;

import clientjfx.controleur.Controleur;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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

public class VueAttenteJoueur implements Vue {


    private Stage stage;
    private Controleur controleur;


    @FXML
    TextField ticket;

    private void setScene(Scene scene) {
        this.scene = scene;
    }

    private Scene scene;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public static VueAttenteJoueur creer(Stage stage) {
        URL location = VueAttenteJoueur.class.getResource("attentejoueur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueAttenteJoueur vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }


    /**
     * Ajouter la phase de synchronisation qui attend que la partie commence.
     * Au retour appel de goToPartie
     */
    @Override
    public void chargerDonnees() {
        this.ticket.setText(this.controleur.getToken());
        this.ticket.setDisable(false);
        Task<Boolean> attenteJoueur = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommencee());
                return true;
            }
        };
        attenteJoueur.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,e -> controleur.goToPartie());
        Thread thread = new Thread(attenteJoueur);
        thread.start();

    }


    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }



}
