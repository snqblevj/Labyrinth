package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

/**
 *
 * Cette classe permet de représenter un ensemble d'élements composant une partie de jeu.
 *
 */
public class ElementsPartie {

    private Joueur[] joueurs;    // Les joueurs de la partie.
    private Objet[] objets;    // Les 18 objets de la partie dans l'ordre de leurs numéros.
    private Plateau plateau;    // Le plateau des pièces.
    private Piece pieceLibre;    // La pièce libre.
    private int nombreJoueurs;    // Le nombre de joueurs.

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Constructeur permettant de générer et d'initialiser l'ensemble des éléments d'une partie (sauf les joueurs qui sont donnés en paramètres).
     * <p>
     * Un plateau est créé en placant 49 oièces de manière aléatoire (utilisation de la méthode placerPiecesAleatoierment de la classe Plateau).
     * La pièce restante (celle non présente sur le plateau) est affectée �  la pièce libre.
     * Les 18 objets sont créés avec des positions aléatoires sur le plateau (utilisation de la méthode Objet.nouveauxObjets)
     * et distribuées aux différents joueurs (utilisation de la méthode attribuerObjetsAuxJoueurs).
     *
     * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribués (c'est au constructeur de le faire).
     */
    public ElementsPartie(Joueur[] joueurs) {

        this.joueurs = joueurs;
        this.plateau = new Plateau();
        this.pieceLibre = plateau.placerPiecesAleatoierment();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }

        Objet[] listObj = Objet.nouveauxObjets();
        for (Objet obj : listObj) {
            IG.placerObjetPlateau(obj.getNumeroObjet(), obj.getPosLignePlateau(), obj.getPosColonnePlateau());
        }
        attribuerObjetsAuxJoueurs();


    }

    /**
     * Un simple constructeur.
     *
     * @param joueurs    Les joueurs de la partie.
     * @param objets     Les 18 objets de la partie.
     * @param plateau    Le plateau de jeu.
     * @param pieceLibre La pièce libre (la pièce hors plateau).
     */
    public ElementsPartie(Joueur[] joueurs, Objet[] objets, Plateau plateau, Piece pieceLibre) {
        this.joueurs = joueurs;
        nombreJoueurs = joueurs.length;
        this.objets = objets;
        this.plateau = plateau;
        this.pieceLibre = pieceLibre;
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant d'attribuer les objets aux différents joueurs de manière aléatoire.
     */
    private void attribuerObjetsAuxJoueurs() {

        if (joueurs.length == 3) {
            for (int objet = 0; objet < 6; objet++) {
                IG.changerObjetJoueur(0, objet, objet);
                IG.changerObjetJoueur(1, objet + 6, objet);
                IG.changerObjetJoueur(2, objet + 12, objet);
                IG.miseAJourAffichage();
            }
        }
        else {
            for (int objet = 0; objet < 9; objet++) {
                IG.changerObjetJoueur(0, objet, objet);
                IG.changerObjetJoueur(1, objet + 9, objet);
                IG.miseAJourAffichage();
            }
        }
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant de récupérer les joueurs de la partie.
     *
     * @return Les joueurs de la partie.
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant de récupérer les pièces de la partie.
     *
     * @return Les objets de la partie.
     */
    public Objet[] getObjets() {
        return objets;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant de récupérer le plateau de pièces de la partie.
     *
     * @return Le plateau de pièces.
     */
    public Plateau getPlateau() {
        return plateau;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant de récupérer la pièce libre de la partie.
     *
     * @return La pièce libre.
     */
    public Piece getPieceLibre() {
        return pieceLibre;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode permettant de récupérer le nombre de joueurs de la partie.
     *
     * @return Le nombre de joueurs.
     */
    public int getNombreJoueurs() {
        return nombreJoueurs;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * Méthode modifiant les différents éléments de la partie suite �  l'insertion de la pièce libre dans le plateau.
     *
     * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
     */
    public void insertionPieceLibre(int choixEntree) {
        if (choixEntree >= 0 && choixEntree < 7) {
            Piece pieceTemp = plateau.getPiece(6, choixEntree).copy();
            for (int i = 6; i > 0; i--) {
                IG.changerPiecePlateau(i, choixEntree, plateau.getPiece(i - 1, choixEntree).getModelePiece(), plateau.getPiece(i - 1, choixEntree).getOrientationPiece());
            }
            IG.changerPiecePlateau(0, choixEntree, pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
            pieceLibre = pieceTemp;
            IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(),pieceLibre.getOrientationPiece());
        } else if (choixEntree >= 7 && choixEntree < 14) {
            Piece pieceTemp = plateau.getPiece(choixEntree % 7, 0).copy();
            for (int i = 6; i > 0; i--) {
                IG.changerPiecePlateau(choixEntree % 7, i, plateau.getPiece(choixEntree % 7, i - 1).getModelePiece(), plateau.getPiece(choixEntree % 7, i - 1).getOrientationPiece());
            }
            IG.changerPiecePlateau(choixEntree % 7, 6, pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
            pieceLibre = pieceTemp;
            IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(),pieceLibre.getOrientationPiece());
        } else if (choixEntree >= 14 && choixEntree < 21) {
            Piece pieceTemp = plateau.getPiece(6, choixEntree % 14).copy();
            for (int i = 0; i < 6; i++) {
                IG.changerPiecePlateau(i, 6 - (choixEntree % 14), plateau.getPiece(i + 1, 6 - (choixEntree % 14)).getModelePiece(), plateau.getPiece(i + 1, 6 - (choixEntree % 14)).getOrientationPiece());
            }
            IG.changerPiecePlateau(6, 6 - (choixEntree % 14), pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
            pieceLibre = pieceTemp;
            IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(),pieceLibre.getOrientationPiece());
        } else if (choixEntree >= 21 && choixEntree < 28) {
            Piece pieceTemp = plateau.getPiece(choixEntree % 21, 6).copy();
            for (int i = 6; i > 0; i--) {
                IG.changerPiecePlateau(6 - (choixEntree % 21), i, plateau.getPiece(6 - (choixEntree % 21), i - 1).getModelePiece(), plateau.getPiece(6 - (choixEntree % 21), i - 1).getOrientationPiece());
            }
            IG.changerPiecePlateau(6 - (choixEntree % 21), 0, pieceLibre.getModelePiece(), pieceLibre.getOrientationPiece());
            pieceLibre = pieceTemp;
            IG.changerPieceHorsPlateau(pieceLibre.getModelePiece(),pieceLibre.getOrientationPiece());
        }

    }

    /**
         * Méthode retournant une copie.
         *
         * @return Une copie des éléments.
         */
    public ElementsPartie copy(){
        Objet[] nouveauxObjets = new Objet[(this.objets).length];
        for (int i = 0; i < objets.length; i++)
            nouveauxObjets[i] = (this.objets[i]).copy();
        Joueur[] nouveauxJoueurs = new Joueur[nombreJoueurs];
        for (int i = 0; i < nombreJoueurs; i++)
            nouveauxJoueurs[i] = (this.joueurs[i]).copy(objets);
        Plateau nouveauPlateau = (this.plateau).copy();
        Piece nouvellePieceLibre = (this.pieceLibre).copy();
        ElementsPartie nouveauxElements = new ElementsPartie(nouveauxJoueurs, nouveauxObjets, nouveauPlateau, nouvellePieceLibre);
        return nouveauxElements;
    }
}

