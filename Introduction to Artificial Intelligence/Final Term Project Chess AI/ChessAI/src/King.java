
//class for king piece instances
public class King extends Piece{
    protected final String type = "King";
    protected final int value = 100;
    protected int x;
    protected int y;
    protected boolean hasMoved = false;
    protected boolean pawnJump = false;

    King(String type, int value, int colourRef){
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
        else if (Math.abs(x-destX)>1 || Math.abs(y-destY)>1) {
            if (Math.abs(x-destX)==2) {
                if (!hasMoved){
                    if (colourRef==0){
                        if (x - destX == 2 && board.board[y][destX-2].isPieceOnSpace() &&
                                board.board[y][destX-2].getPieceOnSpace().getType().equals("Rook") && !board.board[y][destX - 2].getPieceOnSpace().getHasMoved()){
                            if (!board.board[y][x-1].isPieceOnSpace()&&!board.board[y][x-2].isPieceOnSpace()&&!board.board[y][x-3].isPieceOnSpace()){
                                return true;
                            }
                            else return false;
                        }
                        else if(x - destX == -2 && board.board[y][destX + 1].isPieceOnSpace() &&
                                board.board[y][destX + 1].getPieceOnSpace().getType().equals("Rook")&& !board.board[y][destX + 1].getPieceOnSpace().getHasMoved()){
                            if (!board.board[y][x+1].isPieceOnSpace()&&!board.board[y][x+2].isPieceOnSpace()){
                                return true;
                            }
                            else return false;
                        }
                        else {
                            return false;
                        }
                    }
                    else {
                            if (x - destX == 2 && board.board[y][destX-2].isPieceOnSpace() &&
                                    board.board[y][destX-2].getPieceOnSpace().getType().equals("Rook") && !board.board[y][destX - 2].getPieceOnSpace().getHasMoved()) {
                                if (!board.board[y][x - 1].isPieceOnSpace() && !board.board[y][x - 2].isPieceOnSpace()) {
                                    return true;
                                } else return false;
                            } else if (x - destX == -2 && board.board[y][destX + 1].isPieceOnSpace() &&
                                    board.board[y][destX + 1].getPieceOnSpace().getType().equals("Rook")&& !board.board[y][destX + 1].getPieceOnSpace().getHasMoved()) {
                                if (!board.board[y][x + 1].isPieceOnSpace() && !board.board[y][x + 2].isPieceOnSpace() && !board.board[y][x + 3].isPieceOnSpace()) {
                                    return true;
                                } else return false;
                            } else {
                                return false;
                            }
                    }

                }
                else return false;
            }
            else {
                return false;
            }
        }
        else {
            if (board.board[destY][destX].isPieceOnSpace()&&board.board[destY][destX].getPieceOnSpace().getColourRef() == this.getColourRef()) {
                return false;
            }
            return true;
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
    //this class native to the king piece type searches the paths of all enemy pieces to see if the king is in danger from any
    //piece. it is also used to check the validity of user and computer generated moves
    public boolean isChecked(Board b){
        //Check for rooks or queens
        for (int i = 0; i<4; i++){
            switch (i){
                case 0:
                    int j = 1;
                    while (y+j<8){
                        if (b.board[y+j][x].isPieceOnSpace() && b.board[y+j][x].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y + j][x].getPieceOnSpace().getType().equals("Rook") || b.board[y+j][x].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y+j][x].isPieceOnSpace() && b.board[y+j][x].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 1:
                    j = 1;
                    while (y-j>=0){
                        if (b.board[y-j][x].isPieceOnSpace() && b.board[y-j][x].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y - j][x].getPieceOnSpace().getType().equals("Rook") || b.board[y - j][x].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y-j][x].isPieceOnSpace() && b.board[y-j][x].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 2:
                    j = 1;
                    while (x+j<8){
                        if (b.board[y][x+j].isPieceOnSpace() && b.board[y][x+j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y][x + j].getPieceOnSpace().getType().equals("Rook") || b.board[y][x+j].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y][x+j].isPieceOnSpace() && b.board[y][x+j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 3:
                    j = 1;
                    while (x-j>=0){
                        if (b.board[y][x-j].isPieceOnSpace() && b.board[y][x-j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y][x - j].getPieceOnSpace().getType().equals("Rook") || b.board[y][x - j].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y][x-j].isPieceOnSpace() && b.board[y][x-j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
            }
        }
        //check for bishops or queens
        for (int i = 0; i<4; i++){
            switch (i){
                case 0:
                    int j = 1;
                    while (y+j<8 && x+j<8){
                        if (b.board[y+j][x+j].isPieceOnSpace() && b.board[y+j][x+j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y + j][x + j].getPieceOnSpace().getType().equals("Bishop") || b.board[y + j][x + j].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y+j][x+j].isPieceOnSpace() && b.board[y+j][x+j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 1:
                    j = 1;
                    while (y-j>=0 && x+j<8){
                        if (b.board[y-j][x+j].isPieceOnSpace() && b.board[y-j][x+j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y - j][x + j].getPieceOnSpace().getType().equals("Bishop") || b.board[y - j][x + j].getPieceOnSpace().getType().equals("Queen")){
                                return true;
                            }
                            else
                                break;
                        }
                        else if (b.board[y-j][x+j].isPieceOnSpace() && b.board[y-j][x+j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 2:
                    j = 1;
                    while (y+j<8 && x-j>=0){
                        if (b.board[y+j][x-j].isPieceOnSpace() && b.board[y+j][x-j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y + j][x - j].getPieceOnSpace().getType().equals("Bishop") || b.board[y + j][x - j].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y+j][x-j].isPieceOnSpace() && b.board[y+j][x-j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
                case 3:
                    j = 1;
                    while (y-j>=0 && x-j>=0){
                        if (b.board[y-j][x-j].isPieceOnSpace() && b.board[y-j][x-j].getPieceOnSpace().getColourRef()!=this.getColourRef()) {
                            if (b.board[y - j][x - j].getPieceOnSpace().getType().equals("Bishop") || b.board[y - j][x - j].getPieceOnSpace().getType().equals("Queen"))
                                return true;
                            else
                                break;
                        }
                        else if (b.board[y-j][x-j].isPieceOnSpace() && b.board[y-j][x-j].getPieceOnSpace().getColourRef()==this.getColourRef())
                            break;
                        j++;
                    }
                    break;
            }
        }
        //check for pawns
        for (int i = 0; i<2; i++){
            switch (i){
                case 0:
                    if (getColourRef()==1){
                        if (y-1>=0 && x-1>=0 && b.board[y-1][x-1].isPieceOnSpace() && b.board[y-1][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                            if(b.board[y-1][x-1].getPieceOnSpace().getType().equals("Pawn"))
                                return true;
                            else
                                break;
                        }
                    }
                    else{
                        if (y+1<8 && x-1>=0 && b.board[y+1][x-1].isPieceOnSpace() && b.board[y+1][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                            if(b.board[y+1][x-1].getPieceOnSpace().getType().equals("Pawn"))
                                return true;
                            else
                                break;
                        }
                    }
                case 1:
                    if (getColourRef()==1){
                        if (y-1>=0 && x+1<8 && b.board[y-1][x+1].isPieceOnSpace() && b.board[y-1][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                            if(b.board[y-1][x+1].getPieceOnSpace().getType().equals("Pawn"))
                                return true;
                        }
                    }
                    else{
                        if (y+1<8 && x+1<8 && b.board[y+1][x+1].isPieceOnSpace() && b.board[y+1][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                            if(b.board[y+1][x+1].getPieceOnSpace().getType().equals("Pawn"))
                                return true;
                            else
                                break;
                        }
                    }
            }
        }
        // check for knights
        for (int i = 0; i<8; i++){
            switch (i){
                case 0:
                    if (y+2<8 && x+1<8 && b.board[y+2][x+1].isPieceOnSpace() && b.board[y+2][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+2][x+1].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 1:
                    if (y+2<8 && x-1>=0 && b.board[y+2][x-1].isPieceOnSpace() && b.board[y+2][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+2][x-1].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 2:
                    if (y-2>=0 && x-1>=0 && b.board[y-2][x-1].isPieceOnSpace() && b.board[y-2][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-2][x-1].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 3:
                    if (y-2>=0 && x+1<8 && b.board[y-2][x+1].isPieceOnSpace() && b.board[y-2][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-2][x+1].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 4:
                    if (y-1>=0 && x+2<8 && b.board[y-1][x+2].isPieceOnSpace() && b.board[y-1][x+2].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-1][x+2].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 5:
                    if (y+1<8 && x+2<8 && b.board[y+1][x+2].isPieceOnSpace() && b.board[y+1][x+2].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+1][x+2].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 6:
                    if (y+1<8 && x-2>=0 && b.board[y+1][x-2].isPieceOnSpace() && b.board[y+1][x-2].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+1][x-2].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
                case 7:
                    if (y-1>=0 && x-2>=0 && b.board[y-1][x-2].isPieceOnSpace() && b.board[y-1][x-2].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-1][x-2].getPieceOnSpace().getType().equals("Knight"))
                            return true;
                        else
                            break;
                    }
            }
        }
        //check for kings ***only used when king moves
        for (int i = 0; i<8; i++){
            switch (i){
                case 0:
                    if (y+1<8 && x+1<8 && b.board[y+1][x+1].isPieceOnSpace() && b.board[y+1][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+1][x+1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 1:
                    if (y-1>=0 && x+1<8 && b.board[y-1][x+1].isPieceOnSpace() && b.board[y-1][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-1][x+1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 2:
                    if (y+1<8 && x-1>=0 && b.board[y+1][x-1].isPieceOnSpace() && b.board[y+1][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+1][x-1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 3:
                    if (y-1>=0  && x-1>=0 && b.board[y-1][x-1].isPieceOnSpace() && b.board[y-1][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-1][x-1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 4:
                    if (y-1>=0 && b.board[y-1][x].isPieceOnSpace() && b.board[y-1][x].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y-1][x].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 5:
                    if (y+1<8 && b.board[y+1][x].isPieceOnSpace() && b.board[y+1][x].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y+1][x].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 6:
                    if (x-1>=0 && b.board[y][x-1].isPieceOnSpace() && b.board[y][x-1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y][x-1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
                case 7:
                    if (x+1<8 && b.board[y][x+1].isPieceOnSpace() && b.board[y][x+1].getPieceOnSpace().getColourRef()!=this.getColourRef()){
                        if(b.board[y][x+1].getPieceOnSpace().getType().equals("King"))
                            return true;
                        else
                            break;
                    }
            }
        }
        //if no paths reach the king no check is present and return false
        return false;
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
