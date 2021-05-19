package tests;

import composants.Objet;
import grafix.interfaceGraphique.IG;

public class TestObjet {

    public static void main(String[] args) {
        Object param[];
        param = IG.saisirParametres();
        int nombreJoueurs = ((Integer) param[0]).intValue();
        IG.creerFenetreJeu("Fenêtre de jeu du groupe SonicBoomer", nombreJoueurs);
        IG.rendreVisibleFenetreJeu();


        // Options du Joueur 1
        int imagePlayer0 = ((Integer) param[3]).intValue();
        String nomPlayer0 = (String) param[1];
        String categoriePlayer0 = (String) param[2];
        IG.changerNomJoueur(0, nomPlayer0);
        IG.changerImageJoueur(0, imagePlayer0);


        // Options du Joueur 2
        int imagePlayer1 = ((Integer) param[6]).intValue();
        String nomPlayer1 = (String) param[4];
        String categoriePlayer1 = (String) param[5];
        IG.changerNomJoueur(1, nomPlayer1);
        IG.changerImageJoueur(1, imagePlayer1);

        if (nombreJoueurs == 3) {
            //Options du Joueur 3
            int imagePlayer2 = ((Integer) param[9]).intValue();
            String nomPlayer2 = (String) param[7];
            String categoriePlayer2 = (String) param[8];
            IG.changerNomJoueur(2, nomPlayer2);
            IG.changerImageJoueur(2, imagePlayer2);
        }
        IG.miseAJourAffichage();

        Objet[] listObj = Objet.nouveauxObjets();
        for(Objet obj : listObj){
            IG.placerObjetPlateau(obj.getNumeroObjet(),obj.getPosLignePlateau(),obj.getPosColonnePlateau());
        }
        IG.miseAJourAffichage();


    }

}
