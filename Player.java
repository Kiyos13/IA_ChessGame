import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    public Piece.Color color;
    public int depth;
    public ArrayList<Move> allMoves;
    public ArrayList<Piece.Type> allPiecesPlayed;
    public boolean lastTwoMovesAlreadyTheSame = false;
    public int nbMoves;
    public int nbMovesRandomInit;

    /********** CONSTRUCTOR **********/
    public Player(){
        this.depth = 3;
        this.nbMovesRandomInit = 3;
    }
    
    public Player(int depth){
        this.depth = depth;
    }

    public Player(int depth, int nbMovesRandomInit){
        this.depth = depth;
        this.nbMovesRandomInit = nbMovesRandomInit;
    }

    public void initPlayer(Piece.Color color){
        this.color = color;
        this.allMoves = new ArrayList<>();
        this.allPiecesPlayed = new ArrayList<>();
        this.lastTwoMovesAlreadyTheSame = false;
        this.nbMoves = 0;
    }

    public int getScore(Board board){
        int score = 0;
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = board.getPieceInBoard(r, c);
                Piece.Color currentColor = currentPiece.getColor();                
                if (currentColor == board.currentColor) {
                    score -= currentPiece.getValue();
                }
                else{
                    score += currentPiece.getValue();
                }
            }
        }
        return score;
    }

    public ArrayList<Move> generateLegalMoves(Board board, boolean verifyCheck){
        ArrayList<Move> movesPossiblesList = new ArrayList<>();
        int[][] currentPossibleMoves = new int[(Board.boardLength + 1) * (Board.boardLength + 1)][2];
        int currentNbPossibleMoves = 0;
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = board.getPieceInBoard(r, c);
                Piece.Color currentColor = currentPiece.getColor();
                
                if (currentColor == board.currentColor) {
                    currentPossibleMoves = currentPiece.getPossibleMoves(board, verifyCheck);
                    currentNbPossibleMoves = currentPiece.getNbPossibleMoves();
                    //String arenaMoveStart = Board.lettersDict.get(c + 1) + Integer.toString(r + 1);

                    //System.out.printf("===== PIECE : %s en %s %s (%s)\n", currentPiece.getType(), r, c, arenaMoveStart);
                    //System.out.println("Nombre de coups : " + currentNbPossibleMoves);
                    for (int i = 0; i < currentNbPossibleMoves; i++) {
                        Move move = new Move();
                        move.start_position[0] = r;
                        move.start_position[1] = c;
                        move.end_position[0] = currentPossibleMoves[i][0];
                        move.end_position[1] = currentPossibleMoves[i][1];
                        //arenaMoveStart = Board.lettersDict.get(move.end_position[1] + 1) + Integer.toString(move.end_position[0] + 1);
                        //System.out.printf("%s %s -> %s %s (%s)\n", move.start_position[0], move.start_position[1], move.end_position[0], move.end_position[1], arenaMoveStart);
                        movesPossiblesList.add(move);
                    }
                }
            }
        }
        return movesPossiblesList;
    }

    public MiniMaxReturn miniMax(Board board, int depth, boolean isMaximize, Move m){
        MiniMaxReturn returnVal = new MiniMaxReturn();
        returnVal.move = m;
        
        if (this.color != board.currentColor) {
        	if (board.isKingCheckMate(board.currentColor)) {
        		returnVal.val = 100;
                return returnVal;
        	}
            else if(board.isKingCheckMate(this.color)){
                returnVal.val = -100;
                return returnVal;
            }
        }

        if (depth == 0){
            returnVal.val = getScore(board);
            return returnVal;
        }

        if (isMaximize){
            int val = -1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board, true);
            for (Move move: movesPossiblesList){
                boolean moveToAvoid = false;
                if (m != null){
                    if (m.start_position[0] == move.start_position[0]
                        && m.start_position[1] == move.start_position[1]
                        && m.end_position[0] == move.end_position[0]
                        && m.end_position[1] == move.end_position[1]){
                            moveToAvoid = true;
                        }
                }
                if (!moveToAvoid){
                    Board previousBoard = new Board();
                    previousBoard.emptyBoard();
                    previousBoard.boardCopy(board);
                    board.movePiece(move.start_position, move.end_position);
                    MiniMaxReturn miniMaxReturnVal = this.miniMax(board, depth - 1, false, null);
                    int eval = miniMaxReturnVal.val;
                    if (val <= eval){
                        returnVal.move = move;
                    }
                    returnVal.val = Math.max(val, eval);
                    val = returnVal.val;
                    board.emptyBoard();
                    board.boardCopy(previousBoard);
                }
            }
        }
        else{
            int val = 1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board, true);
            for (Move move: movesPossiblesList){
                boolean moveToAvoid = false;
                if (m != null){
                    if (m.start_position[0] == move.start_position[0]
                        && m.start_position[1] == move.start_position[1]
                        && m.end_position[0] == move.end_position[0]
                        && m.end_position[1] == move.end_position[1]){
                            moveToAvoid = true;
                        }
                }
                if (!moveToAvoid){
                    Board previousBoard = new Board();
                    previousBoard.emptyBoard();
                    previousBoard.boardCopy(board);
                    board.movePiece(move.start_position, move.end_position);
                    MiniMaxReturn miniMaxReturnVal = this.miniMax(board, depth - 1, true, null);
                    int eval = miniMaxReturnVal.val;
                    if (val >= eval){
                        returnVal.move = move;
                    }
                    returnVal.val = Math.min(val, eval);
                    val = returnVal.val;
                    board.emptyBoard();
                    board.boardCopy(previousBoard);
                }   
            }         
        }
        return returnVal;
    }

    public MiniMaxReturn alphaBeta(Board board, int depth, boolean isMaximize, Move m, int alpha, int beta){
        MiniMaxReturn returnVal = new MiniMaxReturn();
        returnVal.move = m;

        if (this.color != board.currentColor) {
        	if (board.isKingCheckMate(board.currentColor)) {
        		returnVal.val = 100 + depth;
                return returnVal;
        	}
            else if(board.isKingCheckMate(this.color)){
                returnVal.val = -100 - depth;
                return returnVal;
            }
        }

        if (depth == 0){
            returnVal.val = getScore(board);
            return returnVal;
        }

        if (isMaximize){
            int val = -1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board, true);
            for (Move move: movesPossiblesList){
                boolean moveToAvoid = false;
                if (m != null){
                    if (m.start_position[0] == move.start_position[0]
                        && m.start_position[1] == move.start_position[1]
                        && m.end_position[0] == move.end_position[0]
                        && m.end_position[1] == move.end_position[1]){
                            moveToAvoid = true;
                        }
                }
                if (!moveToAvoid){
                    Board previousBoard = new Board();
                    previousBoard.emptyBoard();
                    previousBoard.boardCopy(board);
                    board.movePiece(move.start_position, move.end_position);
                    MiniMaxReturn miniMaxReturnVal = this.alphaBeta(board, depth - 1, false, null, alpha, beta);
                    int eval = miniMaxReturnVal.val;
                    if (val < eval){
                        returnVal.move = move;
                    }
                    returnVal.val = Math.max(val, eval);
                    val = returnVal.val;
                    if (val >= beta){
                        board.emptyBoard();;
                        board.boardCopy(previousBoard);
                        return returnVal;
                    }
                    alpha = Math.max(alpha, val);
                    board.emptyBoard();
                    board.boardCopy(previousBoard);
                }
        }
        }
        else{
            int val = 1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board, true);
            for (Move move: movesPossiblesList){
                boolean moveToAvoid = false;
                if (m != null){
                    if (m.start_position[0] == move.start_position[0]
                        && m.start_position[1] == move.start_position[1]
                        && m.end_position[0] == move.end_position[0]
                        && m.end_position[1] == move.end_position[1]){
                            moveToAvoid = true;
                        }
                }
                if (!moveToAvoid){
                    Board previousBoard = new Board();
                    previousBoard.emptyBoard();
                    previousBoard.boardCopy(board);
                    board.movePiece(move.start_position, move.end_position);
                    MiniMaxReturn miniMaxReturnVal = this.alphaBeta(board, depth - 1, true, null, alpha, beta);
                    int eval = miniMaxReturnVal.val;
                    if (val > eval){
                        returnVal.move = move;
                    }
                    returnVal.val = Math.min(val, eval);
                    val = returnVal.val;
                    if (alpha >= val){
                        board.emptyBoard();;
                        board.boardCopy(previousBoard);
                        return returnVal;
                    }
                    beta = Math.min(beta, val);
                    board.emptyBoard();
                    board.boardCopy(previousBoard);
                }   
            }         
        }
        return returnVal;
    }

    public Move chooseRandomMove(Board board, boolean verifyCheck){
        ArrayList<Move> moves = this.generateLegalMoves(board, verifyCheck);
        int n = moves.size();
        int random = ThreadLocalRandom.current().nextInt(0, n);
        Move move = moves.get(random);
        return move;
    }

    boolean checkIfTheLastXMoveAreTheSame(int x){
        int n = this.allMoves.size();
        if (n < x){
            return false;
        }

        if (this.allPiecesPlayed.get(n - 1) != this.allPiecesPlayed.get(n - x)){
            return false;
        }

        Move lastMove = this.allMoves.get(n - 1);
        Move secondLastMove = this.allMoves.get(n - x);
        if (secondLastMove.start_position[0] == lastMove.end_position[0]
            && secondLastMove.start_position[1] == lastMove.end_position[1]
            && secondLastMove.end_position[0] == lastMove.start_position[0]
            && secondLastMove.end_position[1] == lastMove.start_position[1])
            return true;
        return false;
    }

    int nbPiecesOnBoard(Board board){
        int n = 0;
        for (int r = 0; r <= Board.boardLength; r++){
            for (int c = 0; c <= Board.boardLength; c++){
                if (board.getPieceInBoard(r, c).getType() != Piece.Type.None){
                    n++;
                }
            }
        }
        return n;
    }

    public Move movePlayer(Board board) throws InterruptedException{
        Board copyBoard = new Board();
        copyBoard.emptyBoard();
        copyBoard.boardCopy(board);

        Move moveForAlgo = null;
        int x = 2;
        if (lastTwoMovesAlreadyTheSame)
            x = 6;
        if (checkIfTheLastXMoveAreTheSame(x)){
            int n = this.allMoves.size();
            moveForAlgo = this.allMoves.get(n - x);
            lastTwoMovesAlreadyTheSame = true;
        }
        
        Move move;
        //int val = 0;
        //To generate random moves at the nbMovesRandomInit first moves (to have development)
        if (this.nbMoves < this.nbMovesRandomInit){
            move = chooseRandomMove(copyBoard, true);
            if (this.nbMoves > 0) {
                int n = allMoves.size();
                while(move.start_position[0] == this.allMoves.get(n - 1).end_position[0] 
                    && move.start_position[1] == this.allMoves.get(n - 1).end_position[1])
                    move = chooseRandomMove(copyBoard, true);
            }
        }
        else{
            if (this.nbPiecesOnBoard(copyBoard) < 5){
                this.depth = 5;
            }
            MiniMaxReturn miniMaxReturnVal = this.alphaBeta(copyBoard, this.depth, true, moveForAlgo, -10000, 10000);
            //MiniMaxReturn miniMaxReturnVal = this.miniMax(copyBoard, this.depth, true, null);
            //System.out.println("Valeur : " + miniMaxReturnVal.val);

            move = miniMaxReturnVal.move;
            //val = miniMaxReturnVal.val;
            if (miniMaxReturnVal.move == null){
                move = chooseRandomMove(copyBoard, false);
            }
        }
        //String arenaMoveStart = Board.lettersDict.get(move.start_position[1] + 1) + Integer.toString(move.start_position[0] + 1);
        //String arenaMoveEnd = Board.lettersDict.get(move.end_position[1] + 1) + Integer.toString(move.end_position[0] + 1);

        //System.out.printf("Coup alpha-beta : %s -> %s\n", arenaMoveStart, arenaMoveEnd);
        //System.out.println("Valeur du coup : " + val);
        this.allPiecesPlayed.add(board.getPieceInBoard(move.start_position[0], move.start_position[1]).getType());
        this.allMoves.add(move);
        this.nbMoves++;
        return move;
    }

    public Move movePlayer2(Board board){
        //System.out.println("PLAYER 2 RANDOM");
        Board copyBoard = new Board();
        copyBoard.emptyBoard();
        copyBoard.boardCopy(board);
        return chooseRandomMove(copyBoard, true);    }
}