import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean hasBeenCheck;
    private boolean hasAlreadyMoved;
    private boolean isControlled; // TODO : fct to detect if a king is controlled by another piece (for casting)

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
                if (currentColor == kingColor || board.isKingCheckAtCoordinates(rKing, cKing, kingColor))
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

    /*public int[][] getPossibleMovesCastling(Board board, int kingRow, int kingColumn, Piece.Color kingColor) {
        Piece[] rooks = new Piece[2];
        int nbOfPossibleMoves = 0;
        int kingIsWhiteInt = (kingColor == Piece.Color.White) ? 1 : 0;
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];

        this.setIsControlled(this.pieceIsControlled(board)); // Set isControlled attribute for the king
        boolean kingIsNotControlled = (!this.getIsControlled()), kingHasNotMoved = (!this.getHasAlreadyMoved());

        if (kingIsNotControlled && kingHasNotMoved) {
            rooks = new Piece[] { board.getPieceInBoard(7 * kingIsWhiteInt, 0), board.getPieceInBoard(7 * kingIsWhiteInt, Piece.maxPosition) };

            // if one of the rook has not moved since the start of the game
            for (int rooksCounter = 0; rooksCounter < 2; rooksCounter++) {
                if (rooks[rooksCounter].getType() == Piece.Type.Rook) {
                    if (!((Rook) rooks[rooksCounter]).getHasAlreadyMoved()) {
                        int sideMove = (rooksCounter == 0) ? -1 : 1;
                        Piece[] piecesBtwKingAndRook = new Piece[3];
                        int piecesBtwKingAndRookCounter = 0;

                        int rookColumn = rooks[rooksCounter].getColumn();
                        Piece.Type supposedToBeRookType = board.getPieceInBoard(kingRow, rookColumn).getType();
                        if (supposedToBeRookType == Piece.Type.Rook) {
                            Piece rookToSwitchWith = board.getPieceInBoard(kingRow, rookColumn);
                            ((Rook) rookToSwitchWith).setIsControlled(rookToSwitchWith.pieceIsControlled(board)); // Set isControlled attribute for the rook
                            boolean rookIsNotControlled = (!((Rook) rookToSwitchWith).getIsControlled());
                            boolean allBtwPiecesAreEmpty = true, allBtwPiecesAreNotControlled = true;
                            
                            for (int columnsBtw = kingColumn + sideMove; columnsBtw != rooks[rooksCounter].getColumn(); columnsBtw = columnsBtw + sideMove) {
                                piecesBtwKingAndRook[piecesBtwKingAndRookCounter] = board.getPieceInBoard(kingRow, columnsBtw);
                                if (piecesBtwKingAndRook[piecesBtwKingAndRookCounter].getType() != Piece.Type.None)
                                    allBtwPiecesAreEmpty = false;
                                else {
                                    Piece.Type supposedToBeNoneType = piecesBtwKingAndRook[piecesBtwKingAndRookCounter].getType();
                                    if (supposedToBeNoneType == Piece.Type.None) {
                                        Piece emptyPieceInBtw = piecesBtwKingAndRook[piecesBtwKingAndRookCounter];
                                        ((EmptyPiece) emptyPieceInBtw).setIsControlled(emptyPieceInBtw.pieceIsControlled(board)); // Set isControlled attribute for the empty pieces
                                        if (((EmptyPiece) emptyPieceInBtw).getIsControlled())
                                            allBtwPiecesAreNotControlled = false;
                                    }
                                }
                                piecesBtwKingAndRookCounter++;
                            }
                            if (allBtwPiecesAreEmpty && allBtwPiecesAreNotControlled && rookIsNotControlled) {
                                possibleMoves[nbOfPossibleMoves] = new int[] { rooks[rooksCounter].getRow(), rooks[rooksCounter].getColumn() };
                                nbOfPossibleMoves++;
                            }
                        }
                    }
                }
            }
        }

        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }*/
    
    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board) {
        Color kingColor = this.getColor();
        int kingRow = this.getRow(), kingColumn = this.getColumn();
        int nbOfPossibleMovesStep1 = 0, nbOfPossibleMovesStep2 = 0,  nbOfPossibleMoves = 0;
        int[][] possibleMovesClassic = new int[Piece.maxPosition * Piece.maxPosition][2];
        //int[][] possibleMovesCastling = new int[Piece.maxPosition * Piece.maxPosition][2];

        possibleMovesClassic = getPossibleMovesClassic(board, kingRow, kingColumn, kingColor);
        nbOfPossibleMovesStep1 = this.getNbPossibleMoves();
        //possibleMovesCastling = getPossibleMovesCastling(board, kingRow, kingColumn, kingColor);
        nbOfPossibleMovesStep2 = 0;//this.getNbPossibleMoves();
        nbOfPossibleMoves = nbOfPossibleMovesStep1 + nbOfPossibleMovesStep2;
        int[][] possibleMoves = new int[nbOfPossibleMoves][2];

        for (int i = 0; i < nbOfPossibleMoves; i++) {
            if (i < nbOfPossibleMovesStep1)
                possibleMoves[i] = possibleMovesClassic[i];
            else{
                //possibleMoves[i] = possibleMovesCastling[i - nbOfPossibleMovesStep1];
            }
        }
        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    @Override
    public ArrayList<Position> getPossibleMoves2(Board board) {
        Color kingColor = this.getColor();
        int kingRow = this.getRow(), kingColumn = this.getColumn();
        ArrayList<Position> possibleMovesClassic = new ArrayList<>();
        //ArrayList<Position> possibleMovesCastling = new ArrayList<>();
        ArrayList<Position> possibleMoves = new ArrayList<>();

        //possibleMovesCastling = getPossibleMovesCastling2(board, kingRow, kingColumn, kingColor);

        for (Position pos: possibleMovesClassic){
            possibleMoves.add(pos);
        }

        /*for (Position pos: possibleMovesCastling){
            possibleMoves.add(pos);
        }*/

        this.setNbPossibleMoves(possibleMoves.size());
        return possibleMoves;
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