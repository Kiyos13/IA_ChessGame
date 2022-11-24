import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean hasAlreadyMoved;
    private boolean isControlled; // TODO : fct to detect if a rook is controlled by another piece (for casting)
    
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
    
    public static ArrayList<Position> getPossibleMovesRook2(Board board, int rookRow, int rookColumn, Piece.Color rookColor) {
        int currentRow = rookRow, currentColumn = rookColumn;
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax;
        Piece.Color currentColor = Piece.Color.None;

        ArrayList<Position> possibleMoves = new ArrayList<>();

        // Loop for the 4 directions (up, right, down, left)
        for (int i = 0; i < 2; i++) {
            for (int rookRowOrColDirectionInt = -1; rookRowOrColDirectionInt <= 1; rookRowOrColDirectionInt += 2) {
                while ((currentRow + rookRowOrColDirectionInt >= 0) && (currentRow + rookRowOrColDirectionInt <= Piece.maxPosition)) {
                    if (i == 0)
                        currentRow += rookRowOrColDirectionInt;
                    else
                        currentColumn += rookRowOrColDirectionInt;
                    conditionRowMin = (currentRow > -1);
                    conditionRowMax = (currentRow <= Piece.maxPosition);
                    conditionCoumnMin = (currentColumn > -1);
                    conditionColumnMax = (currentColumn <= Piece.maxPosition);
                    if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                        currentColor = board.getPieceInBoard(currentRow, currentColumn).getColor();
                        if (currentColor == rookColor)
                            break;
                        else {
                            Position pos = new Position(currentRow, currentColumn);
                            possibleMoves.add(pos);
                            if (currentColor != Piece.Color.None)
                                break;
                        }
                    }
                    else
                        break;             
                }
                currentRow = rookRow;
                currentColumn = rookColumn;
            }
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