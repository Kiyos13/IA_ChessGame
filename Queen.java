import java.util.ArrayList;

public class Queen extends Piece  {
    public int val = 9;

    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board) {
        Piece.Color queenColor = this.getColor();
        int queenRow = this.getRow(), queenColumn = this.getColumn();
        int nbOfPossibleMovesStep1 = 0, nbOfPossibleMovesStep2 = 0, nbOfPossibleMoves = 0;
        int[][] possibleMovesRook = new int[Piece.maxPosition * Piece.maxPosition][2];
        int[][] possibleMovesBishop = new int[Piece.maxPosition * Piece.maxPosition][2];
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];

        possibleMovesRook = Rook.getPossibleMovesRook(board, queenRow, queenColumn, queenColor);
        nbOfPossibleMovesStep1 = possibleMovesRook.length;
        possibleMovesBishop = Bishop.getPossibleMovesBishop(board, queenRow, queenColumn, queenColor);
        nbOfPossibleMovesStep2 = possibleMovesBishop.length;

        nbOfPossibleMoves = nbOfPossibleMovesStep1 + nbOfPossibleMovesStep2;

        for (int i = 0; i < nbOfPossibleMoves; i++) {
            if (i < nbOfPossibleMovesStep1)
                possibleMoves[i] = possibleMovesRook[i];
            else
                possibleMoves[i] = possibleMovesBishop[i - nbOfPossibleMovesStep1];
        }
        
        this.setNbPossibleMoves(nbOfPossibleMoves);
        return possibleMoves;
    }

    @Override
    public ArrayList<Position> getPossibleMoves2(Board board) {
        Piece.Color queenColor = this.getColor();
        int queenRow = this.getRow(), queenColumn = this.getColumn();
        int nbOfPossibleMovesStep1 = 0, nbOfPossibleMovesStep2 = 0, nbOfPossibleMoves = 0;
        ArrayList<Position> possibleMovesRook = new ArrayList<>();
        ArrayList<Position> possibleMovesBishop = new ArrayList<>();
        ArrayList<Position> possibleMoves = new ArrayList<>();

        possibleMovesRook = Rook.getPossibleMovesRook2(board, queenRow, queenColumn, queenColor);
        possibleMovesBishop = Bishop.getPossibleMovesBishop2(board, queenRow, queenColumn, queenColor);

        nbOfPossibleMoves = nbOfPossibleMovesStep1 + nbOfPossibleMovesStep2;

        for (Position pos: possibleMovesBishop){
            possibleMoves.add(pos);
        }
        for (Position pos: possibleMovesRook){
            possibleMoves.add(pos);
        }
        this.setNbPossibleMoves(possibleMoves.size());
        return possibleMoves;
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