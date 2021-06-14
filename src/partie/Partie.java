package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Partie {
    static double version=0.0;
    private ElementsPartie elementsPartie; // Les Ã©lÃ©ments de la partie.

    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de crÃ©er et d'initialiser une nouvelle partie.
     */
    public Partie(){
        // Initialisation de la partie
        parametrerEtInitialiser();
        System.out.println(Arrays.deepToString(elementsPartie.getJoueurs())+"\n"+Arrays.deepToString(elementsPartie.getObjets())+"\n"+elementsPartie.getPlateau()+"\n"+elementsPartie.getPieceLibre()+"\n"+elementsPartie.getNombreJoueurs());
        // On affiche l'ensemble des Ã©lÃ©ments

        // A ComplÃ©ter

        IG.rendreVisibleFenetreJeu();
    }

    /**
     * MÃ©thode permettant de paramÃ¨trer et initialiser les Ã©lÃ©ments de la partie.
     */
    private void parametrerEtInitialiser(){
        // Saisie des diffÃ©rents paramÃ¨tres
        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();
        int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
        // CrÃ©ation des joueurs
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
        // CrÃ©ation des Ã©lÃ©ments de la partie
        elementsPartie=new ElementsPartie(joueurs);
    }


    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de lancer une partie.
     */
    public void lancer() {
        // Attributs utiles pour le jeu (joueurs, plateau, objet)

        int nombreJoueurs = elementsPartie.getNombreJoueurs();
        Joueur[] players = elementsPartie.getJoueurs();
        Objet[] tableauObjet = elementsPartie.getObjets();
        Plateau plateau = elementsPartie.getPlateau();
        boolean terminee = false;
        int compte_tour = 0;

        // Player 1
        int imagePlayer0 = elementsPartie.getJoueurs()[0].getNumeroImagePersonnage();
        String nomPlayer0 = elementsPartie.getJoueurs()[0].getNomJoueur();
        String categoriePlayer0 = elementsPartie.getJoueurs()[0].getCategorie();
        IG.changerNomJoueur(0, nomPlayer0+"("+elementsPartie.getJoueurs()[0].getCategorie()+")");
        IG.changerImageJoueur(0, imagePlayer0);
        IG.placerJoueurPrecis(0,0,0,0,2);

        // Player 2
        int imagePlayer1 = elementsPartie.getJoueurs()[1].getNumeroImagePersonnage();
        String nomPlayer1 = elementsPartie.getJoueurs()[1].getNomJoueur();
        String categoriePlayer1 = elementsPartie.getJoueurs()[1].getCategorie();
        IG.changerNomJoueur(1, nomPlayer1+"("+elementsPartie.getJoueurs()[1].getCategorie()+")");
        IG.changerImageJoueur(1, imagePlayer1);
        IG.placerJoueurPrecis(1,0,6,2,2);

        // Player 3
        if(players.length == 3 ){
            int imagePlayer2 = elementsPartie.getJoueurs()[2].getNumeroImagePersonnage();
            String nomPlayer2 = elementsPartie.getJoueurs()[2].getNomJoueur();
            String categoriePlayer2 = elementsPartie.getJoueurs()[2].getCategorie();
            IG.changerNomJoueur(2, nomPlayer2+"("+elementsPartie.getJoueurs()[2].getCategorie()+")");
            IG.changerImageJoueur(2, imagePlayer2);
            IG.placerJoueurPrecis(2,6,6,2,0);
        }
        IG.miseAJourAffichage();
        int choixEntree = 0;
        int[] choixCase;
        int[][] placerBille;
        // Tour de jeu
        while(!terminee){
            IG.changerJoueurSelectionne(compte_tour);
            choixEntree = IG.attendreChoixEntree();
            elementsPartie.insertionPieceLibre(choixEntree);
            IG.miseAJourAffichage();

            choixCase = players[compte_tour].choisirCaseArrivee(null);
            while((plateau.calculeChemin(players[compte_tour].getPosLigne(),players[compte_tour].getPosColonne(),choixCase[0],choixCase[1]) == null)){
                choixCase = players[compte_tour].choisirCaseArrivee(null);
            }
            int[][] placerBilles =  plateau.calculeChemin(players[compte_tour].getPosLigne(), players[compte_tour].getPosColonne(), choixCase[0],choixCase[1]);
            for(int i = 0; i < placerBilles.length;i++){
                IG.placerBilleSurPlateau(placerBilles[i][0],placerBilles[i][1],1,1,2);
            }
            IG.placerJoueurSurPlateau(compte_tour,choixCase[0],choixCase[1]);
            IG.miseAJourAffichage();
            for(Objet obj : tableauObjet){
                for(int x = 0; x < 7; x++){
                    for(int y = 0; y < 7; y++){
                        if(obj.getPosColonnePlateau() == players[compte_tour].getPosColonne() && obj.getPosLignePlateau()== players[compte_tour].getPosLigne() && obj == players[compte_tour].getProchainObjet()){
                            IG.enleverObjetPlateau(x,y);
                            IG.changerObjetJoueurAvecTransparence(compte_tour,obj.getNumeroObjet(),players[compte_tour].getNombreObjetsRecuperes());
                        }
                    }
                }
            }
            IG.miseAJourAffichage();
            if(players.length == 2 && players[compte_tour].getNombreObjetsRecuperes() == 9){
                terminee = true;
            }
            if(players.length == 3 && players[compte_tour].getNombreObjetsRecuperes() == 6){
                terminee = true;
            }

            compte_tour = (compte_tour + 1) % 3;
        }
    }

    /**
     *
     * Programme principal permettant de lancer le jeu.
     *
     * @param args Les arguments du programmes.
     */
    public static void main(String[] args) {
        while(true){
            Partie partie=new Partie();
            partie.lancer();
            break;
        }
    }
}