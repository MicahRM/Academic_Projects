//class for knight piece
public class Knight extends Piece{
    protected final String type = "Knight";
    protected final int value = 3;
    protected int x;
    protected int y;
    protected boolean hasMoved = false;
    protected boolean pawnJump = false;

    Knight(String type, int value, int colourRef){
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
        else if (Math.abs(x-destX)>2 || Math.abs(y-destY)>2) return false;
        else if (Math.abs(x-destX)==2){
            if(Math.abs(y-destY)!=1) return false;
            else{
                if (board.board[destY][destX].isPieceOnSpace()){
                    if (board.board[destY][destX].getPieceOnSpace().getColourRef()==this.getColourRef()){
                        return false;
                    }
                }
                return true;
            }

        }
        else if (Math.abs(y-destY)==2){
            if(Math.abs(x-destX)!=1) return false;
            else{
                if (board.board[destY][destX].isPieceOnSpace()){
                    if (board.board[destY][destX].getPieceOnSpace().getColourRef()==this.getColourRef()){
                        return false;
                    }
                }
                return true;
            }
        }
        else return false;
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
