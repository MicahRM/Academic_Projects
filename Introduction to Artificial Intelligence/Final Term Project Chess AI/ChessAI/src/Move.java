import java.util.Scanner;
//This class serves as the Move generation Alpha Beta AI class. it will take in a move, board state, depth and recursively
//explore board options and evaluations of the options to return a viable moved
public class Move {
    int startX;
    int startY;
    int endX;
    int endY;
    int eval;
    chessMove selection;
    chessMove newMove;
    int count = 0;

    public Move(Board b, int d, int alpha, int beta, int turn) {
        selection = alphaBeta(d, alpha, beta, turn, b, null);
        startX = selection.getStartX();
        startY = selection.getStartY();
        endX = selection.getEndX();
        endY = selection.getEndY();
        eval = selection.getEval();
    }
    //this is the alpha beta method
    chessMove alphaBeta(int depth, int alpha, int beta, int turn, Board b, chessMove m) {
        //all variables used for returning the board state to the original state
        Piece holder;
        Piece captured;
        Piece enPass;
        Piece castleRook = null;
        boolean moved;
        boolean jump;
        boolean enPassant;
        boolean castled;
        boolean checkMove;
    //base case return the last move
        if (depth == 0)
            return m;
        //for all spaces on the board
        for (Space[] s : b.board) {
            for (Space t : s) {
                //if the piece is the same colour as the computers colour
                if (t.isPieceOnSpace() && t.getPieceOnSpace().getColourRef() == turn) {
                    holder = t.getPieceOnSpace();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            int evaluation;
                            count++;
                            captured = null;
                            newMove = null;
                            //check for valid path of move and use all the same features used for a player move in chessGame
                            if (t.getPieceOnSpace().validPath(j, i, b)) {
                                if (b.board[i][j].isPieceOnSpace()) {
                                    captured = b.board[i][j].getPieceOnSpace();
                                    if (turn == 0)
                                        b.addWhiteScore(b.board[i][j].getPieceOnSpace().getValue());
                                    else
                                        b.addBlackScore(b.board[i][j].getPieceOnSpace().getValue());
                                }
                                b.board[i][j].setPiece(t.getPieceOnSpace());
                                t.removePiece();
                                b.board[i][j].getPieceOnSpace().setSpace(
                                        j, i);
                                moved= b.board[i][j].getPieceOnSpace().getHasMoved();
                                b.board[i][j].getPieceOnSpace().setHasMoved(0);
                                jump= b.board[i][j].getPieceOnSpace().getPawnJump();
                                if (Math.abs(t.getY() - i) == 2) {
                                    b.board[i][j].getPieceOnSpace().setPawnJump(1);
                                } else {
                                    b.board[i][j].getPieceOnSpace().setPawnJump(0);
                                }
                                enPassant=false;
                                enPass = null;
                                if (b.board[i][j].getPieceOnSpace().getType().equals("Pawn")) {
                                    if (b.board[i][j].getPieceOnSpace().getColourRef()==0){
                                        if (b.board[i + 1][j].isPieceOnSpace() &&
                                                b.board[i + 1][j].getPieceOnSpace().getPawnJump()) {
                                            enPassant=true;
                                            enPass = b.board[i + 1][j].getPieceOnSpace();
                                            b.board[i + 1][j].removePiece();
                                        }
                                    }
                                    else{
                                        if (b.board[i - 1][j].isPieceOnSpace() &&
                                                b.board[i - 1][j].getPieceOnSpace().getPawnJump()) {
                                            enPassant=true;
                                            enPass = b.board[i - 1][j].getPieceOnSpace();
                                            b.board[i - 1][j].removePiece();
                                        }
                                    }

                                }
                                castled = false;
                                if (b.board[i][j].getPieceOnSpace().getType().equals("King") &&
                                        Math.abs(t.getX() - j) == 2) {
                                    castled=true;
                                    if (t.getX() - j > 0) {
                                        castleRook=b.board[i][0].getPieceOnSpace();
                                        b.board[i][j + 1].setPiece(b.board[i][0].getPieceOnSpace());
                                        b.board[i][0].removePiece();
                                        b.board[i][j + 1].getPieceOnSpace().setHasMoved(0);
                                        b.board[i][j + 1].getPieceOnSpace().setSpace(j+1, i);
                                    } else {
                                        castleRook=b.board[i][7].getPieceOnSpace();
                                        b.board[i][j - 1].setPiece(b.board[i][7].getPieceOnSpace());
                                        b.board[i][7].removePiece();
                                        b.board[i][j - 1].getPieceOnSpace().setHasMoved(0);
                                        b.board[i][j - 1].getPieceOnSpace().setSpace(j-1, i);
                                    }
                                }
                                if (i == 0 && b.board[i][j]
                                        .getPieceOnSpace().getType().equals("Pawn")) {
                                    b.board[i][j].setPiece(pawnPromotion(turn));
                                    b.board[i][j].getPieceOnSpace().
                                            setSpace(j, i);
                                    b.board[i][j].getPieceOnSpace().setHasMoved(0);
                                }
                                //if Check undo all moves
                                checkMove=false;
                                if (turn==0) {
                                    if (b.whiteKing.isChecked(b)) {
                                        checkMove=true;
                                        b.board[i][j].removePiece();
                                        t.setPiece(holder);
                                        if (captured!=null) {
                                            b.board[i][j].setPiece(captured);
                                            b.addWhiteScore(-captured.getValue());
                                        }


                                        t.getPieceOnSpace().setSpace(t.getX(), t.getY());
                                        if (!moved)
                                            t.getPieceOnSpace().setHasMoved(0);
                                        if (!jump)
                                            t.getPieceOnSpace().setPawnJump(0);
                                        if (enPassant)
                                            b.board[enPass.getY()][enPass.getX()].setPiece(enPass);
                                        enPass = null;
                                        if (castled){
                                            if (castleRook.getX()==5){
                                                b.board[i][5].removePiece();
                                                b.board[i][7].setPiece(castleRook);
                                            }
                                            else {
                                                b.board[i][3].removePiece();
                                                b.board[i][0].setPiece(castleRook);
                                            }
                                        }
                                    }
                                }
                                else if (turn==1){
                                    if (b.blackKing.isChecked(b)) {
                                        checkMove=true;
                                        b.board[i][j].removePiece();
                                        t.setPiece(holder);
                                        if (captured!=null){
                                            b.board[i][j].setPiece(captured);
                                            b.addBlackScore(-captured.getValue());
                                        }

                                        t.getPieceOnSpace().setSpace(t.getX(), t.getY());
                                        if (!moved)
                                            t.getPieceOnSpace().setHasMoved(0);
                                        if (!jump)
                                            t.getPieceOnSpace().setPawnJump(0);
                                        if (enPassant)
                                            b.board[enPass.getY()][enPass.getX()].setPiece(enPass);
                                        enPass = null;
                                        if (castled){
                                            if (castleRook.getX()==5){
                                                b.board[i][5].removePiece();
                                                b.board[i][7].setPiece(castleRook);
                                            }
                                            else {
                                                b.board[i][3].removePiece();
                                                b.board[i][0].setPiece(castleRook);
                                            }
                                        }
                                    }
                                }
                                //if the valid move is does not create check we can move forward with alpha beta
                                if (!checkMove) {
                                    //use int to keep track of evaluation after board state
                                    int movedEval=evaluate(b,turn);
                                    //if no original move has been set yet set the move to the current move
                                    if (m==null){
                                        m=new chessMove(t.getX(),t.getY(),j,i,movedEval);
                                        evaluation = m.getEval();
                                    }
                                    //if there is a valid move set the evaluation to the strength of the current move to be
                                    //compared later
                                    else {
                                        evaluation = -alphaBeta(depth - 1, -beta, -alpha, (turn + 1) % 2, b,new chessMove(t.getX(),t.getY(),j,i,movedEval)).getEval();
                                    }
                                    //reset the board state
                                    b.board[i][j].removePiece();
                                    t.setPiece(holder);
                                    if (captured!=null){
                                        b.board[i][j].setPiece(captured);
                                        if (turn == 0)
                                            b.addWhiteScore(-captured.getValue());
                                        else
                                            b.addBlackScore(-captured.getValue());
                                    }
                                    t.getPieceOnSpace().setSpace(t.getX(), t.getY());
                                    if (!moved)
                                        t.getPieceOnSpace().setHasMoved(1);
                                    if (!jump)
                                        t.getPieceOnSpace().setPawnJump(0);
                                    if (enPassant)
                                        b.board[enPass.getY()][enPass.getX()].setPiece(enPass);
                                    enPass = null;
                                    if (castled){
                                        if (castleRook.getX()==5){
                                            b.board[i][5].removePiece();
                                            b.board[i][7].setPiece(castleRook);
                                        }
                                        else {
                                            b.board[i][3].removePiece();
                                            b.board[i][0].setPiece(castleRook);
                                        }
                                    }
                                    //compare the current move to the stored move and if the current is better set the stored
                                    //move to the current move
                                    if (m.getEval()<evaluation){
                                        m = new chessMove(t.getX(),t.getY(),j,i,movedEval);
                                    }
                                    //if the strength is the same use a bit of randomness
                                    if (m.getEval()==evaluation){
                                        double rand = Math.random();
                                        if (rand>0.80){
                                            m = new chessMove(t.getX(),t.getY(),j,i,evaluate(b,turn));
                                        }
                                    }
                                    //if the move is too good for the opponent prune it
                                    if (m.getEval()>=beta)
                                        return m; //*Prune*
                                    //make the alpha the max of alpha or the stored moves evaluation
                                    if (alpha < m.getEval())
                                        alpha=m.getEval();
                                    else if (alpha > m.getEval()){
                                        m.eval=alpha;
                                    }
                                }
                                //if no move can be made it is checkmate for one of the teams and reflect that in the score
                                //to either avoid or pursue the move
                                  if (m!=null) {
                                        if(b.isCheckMate((turn+1)%2,b))
                                            m.eval = 10000;
                                        else if(b.isCheckMate(turn,b)) {
                                            m.eval=-1000000;
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
        //return the stored move
        return m;
    }
    //same pawn promotion used in chessgame
    private Piece pawnPromotion(int c) {
        Scanner input = new Scanner(System.in);
        System.out.println("Pawn Promotion: Type 1 for Rook, 2 for Knight, 3 for Bishop, or 4 for Queen");
        int promo = input.nextInt();
        return switch (promo) {
            case 1 -> new Rook("Rook", 5, c);
            case 2 -> new Knight("Knight", 3, c);
            case 3 -> new Bishop("Bishop", 3, c);
            case 4 -> new Queen("Queen", 9, c);
            default -> null;
        };
    }
    //Evaluation heuristic uses the value of the pieces to calcuate the strength of the moves based on the available captures
    //We attempted to implement a heuristic based on piece matrices of strong postions but were unable to debug it and were
    //forced to remove it to have a working program.
    private int evaluate(Board b, int t){

        return b.getScoreDiff(t);
    }


}
