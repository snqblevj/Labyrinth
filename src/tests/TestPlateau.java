package tests;

import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;

import java.util.Arrays;

public class TestPlateau {

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

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, 0, 0);
            }
        }

        String message[] = {
                "",
                "Cliquez pour continuer ... ",
                "",
                "",
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();

        IG.attendreClic();

        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau = plateau.placerPiecesAleatoierment();
        IG.attendreClic();


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }
        IG.miseAJourAffichage();
        IG.attendreClic();

        int length = 0;
        int i_max = 0;
        int j_max = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(plateau.calculeChemin(3,3,i,j) != null) {
                    System.out.println("Cases entre  (3,3) et " + i + " " + j + " : " + Arrays.deepToString(plateau.calculeChemin(3, 3, i, j)));
                    if(plateau.calculeChemin(3, 3, i, j).length > length){
                        i_max = i;
                        j_max = j;
                        length = plateau.calculeChemin(3, 3, i, j).length;
                    }
                }
            }
        }

        int[][] placerBilles =  plateau.calculeChemin(3, 3, i_max, j_max);
        for(int i = 0; i < placerBilles.length;i++){
            IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
        }

        IG.miseAJourAffichage();



    }
}


