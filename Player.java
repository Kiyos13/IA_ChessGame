import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    public Piece.Color color;
    private int depth = 3;

    public void initPlayer(Piece.Color color){
        this.color = color;
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

    public ArrayList<Move> generateLegalMoves(Board board){
        ArrayList<Move> movesPossiblesList = new ArrayList<>();
        int[][] currentPossibleMoves = new int[(Board.boardLength + 1) * (Board.boardLength + 1)][2];
        int currentNbPossibleMoves = 0;
        if (board.isKingCheck(board.currentColor)){
            Position pos = board.getCoordinatesForKing(board.currentColor);
            Piece kingPiece = board.getPieceInBoard(pos.r, pos.c);
            currentPossibleMoves = kingPiece.getPossibleMoves(board);
            currentNbPossibleMoves = currentPossibleMoves.length;

            for (int i = 0; i < currentNbPossibleMoves; i++) {
                Move move = new Move();
                move.start_position[0] = pos.r;
                move.start_position[1] = pos.c;
                move.end_position[0] = currentPossibleMoves[i][0];
                move.end_position[1] = currentPossibleMoves[i][1];
                System.out.printf("%s %s -> %s %s\n", pos.r, pos.c, currentPossibleMoves[i][0], currentPossibleMoves[i][1]);
                movesPossiblesList.add(move);
            }
        return movesPossiblesList;
        }

        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                Piece currentPiece = board.getPieceInBoard(r, c);
                Piece.Color currentColor = currentPiece.getColor();
                
                if (currentColor == board.currentColor) {
                    currentPossibleMoves = currentPiece.getPossibleMoves(board);
                    currentNbPossibleMoves = currentPiece.getNbPossibleMoves();

                    for (int i = 0; i < currentNbPossibleMoves; i++) {
                        Move move = new Move();
                        move.start_position[0] = r;
                        move.start_position[1] = c;
                        move.end_position[0] = currentPossibleMoves[i][0];
                        move.end_position[1] = currentPossibleMoves[i][1];
                        movesPossiblesList.add(move);
                    }
                }
            }
        }
        return movesPossiblesList;
    }

    public MiniMaxReturn miniMax(Board board, int depth, boolean isMaximize, Move m) throws InterruptedException{
        MiniMaxReturn returnVal = new MiniMaxReturn();
        returnVal.move = m;

        if (board.gameIsFinished() || depth == 0){
            returnVal.val = getScore(board);
            //System.out.println("Couleur : " + board.currentColor);
            //System.out.println("Score :" + returnVal.val);
            //board.displayBoard();
            //Thread.sleep(1000);
            return returnVal;
        }

        if (isMaximize){
            int val = -1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board);
            for (Move move: movesPossiblesList){
                //System.out.printf("%d,%d -> %d,%d\n", move.start_position[0], move.start_position[1], move.end_position[0], move.end_position[1]);
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
                board.emptyBoard();;
                board.boardCopy(previousBoard);
            }
        }
        else{
            int val = 1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board);
            Iterator<Move> it = movesPossiblesList.iterator();
            while(it.hasNext()){
                Move move = it.next();
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
                board.emptyBoard();;
                board.boardCopy(previousBoard);
            }            
        }
        return returnVal;
    }

    public MiniMaxReturn alphaBeta(Board board, int depth, boolean isMaximize, Move m, int alpha, int beta) throws InterruptedException{
        MiniMaxReturn returnVal = new MiniMaxReturn();
        returnVal.move = m;

        if (board.gameIsFinished() || depth == 0){
            //board.displayBoard();
            Thread.sleep(10000);
            returnVal.val = getScore(board);
            return returnVal;
        }

        if (isMaximize){
            int val = -1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board);
            for (Move move: movesPossiblesList){
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
                board.emptyBoard();;
                board.boardCopy(previousBoard);
            }
        }
        else{
            int val = 1000;
            ArrayList<Move> movesPossiblesList = generateLegalMoves(board);
            Iterator<Move> it = movesPossiblesList.iterator();
            while(it.hasNext()){
                Move move = it.next();
                Board previousBoard = new Board();
                previousBoard.emptyBoard();
                previousBoard.boardCopy(board);
                board.movePiece(move.start_position, move.end_position);
                MiniMaxReturn miniMaxReturnVal = this.alphaBeta(board, depth - 1, true, null, alpha, beta);
                int eval = miniMaxReturnVal.val;
                if (val >= eval){
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
                board.emptyBoard();;
                board.boardCopy(previousBoard);
            }            
        }
        return returnVal;
    }

    public Move movePlayer(Board board) throws InterruptedException{
        //System.out.println("PLAYER 1 MINIMAX");
        Board copyBoard = new Board();
        copyBoard.emptyBoard();
        copyBoard.boardCopy(board);
        //System.out.println("BOARD NOT COPY");
        //board.displayBoard();
        //System.out.println("INTIIAL BOARD COPY");
        //copyBoard.displayBoard();
        //MiniMaxReturn miniMaxReturnVal = this.alphaBeta(copyBoard, this.depth, true, null, -10000, 10000);
        MiniMaxReturn miniMaxReturnVal = this.miniMax(copyBoard, this.depth, true, null);
        //System.out.println("Valeur : " + miniMaxReturnVal.val);
        Move move = miniMaxReturnVal.move;
        return move;
    }

    public Move movePlayer2(Board board){
        System.out.println("PLAYER 2 RANDOM");
        Board copyBoard = new Board();
        copyBoard.emptyBoard();
        copyBoard.boardCopy(board);
        System.out.println("INTIIAL BOARD COPY");
        //copyBoard.displayBoard();
        ArrayList<Move> moves = this.generateLegalMoves(copyBoard);
        int n = moves.size();
        System.out.println(n);
        int random = ThreadLocalRandom.current().nextInt(0, n);
        Move move = moves.get(random);
        return move;
    }
}
