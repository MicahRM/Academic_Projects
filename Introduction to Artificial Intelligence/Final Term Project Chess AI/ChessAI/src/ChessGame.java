import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;
/*
@Authors Azeel Jivraj 6805584
        Micah Rose-Mighty 6498935
        COSC 3P71 final chess project implemented from scratch in java using intellij IDE. ChessGame is the main executable file.
 */
public class ChessGame {
    //int to reference which player is next to make a move. 0=white, 1=black.
    protected int playerTurn;

    protected Board board;
    protected boolean gameInProgress;
    protected Space selectedSpace;
    protected Space selectedDest;
    protected King whiteKing;
    protected King blackKing;
    protected Piece lastMoved;

//the game instance
    ChessGame() {
        playerTurn = 0;
        gameInProgress = true;
        Scanner input = new Scanner(System.in);
        board = new Board();
        Board boardCopy = new Board();
        whiteKing = (King) board.board[0][4].getPieceOnSpace();
        blackKing = (King) board.board[7][4].getPieceOnSpace();
        System.out.println("For player vs player type 1, for player vs computer type 2");
        //let the user select the game mode
        int gameModeSelection = input.nextInt();
        //player vs player is simple ches following all the rules and ending on checkmate
        if (gameModeSelection == 1) {
            printBoard();
            //game in progress remains true until checkmate is reached
            while (gameInProgress) {
                //board copy used to reverse the moves and return to game state in the event of an illegal move
                boardCopy = board;
                //whites turn
                if (playerTurn == 0) {
                    //until a valid space is chosen this is true
                    boolean spaceNotChosen = true;
                    //loop till the user chooses a space with its piece to move
                    while (spaceNotChosen) {
                        //check to make sure not check mate before move is made
                        if (board.whiteKing.isChecked(board)) {
                            if (board.isCheckMate(0, board)) {
                                gameInProgress = false;
                                System.out.println("Game over! Black Wins");
                                break;
                            }
                            board.board[lastMoved.getY()][lastMoved.getX()].setPiece(lastMoved);
                            System.out.println("Check. Must protect king with next move");
                        }
                        //allow user to select a piece by entering its coordinates in the format "B2"
                        System.out.println("White's Move. Enter the index of the piece you wish to move.*Use Capitals* (Ex.B2) ");
                        //read input
                        String selectedPieceIndex = input.next();
                        selectedSpace = convertInput(selectedPieceIndex);
                        //if the input is null try again and let the user know
                        if (selectedSpace == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. (Ex. B2)");
                        //if the space has a piece make sure its the users
                        else if (selectedSpace.isPieceOnSpace()) {
                            if (selectedSpace.getPieceOnSpace().getColourRef() == playerTurn) {
                                //set to false since space is chosen
                                spaceNotChosen = false;
                                //give user the option to reselect in the case of a bad selection
                                System.out.println(selectedSpace.getPieceOnSpace().getType());
                                System.out.println("Type x to reselect");
                            } else System.out.println("The piece you selected is not yours, please try again");
                        } else System.out.println("No Piece on chosen index, please try again.");
                    }
                    // now we let them choose a destination in same format
                    boolean destNotChosen = true;
                    while (destNotChosen) {
                        // same steps as above to verify input
                        System.out.println("Select destination space for the selected Piece using the same format as above");
                        String selectedDestIndex = input.next();
                        if (selectedDestIndex.equals("x")) {
                            playerTurn = playerTurn + 3;
                            break;
                        }
                        selectedDest = convertInput(selectedDestIndex);
                        if (selectedSpace == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. Ex. B2");
                        //here we make sure the move is valid for the piece selected
                        else if (selectedSpace.getPieceOnSpace().validPath(selectedDest.getX(), selectedDest.getY(), board)) {
                            //these steps adjust the board state to new move and keep track of the rules
                            if (board.board[selectedDest.getY()][selectedDest.getX()].isPieceOnSpace())
                                board.addWhiteScore(board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getValue());
                            board.board[selectedDest.getY()][selectedDest.getX()].setPiece(selectedSpace.getPieceOnSpace());
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setSpace(
                                    selectedDest.getX(), selectedDest.getY());
                            selectedSpace.removePiece();
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            //pawns can move 2 spaces as their first move
                            if (Math.abs(selectedSpace.getY() - selectedDest.getY()) == 2) {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(1);
                            } else {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(0);
                            }
                            //en passant check
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("Pawn")) {
                                if (board.board[selectedDest.getY() - 1][selectedDest.getX()].isPieceOnSpace() &&
                                        board.board[selectedDest.getY() - 1][selectedDest.getX()].getPieceOnSpace().getPawnJump()) {
                                    board.board[selectedDest.getY() - 1][selectedDest.getX()].removePiece();
                                }
                            }
                            //castling check
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("King") &&
                                    Math.abs(selectedSpace.getX() - selectedDest.getX()) == 2) {
                                if (selectedSpace.getX() - selectedDest.getX() > 0) {
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].setPiece(board.board[selectedDest.getY()][0].getPieceOnSpace());
                                    board.board[selectedDest.getY()][0].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].getPieceOnSpace().setHasMoved(0);
                                } else {
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].setPiece(board.board[selectedDest.getY()][7].getPieceOnSpace());
                                    board.board[selectedDest.getY()][7].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].getPieceOnSpace().setHasMoved(0);
                                }
                            }
                            //pawn promotion
                            if (selectedDest.getY() == 7 && board.board[selectedDest.getY()][selectedDest.getX()]
                                    .getPieceOnSpace().getType().equals("Pawn")) {
                                board.board[selectedDest.getY()][selectedDest.getX()].setPiece(pawnPromotion(selectedDest.getPieceOnSpace().getColourRef()));
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().
                                        setSpace(selectedDest.getX(), selectedDest.getY());
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            }
                            //now check to make sure the move doesnt result in check for ourselves
                            if (board.whiteKing.isChecked(board)) {
                                System.out.println("selected move results in check and can't be made. please make another move");
                                board = boardCopy;
                            } else {
                                //if all good we end the turn
                                destNotChosen = false;
                                lastMoved = board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace();
                            }
                            //if not valid let them know and they reselect
                        } else System.out.println("The Destination you chose is not a valid move. Please try again.");
                    }
                    //same logic used above except for the black team
                } else if (playerTurn == 1) {
                    boolean spaceNotChosen = true;
                    while (spaceNotChosen) {
                        if (board.blackKing.isChecked(board)) {
                            if (board.isCheckMate(1, board)) {
                                gameInProgress = false;
                                System.out.println("Game over! Black Wins");
                                break;
                            }
                            board.board[lastMoved.getY()][lastMoved.getX()].setPiece(lastMoved);
                            System.out.println("Check. Must protect king with next move");
                        }
                        System.out.println("Black's Move. Enter the index of the piece you wish to move.*Use Capitals* (Ex.B2) ");
                        String selectedPieceIndex = input.next();
                        selectedSpace = convertInput(selectedPieceIndex);
                        if (selectedSpace == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. (Ex. B2)");
                        else if (selectedSpace.isPieceOnSpace()) {
                            if (selectedSpace.getPieceOnSpace().getColourRef() == playerTurn) {
                                spaceNotChosen = false;
                                System.out.println(selectedSpace.getPieceOnSpace().getType());
                                System.out.println("Type x to reselect");
                            } else System.out.println("The piece you selected is not yours, please try again");
                        } else System.out.println("No Piece on chosen index, please try again.");
                    }
                    boolean destNotChosen = true;
                    while (destNotChosen) {
                        System.out.println("Select destination space for the selected Piece using the same format as above");
                        String selectedDestIndex = input.next();
                        if (selectedDestIndex.equals("x")) {
                            playerTurn = playerTurn + 3;
                            break;
                        }
                        selectedDest = convertInput(selectedDestIndex);
                        if (selectedDest == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. Ex. B2");
                        else if (selectedSpace.getPieceOnSpace().validPath(selectedDest.getX(), selectedDest.getY(), board)) {
                            if (board.board[selectedDest.getY()][selectedDest.getX()].isPieceOnSpace())
                                board.addBlackScore(board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getValue());
                            board.board[selectedDest.getY()][selectedDest.getX()].setPiece(selectedSpace.getPieceOnSpace());
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setSpace(
                                    selectedDest.getX(), selectedDest.getY());
                            selectedSpace.removePiece();
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            if (Math.abs(selectedSpace.getY() - selectedDest.getY()) == 2) {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(1);
                            } else {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(0);
                            }
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("Pawn")) {
                                if (board.board[selectedDest.getY() + 1][selectedDest.getX()].isPieceOnSpace() &&
                                        board.board[selectedDest.getY() + 1][selectedDest.getX()].getPieceOnSpace().getPawnJump()) {
                                    board.board[selectedDest.getY() + 1][selectedDest.getX()].removePiece();
                                }
                            }
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("King") &&
                                    Math.abs(selectedSpace.getX() - selectedDest.getX()) == 2) {
                                if (selectedSpace.getX() - selectedDest.getX() > 0) {
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].setPiece(board.board[selectedDest.getY()][0].getPieceOnSpace());
                                    board.board[selectedDest.getY()][0].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].getPieceOnSpace().setHasMoved(0);
                                } else {
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].setPiece(board.board[selectedDest.getY()][7].getPieceOnSpace());
                                    board.board[selectedDest.getY()][7].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].getPieceOnSpace().setHasMoved(0);
                                }
                            }
                            if (selectedDest.getY() == 0 && board.board[selectedDest.getY()][selectedDest.getX()]
                                    .getPieceOnSpace().getType().equals("Pawn")) {
                                board.board[selectedDest.getY()][selectedDest.getX()].setPiece(pawnPromotion(selectedDest.getPieceOnSpace().getColourRef()));
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().
                                        setSpace(selectedDest.getX(), selectedDest.getY());
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            }
                            if (board.blackKing.isChecked(board)) {
                                System.out.println("selected move results in check and can't be made. please make another move");
                                board = boardCopy;
                            } else {
                                lastMoved = board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace();
                                destNotChosen = false;
                            }
                        } else System.out.println("The Destination you chose is not a valid move. Please try again.");
                        printBoard();
                    }
                }
                //change the player turn and print the board to the console
                playerTurn = (playerTurn + 1) % 2;
                System.out.println("__________________________________________________________");
                printBoard();

            }
        }
        // if its player vs computer the same logic above is used except the the move for the computer is generated by the
        //alpha beta algorithm
        else {
            System.out.println("please enter an integer value used for depth of alpha beta search tree");
            int depth = input.nextInt();
            printBoard();
            while (gameInProgress) {
                boardCopy = board;
                if (playerTurn == 0) {
                    boolean spaceNotChosen = true;
                    while (spaceNotChosen) {
                        if (board.whiteKing.isChecked(board)) {
                            if (board.isCheckMate(0, board)) {
                                gameInProgress = false;
                                System.out.println("Game over! Black Wins");
                                break;
                            }
                            board.board[lastMoved.getY()][lastMoved.getX()].setPiece(lastMoved);
                            System.out.println("Check. Must protect king with next move");
                        }
                        System.out.println("White's Move. Enter the index of the piece you wish to move.*Use Capitals* (Ex.B2) ");
                        String selectedPieceIndex = input.next();
                        selectedSpace = convertInput(selectedPieceIndex);
                        if (selectedSpace == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. (Ex. B2)");
                        else if (selectedSpace.isPieceOnSpace()) {
                            if (selectedSpace.getPieceOnSpace().getColourRef() == playerTurn) {
                                spaceNotChosen = false;
                                System.out.println(selectedSpace.getPieceOnSpace().getType());
                                System.out.println("Type x to reselect");
                            } else System.out.println("The piece you selected is not yours, please try again");
                        } else System.out.println("No Piece on chosen index, please try again.");
                    }
                    boolean destNotChosen = true;
                    while (destNotChosen) {
                        System.out.println("Select destination space for the selected Piece using the same format as above");
                        String selectedDestIndex = input.next();
                        if (selectedDestIndex.equals("x")) {
                            playerTurn = playerTurn + 3;
                            break;
                        }
                        selectedDest = convertInput(selectedDestIndex);
                        if (selectedSpace == null)
                            System.out.println("Invalid Choice. Please try again & remember to follow" +
                                    "the correct format of a capital letter followed by an integer. Ex. B2");
                        else if (selectedSpace.getPieceOnSpace().validPath(selectedDest.getX(), selectedDest.getY(), board)) {
                            if (board.board[selectedDest.getY()][selectedDest.getX()].isPieceOnSpace())
                                board.addWhiteScore(board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getValue());
                            board.board[selectedDest.getY()][selectedDest.getX()].setPiece(selectedSpace.getPieceOnSpace());
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setSpace(
                                    selectedDest.getX(), selectedDest.getY());
                            selectedSpace.removePiece();
                            board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            if (Math.abs(selectedSpace.getY() - selectedDest.getY()) == 2) {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(1);
                            } else {
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(0);
                            }
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("Pawn")) {
                                if (board.board[selectedDest.getY() - 1][selectedDest.getX()].isPieceOnSpace() &&
                                        board.board[selectedDest.getY() - 1][selectedDest.getX()].getPieceOnSpace().getPawnJump()) {
                                    board.board[selectedDest.getY() - 1][selectedDest.getX()].removePiece();
                                }
                            }
                            if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("King") &&
                                    Math.abs(selectedSpace.getX() - selectedDest.getX()) == 2) {
                                if (selectedSpace.getX() - selectedDest.getX() > 0) {
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].setPiece(board.board[selectedDest.getY()][0].getPieceOnSpace());
                                    board.board[selectedDest.getY()][0].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() + 1].getPieceOnSpace().setHasMoved(0);
                                } else {
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].setPiece(board.board[selectedDest.getY()][7].getPieceOnSpace());
                                    board.board[selectedDest.getY()][7].removePiece();
                                    board.board[selectedDest.getY()][selectedDest.getX() - 1].getPieceOnSpace().setHasMoved(0);
                                }
                            }
                            if (selectedDest.getY() == 7 && board.board[selectedDest.getY()][selectedDest.getX()]
                                    .getPieceOnSpace().getType().equals("Pawn")) {
                                board.board[selectedDest.getY()][selectedDest.getX()].setPiece(pawnPromotion(selectedDest.getPieceOnSpace().getColourRef()));
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().
                                        setSpace(selectedDest.getX(), selectedDest.getY());
                                board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                            }
                            if (board.whiteKing.isChecked(board)) {
                                System.out.println("selected move results in check and can't be made. please make another move");
                                board = boardCopy;
                            } else {
                                destNotChosen = false;
                                lastMoved = board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace();
                            }
                        } else System.out.println("The Destination you chose is not a valid move. Please try again.");
                    }
                } else if (playerTurn == 1) {
                    if (board.blackKing.isChecked(board)) {
                        if (board.isCheckMate(1, board)) {
                            gameInProgress = false;
                            System.out.println("Game over! White Wins");
                            break;
                        }
                        printBoard();
                        board.board[lastMoved.getY()][lastMoved.getX()].setPiece(lastMoved);
                        System.out.println("Check. Must protect king with next move");
                    }
                    //here we use the alpha beta algorithm to generate a move
                    Move computerChoice = new Move(board,depth,-10000,10000,1);
                    //tell the user the computers move
                    System.out.println("Computer plays:");
                    System.out.println(computerChoice.startX + "," + computerChoice.startY);
                    System.out.println(computerChoice.endX + "," + computerChoice.endY);
                    selectedSpace = board.board[computerChoice.startY][computerChoice.startX];
                    selectedDest = board.board[computerChoice.endY][computerChoice.endX];
                    if (board.board[selectedDest.getY()][selectedDest.getX()].isPieceOnSpace())
                        board.addBlackScore(board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getValue());
                    board.board[selectedDest.getY()][selectedDest.getX()].setPiece(selectedSpace.getPieceOnSpace());
                    board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setSpace(
                            selectedDest.getX(), selectedDest.getY());
                    selectedSpace.removePiece();
                    board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                    if (Math.abs(selectedSpace.getY() - selectedDest.getY()) == 2) {
                        board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(1);
                    } else {
                        board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setPawnJump(0);
                    }
                    if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("Pawn")) {
                        if (board.board[selectedDest.getY() + 1][selectedDest.getX()].isPieceOnSpace() &&
                                board.board[selectedDest.getY() + 1][selectedDest.getX()].getPieceOnSpace().getPawnJump()) {
                            board.board[selectedDest.getY() + 1][selectedDest.getX()].removePiece();
                        }
                    }
                    if (board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().getType().equals("King") &&
                            Math.abs(selectedSpace.getX() - selectedDest.getX()) == 2) {
                        if (selectedSpace.getX() - selectedDest.getX() > 0) {
                            board.board[selectedDest.getY()][selectedDest.getX() + 1].setPiece(board.board[selectedDest.getY()][0].getPieceOnSpace());
                            board.board[selectedDest.getY()][0].removePiece();
                            board.board[selectedDest.getY()][selectedDest.getX() + 1].getPieceOnSpace().setHasMoved(0);
                        } else {
                            board.board[selectedDest.getY()][selectedDest.getX() - 1].setPiece(board.board[selectedDest.getY()][7].getPieceOnSpace());
                            board.board[selectedDest.getY()][7].removePiece();
                            board.board[selectedDest.getY()][selectedDest.getX() - 1].getPieceOnSpace().setHasMoved(0);
                        }
                    }
                    if (selectedDest.getY() == 0 && board.board[selectedDest.getY()][selectedDest.getX()]
                            .getPieceOnSpace().getType().equals("Pawn")) {
                        board.board[selectedDest.getY()][selectedDest.getX()].setPiece(new Queen("Queen", 9, 1));
                        board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().
                                setSpace(selectedDest.getX(), selectedDest.getY());
                        board.board[selectedDest.getY()][selectedDest.getX()].getPieceOnSpace().setHasMoved(0);
                    }
                }
                playerTurn = (playerTurn + 1) % 2;
                System.out.println("__________________________________________________________");
                printBoard();

            }

        }
    }
    //process user input into usable coordinates
    private Space convertInput(String s) {
        String x = String.valueOf(s.charAt(0));
        String y = String.valueOf(s.charAt(1));
        int posX;
        int posY;

        switch (x) {
            case "A":
                posX = 0;
                break;
            case "B":
                posX = 1;
                break;
            case "C":
                posX = 2;
                break;
            case "D":
                posX = 3;
                break;
            case "E":
                posX = 4;
                break;
            case "F":
                posX = 5;
                break;
            case "G":
                posX = 6;
                break;
            case "H":
                posX = 7;
                break;
            default:
                return null;
        }
        switch (y) {
            case "1":
                posY = 0;
                break;
            case "2":
                posY = 1;
                break;
            case "3":
                posY = 2;
                break;
            case "4":
                posY = 3;
                break;
            case "5":
                posY = 4;
                break;
            case "6":
                posY = 5;
                break;
            case "7":
                posY = 6;
                break;
            case "8":
                posY = 7;
                break;
            default:
                return null;
        }

        return board.board[posY][posX];

    }
    //format the board and print it to console for reference
    private void printBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i+1);
            for (int j = 0; j < 8; j++) {
                if (board.board[i][j].isPieceOnSpace()) {
                    System.out.print("[" + getIcon(board.board[i][j].getPieceOnSpace()) + " ]");
                } else System.out.print("[   ]");
            }
            System.out.println();
        }
        System.out.println("  A    B    C    D    E    F    G    H");
    }
    //get the piece icon to be displayed
    private String getIcon(Piece p) {
        if (p.getColourRef() == 0) {
            return switch (p.getType()) {
                case "Pawn" -> "0P";
                case "Rook" -> "0R";
                case "Knight" -> "0Kn";
                case "Bishop" -> "0B";
                case "Queen" -> "0Q";
                default -> "0K";
            };
        } else {
            return switch (p.getType()) {
                case "Pawn" -> "1P";
                case "Rook" -> "1R";
                case "Knight" -> "1Kn";
                case "Bishop" -> "1B";
                case "Queen" -> "1Q";
                default -> "1K";
            };
        }
    }

    //pawn promotion lets the user select the piece to replace the pawn based on an integer input 1-4
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


//main method to call constructor
    public static void main(String[] args) {
        ChessGame c = new ChessGame();
    }


}
