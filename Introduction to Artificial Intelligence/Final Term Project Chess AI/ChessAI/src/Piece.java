//this abstract class outlines the necessary behaviours of a piece in chess
public abstract class Piece {
    final String type;
    final int value;
    //colourRef tells us which colour the piece is via integer. 0 = white, 1 = black
    final int colourRef;
    //constructor sets the type, value, and colour of the piece based on parameters
    Piece(String type, int value, int colourRef){
        this.type = type;
        this.value = value;
        this.colourRef = colourRef;
    }
    //returns the colourRef used for team assignment
    public abstract int getColourRef();
    //returns the piece type ie: pawn, rook...
    public abstract String getType();
    //returns the value of the piece
    public abstract int getValue();
    /*This method takes in destination coordinates for a move and compares it with the current position of the piece and
    the allowed movements for the piece outlined in the rules of chess. It will allow pieces to move to spaces occupied
    by an opposing piece as a capture but will not allow for moves in which the path to the destination is blocked by any
    piece on the board or if the destination is occupied by a friendly piece.
    *
     */
    public abstract boolean validPath(int destX, int destY, Board board);
    //this method sets the position variables of the piece based on its position on the board
    public abstract void setSpace(int x, int y);
    //this method changes the hasMoved variable for a piece once moved to prevent moves that require an unmoved piece to
    //be involved
    public abstract void setHasMoved(int i);
    //returns the value of getHasMoved
    public abstract boolean getHasMoved();
    //returns the value of pawnJump
    public abstract boolean getPawnJump();
    //sets the variable pawn jump to allow for en passant if the other rules are obeyed
    public abstract void setPawnJump(int i);
    //returns x coordinate of the piece
    public abstract int getX();
    //returns y coordinate of the piece
    public abstract int getY();


}
