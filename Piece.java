import java.util.ArrayList;

public abstract class Piece {

    /********** VARs **********/
    public static int minPosition = 0;
    public static int maxPosition = 7;
    public int val = 2;

    /********** ENUMs **********/
    enum Color {
        Black,
        White,
        None
    }

    enum Type {
        Pawn,
        Rook,
        Knight,
        Bishop,
        Queen,
        King,
        None
    }
    
    /********** ATTRIBUTEs **********/
    public int row;
	public int column;
    public Color color;
    public Color enemyColor;
    public Type type;
    public int nbPossibleMoves;

    /********** SETs **********/
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setNbPossibleMoves(int nbPossibleMoves) {
        this.nbPossibleMoves = nbPossibleMoves;
    }


    /********** GETs **********/
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Color getColor() {
        return color;
    }

    public Color getEnemyColor() {
        return enemyColor;
    }

    public Type getType() {
        return type;
    }

    public int getValue(){
        if (this.getType() == Piece.Type.Bishop){
            return 3;
        }
        else if(this.getType() == Piece.Type.King){
            return 0;
        }
        else if(this.getType() == Piece.Type.Knight){
            return 3;
        }
        else if(this.getType() == Piece.Type.Pawn){
            return 1;
        }
        else if(this.getType() == Piece.Type.Queen){
            return 9;
        }
        else if(this.getType() == Piece.Type.Rook){
            return 5;
        }
        else{
            return 0;
        }
    }

    public int getNbPossibleMoves() {
        return nbPossibleMoves;
    }

    /********** FUNCTIONs **********/
    public boolean isFirstRow() {
        return row == minPosition;
    }

    public boolean isFirstColumn() {
        return column == minPosition;
    }

    public boolean isLastRow() {
        return row == maxPosition;
    }

    public boolean isLastColumn() {
        return column == maxPosition;
    }

    public boolean pieceIsControlled(Board board) {
        int targetRow = this.getRow(), targetColumn = this.getColumn();
        int[][] currentPossibleMoves = new int[(Board.boardLength + 1) * (Board.boardLength + 1)][2];
        Color enemyColor = this.getEnemyColor(), currentColor;
        Piece currentPiece;

        for (int row = 0; row < maxPosition; row++) {
            for (int column = 0; column < maxPosition; column++) {
                currentPiece = board.getPieceInBoard(row, column);
                currentColor = currentPiece.getColor();
                if (currentColor == enemyColor) {
                    currentPossibleMoves = currentPiece.getPossibleMoves(board, true);

                    for (int nbMoves = 0; nbMoves < currentPossibleMoves.length; nbMoves++) {
                        if ((currentPossibleMoves[nbMoves][0] == targetRow) && (currentPossibleMoves[nbMoves][1] == targetColumn))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public int[][] getPossibleMovesWithVerifyCheck(Board board, int[][] possibleMoves){
        int possibleMovesLength = possibleMoves.length;
        int[][] possibleMovesTemp = new int[possibleMovesLength][2];
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
        }

        return possibleMovesFinal;  
    }

    /********** ABSTRACTs **********/
    public abstract int[][] getPossibleMoves(Board board, boolean verifyCheck);

    /********** CONSTRUCTOR **********/
    public Piece(int row, int column, Color color, Type type) {
        this.setRow(row);
        this.setColumn(column);
        this.setColor(color);
        Color enemyC = Color.None;
        if (color == Color.Black)
            enemyC = Color.White;
        else if (color == Color.White)
            enemyC = Color.Black;
        this.setEnemyColor(enemyC);
        this.setType(type);
    }
}