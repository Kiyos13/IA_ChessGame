public class Pawn extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean isFirstMove;

    /********** SETs **********/
    public void setIsFirstMove(boolean isFirstM) {
        isFirstMove = isFirstM;
    }

    /********** GETs **********/
    public boolean getIsFirstMove() {
        return isFirstMove;
    }

    /********** SUB_FUNCs **********/
    public int[][] getPossibleMovesStraight(Board board, int pawnRow, int pawnColumn, int gap) {
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax, targetIsEmpty;
        boolean isPawnFirstMove = this.getIsFirstMove(), firstDistanceIsPossible = false;
        int nbOfPossibleMoves = 0;
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];

        // Loop for one or two step(s) down / up
        for (int distance = 1; distance < 3; distance++) {
            conditionRowMin = (pawnRow + gap * distance > -1);
            conditionRowMax = (pawnRow + gap * distance <= Piece.maxPosition);
            conditionCoumnMin = (pawnColumn > -1);
            conditionColumnMax = (pawnColumn <= Piece.maxPosition);

            if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                targetIsEmpty = (board.getPieceInBoard(pawnRow + gap * distance, pawnColumn).getColor() == Piece.Color.None);

                if ((((distance == 2) && (isPawnFirstMove) && (firstDistanceIsPossible)) || (distance == 1)) && targetIsEmpty) {
                    possibleMoves[nbOfPossibleMoves] = new int[] { pawnRow + gap * distance, pawnColumn };
                    nbOfPossibleMoves++;
                    if (distance == 1)
                        firstDistanceIsPossible = true;
                }
            }
        }

        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    public int[][] getPossibleMovesSideways(Board board, int pawnRow, int pawnColumn, int gap) {
        boolean conditionRowMin, conditionRowMax, conditionCoumnMin, conditionColumnMax, targetIsEmpty;
        int nbOfPossibleMoves = 0;
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];

        // Loop for sideway right (+1) and left (-1)
        for (int sidewayGap = -1; sidewayGap <= 1; sidewayGap += 2) {
            conditionRowMin = (pawnRow + gap > -1);
            conditionRowMax = (pawnRow + gap <= Piece.maxPosition);
            conditionCoumnMin = (pawnColumn + sidewayGap > -1);
            conditionColumnMax = (pawnColumn + sidewayGap <= Piece.maxPosition);
            if (conditionRowMin && conditionRowMax && conditionCoumnMin && conditionColumnMax) {
                if (board.getPieceInBoard(pawnRow + gap, pawnColumn + sidewayGap).getColor() == Piece.Color.White) {
                    possibleMoves[nbOfPossibleMoves] = new int[] { pawnRow + gap, pawnColumn + sidewayGap };
                    nbOfPossibleMoves++;
                }
            }
        }

        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board) {
        Piece.Color pawnColor = this.getColor();
        int pawnRow = this.getRow(), pawnColumn = this.getColumn();
        int nbOfPossibleMovesStep1 = 0, nbOfPossibleMovesStep2 = 0, nbOfPossibleMoves = 0, gap = 1;
        int[][] possibleMovesStraight = new int[Piece.maxPosition * Piece.maxPosition][2];
        int[][] possibleMovesSideways = new int[Piece.maxPosition * Piece.maxPosition][2];
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];

        if (pawnColor == Piece.Color.White)
            gap = -1;


        possibleMovesStraight = getPossibleMovesStraight(board, pawnRow, pawnColumn, gap);
        nbOfPossibleMovesStep1 = this.getNbPossibleMoves();
        possibleMovesSideways = getPossibleMovesSideways(board, pawnRow, pawnColumn, gap);
        nbOfPossibleMovesStep2 = this.getNbPossibleMoves();

        nbOfPossibleMoves = nbOfPossibleMovesStep1 + nbOfPossibleMovesStep2;

        for (int i = 0; i < nbOfPossibleMoves; i++) {
            if (i < nbOfPossibleMovesStep1)
                possibleMoves[i] = possibleMovesStraight[i];
            else
                possibleMoves[i] = possibleMovesSideways[i - nbOfPossibleMovesStep1];
        }

        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    /********** CONSTRUCTOR **********/
    public Pawn(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Pawn);
        setIsFirstMove(true);
    }
}