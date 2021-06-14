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

        this.objets = Objet.nouveauxObjets();
        for (Objet obj : objets) {
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
     * GB 02/06/21
     * <p>
     * Méthode modifiant les différents éléments de la partie suite �  l'insertion de la pièce libre dans le plateau.
     *
     * @param choixEntree L'entrée choisie pour réaliser l'insertion (un nombre entre 0 et 27).
     */
    public void insertionPieceLibre(int choixEntree){
        // Fleche du haut selectionne
        if (choixEntree<7){
            Piece save = plateau.getPiece(6, choixEntree);
            for (int i=6; i>=1; i--){
                plateau.positionnePiece(plateau.getPiece(i-1, choixEntree), i, choixEntree);
            }
            plateau.positionnePiece(pieceLibre, 0, choixEntree);
            pieceLibre= save;
            // modification de la position des joueurs
            for (int n =0; n<nombreJoueurs;n++){
                if (joueurs[n].getPosColonne()==choixEntree && joueurs[n].getPosLigne()==6){
                    joueurs[n].setPosition(0, joueurs[n].getPosColonne());
                }else if (joueurs[n].getPosColonne()==choixEntree){
                    joueurs[n].setPosition(joueurs[n].getPosLigne()+1, choixEntree);
                }
            }
            // modification de la position des Objets
            for (int numObjet =0; numObjet<objets.length;numObjet++){
                if (objets[numObjet].getPosColonnePlateau()==choixEntree && objets[numObjet].getPosLignePlateau()==6){
                    objets[numObjet].positionneObjet(0, objets[numObjet].getPosColonnePlateau());
                }else if (objets[numObjet].getPosColonnePlateau()==choixEntree){
                    objets[numObjet].positionneObjet(objets[numObjet].getPosLignePlateau()+1, choixEntree);
                }
            }
            // Fleche de droite selectionne
        }else if(choixEntree<14){
            Piece save = plateau.getPiece(choixEntree-7, 0);
            for (int i=0; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(choixEntree-7, i+1), choixEntree-7,i);
            }
            plateau.positionnePiece(pieceLibre, choixEntree-7, 6);
            pieceLibre= save;
            // modification de la position des joueurs
            for (int n =0; n<nombreJoueurs;n++){
                if (joueurs[n].getPosLigne()==choixEntree-7 && joueurs[n].getPosColonne()==0){
                    joueurs[n].setPosition(joueurs[n].getPosLigne(), 6);
                }else if (joueurs[n].getPosLigne()==choixEntree-7){
                    joueurs[n].setPosition(choixEntree-7, joueurs[n].getPosColonne()-1);
                }
            }
            // modification de la position des Objets
            for (int numObjet =0; numObjet<objets.length;numObjet++){

                if (objets[numObjet].getPosLignePlateau()==choixEntree-7 && objets[numObjet].getPosColonnePlateau()==0){
                    objets[numObjet].positionneObjet(objets[numObjet].getPosLignePlateau(), 6);
                }else if (objets[numObjet].getPosLignePlateau()==choixEntree-7){
                    objets[numObjet].positionneObjet(choixEntree-7, objets[numObjet].getPosColonnePlateau()-1);
                }

            }
            // Fleche du bas selectionne
        }else if (choixEntree<21){

            Piece save = plateau.getPiece(0, 20-choixEntree);
            for (int i=0; i<6; i++){
                plateau.positionnePiece(plateau.getPiece(i+1, 20-choixEntree), i, 20-choixEntree);
            }
            plateau.positionnePiece(pieceLibre, 6, 20-choixEntree);
            pieceLibre= save;
            // modification de la position des joueurs
            for (int n =0; n<nombreJoueurs;n++){
                if (joueurs[n].getPosColonne()==20-choixEntree && joueurs[n].getPosLigne()==0){
                    joueurs[n].setPosition(6, 20-choixEntree);
                }else if (joueurs[n].getPosColonne()==20-choixEntree){
                    joueurs[n].setPosition(joueurs[n].getPosLigne()-1, 20-choixEntree);
                }
            }
            // modification de la position des Objets
            for (int numObjet =0; numObjet<objets.length;numObjet++){
                if (objets[numObjet].getPosColonnePlateau()==20-choixEntree && objets[numObjet].getPosLignePlateau()==0){
                    objets[numObjet].positionneObjet(6, 20-choixEntree);
                }else if (objets[numObjet].getPosColonnePlateau()==20-choixEntree){
                    objets[numObjet].positionneObjet(objets[numObjet].getPosLignePlateau()-1, 20-choixEntree);
                }
            }
            // Fleche de gauche selectionne
        }else{
            Piece save = plateau.getPiece(27-choixEntree, 6);
            for (int i=6; i>=1; i--){
                plateau.positionnePiece(plateau.getPiece(27-choixEntree, i-1), 27-choixEntree,i );
            }
            plateau.positionnePiece(pieceLibre, 27-choixEntree, 0);
            pieceLibre= save;
            // modification de la position des joueurs
            for (int n =0; n<nombreJoueurs;n++){
                if (joueurs[n].getPosLigne()==27-choixEntree && joueurs[n].getPosColonne()==6){
                    joueurs[n].setPosition(27-choixEntree, 0);
                }else if (joueurs[n].getPosLigne()==27-choixEntree){
                    joueurs[n].setPosition(27-choixEntree, joueurs[n].getPosColonne()+1);
                }
            }
            // modification de la position des Objets
            for (int numObjet = 0; numObjet<objets.length;numObjet++){
                if (objets[numObjet].getPosLignePlateau()==27-choixEntree && objets[numObjet].getPosColonnePlateau()==6){
                    objets[numObjet].positionneObjet(objets[numObjet].getPosLignePlateau(), 0);
                }else if (objets[numObjet].getPosLignePlateau()==27-choixEntree){
                    objets[numObjet].positionneObjet(27-choixEntree, objets[numObjet].getPosColonnePlateau()+1);
                }
            }
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

    public Objet objetIci(int ligne, int colonne) {
        for (int i = 0; i < 18; i++) {
            if (this.objets[i].getPosLignePlateau() == ligne && this.objets[i].getPosColonnePlateau() == colonne) {
                if (this.objets[i].surPlateau()) {
                    return this.objets[i];
                }
            }
        }
        return null;
    }
}

