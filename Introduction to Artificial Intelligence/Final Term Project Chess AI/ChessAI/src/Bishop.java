//class for bishop piece
public class Bishop extends Piece{
    protected final String type = "Bishop";
    protected final int value = 3;
    protected int x;
    protected int y;
    protected boolean hasMoved = false;
    protected boolean pawnJump = false;

    Bishop(String type, int value, int colourRef){
        super(type, value, colourRef);
    }
    @Override
    public int getColourRef(){
        return colourRef;
    }
    @Override
    public String getType(){
        return type;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean validPath(int destX, int destY, Board board) {
        if (x==destX && y==destY){
            return false;
        }
        else if (Math.abs(x-destX)!=Math.abs(y-destY)) return false;
        else {
            if (x-destX>0){
                if (y-destY>0){
                    for (int i = 1; i < (x - destX); i++) {
                        if (board.board[y-i][x-i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                    }
                    return true;
                }
                else{
                    for (int i = 1; i < (x - destX); i++) {
                        if (board.board[y+i][x-i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            else {
                if (y-destY>0){
                    for (int i = 1; i < Math.abs(x - destX); i++) {
                        if (board.board[y-i][x+i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                    }
                    return true;

                }
                else{
                    for (int i = 1; i < Math.abs(x - destX); i++) {
                        if (board.board[y+i][x+i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

    }

    @Override
    public void setSpace(int x, int y) {
        this.x=x;
        this.y=y;
    }
    @Override
    public void setHasMoved(int i){
        if (i==0)
            hasMoved=true;
        else
            hasMoved=false;
    }
    @Override
    public boolean getHasMoved(){
        return hasMoved;
    }

    @Override
    public void setPawnJump(int i){
        pawnJump = false;
    }

    @Override
    public boolean getPawnJump() {
        return pawnJump;
    }
    @Override
    public int getX(){
        return x;
    }
    @Override
    public int getY(){
        return y;
    }
}
