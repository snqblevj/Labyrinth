package tests;

import composants.*;
import grafix.interfaceGraphique.PanneauJeu;
import joueurs.*;
import grafix.interfaceGraphique.IG;

public class TestJoueurs {

    public static void main(String[] args) {

        Object param[];
        param = IG.saisirParametres();
        int nombreJoueurs = ((Integer) param[0]).intValue();
        IG.creerFenetreJeu("Fenêtre de jeu du groupe SonicBoomer", nombreJoueurs);
        IG.rendreVisibleFenetreJeu();


        // Options du Joueur 1
        int imagePlayer0 = ((Integer) param[3]).intValue();
        String nomPlayer0 = (String) param[1]+" ( "+(String) param[2]+" )";
        String categoriePlayer0 = (String) param[2];
        IG.changerNomJoueur(0, nomPlayer0);
        IG.changerImageJoueur(0, imagePlayer0);


        // Options du Joueur 2
        int imagePlayer1 = ((Integer) param[6]).intValue();
        String nomPlayer1 = (String) param[4]+" ( "+(String) param[5]+" )";
        String categoriePlayer1 = (String) param[5];
        IG.changerNomJoueur(1, nomPlayer1);
        IG.changerImageJoueur(1, imagePlayer1);

        if (nombreJoueurs == 3) {
            //Options du Joueur 3
            int imagePlayer2 = ((Integer) param[9]).intValue();
            String nomPlayer2 = (String) param[7] +" ( "+(String) param[8]+" )";
            String categoriePlayer2 = (String) param[8];
            IG.changerNomJoueur(2, nomPlayer2);
            IG.changerImageJoueur(2, imagePlayer2);
        }

        IG.miseAJourAffichage();

        Plateau plateau=new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
        Joueur joueurs[]=Joueur.nouveauxJoueurs(param);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }

        for(int i = 0; i < nombreJoueurs; i++){
            IG.placerJoueurSurPlateau(joueurs[i].getNumJoueur(),joueurs[i].getPosLigne(),joueurs[i].getPosColonne());
        }

        IG.miseAJourAffichage();
        IG.attendreClic();

        for(int i = 0; i < nombreJoueurs; i++){
            joueurs[i].choisirCaseArrivee(null);
            while(plateau.calculeChemin(joueurs[i].getPosLigne(),joueurs[i].getPosColonne(),joueurs[i].choisirCaseArrivee(null)[0],joueurs[i].choisirCaseArrivee(null)[0]) == null){
                joueurs[i].choisirCaseArrivee(null);
            }
            IG.placerJoueurSurPlateau(joueurs[i].getNumJoueur(),joueurs[i].choisirCaseArrivee(null)[0],joueurs[i].choisirCaseArrivee(null)[1]);
            IG.miseAJourAffichage();
        }
    }

}
