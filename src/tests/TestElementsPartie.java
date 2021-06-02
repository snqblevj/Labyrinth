package tests;

import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import partie.ElementsPartie;

public class TestElementsPartie {

    public static void main(String[] args) {

        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();
        int  nbJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);
        IG.rendreVisibleFenetreJeu();
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
        ElementsPartie elementsPartie=new ElementsPartie(joueurs);


        // Options du Joueur 1
        int imagePlayer0 = ((Integer) parametresJeu[3]).intValue();
        String nomPlayer0 = (String) parametresJeu[1]+" ( "+(String) parametresJeu[2]+" )";
        String categoriePlayer0 = (String) parametresJeu[2];
        IG.changerNomJoueur(0, nomPlayer0);
        IG.changerImageJoueur(0, imagePlayer0);


        // Options du Joueur 2
        int imagePlayer1 = ((Integer) parametresJeu[6]).intValue();
        String nomPlayer1 = (String) parametresJeu[4]+" ( "+(String) parametresJeu[5]+" )";
        String categoriePlayer1 = (String) parametresJeu[5];
        IG.changerNomJoueur(1, nomPlayer1);
        IG.changerImageJoueur(1, imagePlayer1);

        if (nbJoueurs == 3){
            //Options du Joueur 3
            int imagePlayer2 = ((Integer) parametresJeu[9]).intValue();
            String nomPlayer2 = (String) parametresJeu[7] +" ( "+(String) parametresJeu[8]+" )";
            String categoriePlayer2 = (String) parametresJeu[8];
            IG.changerNomJoueur(2, nomPlayer2);
            IG.changerImageJoueur(2, imagePlayer2);
        }

        String message[] = {
                "",
                "",
                "Cliquez pour commencer ... ",
                "",
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        message[2] = "Choisissez une entrée";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();

        for(int i = 0; i < 4; i++){
            int x = IG.attendreChoixEntree();
            elementsPartie.insertionPieceLibre(x);
            IG.miseAJourAffichage();
        }

        message[2] = "C'est terminé ! ";
        message[3] = "Cliquez pour quitter";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();


        IG.attendreClic();
        IG.fermerFenetreJeu();









    }

}
