import java.util.Hashtable;

public class Board {

    /********** VARs **********/
    public static int boardLength = 7;

    /********** DICTs **********/
    public static Hashtable<Integer, String> lettersDict = new Hashtable<Integer, String>();
    public static Hashtable<Piece.Type, Integer> piecesValuesDict = new Hashtable<Piece.Type, Integer>();

    /********** ATTRIBUTs **********/
	private Piece[][] board;
    private int blackPoints;
    private int whitePoints;
    private Piece.Color winner;
    public Piece.Color currentColor;

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

    public Piece[][] getBoard() {
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
        this.board = new Piece[Board.boardLength + 1][Board.boardLength + 1];
    }

    private void initPawns() {
        // Positions
        int blackPawnsRow = boardLength - 1;
        int whitePawnsRow = 1;

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
        setPieceInBoard(firstRow, rookFirstColumn, new Rook(firstRow, rookFirstColumn, Piece.Color.White));
        setPieceInBoard(firstRow, rookLastColumn, new Rook(firstRow, rookLastColumn, Piece.Color.White));
        setPieceInBoard(lastRow, rookFirstColumn, new Rook(lastRow, rookFirstColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, rookLastColumn, new Rook(lastRow, rookLastColumn, Piece.Color.Black));
    }

    private void initKnights() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int knightFirstColumn = 1;
        int knightLastColumn = boardLength - 1;

        // Knights
        setPieceInBoard(firstRow, knightFirstColumn, new Knight(firstRow, knightFirstColumn, Piece.Color.White));
        setPieceInBoard(firstRow, knightLastColumn, new Knight(firstRow, knightLastColumn, Piece.Color.White));
        setPieceInBoard(lastRow, knightFirstColumn, new Knight(lastRow, knightFirstColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, knightLastColumn, new Knight(lastRow, knightLastColumn, Piece.Color.Black));
    }

    private void initBishops() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int bishopFirstColumn = 2;
        int bishopLastColumn = boardLength - 2;

        // Bishops
        setPieceInBoard(firstRow, bishopFirstColumn, new Bishop(firstRow, bishopFirstColumn, Piece.Color.White));
        setPieceInBoard(firstRow, bishopLastColumn, new Bishop(firstRow, bishopLastColumn, Piece.Color.White));
        setPieceInBoard(lastRow, bishopFirstColumn, new Bishop(lastRow, bishopFirstColumn, Piece.Color.Black));
        setPieceInBoard(lastRow, bishopLastColumn, new Bishop(lastRow, bishopLastColumn, Piece.Color.Black));
    }

    private void initQueens() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int queenColumn = 4;

        // Queens
        setPieceInBoard(firstRow, queenColumn, new Queen(firstRow, queenColumn, Piece.Color.White));
        setPieceInBoard(lastRow, queenColumn, new Queen(lastRow, queenColumn, Piece.Color.Black));
    }

    private void initKings() {
        // Positions
        int firstRow = 0;
        int lastRow = boardLength;
        int kingColumn = 3;

        // Kings
        setPieceInBoard(firstRow, kingColumn, new King(firstRow, kingColumn, Piece.Color.White));
        setPieceInBoard(lastRow, kingColumn, new King(lastRow, kingColumn, Piece.Color.Black));
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

    public void emptyBoard() {
        // Blanks
        for (int r = 0; r <= boardLength; r++) {
            for (int c = 0; c <= boardLength; c++) {
                setPieceInBoard(r, c, new EmptyPiece(r, c));
            }
        }
    }

    public void initBoard() {
        //this.initSizeBoard(); // Size of board
        
        this.initPawns();   // Pawns
        this.initRooks();   // Rooks
        this.initKnights(); // Knights
        this.initBishops(); // Bishops
        this.initQueens();  // Queens
        this.initKings();   // Kings
        this.initBlanks();  // Blanks
        
        this.setBlackPoints(0); // BlackPoints
        this.setWhitePoints(0); // WhitePoints
        this.setWinner(Piece.Color.None); // Winner
    }

    public void resetBoard() {
        this.initBoard();
    }

    /*** DICTs ***/
    private void initLettersDict() {
        lettersDict.put(1, "h");
        lettersDict.put(2, "g");
        lettersDict.put(3, "f");
        lettersDict.put(4, "e");
        lettersDict.put(5, "d");
        lettersDict.put(6, "c");
        lettersDict.put(7, "b");
        lettersDict.put(8, "a");
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
        System.out.println("\t    H\t    G\t    F\t    E\t    D\t    C\t    B\t    A\n");
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
                    System.out.printf("\u001B[31m");
                
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
                System.out.println("\t    H\t    G\t    F\t    E\t    D\t    C\t    B\t    A\n");
            }
        }
        System.out.println("\n");
    }


    /***** FUNCs *****/
    public void movePieceArena(String move) {
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(2, 4);

        int[] startPositionInt = {-1, -1};
        int[] endPositionInt = {-1, -1};

        for (int i = 1; i <= boardLength + 1; i++) {
            if (startPosition.substring(0, 1).equals(lettersDict.get(i)))
                startPositionInt[1] = i - 1;

            if (endPosition.substring(0, 1).equals(lettersDict.get(i)))
                endPositionInt[1] = i - 1;
        }

        startPositionInt[0] = Integer.parseInt(startPosition.substring(1, 2)) - 1;
        endPositionInt[0] = Integer.parseInt(endPosition.substring(1, 2)) - 1;

        System.out.printf("On a : %s -> %s\n", startPosition, endPosition);
        System.out.printf("On a : %s %s -> %s %s\n", startPositionInt[0], startPositionInt[1], endPositionInt[0], endPositionInt[1]);

        boolean startPositionLineOk = (startPositionInt[0] >= 0) && (startPositionInt[0] <= 7);
        boolean startPositionColumnOk = (startPositionInt[1] >= 0) && (startPositionInt[1] <= 7);
        boolean endPositionLineOk = (endPositionInt[0] >= 0) && (endPositionInt[0] <= 7);
        boolean endPositionColumnOk = (endPositionInt[1] >= 0) && (endPositionInt[1] <= 7);
        boolean isCastling = false;

        //Check if it is castling
        if (getPieceInBoard(startPositionInt[0], startPositionInt[1]).getType() == Piece.Type.King
            && (startPositionInt[1] - endPositionInt[1] - 2 == 0 
            || startPositionInt[1] - endPositionInt[1] + 2 == 0)){
                isCastling = true;
                int row = 0;
                if (getPieceInBoard(startPositionInt[0], startPositionInt[1]).getColor() == Piece.Color.Black)
                    row = 7;

                //Small castling
                if (endPositionInt[1] == 1){
                    this.movePiece(startPositionInt, endPositionInt);
                    int[] startPositionRook = { row, 0 };
                    int[] endPositionRook = { row, 2 };
                    this.movePiece(startPositionRook, endPositionRook);
                }
                
                //Big castling
                else if (endPositionInt[1] == 5){
                    this.movePiece(startPositionInt, endPositionInt);
                    int[] startPositionRook = { row, 7 };
                    int[] endPositionRook = { row, 4 };
                    this.movePiece(startPositionRook, endPositionRook);
                }

        }

        if (startPositionColumnOk && startPositionLineOk && endPositionColumnOk && endPositionLineOk && !isCastling){
            this.movePiece(startPositionInt, endPositionInt);
        }

        if (move.length() == 5) {
            Piece promotedPiece = null;
            String promotedLetter = move.substring(4, 5);

            Piece.Color colorPiece = this.getPieceInBoard(endPositionInt[0], endPositionInt[1]).getColor();

            if (promotedLetter.equals("p"))
                promotedPiece = new Pawn(endPositionInt[0], endPositionInt[1], colorPiece);
            else if (promotedLetter.equals("r"))
                promotedPiece = new Rook(endPositionInt[0], endPositionInt[1], colorPiece);
            else if (promotedLetter.equals("k"))
                promotedPiece = new Knight(endPositionInt[0], endPositionInt[1], colorPiece);
            else if (promotedLetter.equals("b"))
                promotedPiece = new Bishop(endPositionInt[0], endPositionInt[1], colorPiece);
            else if (promotedLetter.equals("q"))
                promotedPiece = new Queen(endPositionInt[0], endPositionInt[1], colorPiece);

            if (promotedPiece != null)
                this.setPieceInBoard(endPositionInt[0], endPositionInt[1], promotedPiece);
        }
    }

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
            this.setPieceInBoard(startPosition[0], startPosition[1], new EmptyPiece(startPosition[0], startPosition[1]));
        }
        
        //Change the color of the current player
        if (this.currentColor == Piece.Color.White){
            this.currentColor = Piece.Color.Black;
        }
        else{
            this.currentColor = Piece.Color.White;
        }
    }

    public boolean gameIsFinished() {
        if (isKingCheckMate(currentColor))
            return true;
        return false;
    }

    public void invisibleKing(Position pos){
        Piece kingPiece = getPieceInBoard(pos.r, pos.c);
        kingPiece.setType(Piece.Type.None);
    }

    public void visibleKing(Position pos){
        Piece kingPiece = getPieceInBoard(pos.r, pos.c);
        kingPiece.setType(Piece.Type.King);
    }

    public Position getCoordinatesForKing(Piece.Color color){
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = this.getPieceInBoard(r, c);
                if (currentPiece.getType() == Piece.Type.King && currentPiece.getColor() == color){
                    Position pos = new Position(r, c);
                    return pos;
                }
            }
        }
        return new Position(0, 0);
     }

    public boolean isKingCheck(Piece.Color color){
        Position pos = this.getCoordinatesForKing(color);
        return isKingCheckAtCoordinates(pos.r, pos.c, color);
    }

    public boolean isKingCheckAtCoordinates(int rKing, int cKing, Piece.Color color){
        Position pos = this.getCoordinatesForKing(color);
    
        int[][] currentPossibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];
        int currentNbPossibleMoves = 0;
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = this.getPieceInBoard(r, c);
                if(currentPiece.getType() != Piece.Type.None && currentPiece.getType() != Piece.Type.King && currentPiece.getColor() != color){
                    if(currentPiece.getType() == Piece.Type.Pawn && cKing != c){
                        if (currentPiece.getColor() == Piece.Color.Black){
                            if (r - 1 == rKing && c - 1 == cKing)
                                return true;
                            else if (r - 1 == rKing && c + 1 == cKing)
                                return true;
                        }
                        else{
                            if (r + 1 == rKing && c - 1 == cKing)
                                return true;
                            else if (r + 1 == rKing && c + 1 == cKing)
                                return true;
                        }
                    }
                    else if (currentPiece.getType() != Piece.Type.Pawn){
                        Board trainingBoard = new Board();
                        trainingBoard.emptyBoard();
                        trainingBoard.boardCopy(this);
                        Move move = new Move();
                        move.start_position[0] = pos.r;
                        move.start_position[1] = pos.c;
                        move.end_position[0] = rKing;
                        move.end_position[1] = cKing;
                        trainingBoard.movePiece(move.start_position, move.end_position);
                        Position newPos = new Position(rKing, cKing);
                        trainingBoard.invisibleKing(newPos);
                        currentPossibleMoves = currentPiece.getPossibleMoves(trainingBoard, false);
                        currentNbPossibleMoves = currentPiece.getNbPossibleMoves();
                        for (int i = 0; i < currentNbPossibleMoves; i++){
                            if (rKing == currentPossibleMoves[i][0] && cKing == currentPossibleMoves[i][1])
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Check if the King color (Black or White) is Check Mate 
    public boolean isKingCheckMate(Piece.Color color){
        int[][] currentPossibleMoves = new int[(Board.boardLength + 1) * (Board.boardLength + 1)][2];
        int currentNbPossibleMoves;
        for (int r = 0; r <= Board.boardLength; r++){
            for (int c = 0; c <= Board.boardLength; c++){
                Piece currentPiece = getPieceInBoard(r, c);
                if (currentPiece.getColor() == color){
                    currentPossibleMoves = currentPiece.getPossibleMoves(this, false);
                    currentNbPossibleMoves = currentPiece.getNbPossibleMoves();
                    //String arenaMoveStart = Board.lettersDict.get(c + 1) + Integer.toString(r + 1);
                    //System.out.printf("===== PIECE : %s en %s %s (%s)\n", currentPiece.getType(), r, c, arenaMoveStart);
                    //System.out.println("Nombre de coups : " + currentNbPossibleMoves);
                    for (int i = 0; i < currentNbPossibleMoves; i++){
                        Board trainingBoard = new Board();
                        trainingBoard.emptyBoard();
                        trainingBoard.boardCopy(this);
                        Move m = new Move();
                        m.start_position[0] = r;
                        m.start_position[1] = c;
                        m.end_position[0] = currentPossibleMoves[i][0];
                        m.end_position[1] = currentPossibleMoves[i][1];
                        //arenaMoveStart = Board.lettersDict.get(m.end_position[1] + 1) + Integer.toString(m.end_position[0] + 1);
                        //System.out.printf("%s %s -> %s %s (%s)\n", m.start_position[0], m.start_position[1], m.end_position[0], m.end_position[1], arenaMoveStart);
                        trainingBoard.movePiece(m.start_position, m.end_position);
                        if (!trainingBoard.isKingCheck(color)){
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }
     
    public void boardCopy(Board board){
        this.currentColor = board.currentColor;
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = board.getPieceInBoard(r, c);
                Piece.Type currentPieceType = currentPiece.type;
                if (currentPieceType == Piece.Type.Pawn){
                    Pawn newPiece = (Pawn) currentPiece;
                    //new Pawn(r, c, currentPiece.color);
                    Pawn newPawn = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newPawn);
                    this.setPieceInBoard(r, c, newPawn);
                }
                else if (currentPieceType == Piece.Type.Bishop){
                    Bishop newPiece = (Bishop) currentPiece;
                    Bishop newBishop = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newBishop);
                    this.setPieceInBoard(r, c, newBishop);
                }
                else if (currentPieceType == Piece.Type.King){
                    King newPiece = (King) currentPiece;
                    King newKing = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newKing);
                    this.setPieceInBoard(r, c, newKing);
                }
                else if (currentPieceType == Piece.Type.Knight){
                    Knight newPiece = (Knight) currentPiece;
                    Knight newKnight = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newKnight);
                    this.setPieceInBoard(r, c, newKnight);
                }
                else if (currentPieceType == Piece.Type.Queen){
                    Queen newPiece = (Queen) currentPiece;
                    Queen newQueen = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newQueen);
                    this.setPieceInBoard(r, c, newQueen);
                }
                else if (currentPieceType == Piece.Type.Rook){
                    Rook newPiece = (Rook) currentPiece;
                    Rook newRook = newPiece.copy();
                    //newBoard.setPieceInBoard(r, c, newRook);
                    this.setPieceInBoard(r, c, newRook);
                }
            }
        }
    }   

    /********** CONSTRUCTOR **********/
    public Board() {
        this.initLettersDict();
        this.initPiecesValuesDict();
        this.initSizeBoard(); // Size of board
        this.currentColor = Piece.Color.White;
    }

}