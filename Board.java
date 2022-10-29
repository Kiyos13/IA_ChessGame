import java.util.Hashtable;

public class Board {

    /********** VARs **********/
    public static int boardLength = 7;

    /********** DICTs **********/
    public static Hashtable<Integer, String> lettersDict = new Hashtable<Integer, String>();
    public static Hashtable<Piece.Type, Integer> piecesValuesDict = new Hashtable<Piece.Type, Integer>();

    /********** ATTRIBUTs **********/
	private static Piece[][] board;
    private int blackPoints;
    private int whitePoints;
    private Piece.Color winner;

    /********** SETs **********/    
    public void setPieceInBoard(int row, int column, Piece piece) {
        board[row][column] = piece;
    }

    public void setBlackPoints(int points) {
        blackPoints = points;
    }

    public void setWhitePoints(int points) {
        whitePoints = points;
    }

    public void setWinner(Piece.Color winnerColor) {
        winner = winnerColor;
    }

    /********** GETs **********/
    public Piece getPieceInBoard(int row, int column) {
        return board[row][column];
    }

    public static Piece[][] getBoard() {
        return board;
    }

    public int getBlackPoints() {
        return this.blackPoints;
    }

    public int getWhitePoints() {
        return this.whitePoints;
    }

    public Piece.Color getWinner() {
        int blackPts = this.getBlackPoints();
        int whitePts = this.getWhitePoints();

        if (blackPts > whitePts)
            return Piece.Color.Black;
        else if (blackPts < whitePts)
            return Piece.Color.White;
        else
            return Piece.Color.None;
    }

    /********** FUNCTIONs **********/
    /***** Inits *****/
    public void initSizeBoard() {
        this.board = new Piece[this.boardLength + 1][this.boardLength + 1];
    }

    private void initPawns() {
        // Positions
        int blackPawnsRow = 1;
        int whitePawnsRow = boardLength - 1;

        // Pawns
        for (int c = 0; c <= boardLength; c++) {
            setPieceInBoard(blackPawnsRow, c, new Pawn(blackPawnsRow, c, Piece.Color.Black));
            setPieceInBoard(whitePawnsRow, c, new Pawn(whitePawnsRow, c, Piece.Color.White));
        }
    }

    private void initRooks() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int rookFirstColumn = 0;
        int rookLastColumn = boardLength;

        // Rooks
        setPieceInBoard(firstRow, rookFirstColumn, new Rook(firstRow, rookFirstColumn, Piece.Color.Black));
        setPieceInBoard(firstRow, rookLastColumn, new Rook(firstRow, rookLastColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, rookFirstColumn, new Rook(lastRow, rookFirstColumn, Piece.Color.White));
        setPieceInBoard(lastRow, rookLastColumn, new Rook(lastRow, rookLastColumn, Piece.Color.White));
    }

    private void initKnights() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int knightFirstColumn = 1;
        int knightLastColumn = boardLength - 1;

        // Knights
        setPieceInBoard(firstRow, knightFirstColumn, new Knight(firstRow, knightFirstColumn, Piece.Color.Black));
        setPieceInBoard(firstRow, knightLastColumn, new Knight(firstRow, knightLastColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, knightFirstColumn, new Knight(lastRow, knightFirstColumn, Piece.Color.White));
        setPieceInBoard(lastRow, knightLastColumn, new Knight(lastRow, knightLastColumn, Piece.Color.White));
    }

    private void initBishops() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int bishopFirstColumn = 2;
        int bishopLastColumn = boardLength - 2;

        // Bishops
        setPieceInBoard(firstRow, bishopFirstColumn, new Bishop(firstRow, bishopFirstColumn, Piece.Color.Black));
        setPieceInBoard(firstRow, bishopLastColumn, new Bishop(firstRow, bishopLastColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, bishopFirstColumn, new Bishop(lastRow, bishopFirstColumn, Piece.Color.White));
        setPieceInBoard(lastRow, bishopLastColumn, new Bishop(lastRow, bishopLastColumn, Piece.Color.White));
    }

    private void initQueens() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int queenColumn = 3;

        // Queens
        setPieceInBoard(firstRow, queenColumn, new Queen(firstRow, queenColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, queenColumn, new Queen(lastRow, queenColumn, Piece.Color.White));
    }

    private void initKings() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int kingColumn = 4;

        // Kings
        setPieceInBoard(firstRow, kingColumn, new King(firstRow, kingColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, kingColumn, new King(lastRow, kingColumn, Piece.Color.White));
    }

    private void initBlanks() {
        // Positions
        int firstBlankRow = 2;
        int lastBlankRow = boardLength - 2;

        // Blanks
        for (int r = firstBlankRow; r <= lastBlankRow; r++) {
            for (int c = 0; c <= boardLength; c++) {
                setPieceInBoard(r, c, new EmptyPiece(r, c));
            }
        }
    }

    public void initBoard() {
        this.initSizeBoard(); // Size of board
        
        this.initPawns();   // Pawns
        this.initRooks();   // Rooks
        this.initKnights(); // Knights
        this.initBishops(); // Bishops
        this.initQueens();  // Queens
        this.initKings();   // Kings
        this.initBlanks();  // Blanks

        setPieceInBoard(4, 4, new King(4, 4, Piece.Color.Black));
        setPieceInBoard(4, 7, new Rook(4, 7, Piece.Color.Black));
        setPieceInBoard(4, 0, new Rook(4, 0, Piece.Color.Black));

        this.setBlackPoints(0); // BlackPoints
        this.setWhitePoints(0); // WhitePoints
        this.setWinner(Piece.Color.None); // Winner
    }

    public void resetBoard() {
        this.initBoard();
    }

    /*** DICTs ***/
    private void initLettersDict() {
        lettersDict.put(1, "A");
        lettersDict.put(2, "B");
        lettersDict.put(3, "C");
        lettersDict.put(4, "D");
        lettersDict.put(5, "E");
        lettersDict.put(6, "F");
        lettersDict.put(7, "G");
        lettersDict.put(8, "H");
    }

    private void initPiecesValuesDict() {
        piecesValuesDict.put(Piece.Type.Pawn, 1);
        piecesValuesDict.put(Piece.Type.Rook, 5);
        piecesValuesDict.put(Piece.Type.Knight, 3);
        piecesValuesDict.put(Piece.Type.Bishop, 3);
        piecesValuesDict.put(Piece.Type.Queen, 9);
        piecesValuesDict.put(Piece.Type.King, 100);
        piecesValuesDict.put(Piece.Type.None, 0);
    }

    /***** DISPLAYs *****/
    public void displayBoard() {
        Piece[][] board = getBoard();
        Piece currentPiece;
        Piece.Color currentColor = Piece.Color.None;
        Piece.Type currentType = Piece.Type.None;

        System.out.println("\n");
        System.out.println("\t    A\t    B\t    C\t    D\t    E\t    F\t    G\t    H\n");
        System.out.println("\t-----------------------------------------------------------------");
        for (int r = 0; r <= boardLength; r++) {
            System.out.println("\t|       |       |       |       |       |       |       |       |");
            for (int c = 0; c <= boardLength; c++) {
                currentPiece = board[r][c];
                currentColor = currentPiece.getColor();
                currentType = currentPiece.getType();

                if (c == 0)
                    System.out.printf("   %d\t|", r + 1);

                if (currentColor == Piece.Color.White)
                    System.out.printf("\u001B[35m");
                else if (currentColor == Piece.Color.Black)
                    System.out.printf("\u001B[34m");
                
                if (currentType == Piece.Type.Pawn)
                    System.out.printf("   p\t");
                else if (currentType == Piece.Type.Rook)
                    System.out.printf("   r\t");
                else if (currentType == Piece.Type.Knight)
                    System.out.printf("   k\t");
                else if (currentType == Piece.Type.Bishop)
                    System.out.printf("   b\t");
                else if (currentType == Piece.Type.Queen)
                    System.out.printf("   q\t");
                else if (currentType == Piece.Type.King)
                    System.out.printf("   K\t");
                else
                    System.out.printf("   .\t");
                
                System.out.printf("\u001B[37m|");

                if (c == boardLength)
                    System.out.printf("   %d", r + 1);
            }
            if (r < boardLength) {
                System.out.println("\n\t|       |       |       |       |       |       |       |       |");
                System.out.println("\t--------|-------|-------|-------|-------|-------|-------|-------|");
            }
            else {
                System.out.println("\n\t|       |       |       |       |       |       |       |       |");
                System.out.println("\t-----------------------------------------------------------------");
                System.out.println("\n\t    A\t    B\t    C\t    D\t    E\t    F\t    G\t    H");
            }
        }
        System.out.println("\n");
    }


    /***** FUNCs *****/
    public void movePiece(int[] startPosition, int[] endPosition) {
        Piece pieceToMove = this.getPieceInBoard(startPosition[0], startPosition[1]);
        Piece.Type typePieceToMove = pieceToMove.getType();
        Piece.Color colorPieceToMove = pieceToMove.getColor();

        // Check if the piece to move is a pawn, a rook or a king to change their attributes
        if (typePieceToMove == Piece.Type.Pawn)
            ((Pawn) pieceToMove).setIsFirstMove(false);
        else if (typePieceToMove == Piece.Type.Rook)
            ((Rook) pieceToMove).setHasAlreadyMoved(true);
        else if (typePieceToMove == Piece.Type.King)
            ((King) pieceToMove).setHasAlreadyMoved(true);

        // Get infos on the piece that is going to be taken
        Piece takenPiece = this.getPieceInBoard(endPosition[0], endPosition[1]);
        Piece.Type takenPieceType = takenPiece.getType();
        if (colorPieceToMove == Piece.Color.White)
            this.setWhitePoints(this.getWhitePoints() + piecesValuesDict.get(takenPieceType));
        else if (colorPieceToMove == Piece.Color.Black)
            this.setBlackPoints(this.getBlackPoints() + piecesValuesDict.get(takenPieceType));
        
        // Move piece to target position
        pieceToMove.setRow(endPosition[0]);
        pieceToMove.setColumn(endPosition[1]);
        this.setPieceInBoard(endPosition[0], endPosition[1], pieceToMove);

        // Move rook if castling
        if ((typePieceToMove == Piece.Type.King) && (takenPieceType == Piece.Type.Rook)) {
            takenPiece.setRow(startPosition[0]);
            takenPiece.setColumn(startPosition[1]);
            this.setPieceInBoard(startPosition[0], startPosition[1], pieceToMove);
        }
        else {
            // Put empty piece to start position
            this.setPieceInBoard(startPosition[0], startPosition[1], new EmptyPiece(endPosition[0], endPosition[1]));
        }
    }

    public boolean gameIsFinished() {
        int blackPts = this.getBlackPoints();
        int whitePts = this.getWhitePoints();

        if ((blackPts >= 100) || (whitePts >= 100))
            return true;
        return false;

        /* TODO :
         * La partie peut être nulle si une position identique est sur le point de survenir ou vient de survenir au moins trois fois sur l’échiquier.
         * La partie peut être nulle si chaque joueur a joué au moins les 50 derniers coups consécutifs sans aucun mouvement de pion ni aucune prise.
         * La partie est nulle quand une position est telle qu’aucun joueur ne peut mater le roi adverse avec une série de coups légaux.
         * La partie est nulle lorsque le joueur ayant le trait n’a aucun coup légal et que son roi n’est pas en échec.
         */
    }

    /********** CONSTRUCTOR **********/
    public Board() {
        this.initLettersDict();
        this.initPiecesValuesDict();
        this.initBoard();
    }
}