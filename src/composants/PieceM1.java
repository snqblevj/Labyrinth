package composants;

/**
 *
 * Cette classe permet de reprÃ©senter les piÃ¨ces du jeu de modÃ¨le 0.
 *
 */
public class PieceM1 extends Piece {

    /**
     * A Faire (06/05/2021 TC Finalisée)
     *
     * Constructeur permettant de construire une piÃ¨ce de modÃ¨le 0 et d'orientation 0.
     */
    public PieceM1() {
        super(1,true,false,true,false);
    }
    /**
     * A Faire (06/05/2021 TC Finalisée)
     *
     * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
     * @return Une copie de la piÃ¨ce.
     */
    public Piece copy(){
        Piece piece=new PieceM1();
        piece.setOrientation(this.getOrientationPiece());


        return piece;
    }
}