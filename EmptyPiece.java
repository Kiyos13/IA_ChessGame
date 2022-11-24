import java.util.ArrayList;

public class EmptyPiece extends Piece {

    /********** ATTRIBUTEs **********/
    private boolean isControlled; // TODO : fct to detect if an empty piece is controlled by another piece (for castling)

    /********** SETs **********/
    public void setIsControlled(boolean isC) {
        isControlled = isC;
    }

    /********** GETs **********/
    public boolean getIsControlled() {
        return isControlled;
    }

    /********** ABSTRACTs **********/
    @Override
    public int[][] getPossibleMoves(Board board, boolean verifyCheck) {
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];
        this.setNbPossibleMoves(0);
        return possibleMoves;  
    }

    public ArrayList<Position> getPossibleMoves2(Board board) {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        this.setNbPossibleMoves(0);
        return possibleMoves;  
    }   

    /********** CONSTRUCTOR **********/
    public EmptyPiece(int row, int column) {
        super(row, column, Piece.Color.None, Piece.Type.None);
        setIsControlled(false);
    }
}