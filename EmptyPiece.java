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
    public int[][] getPossibleMoves(Board board) {
        int[][] possibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];
        this.setNbPossibleMoves(0);
        return possibleMoves;  
    }

    /********** CONSTRUCTOR **********/
    public EmptyPiece(int row, int column) {
        super(row, column, Piece.Color.None, Piece.Type.None);
        setIsControlled(false);
    }
}