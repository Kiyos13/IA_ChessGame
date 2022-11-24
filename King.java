import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean hasBeenCheck;
    private boolean hasAlreadyMoved;
    private boolean isControlled;

    /********** SETs **********/
    public void setHasBeenCheck(boolean hasBeenC) {
        hasBeenCheck = hasBeenC;
    }

    public void setHasAlreadyMoved(boolean hasAlreadyM) {
        hasAlreadyMoved = hasAlreadyM;
    }
    
    public void setIsControlled(boolean isC) {
        isControlled = isC;
    }

    /********** GETs **********/
    public boolean getHasBeenCheck() {
        return hasBeenCheck;
    }

    public boolean getHasAlreadyMoved() {
        return hasAlreadyMoved;
    }

    public boolean getIsControlled() {
        return isControlled;
    }
    
    /********** SUB_FUNCTs **********/
    public int[][] getPossibleMovesClassic(Board board, int kingRow, int kingColumn, Piece.Color kingColor) {
        Color currentColor;
        int nbOfPossibleMoves = 0;
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax, currentTargetIsPossible;

        // Normal moves
        int[][] possiblePositions = {
            {kingRow - 1, kingColumn}, {kingRow - 1, kingColumn + 1}, 
            {kingRow, kingColumn + 1}, {kingRow + 1, kingColumn + 1}, 
            {kingRow + 1, kingColumn}, {kingRow + 1, kingColumn - 1}, 
            {kingRow, kingColumn - 1}, {kingRow - 1, kingColumn - 1}
        };

        for (int position = 0; position <= Piece.maxPosition; position++) {
            currentTargetIsPossible = true;
            conditionRowMin = (possiblePositions[position][0] > -1);
            conditionRowMax = (possiblePositions[position][0] <= Piece.maxPosition);
            conditionCoumnMin = (possiblePositions[position][1] > -1);
            conditionColumnMax = (possiblePositions[position][1] <= Piece.maxPosition);
            if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                currentColor = board.getPieceInBoard(possiblePositions[position][0], possiblePositions[position][1]).getColor();
                int rKing = possiblePositions[position][0];
                int cKing = possiblePositions[position][1];
                if (currentColor == kingColor)
                    currentTargetIsPossible = false;
                if (currentTargetIsPossible) {
                    possibleMoves[nbOfPossibleMoves] = new int[] { possiblePositions[position][0], possiblePositions[position][1] };
                    nbOfPossibleMoves++;
                }
            }
        }
        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        Color kingColor = this.getColor();
        int kingRow = this.getRow(), kingColumn = this.getColumn();
        int nbOfPossibleMoves = 0;
        int[][] possibleMovesClassic = new int[Piece.maxPosition * Piece.maxPosition][2];

        possibleMovesClassic = getPossibleMovesClassic(board, kingRow, kingColumn, kingColor);
        nbOfPossibleMoves = this.getNbPossibleMoves();

        int[][] possibleMoves = new int[nbOfPossibleMoves][2];

        for (int i = 0; i < nbOfPossibleMoves; i++) {
                possibleMoves[i] = possibleMovesClassic[i];
        }

        if (verifyCheck){
            int[][] possibleMovesFinal = getPossibleMovesWithVerifyCheck(board, possibleMoves);
            this.setNbPossibleMoves(possibleMovesFinal.length);
            return possibleMovesFinal;
        }
        else{
            this.setNbPossibleMoves(nbOfPossibleMoves);
            return possibleMoves;
        }
    }

    /********** CONSTRUCTOR **********/
    public King(int row, int column, Color color) {
        super(row, column, color, Piece.Type.King);
        setHasBeenCheck(false);
        setHasAlreadyMoved(false);
        setIsControlled(false);
    }

    public King copy(){
        King k = new King(this.row, this.column, this.color);
        k.hasAlreadyMoved = this.hasAlreadyMoved;
        k.hasBeenCheck = this.hasBeenCheck;
        k.isControlled = this.isControlled;
        return k;
    }
}