package tests;
import grafix.interfaceGraphique.IG;


public class DemoIG19 {

    public static void main(String[] args) {
        // Une petite démonstration conernant l'interface graphique


        // Saisie des différents paramÃ¨tres
        Object parametres[];
        parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramètrage pour la saisie

        // Création de la fenêtre de jeu et affichage de la fenêtre
        int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
        IG.creerFenetreJeu("Démo Librairie IG version 1.9",nbJoueurs); // On crée la fenêtre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

        IG.jouerUnSon(2); // On joue le son numéro 2
        IG.pause(300); // On attend 300 ms
        IG.jouerUnSon(2); // On joue de nouveau le son numéro 2

        // Affichage d'un message
        String message[]={
                "",
                "Démo Librairie Graphique 1.9 ...	",
                "Cliquer pour continuer ...",
                ""
        };
        IG.afficherMessage(message); // On change de message de la fenêtre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
        IG.attendreClic();  // On attend un clic de souris

        // Changement d'une pièce sur le plateau
        IG.changerPiecePlateau(3,4,2,1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Changement des caractéristiques du premier joueur avec les paramÃ¨tres saisis
        int numImageJoueur0=((Integer)parametres[3]).intValue();
        String nomJoueur0=(String)parametres[1];
        String categorieJoueur0=(String)parametres[2];
        IG.attendreClic();
        IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
        IG.changerImageJoueur(0,numImageJoueur0);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Changement d'objets au premier joueur et second joueur

        for (int i=0;i<4;i++){
            IG.changerObjetJoueur(0,i,i);
            IG.changerObjetJoueur(1,i+7,i);
        }
        for (int i=4;i<6;i++){
            IG.changerObjetJoueurAvecTransparence(0,i,i);
            IG.changerObjetJoueurAvecTransparence(1,i+7,i);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place des objets sur le plateau
        int numObjet=0;
        for (int i=0;i<7;i++)
            for (int j=0;j<7;j++)
                IG.placerObjetPlateau((numObjet++)%18,i,j);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Mettre en sélection l'objet 2
        IG.changerObjetSelectionne(2);

        // Place les joueurs sur le plateau

        for (int i=0;i<nbJoueurs;i++)
            IG.placerJoueurSurPlateau(i,1+i,1+i);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place tous les joueurs sur la même pièce

        for (int i=0;i<nbJoueurs;i++)
            IG.placerJoueurSurPlateau(i,1,1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place des billes sur les lignes 2 et 3
        for (int i=0;i<7;i++)
            for (int j=0;j<3;j++){
                IG.placerBilleSurPlateau(2,i,1,j,j);
                IG.placerBilleSurPlateau(3,i,1,j,j+1);
            }
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Sélectionne le joueur 1
        IG.changerJoueurSelectionne(1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Sélectionne la flèche 3
        IG.selectionnerFleche(3);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Sélectionne la pièce sur le plateau à  la ligne 2 colonne 3.
        IG.selectionnerPiecePlateau(2,3);
        IG.miseAJourAffichage();
        IG.attendreClic();



        // Change la pièce hors du plateau
        IG.changerPieceHorsPlateau(2,2);
        IG.miseAJourAffichage();
        IG.attendreClic();
        message[0]="";
        message[1]="Rotationner la pièce qui se ";
        message[2]="trouve hors du plateau ... ";
        message[3]="Puis sélectionner une flèche ...";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        // Attente d'un clic sur une entrée
        int entree=IG.attendreChoixEntree();

        System.out.println("Entrée : "+entree);
        System.out.println("Modele : "+IG.recupererModelePieceHorsPlateau());
        System.out.println("Orientation : "+IG.recupererOrientationPieceHorsPlateau());

        // Attente d'un clic sur une piÃ¨ce
        message[0]="";
        message[1]="Sélectionner une pièce sur ";
        message[2]="le plateau ... ";
        message[3]="";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        int choixPiece[]=IG.attendreChoixPiece();
        System.out.println("Pièce sélectionnée : ("+choixPiece[0]+","+choixPiece[1]+")");


        message[0]="";
        message[1]="C'est terminé !";
        message[2]="Cliquer pour quitter ...";
        message[3]="";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
        System.exit(0);


    }

}