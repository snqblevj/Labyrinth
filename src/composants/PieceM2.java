package composants;

/**
 *
 * Cette classe permet de reprÃ©senter les piÃ¨ces du jeu de modÃ¨le 2.
 *
 */
public class PieceM2 extends Piece {

    /**
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de construire une piÃ¨ce de modÃ¨le 2 et d'orientation 0.
     */
    public PieceM2() {
        super(2,true,true,false,true);
    }
    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
     * @return Une copie de la piÃ¨ce.
     */
    public Piece copy(){
        Piece piece=new PieceM2();
        piece.setOrientation(this.getOrientationPiece());

        return piece;
    }
}
