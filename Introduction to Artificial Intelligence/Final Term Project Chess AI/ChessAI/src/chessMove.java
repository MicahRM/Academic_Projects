//This class outlines the object behaviours and states for a chessMove and is used by the AI in the Move class to generate
//an executable move based on the board state and valid paths of the pieces.
public class chessMove {
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected int eval;

    public chessMove(int startX, int startY, int endX, int endY, int eval){
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
        this.eval=eval;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getEval() {
        return eval;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
