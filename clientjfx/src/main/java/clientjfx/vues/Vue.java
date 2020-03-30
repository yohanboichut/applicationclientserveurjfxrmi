package clientjfx.vues;

import clientjfx.controleur.Controleur;

public interface Vue {
    void show();

    void chargerDonnees();

    void initialiserControleur(Controleur controleur);
}
