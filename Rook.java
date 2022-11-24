
public class Rook extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean hasAlreadyMoved;
    private boolean isControlled;
    
    /********** SETs **********/
    public void setHasAlreadyMoved(boolean hasAlreadyM) {
        hasAlreadyMoved = hasAlreadyM;
    }

    public void setIsControlled(boolean isC) {
        isControlled = isC;
    }

    /********** GETs **********/
    public boolean getHasAlreadyMoved() {
        return hasAlreadyMoved;
    }

    public boolean getIsControlled() {
        return isControlled;
    }
    
    /********** SUB_FUNCTs **********/
    public static int[][] getPossibleMovesRook(Board board, int rookRow, int rookColumn, Piece.Color rookColor) {
        int nbOfPossibleMoves = 0;
        int currentRow = rookRow, currentColumn = rookColumn;
        int[] currentPossibleMove;
        int[][] possibleMovesMax = new int[Piece.maxPosition * Piece.maxPosition][2];
        Piece.Type currentType = Piece.Type.None;
        Piece.Color currentColor = Piece.Color.None;

        //Left Horizontal move
        if (currentColumn > 0){
            int i = currentColumn - 1;
            currentType = board.getPieceInBoard(currentRow, i).getType();
            currentColor = board.getPieceInBoard(currentRow, i).getColor();
            boolean notFirstOppositePieceFound = true;
            while (i >= 0 && currentColor!= rookColor && notFirstOppositePieceFound){
                if (currentType != Piece.Type.None){
                    notFirstOppositePieceFound = false;
                }
                currentPossibleMove = new int[] { currentRow, i };
                possibleMovesMax[nbOfPossibleMoves] = currentPossibleMove;
                nbOfPossibleMoves++;
                i--;
                if (i >= 0){
                    currentType = board.getPieceInBoard(currentRow, i).getType();
                    currentColor = board.getPieceInBoard(currentRow, i).getColor();
                }
            }
        }

        //Right Horizontal move
        if (currentColumn < Piece.maxPosition){
            int i = currentColumn + 1;
            currentType = board.getPieceInBoard(currentRow, i).getType();
            currentColor = board.getPieceInBoard(currentRow, i).getColor();
            boolean notFirstOppositePieceFound = true;
            while (i <= Piece.maxPosition && currentColor!= rookColor && notFirstOppositePieceFound){
                if (currentType != Piece.Type.None){
                    notFirstOppositePieceFound = false;
                }
                currentPossibleMove = new int[] { currentRow, i };
                possibleMovesMax[nbOfPossibleMoves] = currentPossibleMove;
                nbOfPossibleMoves++;
                i++;
                if (i <= Piece.maxPosition){
                    currentType = board.getPieceInBoard(currentRow, i).getType();
                    currentColor = board.getPieceInBoard(currentRow, i).getColor();
                }
            }
        }

        //Down Vertical move
        if (currentRow < Piece.maxPosition){
            int i = currentRow + 1;
            currentType = board.getPieceInBoard(i, currentColumn).getType();
            currentColor = board.getPieceInBoard(i, currentColumn).getColor();
            boolean notFirstOppositePieceFound = true;
            while (i <= Piece.maxPosition && currentColor!= rookColor && notFirstOppositePieceFound){
                if (currentType != Piece.Type.None){
                    notFirstOppositePieceFound = false;
                }
                currentPossibleMove = new int[] { i, currentColumn };
                possibleMovesMax[nbOfPossibleMoves] = currentPossibleMove;
                nbOfPossibleMoves++;
                i++;
                if (i <= Piece.maxPosition){
                    currentType = board.getPieceInBoard(i, currentColumn).getType();
                    currentColor = board.getPieceInBoard(i, currentColumn).getColor();
                }
            }
        }

        //Up Vertical move
        if (currentRow > 0){
            int i = currentRow - 1;
            currentType = board.getPieceInBoard(i, currentColumn).getType();
            currentColor = board.getPieceInBoard(i, currentColumn).getColor();
            boolean notFirstOppositePieceFound = true;
            while (i >= 0 && currentColor != rookColor && notFirstOppositePieceFound){
                if (currentType != Piece.Type.None){
                    notFirstOppositePieceFound = false;
                }
                currentPossibleMove = new int[] { i, currentColumn };
                possibleMovesMax[nbOfPossibleMoves] = currentPossibleMove;
                nbOfPossibleMoves++;
                i--;
                if (i >= 0){
                    currentType = board.getPieceInBoard(i, currentColumn).getType();
                    currentColor = board.getPieceInBoard(i, currentColumn).getColor();
                }
            }
        }

        int[][] possibleMoves = new int[nbOfPossibleMoves][2];
        for (int j = 0; j < nbOfPossibleMoves; j++) {
            possibleMoves[j] = possibleMovesMax[j];
        }
        return possibleMoves;
    }
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        int[][] possibleMoves = getPossibleMovesRook(board, this.getRow(), this.getColumn(), this.getColor());
        if (verifyCheck){
            int[][] possibleMovesFinal = getPossibleMovesWithVerifyCheck(board, possibleMoves);
            this.setNbPossibleMoves(possibleMovesFinal.length);
            return possibleMovesFinal;
        }
        else{
            this.setNbPossibleMoves(possibleMoves.length);
            return possibleMoves;
        } 
    }

    /********** CONSTRUCTOR **********/
    public Rook(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Rook);
        setHasAlreadyMoved(false);
        setIsControlled(false);
    }

    public Rook copy(){
        Rook r = new Rook(this.row, this.column, this.color);
        r.isControlled = this.isControlled;
        r.hasAlreadyMoved = this.hasAlreadyMoved;
        return r;
    }
}