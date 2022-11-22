import java.util.Random;

public class Game {

    private static int[][] chooseMove(int[][][] allPossibleMovesWithStart, int totalNbPossibleMoves) {
        // TODO : AI
        Random random = new Random(System.currentTimeMillis());
        int lower = 0, higher = totalNbPossibleMoves;
        int randomIndexPossibleMoves = random.nextInt(higher - lower) + lower;

        return allPossibleMovesWithStart[randomIndexPossibleMoves];
    }
     
    public static void main(String[] args) throws InterruptedException {
        System.out.println("================ NOUVELLE PARTIE =================");

        //UCI uci = new UCI();
        //uci.uciCommunication();
        /********** ! EVERYTHING AFTER WILL NOT BE EXECUTED ! **********/


        
        Board board = new Board();
        board.initBoard();
        board.displayBoard();

        // CHECK IS CHESS MATE //
        Move m = new Move();
        m.start_position[0] = 0;
        m.start_position[1] = 3;
        m.end_position[0] = 6;
        m.end_position[1] = 1;
        board.movePiece(m.start_position, m.end_position);

        m.start_position[0] = 7;
        m.start_position[1] = 6;
        m.end_position[0] = 5;
        m.end_position[1] = 3;
        board.movePiece(m.start_position, m.end_position);

        Position mPos = new Position(7,7);

        int[][] currentPossibleMoves = new int[Piece.maxPosition * Piece.maxPosition][2];
        int currentNbPossibleMoves = 0;
        //Piece currentPiece = board.getPieceInBoard(m.end_position[0], m.end_position[1]);
        Piece currentPiece = board.getPieceInBoard(mPos.r, mPos.c);

        currentPossibleMoves = currentPiece.getPossibleMoves(board);
        currentNbPossibleMoves = currentPiece.getNbPossibleMoves();
        board.displayBoard();

        String arenaMoveStart = Board.lettersDict.get(mPos.c + 1) + Integer.toString(mPos.r + 1);

        System.out.printf("POSITION %s %s,%s (%s)\n", currentPiece.getType(), mPos.r, mPos.c, arenaMoveStart);
        System.out.printf("Nombre de coups possibles : %s\n", currentNbPossibleMoves);
        for (int i = 0; i < currentNbPossibleMoves; i++){
            arenaMoveStart = Board.lettersDict.get(currentPossibleMoves[i][1] + 1) + Integer.toString(currentPossibleMoves[i][0] + 1);
            System.out.printf("%s,%s (%s)\n", currentPossibleMoves[i][0], currentPossibleMoves[i][1], arenaMoveStart);
        }

        Move m2 = new Move();
        m2.start_position[0] = 6;
        m2.start_position[1] = 1;
        m2.end_position[0] = 7;
        m2.end_position[1] = 1;
        System.out.printf("Check of King in (%s,%s) ? %s\n", m2.end_position[0], m2.end_position[1], board.isKingCheckAtCoordinates(m2.end_position[0], m2.end_position[1], Piece.Color.White));
        board.displayBoard();

        // END CHECH IF CHESS MATE //S

        Player player1 = new Player();
        Player player2 = new Player();

        player1.initPlayer(Piece.Color.White);
        player2.initPlayer(Piece.Color.Black);

        Piece.Color winnerOfTheGame = Piece.Color.None;

        int n = 0;
        /*while (!board.gameIsFinished()) {
                if (board.currentColor == player1.color){
                    Move move = player1.movePlayer(board);
                    System.out.printf("PLAYER 1 %d, %d -> %d %d\n\n", move.start_position[0], move.start_position[1], move.end_position[0], move.end_position[1]);
                    board.movePiece(move.start_position, move.end_position);
                }
                else{
                    Move move = player2.movePlayer2(board);
                    System.out.printf("PLAYER 2 %d, %d -> %d %d\n\n", move.start_position[0], move.start_position[1], move.end_position[0], move.end_position[1]);
                    board.movePiece(move.start_position, move.end_position);
                }
                n++;
                //board.displayBoard();
                Thread.sleep(5000);
            }*/

            winnerOfTheGame = board.getWinner();
            System.out.printf("   The %s player wins the game with %s plays\n\n", winnerOfTheGame, n);
    }

    /******************** DISPLAYs ********************/
    /*
    private static void displayCurrentPossibleMoves(Piece.Color currentColor, Piece.Type currentType, int c, int r, int currentNbPossibleMoves, int[][] currentPossibleMoves) {
        System.out.printf("%s %s\t%s%d - %d moves", currentColor, currentType, Board.lettersDict.get(c + 1), r + 1, currentNbPossibleMoves);
        if (currentNbPossibleMoves > 0) {
            System.out.printf(" : ");
            for (int nbMoves = 0; nbMoves < currentNbPossibleMoves; nbMoves++) {
                System.out.printf("%s:%d ", Board.lettersDict.get(currentPossibleMoves[nbMoves][1] + 1), currentPossibleMoves[nbMoves][0] + 1);
            }
        }
        System.out.printf("\n");
    }

    private static void displayChoosenMoveAndWinStatus(String currentMoveForArena, int currentWhitePoints, int currentBlackPoints, Piece.Color currentWinner) {
        System.out.printf("Choosen move = %s\tWhite points = %s - Black points = %s -> Current winner = %s\n", 
                                currentMoveForArena, currentWhitePoints, currentBlackPoints, currentWinner);
    }
    */
}