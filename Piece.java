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
        ArrayList<Position> currentPossibleMoves = new ArrayList<>();
        Color enemyColor = this.getEnemyColor(), currentColor;
        Piece currentPiece;

        for (int row = 0; row < maxPosition; row++) {
            for (int column = 0; column < maxPosition; column++) {
                currentPiece = board.getPieceInBoard(row, column);
                currentColor = currentPiece.getColor();
                if (currentColor == enemyColor) {
                    currentPossibleMoves = currentPiece.getPossibleMoves2(board);
                    for (int nbMoves = 0; nbMoves < currentPossibleMoves.size(); nbMoves++) {
                        if ((currentPossibleMoves.get(nbMoves).r == targetRow) && (currentPossibleMoves.get(nbMoves).c == targetColumn))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /********** ABSTRACTs **********/
    public abstract int[][] getPossibleMoves(Board board);
    public abstract ArrayList<Position> getPossibleMoves2(Board board);

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