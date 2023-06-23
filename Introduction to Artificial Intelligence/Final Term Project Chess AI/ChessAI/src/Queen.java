//class for queen piece
public class Queen extends Piece{
    protected final String type = "Queen";
    protected final int value = 9;
    protected int x;
    protected int y;
    protected boolean hasMoved = false;
    protected boolean pawnJump = false;

    Queen(String type, int value, int colourRef){
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
        else if (x!=destX && y!=destY){
            if (Math.abs(x-destX)!=Math.abs(y-destY)) return false;
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
        else {
            if (y!=destY) {
                if (y - destY < 0) {
                    for (int i = 1; i < Math.abs(y - destY); i++) {
                        if (board.board[y+i][x].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][x].isPieceOnSpace()) {
                        if (board.board[destY][x].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                    }
                    return true;
                }
                else {
                    for (int i = 1; i < (y - destY); i++) {
                        if (board.board[y-i][x].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[destY][x].isPieceOnSpace()) {

                        if (board.board[destY][x].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
            }
            else if (x!=destX) {
                if (x - destX < 0) {
                    for (int i = 1; i < Math.abs(x - destX); i++) {
                        if (board.board[y][x+i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[y][destX].isPieceOnSpace()) {
                        if (board.board[y][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
                else {
                    for (int i = 1; i < (x - destX); i++) {
                        if (board.board[y][x-i].isPieceOnSpace()) {
                            return false;
                        }
                    }
                    if (board.board[y][destX].isPieceOnSpace()) {

                        if (board.board[y][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
            }
            else return true;
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
