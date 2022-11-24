
public class Bishop extends Piece {

    /********** SUB_FUNCTs **********/
    public static int[][] getPossibleMovesBishop(Board board, int bishopRow, int bishopColumn, Piece.Color bishopColor) {
        int nbOfPossibleMoves = 0, currentRow = bishopRow, currentColumn = bishopColumn;
        int[][] possibleMovesMax = new int[Piece.maxPosition * Piece.maxPosition][2];
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax;
        Piece.Color currentColor = Piece.Color.None;
        
        // Loop for the 4 directions (up right, down right, down left, up left)
        for (int bishopRowDirectionInt = -1; bishopRowDirectionInt <= 1; bishopRowDirectionInt += 2) {
            for (int bishopColumnDirectionInt = -1; bishopColumnDirectionInt <= 1; bishopColumnDirectionInt += 2) {
                while ((currentRow + bishopRowDirectionInt >= 0) && (currentColumn + bishopColumnDirectionInt >= 0)) {
                    currentRow += bishopRowDirectionInt;
                    currentColumn += bishopColumnDirectionInt;
                    conditionRowMin = (currentRow > -1);
                    conditionRowMax = (currentRow <= Piece.maxPosition);
                    conditionCoumnMin = (currentColumn > -1);
                    conditionColumnMax = (currentColumn <= Piece.maxPosition);
                    if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                        currentColor = board.getPieceInBoard(currentRow, currentColumn).getColor();
                        if (currentColor == bishopColor)
                            break;
                        else {
                            possibleMovesMax[nbOfPossibleMoves] = new int[] { currentRow, currentColumn };
                            nbOfPossibleMoves++;
                            if (currentColor != Piece.Color.None)
                                break;
                        }
                    }
                    else
                        break;             
                }
                currentRow = bishopRow;
                currentColumn = bishopColumn;
            }
        }

        int[][] possibleMoves = new int[nbOfPossibleMoves][2];
        for (int i = 0; i < nbOfPossibleMoves; i++) {
            possibleMoves[i] = possibleMovesMax[i];
        }
        return possibleMoves;
    }
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        int[][] possibleMoves = getPossibleMovesBishop(board, this.getRow(), this.getColumn(), this.getColor());

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
    public Bishop(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Bishop);
    }

    public Bishop copy(){
        Bishop b = new Bishop(this.row, this.column, this.color);
        return b;
    }
}