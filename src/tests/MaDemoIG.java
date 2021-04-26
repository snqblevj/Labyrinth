package tests;
import grafix.interfaceGraphique.IG;



public class MaDemoIG {

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
                IG.changerPiecePlateau(i, j, 2, 0);
            }
        }


        IG.placerJoueurPrecis(0, 3, 0, 1, 0);
        IG.placerJoueurPrecis(1, 3, 6,1,2);
        IG.changerPieceHorsPlateau(1, 0);
        IG.miseAJourAffichage();

        // Attribution des objets aux joueurs
        if (nombreJoueurs == 3) {
            for (int objet = 0; objet < 6; objet++) {
                IG.changerObjetJoueur(0, objet, objet);
                IG.changerObjetJoueur(1, objet + 6, objet);
                IG.changerObjetJoueur(2, objet + 12, objet);
                IG.miseAJourAffichage();
            }
        } else {
            for (int objet = 0; objet < 9; objet++) {
                IG.changerObjetJoueur(0, objet, objet);
                IG.changerObjetJoueur(1, objet + 9, objet);
                IG.miseAJourAffichage();
            }
        }


        //Affichage des objets sur le plateau
        int objetAPlacer = 0;
        for (int x = 0; x < 7; x++){
            for (int y = 0; y < 7; y++){
                if(objetAPlacer < 18) {
                    IG.placerObjetPlateau(objetAPlacer, x, y);
                    IG.miseAJourAffichage();
                    objetAPlacer++;
                }
            }
        }

        //Message de bienvenue
        String message[] = {
                "",
                "Bonjour ! ",
                "Cliquez pour continuer ... ",
                "",
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();



        //Faire une rotation des pièces du tableau et faire avancer le joueur

        int posJ1 = 0;
        int posJ2 = 2;
        int posColJ1 = 0;
        int posColJ2 = 6;
        int orientationPiece = 1;
        for(int i = 0; i < 4; i++){
            IG.attendreClic();
            if(posJ1 > 2){
                posJ1 = 0;
                posColJ1 += 1;
            }
            if(posJ2 < 0){
                posJ2 = 2;
                posColJ2 -= 1;
            }
            IG.placerJoueurPrecis(0,3,posColJ1,1,posJ1+1);
            IG.placerBilleSurPlateau(3,posColJ1,1,posJ1,0);
            IG.placerJoueurPrecis(1,3,posColJ2,1,posJ2-1);
            IG.placerBilleSurPlateau(3,posColJ2,1,posJ2,0);
            posJ1++;
            posJ2--;
            message[1] = "Après le clic " + (i+1);
            IG.afficherMessage(message);
            IG.enleverObjetPlateau(0,i);
            IG.changerObjetJoueurAvecTransparence(0,i,i);

            if(orientationPiece > 1){
                orientationPiece = 0;
            }
            IG.changerPieceHorsPlateau(1,orientationPiece);
            orientationPiece++;
            IG.miseAJourAffichage();
        }
        IG.attendreClic();
        IG.afficherGagnant(0);
        IG.miseAJourAffichage();
        message[1] = "Cliquez sur une flèche";
        message[2] = "pour quitter !";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreChoixEntree();
        message[1] = "Arrêt du programme";
        message[2] = "dans 2 secondes";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.pause(2000);
        IG.fermerFenetreJeu();


    }
}


