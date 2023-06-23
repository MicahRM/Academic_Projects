//class for pawn piece
public class Pawn extends Piece{
    protected final String type = "Pawn";
    protected final int value = 1;
    protected int x;
    protected int y;
    protected boolean hasMoved = false;
    protected boolean pawnJump = false;

    Pawn(String type, int value, int colourRef){
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
        else if (x!=destX) {
            if (Math.abs(x - destX) == 1) {
                if (colourRef==0){
                    if(y-destY!=-1) return false;
                    else {
                        if (board.board[destY][destX].isPieceOnSpace()) {
                            if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                                return false;
                            } else return true;
                        }
                        else {
                            if (board.board[y][destX].isPieceOnSpace() && board.board[y][destX].getPieceOnSpace().getPawnJump()){
                                return true;
                            }
                            return false;
                        }
                    }
                }
                else if (colourRef==1){
                    if(y-destY!=1) return false;
                    else {
                        if (board.board[destY][destX].isPieceOnSpace()) {
                            if (board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                                return false;
                            } else return true;
                        }
                        else {
                            if (board.board[y][destX].isPieceOnSpace() && board.board[y][destX].getPieceOnSpace().getPawnJump()){
                                return true;
                            }
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        else if (colourRef==0){
            if(y-destY!=-1) {
                if (y-destY==-2 && !hasMoved){
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
            else {
                if (board.board[destY][destX].isPieceOnSpace()) {
                    return false;
                }
                return true;
            }
        }
        else if (colourRef==1){
            if(y-destY!=1) {
                if (y-destY==2 && !hasMoved){
                    if (board.board[destY][destX].isPieceOnSpace()) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
            else {
                if (board.board[destY][destX].isPieceOnSpace()) {
                    return false;
                }
                return true;
            }
        }
        else return true;
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
        if (i==1){
            pawnJump=true;
        }
        else{
            pawnJump = false;
        }
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
