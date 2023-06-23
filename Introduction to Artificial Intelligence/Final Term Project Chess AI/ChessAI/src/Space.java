public class Space {

    protected final int x;
    protected final int y;
    ////colourRef tells us which colour the piece is via integer. 0 = white, 1 = black
    protected final int colourRef;
    protected Piece pieceOnSpace;

    public Space (int x, int y){
        this.x = x;
        this.y = y;
        colourRef = (x+y+1)%2;
        pieceOnSpace = null;

    }
    public int getColourRef(){
        return colourRef;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isPieceOnSpace(){
        if (pieceOnSpace==null) return false;
        else return true;
    }

    public Piece getPieceOnSpace(){
        return pieceOnSpace;
    }

    public String getPieceType(){
        if (pieceOnSpace==null) {
            System.out.println("No Piece here!");
            return null;
        }
        else {
            return pieceOnSpace.getType();
        }
    }

    public void setPiece(Piece p) {
        pieceOnSpace = p;
    }

    public void removePiece(){
        pieceOnSpace = null;
    }
}
