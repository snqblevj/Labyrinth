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

        IG.miseAJourAffichage();

        Plateau plateau=new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
        Joueur joueurs[] = Joueur.nouveauxJoueurs(param);

        int imagePlayer0 = ((Integer) param[3]).intValue();
        String nomPlayer0 = (String) param[1];
        String categoriePlayer0 = (String) param[2];
        IG.changerNomJoueur(0, nomPlayer0+"("+param[2]+")");
        IG.changerImageJoueur(0, imagePlayer0);
        IG.placerJoueurPrecis(0,0,0,0,2);

        int imagePlayer1 = ((Integer) param[6]).intValue();
        String nomPlayer1 = (String) param[4];
        String categoriePlayer1 = (String) param[5];
        IG.changerNomJoueur(1, nomPlayer1+"("+param[5]+")");
        IG.changerImageJoueur(1, imagePlayer1);
        IG.placerJoueurPrecis(1,0,6,2,2);

        if (nombreJoueurs == 3) {
            //Options du Joueur 3
            int imagePlayer2 = ((Integer) param[9]).intValue();
            String nomPlayer2 = (String) param[7];
            String categoriePlayer2 = (String) param[8];
            IG.changerNomJoueur(2, nomPlayer2+"("+param[8]+")");
            IG.changerImageJoueur(2, imagePlayer2);
            IG.placerJoueurPrecis(2,6,6,2,0);
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }

        String message[] = {
                "",
                "Bonjour ! ",
                "Cliquez pour commencer...",
                "",
        };
        IG.afficherMessage(message);
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();
        IG.attendreClic();

        message[1] = "Au tour de"+""+joueurs[0].getNomJoueur();
        message[2] = "Selectionner une case";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();

        //Joueur 1
        if(joueurs[0].getCategorie().equals("OrdiType0") || joueurs[0].getCategorie().equals("OrdiType1") || joueurs[0].getCategorie().equals("OrdiType2") || joueurs[0].getCategorie().equals("OrdiType3")){
            int[] choixCase = {joueurs[0].getPosLigne(),joueurs[0].getPosColonne()};
            int[][] placerBilles =  plateau.calculeChemin(joueurs[0].getPosLigne(), joueurs[0].getPosColonne(), choixCase[0],choixCase[1]);
            for(int i = 0; i < placerBilles.length;i++){
                IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
            }
        }
        else {
            int[] choixCase = joueurs[0].choisirCaseArrivee(null);
            while((plateau.calculeChemin(joueurs[0].getPosLigne(),joueurs[0].getPosColonne(),choixCase[0],choixCase[1]) == null)){
                choixCase = joueurs[0].choisirCaseArrivee(null);
            }

            int[][] placerBilles =  plateau.calculeChemin(joueurs[0].getPosLigne(), joueurs[0].getPosColonne(), choixCase[0],choixCase[1]);
            for(int i = 0; i < placerBilles.length;i++){
                IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
            }
            IG.placerJoueurSurPlateau(0,choixCase[0],choixCase[1]);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();

        message[1] = "Au tour de"+""+joueurs[1].getNomJoueur();
        message[2] = "Selectionner une case";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();

        //Joueur 2
        if(joueurs[1].getCategorie().equals("OrdiType0") || joueurs[1].getCategorie().equals("OrdiType1") || joueurs[1].getCategorie().equals("OrdiType2") || joueurs[1].getCategorie().equals("OrdiType3")){
            int[] choixCase = {joueurs[1].getPosLigne(),joueurs[1].getPosColonne()};
            int[][] placerBilles =  plateau.calculeChemin(joueurs[0].getPosLigne(), joueurs[1].getPosColonne(), choixCase[0],choixCase[1]);
            for(int i = 0; i < placerBilles.length;i++){
                IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
            }
        }
        else {
            int[] choixCase = joueurs[1].choisirCaseArrivee(null);
            while((plateau.calculeChemin(joueurs[1].getPosLigne(),joueurs[1].getPosColonne(),choixCase[0],choixCase[1]) == null)){
                choixCase = joueurs[1].choisirCaseArrivee(null);
            }

            int[][] placerBilles =  plateau.calculeChemin(joueurs[1].getPosLigne(), joueurs[1].getPosColonne(), choixCase[0],choixCase[1]);
            for(int i = 0; i < placerBilles.length;i++){
                IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
            }
            IG.placerJoueurSurPlateau(1,choixCase[0],choixCase[1]);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();
        if(nombreJoueurs == 3){
            message[1] = "Au tour de"+""+joueurs[2].getNomJoueur();
            message[2] = "Selectionner une case";
            IG.afficherMessage(message);
            IG.miseAJourAffichage();
            //Joueur 3
            if(joueurs[2].getCategorie().equals("OrdiType0") || joueurs[2].getCategorie().equals("OrdiType1") || joueurs[2].getCategorie().equals("OrdiType2") || joueurs[2].getCategorie().equals("OrdiType3")){
                int[] choixCase = {joueurs[2].getPosLigne(),joueurs[2].getPosColonne()};
                int[][] placerBilles =  plateau.calculeChemin(joueurs[2].getPosLigne(), joueurs[2].getPosColonne(), choixCase[0],choixCase[1]);
                for(int i = 0; i < placerBilles.length;i++){
                    IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
                }
            }
            else {
                int[] choixCase = joueurs[2].choisirCaseArrivee(null);
                while((plateau.calculeChemin(joueurs[2].getPosLigne(),joueurs[2].getPosColonne(),choixCase[0],choixCase[1]) == null)){
                    choixCase = joueurs[2].choisirCaseArrivee(null);
                }

                int[][] placerBilles =  plateau.calculeChemin(joueurs[2].getPosLigne(), joueurs[2].getPosColonne(), choixCase[0],choixCase[1]);
                for(int i = 0; i < placerBilles.length;i++){
                    IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
                }
                IG.placerJoueurSurPlateau(2,choixCase[0],choixCase[1]);
                IG.miseAJourAffichage();

            }

            message[1] = "C'est terminé ! ";
            message[2] = "Cliquez pour quitter";
            IG.afficherMessage(message);
            IG.miseAJourAffichage();
            IG.attendreClic();
            IG.fermerFenetreJeu();

        }


    }

}
