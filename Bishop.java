import java.util.ArrayList;

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

    public static ArrayList<Position> getPossibleMovesBishop2(Board board, int bishopRow, int bishopColumn, Piece.Color bishopColor) {
        int currentRow = bishopRow, currentColumn = bishopColumn;
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax;
        Piece.Color currentColor = Piece.Color.None;
        ArrayList<Position> possibleMoves = new ArrayList<>();

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
                            possibleMoves.add(new Position(currentRow, currentColumn));
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
        return possibleMoves;
    }
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board) {
        int[][] possibleMoves = getPossibleMovesBishop(board, this.getRow(), this.getColumn(), this.getColor());
        int possibleMovesLength = possibleMoves.length;

        /*int[][] possibleMovesTemp = new int[possibleMovesLength][2];
        int possibleMovesTempLength = 0;

        for (int i = 0; i < possibleMovesLength; i++){
            Board trainingBoard = new Board();
            trainingBoard.emptyBoard();
            trainingBoard.boardCopy(board);
            int[] start_position = new int[] { this.getRow(), this.getColumn() };
            int[] end_position = new int[] { possibleMoves[i][0], possibleMoves[i][1] };
            trainingBoard.movePiece(start_position, end_position);
            if (!trainingBoard.isKingCheck(this.color)){
                possibleMovesTemp[possibleMovesTempLength] = possibleMoves[i];
                possibleMovesTempLength++;
            }
        }

        int[][] possibleMovesFinal = new int[possibleMovesTempLength][2];
        for (int i = 0; i < possibleMovesTempLength; i++){
            possibleMovesFinal[i] = possibleMovesTemp[i];
        }*/

        //this.setNbPossibleMoves(possibleMovesFinal.length);
        this.setNbPossibleMoves(possibleMoves.length);
        return possibleMoves;
    }

    public ArrayList<Position> getPossibleMoves2(Board board) {
        ArrayList<Position> possibleMoves = getPossibleMovesBishop2(board, this.getRow(), this.getColumn(), this.getColor());
        this.setNbPossibleMoves(possibleMoves.size());
        return possibleMoves;
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