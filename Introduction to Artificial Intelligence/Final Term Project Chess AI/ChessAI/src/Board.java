
//this class outlines the behaviours and states necessary for a chess board of 8x8 squares
public class Board {
    protected Space[][] board;
    //keep track of our kings for check/checkmate purposes
    protected King whiteKing;
    protected King blackKing;
    //keep track of each players scores from capturing pieces
    protected int whiteScore;
    protected int blackScore;

    Board(){
        //create empty board of spaces
        board = new Space[8][8];
        //fill the board with pieces and set the colour of the spaces.*useful for queen positioning and initial setup only
        createAndFill();
        //set up the pointers to the starting position of both kings
        whiteKing = (King)board[0][4].getPieceOnSpace();
        blackKing = (King)board[7][4].getPieceOnSpace();
        //initialize both scores to 0 to start
        whiteScore=0;
        blackScore=0;
    }
//this method sets the colour of the spaces and places the necessary starting piece on the spaces using getInitPieces
    private void createAndFill(){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                board[j][i] = new Space(i,j);
                board[j][i].setPiece(getInitPiece(i,j));
                if (board[j][i].getPieceOnSpace()!=null) {
                    board[j][i].getPieceOnSpace().setSpace(i, j);
                }
            }
        }
    }
//this method holds the starting coordinates for all pieces and returns the piece based on the position parameters
    private Piece getInitPiece(int x, int y){
        if(y==7){
            if(x==0||x==7){
                return new Rook("Rook",5,1);
            }
            else if (x==1||x==6){
                return new Knight("Knight", 3, 1);
            }
            else if (x==2||x==5){
                return new Bishop("Bishop", 3, 1);
            }
            else if(x==3){
                return new Queen("Queen", 9, 1);
            }
            else {
                return new King("King", 100, 1);
            }
        }
        else if (y==6){
            return new Pawn("Pawn", 1,1);
        }
        else if (y==1){
            return new Pawn("Pawn", 1,0);
        }
        else if(y==0){
            if(x==0||x==7){
                return new Rook("Rook",5,0);
            }
            else if (x==1||x==6){
                return new Knight("Knight", 3, 0);
            }
            else if (x==2||x==5){
                return new Bishop("Bishop", 3, 0);
            }
            else if(x==3){
                return new Queen("Queen", 9, 0);
            }
            else{
                return new King("King", 100, 0);
            }
        }
        else return null;
    }
//this method is used to check for checkmate. it will test all possible moves on the board for the colour c passed in as
    //parameter and see if any potential moves result in a board state without check for the team of colour c. if even one
    //is found then the board is not in checkmate state and we return false.
    public boolean isCheckMate(int c, Board board){
        //intialize a test board to avoid changing the game board
        Board testBoard = new Board();
        //holder piece for when we move a piece and need to reset after checks
        Piece holder;
        //for all spaces on the board
        for (Space[] s:board.board){
            for (Space t:s){
                //make sure the piece is ours
                if(t.isPieceOnSpace() && t.getPieceOnSpace().getColourRef()==c){
                    //holder is the piece we try to move
                    holder = t.getPieceOnSpace();
                    //now we will check every possible space to see if its a valid move
                    for (int i = 0; i<8; i++){
                        for (int j = 0; j<8; j++){
                            testBoard = board;
                            t.setPiece(holder);
                            //pointer for holding captured pieces
                            Piece p = null;
                            //if the coordinate is valid
                            if (t.getPieceOnSpace().validPath(j,i,board)){
                                //if theres a capture we want to keep it in our program
                                if (testBoard.board[i][j].isPieceOnSpace()){
                                    p = testBoard.board[i][j].getPieceOnSpace();
                                }
                                //make all changes to board to constitute a move to the destination
                                testBoard.board[i][j].setPiece(t.getPieceOnSpace());
                                t.removePiece();
                                testBoard.board[i][j].getPieceOnSpace().setSpace(j,i);
                                //if white or black we change which king we are looking for on check
                                if (c==0){
                                    //if the piece we are moving is a king we want set the board's king pieces new space
                                    if(testBoard.board[i][j].getPieceOnSpace().getType().equals("King")){
                                        whiteKing.setSpace(j,i);
                                    }
                                    //if its not checked we reset the board and return false
                                    if (!testBoard.whiteKing.isChecked(testBoard)) {
                                        testBoard.board[i][j].removePiece();
                                        t.setPiece(holder);
                                        t.getPieceOnSpace().setSpace(t.getX(),t.getY());
                                        if (p!=null)
                                            testBoard.board[i][j].setPiece(p);
                                        if(t.getPieceOnSpace().getType().equals("King")){
                                            whiteKing.setSpace(t.getX(),t.getY());
                                        }
                                        return false;
                                    }
                                }
                                else {
                                    if(testBoard.board[i][j].getPieceOnSpace().getType().equals("King")){
                                        blackKing.setSpace(j,i);
                                    }
                                    if (!testBoard.blackKing.isChecked(testBoard)) {
                                        testBoard.board[i][j].removePiece();
                                        t.setPiece(holder);
                                        t.getPieceOnSpace().setSpace(t.getX(),t.getY());
                                        if (p!=null)
                                            testBoard.board[i][j].setPiece(p);
                                        if(t.getPieceOnSpace().getType().equals("King")){
                                            blackKing.setSpace(t.getX(),t.getY());
                                        }
                                        return false;
                                    }
                                }
                                //if it is checked we reset the board and try again
                                testBoard.board[i][j].removePiece();
                                t.setPiece(holder);
                                t.getPieceOnSpace().setSpace(t.getX(),t.getY());
                                if (p!=null)
                                    testBoard.board[i][j].setPiece(p);
                                if(t.getPieceOnSpace().getType().equals("King")){
                                    if (t.getPieceOnSpace().getColourRef()==0){
                                        whiteKing.setSpace(t.getX(),t.getY());
                                    }
                                    else{
                                        blackKing.setSpace(t.getX(),t.getY());
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        //return true if no possible moves are found
        return true;
    }
    //the following 2 methods add score to the respective player for captures based on a parameter of the value of piece captured
    public void addWhiteScore(int i){
        whiteScore=whiteScore+i;
    }

    public void addBlackScore(int i){
        blackScore=blackScore+i;
    }
    //calculate the score difference in favour of colour i
    public int getScoreDiff(int i){
        if (i==0){
            return whiteScore-blackScore;
        }
        else
            return blackScore-whiteScore;
    }

}
