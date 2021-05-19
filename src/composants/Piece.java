package composants;

import org.w3c.dom.ls.LSOutput;
import java.util.Random;

/**
 * A Faire (08/05/2021 TL Finalisée)
 * Cette classe permet de représenter les différentes pièces du jeu.
 *
 */
abstract public class Piece{

    private int modelePiece; 		// Le modèle de la pièce
    private int orientationPiece; 	// L'orientation de la pièce
    private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Constructeur permettant de créer une pièce d'un modèle avec l'orientation 0.
     * @param modelePiece Le modèle de la pièce.
     * @param pointEntreeHaut Un booléen indiquant si la pièce a un point d'entrée en haut.
     * @param pointEntreeDroite Un booléen indiquant si la pièce a un point d'entrée Ã  droite.
     * @param pointEntreeBas Un booléen indiquant si la pièce a un point d'entrée en bas.
     * @param pointEntreeGauche Un booléen indiquant si la pièce a un point d'entrée Ã  gauche.
     */
    public Piece(int modelePiece,boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche){
        this.modelePiece = modelePiece;
        this.orientationPiece = 0;
        this.pointsEntree = new boolean[4];
        this.pointsEntree[0] = pointEntreeHaut;
        this.pointsEntree[1] = pointEntreeDroite;
        this.pointsEntree[2] = pointEntreeBas;
        this.pointsEntree[3] = pointEntreeGauche;
    }

    /**
     * Méthode retournant un String représentant la pièce.
     */
    @Override
    public String toString() {
        return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode permettant de rotationner une pièce dans le sens d'une horloge.
     */
    public void rotation(){
        boolean tmp = pointsEntree[0], tmp2;
        for(int i = 0; i < pointsEntree.length; i++){
            if(i == 0){
                pointsEntree[i] = pointsEntree[pointsEntree.length - 1];
            }
            else{
                tmp2 = pointsEntree[i];
                pointsEntree[i] = tmp;
                tmp = tmp2;
            }
        }
        orientationPiece++;
        if(getModelePiece()==1){
            orientationPiece = orientationPiece % 2;
        }
        else{
            if(orientationPiece>3){
                orientationPiece = 0;
            }
        }
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode permettant d'orienter une pièce vers une orientation spécifique.
     * @param orientationPiece Un entier correspondant à la nouvelle orientation de la pièce.
     */
    public void setOrientation(int orientationPiece){
        if(orientationPiece >= 0 && orientationPiece <= 3){
            while (this.orientationPiece != orientationPiece){
                rotation();
            }
        }
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode retournant le modèle de la pièce.
     * @return Un entier corrspondant au modèle de la pièce.
     */
    public int getModelePiece() {
        return modelePiece;
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode retournant l'orientation de la pièce.
     * @return un entier retournant l'orientation de la pièce.
     */
    public int getOrientationPiece() {
        return orientationPiece;
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode indiquant si il existe un point d'entrée à  une certaine position (0: en haut, 1: à droite, 2: en bas, 3: à  gauche).
     * @param pointEntree L'indice/la position du point d'entrée.
     * @return true si il y a un point d'entrée, sinon false.
     */
    public boolean getPointEntree(int pointEntree){
        return pointsEntree[pointEntree];
    }

    /**
     * A Faire (08/05/2021 TL Finalisée)
     *
     * Méthode permettant de créer un tableau contenant toutes les pièces du jeu (les 50 pièces).
     * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
     * L'orientation de chaque pièce sera aléatoire.
     * @return Un tableau contenant toutes les pièces du jeu.
     */
    public static Piece[] nouvellesPieces(){
        Piece pieces[] = new Piece[50];
        for(int i = 0; i < 20; i++){
            pieces[i] = new PieceM0();
            Random rdm = new Random();
            int alea;
            alea = rdm.nextInt(4);
            pieces[i].setOrientation(alea);
        }

        for(int j = 20; j < 32; j++){
            pieces[j] = new PieceM1();
            Random rdm = new Random();
            int alea;
            alea = rdm.nextInt(2);
            pieces[j].setOrientation(alea);
        }

        for(int k = 32; k < 50; k++){
            pieces[k] = new PieceM2();
            Random rdm = new Random();
            int alea;
            alea = rdm.nextInt(4);
            pieces[k].setOrientation(alea);
        }
        
        return pieces;
    }

    /**
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     * @return Une copie de la pièce.
     */
    public abstract Piece copy();
}