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
        // initialisation des attributs (a la place de faire elementsPartie. ... pour certain)
        int nbJoueurs = elementsPartie.getNombreJoueurs();
        Joueur[] joueurs = elementsPartie.getJoueurs();
        Objet[] tabObjet = elementsPartie.getObjets();
        Plateau plateau = elementsPartie.getPlateau();
        boolean gagnant = false;
        int[][] resultat = null;
        int[][] resultatPrecis = null;
        // boucle de la partie (une tours par manche)
        while (gagnant == false) {
            //boucle de tours des joueurs de la partie
            for (int numJoueurs = 0; numJoueurs < nbJoueurs; numJoueurs++) {
                // deplacement pour les joueurs type Humain
                if (joueurs[numJoueurs].getCategorie() != "OrdiType3" && joueurs[numJoueurs].getCategorie() != "OrdiType2" && joueurs[numJoueurs].getCategorie() != "OrdiType1" && joueurs[numJoueurs].getCategorie() != "OrdiType0") {
                    String messageJoueur[] = { // Le joueur qui doit jouer
                            "",
                            "Au tour de " + joueurs[numJoueurs].getNomJoueur(),
                            "",
                            "Cliquez pour continuer ...",
                            ""
                    };
                    IG.afficherMessage(messageJoueur);
                    IG.changerJoueurSelectionne(numJoueurs);
                    IG.changerObjetSelectionne(tabObjet[joueurs[numJoueurs].getObjetsJoueur()[joueurs[numJoueurs].getNombreObjetsRecuperes()].getNumeroObjet()].getNumeroObjet());
                    IG.miseAJourAffichage();
                    IG.attendreClic();

                    // choix de la l'orientation de la piece hors plateau et de la flèche
                    String messagePieceHorsPlateau[] = {
                            "",
                            "Choisissez une orientation",
                            "de la piece libre",
                            "Ensuite, selectionnez une flèche",
                            ""
                    };
                    IG.afficherMessage(messagePieceHorsPlateau);
                    IG.miseAJourAffichage();
                    int choix = 0;
                    choix = IG.attendreChoixEntree();
                    elementsPartie.getPieceLibre().setOrientation(IG.recupererOrientationPieceHorsPlateau());
                    elementsPartie.insertionPieceLibre(choix);
                    // modification du plateau en fonction du resultat de l'insertion de la piece
                    // objets
                    for (int i = 0; i < tabObjet.length; i++) {
                        for (int ligne = 0; ligne < 7; ligne++) {
                            for (int colonne = 0; colonne < 7; colonne++) {
                                if (tabObjet[i].getPosLignePlateau() != ligne && tabObjet[i].getPosColonnePlateau() != colonne) {
                                    IG.enleverObjetPlateau(ligne, colonne);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < tabObjet.length; i++) {
                        if (tabObjet[i].surPlateau()) {
                            IG.placerObjetPlateau(tabObjet[i].getNumeroObjet(), tabObjet[i].getPosLignePlateau(), tabObjet[i].getPosColonnePlateau());
                        }
                    }
                    IG.miseAJourAffichage();
                    // pieces
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {
                            IG.changerPiecePlateau(i, j, elementsPartie.getPlateau().getPiece(i, j).getModelePiece(), elementsPartie.getPlateau().getPiece(i, j).getOrientationPiece());
                        }
                    }
                    // joueurs
                    IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
                    for (int i = 0; i < nbJoueurs; i++) {
                        IG.placerJoueurSurPlateau(i, joueurs[i].getPosLigne(), joueurs[i].getPosColonne());

                    }
                    IG.miseAJourAffichage();

                    // selection de la case d'arriver du joueur
                    String messageDeplacement[] = {
                            "",
                            "Selectionnez une case d'arrivé",
                            ""
                    };
                    IG.afficherMessage(messageDeplacement);
                    IG.miseAJourAffichage();
                    int[] casArr = new int[2];
                    casArr = joueurs[numJoueurs].choisirCaseArrivee(null);
                    IG.deselectionnerFleche();
                    resultat = plateau.calculeChemin(joueurs[numJoueurs].getPosLigne(), joueurs[numJoueurs].getPosColonne(), casArr[0], casArr[1]);

                    // selection tant qu'il n'y a pas de chemin vers la case selctionnée
                    while (resultat == null) {
                        casArr = joueurs[numJoueurs].choisirCaseArrivee(null);
                        resultat = plateau.calculeChemin(joueurs[numJoueurs].getPosLigne(), joueurs[numJoueurs].getPosColonne(), casArr[0], casArr[1]);
                    }
                    resultatPrecis = plateau.calculeCheminDetaille(resultat, numJoueurs);
                    IG.placerJoueurSurPlateau(joueurs[numJoueurs].getNumJoueur(), resultat[resultat.length - 1][0], resultat[resultat.length - 1][1]);
                    for (int n = 0; n < resultatPrecis.length; n++) {
                        IG.placerBilleSurPlateau(resultatPrecis[n][0], resultatPrecis[n][1], resultatPrecis[n][2], resultatPrecis[n][3], numJoueurs);
                    }
                    joueurs[numJoueurs].setPosition(resultat[resultat.length - 1][0], resultat[resultat.length - 1][1]);
                    IG.deselectionnerPiecePlateau();

                }// Tours si joueur ordinateur
                else if (joueurs[numJoueurs].getCategorie() == "OrdiType3" ||
                        joueurs[numJoueurs].getCategorie() == "OrdiType2" ||
                        joueurs[numJoueurs].getCategorie() == "OrdiType1" ||
                        joueurs[numJoueurs].getCategorie() == "OrdiType0") {

                    String messageJoueur[] = { // Le joueur qui doit jouer
                            "",
                            "Au tour de " + joueurs[numJoueurs].getNomJoueur(),
                            ""
                    };
                    IG.afficherMessage(messageJoueur);
                    IG.miseAJourAffichage();
                    IG.changerJoueurSelectionne(numJoueurs);
                    IG.changerObjetSelectionne(tabObjet[joueurs[numJoueurs].getObjetsJoueur()[joueurs[numJoueurs].getNombreObjetsRecuperes()].getNumeroObjet()].getNumeroObjet());

                    int choix = joueurs[numJoueurs].choisirOrientationEntree(elementsPartie)[1];
                    elementsPartie.getPieceLibre().setOrientation(joueurs[numJoueurs].choisirOrientationEntree(elementsPartie)[0]);
                    System.out.println(choix + "  ");
                    elementsPartie.insertionPieceLibre(choix);
                    // indication de l'endroit de l'insertion pour les autres joueurs
                    String messageInsertionOrdi[] = {
                            "",
                            "Isertion faite sur la flèche " + choix,
                            ""
                    };
                    IG.afficherMessage(messageInsertionOrdi);
                    IG.miseAJourAffichage();
                    // modification du plateau en fonction le la flèche "choisie"
                    // objets
                    for (int i = 0; i < tabObjet.length; i++) {
                        for (int ligne = 0; ligne < 7; ligne++) {
                            for (int colonne = 0; colonne < 7; colonne++) {
                                if (tabObjet[i].getPosLignePlateau() != ligne && tabObjet[i].getPosColonnePlateau() != colonne) {
                                    IG.enleverObjetPlateau(ligne, colonne);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < tabObjet.length; i++) {
                        if (tabObjet[i].surPlateau()) {
                            IG.placerObjetPlateau(tabObjet[i].getNumeroObjet(), tabObjet[i].getPosLignePlateau(), tabObjet[i].getPosColonnePlateau());
                        }
                    }
                    IG.miseAJourAffichage();
                    // pieces
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {
                            IG.changerPiecePlateau(i, j, elementsPartie.getPlateau().getPiece(i, j).getModelePiece(), elementsPartie.getPlateau().getPiece(i, j).getOrientationPiece());
                        }
                    }
                    // joueurs
                    IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), elementsPartie.getPieceLibre().getOrientationPiece());
                    for (int i = 0; i < nbJoueurs; i++) {
                        IG.placerJoueurSurPlateau(i, joueurs[i].getPosLigne(), joueurs[i].getPosColonne());

                    }
                    IG.miseAJourAffichage();

                    // deplacement du joueur Ordinateur
                    // Si il peut aller vers un objet il fait le deplacement
                    int[] casArr = joueurs[numJoueurs].choisirCaseArrivee(elementsPartie);
                    resultat = plateau.calculeChemin(joueurs[numJoueurs].getPosLigne(), joueurs[numJoueurs].getPosColonne(), casArr[0], casArr[1]);
                    resultatPrecis = plateau.calculeCheminDetaille(resultat, numJoueurs);
                    IG.placerJoueurSurPlateau(joueurs[numJoueurs].getNumJoueur(), resultat[resultat.length - 1][0], resultat[resultat.length - 1][1]);
                    for (int n = 0; n < resultatPrecis.length; n++) {
                        IG.placerBilleSurPlateau(resultatPrecis[n][0], resultatPrecis[n][1], resultatPrecis[n][2], resultatPrecis[n][3], numJoueurs);
                    }
                    joueurs[numJoueurs].setPosition(resultat[resultat.length - 1][0], resultat[resultat.length - 1][1]);
                }
                IG.miseAJourAffichage();
                // supprimer toutes les billes du plateau
                if (resultatPrecis != null) {
                    for (int x = 0; x < resultatPrecis.length; x++) {
                        IG.supprimerBilleSurPlateau(resultatPrecis[x][0], resultatPrecis[x][1], resultatPrecis[x][2], resultatPrecis[x][3]);
                        IG.miseAJourAffichage();
                    }
                }

                // enleve m'objet du plateau si il est recuperé
                if (joueurs[numJoueurs].getPosLigne() == joueurs[numJoueurs].getProchainObjet().getPosLignePlateau() && joueurs[numJoueurs].getPosColonne() == joueurs[numJoueurs].getProchainObjet().getPosColonnePlateau()) {
                    joueurs[numJoueurs].getProchainObjet().enleveDuPlateau();
                    IG.enleverObjetPlateau(joueurs[numJoueurs].getPosLigne(), joueurs[numJoueurs].getPosColonne());
                    IG.changerObjetJoueurAvecTransparence(numJoueurs, joueurs[numJoueurs].getProchainObjet().getNumeroObjet(), joueurs[numJoueurs].getNombreObjetsRecuperes());
                    joueurs[numJoueurs].recupererObjet();
                }
                // met fin a la boucle while si un joueur autres que le dernier (2e ou 3e en fonction du nombre de joueurs)
                if (joueurs[numJoueurs].getNombreObjetsRecuperes() == joueurs[numJoueurs].getObjetsJoueur().length) {
                    gagnant = true;
                    if (nbJoueurs == 3) {
                        if (numJoueurs == 0 || numJoueurs == 1) {
                            break;
                        }
                    } else {
                        if (numJoueurs == 0) {
                            break;
                        }
                    }
                }
            }
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
        }
    }
}