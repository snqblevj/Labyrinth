package composants;

/**
 *
 * Cette classe permet de reprÃ©senter les piÃ¨ces du jeu de modÃ¨le 0.
 *
 */
public class PieceM0 extends Piece {

    /**
     * A Faire ( 06/05/21 ILDP Finalisé)
     *
     * Constructeur permettant de construire une piÃ¨ce de modÃ¨le 0 et d'orientation 0.
     */
    public PieceM0() {
        super(0,false,true,true,false);
    }
    /**
     * A Faire (06/05/21 ILDP Finalisé)
     *
     * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
     * @return Une copie de la piÃ¨ce.
     */
    public Piece copy(){
        Piece piece=new PieceM0();
        piece.setOrientation(this.getOrientationPiece());


        return piece;
    }
}