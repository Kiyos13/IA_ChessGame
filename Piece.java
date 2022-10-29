public abstract class Piece {

    /********** VARs **********/
    public static int minPosition = 0;
    public static int maxPosition = 7;

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
    private int row;
	private int column;
    private Color color;
    private Color enemyColor;
    private Type type;
    private int nbPossibleMoves;

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
        int[][] currentPossibleMoves = new int[maxPosition * maxPosition][2];
        Color enemyColor = this.getEnemyColor(), currentColor;
        Piece currentPiece;

        for (int row = 0; row < maxPosition; row++) {
            for (int column = 0; column < maxPosition; column++) {
                currentPiece = board.getPieceInBoard(row, column);
                currentColor = currentPiece.getColor();
                if (currentColor == enemyColor) {
                    currentPossibleMoves = currentPiece.getPossibleMoves(board);
                    for (int nbMoves = 0; nbMoves < currentPossibleMoves.length; nbMoves++) {
                        if ((currentPossibleMoves[nbMoves][0] == targetRow) && (currentPossibleMoves[nbMoves][1] == targetColumn))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /********** ABSTRACTs **********/
    public abstract int[][] getPossibleMoves(Board board);

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