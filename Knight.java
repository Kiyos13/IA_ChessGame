import java.util.Arrays;

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
    public int[][] getPossibleMoves(Board board) {
        int knightRow = this.getRow(), knightColumn = this.getColumn();
        int currentRow, currentColumn, currentTargetIsPossible, nbOfPossibleMoves = 0;
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2], currentPath;
        int[][][] paths = movesPaths(knightRow, knightColumn);
        boolean moveAlreadyInArray, firstPathForOnePosition, conditionPathHasEnemyPieces, conditionLastIsKnightColor;
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax;
        Piece.Color knightColor = this.getColor(), enemyColor = this.getEnemyColor(), currentColor;

        for (int possiblePos = 0; possiblePos < 16; possiblePos++) {
            firstPathForOnePosition = (possiblePos % 2 == 0);
            moveAlreadyInArray = false;
            currentPath = paths[possiblePos];
            currentTargetIsPossible = 1;
            for (int pathBox = 0; pathBox < 3; pathBox++) {
                currentRow = currentPath[pathBox][0];
                currentColumn = currentPath[pathBox][1];
                conditionRowMin = (currentRow > -1);
                conditionRowMax = (currentRow <= Piece.maxPosition);
                conditionCoumnMin = (currentColumn > -1);
                conditionColumnMax = (currentColumn <= Piece.maxPosition);

                if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                    currentColor = board.getPieceInBoard(currentRow, currentColumn).getColor();
                    conditionPathHasEnemyPieces = ((pathBox != 2) && (currentColor == enemyColor));
                    conditionLastIsKnightColor = ((pathBox == 2) && (currentColor == knightColor));

                    if (conditionPathHasEnemyPieces || conditionLastIsKnightColor)
                        currentTargetIsPossible = 0;

                    if ((pathBox == 2) && (currentTargetIsPossible == 1)) {
                        if (firstPathForOnePosition)
                            moveAlreadyInArray = true;

                        if (!moveAlreadyInArray) {
                            possibleMoves[nbOfPossibleMoves] = new int[] { currentRow, currentColumn };
                            nbOfPossibleMoves++;
                        }
                    }
                }
            }
        }
        
        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    /********** CONSTRUCTOR **********/
    public Knight(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Knight);
    }
}