import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean hasAlreadyMoved;
    private boolean isControlled; // TODO : fct to detect if a rook is controlled by another piece (for casting)
    public int val = 5;
    
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
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax;
        Piece.Color currentColor = Piece.Color.None;

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
                            currentPossibleMove = new int[] { currentRow, currentColumn };
                            possibleMovesMax[nbOfPossibleMoves] = currentPossibleMove;
                            nbOfPossibleMoves++;
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

        int[][] possibleMoves = new int[nbOfPossibleMoves][2];
        for (int i = 0; i < nbOfPossibleMoves; i++) {
            possibleMoves[i] = possibleMovesMax[i];
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
    public int[][] getPossibleMoves(Board board) {
        int[][] possibleMoves = getPossibleMovesRook(board, this.getRow(), this.getColumn(), this.getColor());
        this.setNbPossibleMoves(possibleMoves.length);
        return possibleMoves;  
    }

    @Override
    public ArrayList<Position> getPossibleMoves2(Board board) {
        ArrayList<Position> possibleMoves = getPossibleMovesRook2(board, this.getRow(), this.getColumn(), this.getColor());
        this.setNbPossibleMoves(possibleMoves.size());
        return possibleMoves;
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