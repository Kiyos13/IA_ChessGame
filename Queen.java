import java.util.ArrayList;

public class Queen extends Piece  {

    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        Piece.Color queenColor = this.getColor();
        int queenRow = this.getRow(), queenColumn = this.getColumn();
        int nbOfPossibleMovesStep1 = 0, nbOfPossibleMovesStep2 = 0, nbOfPossibleMoves = 0;
        int[][] possibleMovesRook = new int[Piece.maxPosition * Piece.maxPosition][2];
        int[][] possibleMovesBishop = new int[Piece.maxPosition * Piece.maxPosition][2];

        possibleMovesRook = Rook.getPossibleMovesRook(board, queenRow, queenColumn, queenColor);
        nbOfPossibleMovesStep1 = possibleMovesRook.length;
        possibleMovesBishop = Bishop.getPossibleMovesBishop(board, queenRow, queenColumn, queenColor);
        nbOfPossibleMovesStep2 = possibleMovesBishop.length;

        nbOfPossibleMoves = nbOfPossibleMovesStep1 + nbOfPossibleMovesStep2;
        int[][] possibleMoves = new int[nbOfPossibleMoves][2];

        for (int i = 0; i < nbOfPossibleMoves; i++) {
            if (i < nbOfPossibleMovesStep1)
                possibleMoves[i] = possibleMovesRook[i];
            else
                possibleMoves[i] = possibleMovesBishop[i - nbOfPossibleMovesStep1];
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
    public Queen(int row, int column, Color color) {
        super(row, column, color, Piece.Type.Queen);
    }

    public Queen copy(){
        Queen q = new Queen(this.row, this.column, this.color);
        return q;
    }
}