
public class Knight extends Piece {
    
    /********** FUNCTIONs **********/
    public int[][][] movesPaths(int knightRow, int knightColumn) {
        int[][][] paths = {
            { { knightRow,     knightColumn - 1 }, { knightRow,     knightColumn - 1 }, { knightRow - 1, knightColumn - 2 } },
            { { knightRow - 1, knightColumn     }, { knightRow - 1, knightColumn - 1 }, { knightRow - 1, knightColumn - 2 } },
            { { knightRow,     knightColumn - 1 }, { knightRow - 1, knightColumn - 1 }, { knightRow - 2, knightColumn - 2 } },
            { { knightRow - 1, knightColumn     }, { knightRow - 2, knightColumn     }, { knightRow - 2, knightColumn - 1 } },
            { { knightRow - 1, knightColumn     }, { knightRow - 2, knightColumn     }, { knightRow - 2, knightColumn + 1 } },
            { { knightRow,     knightColumn + 1 }, { knightRow - 1, knightColumn + 1 }, { knightRow - 2, knightColumn + 1 } },
            { { knightRow - 1, knightColumn     }, { knightRow - 1, knightColumn + 1 }, { knightRow - 1, knightColumn + 2 } },
            { { knightRow,     knightColumn + 1 }, { knightRow,     knightColumn + 2 }, { knightRow - 1, knightColumn + 2 } },
            { { knightRow,     knightColumn + 1 }, { knightRow,     knightColumn + 2 }, { knightRow + 1, knightColumn + 2 } },
            { { knightRow + 1, knightColumn     }, { knightRow + 1, knightColumn + 1 }, { knightRow + 1, knightColumn + 2 } },
            { { knightRow,     knightColumn + 1 }, { knightRow + 1, knightColumn + 1 }, { knightRow + 2, knightColumn + 1 } },
            { { knightRow + 1, knightColumn     }, { knightRow + 2, knightColumn     }, { knightRow + 2, knightColumn + 1 } },
            { { knightRow + 1, knightColumn     }, { knightRow + 2, knightColumn     }, { knightRow + 2, knightColumn - 1 } },
            { { knightRow,     knightColumn - 1 }, { knightRow + 1, knightColumn - 1 }, { knightRow + 2, knightColumn - 1 } },
            { { knightRow + 1, knightColumn     }, { knightRow + 1, knightColumn - 1 }, { knightRow + 1, knightColumn - 2 } },
            { { knightRow,     knightColumn - 1 }, { knightRow,     knightColumn - 2 }, { knightRow + 1, knightColumn - 2 } }

        };

        return paths;
    }
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        int knightRow = this.getRow(), knightColumn = this.getColumn();
        Piece.Color knightColor = this.color;
        Color currentColor;
        int nbOfPossibleMoves = 0;
        int[][] possibleMovesTemp = new int[Piece.maxPosition * Piece.maxPosition][2];
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax, currentTargetIsPossible;

        // Normal moves
        int[][] possiblePositions = {
            {knightRow - 2, knightColumn - 1}, {knightRow - 2, knightColumn + 1},
            {knightRow - 1, knightColumn + 2}, {knightRow + 1, knightColumn + 2},
            {knightRow + 2, knightColumn + 1}, {knightRow + 2, knightColumn - 1},
            {knightRow + 1 , knightColumn - 2}, {knightRow - 1, knightColumn - 2}
        };

        for (int position = 0; position <= Piece.maxPosition; position++) {
            currentTargetIsPossible = true;
            conditionRowMin = (possiblePositions[position][0] > -1);
            conditionRowMax = (possiblePositions[position][0] <= Piece.maxPosition);
            conditionCoumnMin = (possiblePositions[position][1] > -1);
            conditionColumnMax = (possiblePositions[position][1] <= Piece.maxPosition);
            if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                currentColor = board.getPieceInBoard(possiblePositions[position][0], possiblePositions[position][1]).getColor();
                if (currentColor != knightColor) {
                    possibleMovesTemp[nbOfPossibleMoves] = new int[] { possiblePositions[position][0], possiblePositions[position][1] };
                    nbOfPossibleMoves++;
                }
            }
        }

        int[][] possibleMoves = new int[nbOfPossibleMoves][2];

        for (int i = 0; i < nbOfPossibleMoves; i++)
            possibleMoves[i] = possibleMovesTemp[i];
            
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
    public Knight(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Knight);
    }

    public Knight copy(){
        Knight k = new Knight(this.row, this.column, this.color);
        return k;
    }
}