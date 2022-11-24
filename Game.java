
public class Game {
     
    public static void main(String[] args) throws InterruptedException {
        System.out.println("================ NOUVELLE PARTIE =================");

        UCI uci = new UCI();
        uci.uciCommunication();
        /********** ! EVERYTHING AFTER WILL NOT BE EXECUTED ! **********/


        /*Board board = new Board();
        board.emptyBoard();
        //board.initBoard();
        board.displayBoard();

        board.setPieceInBoard(0, 2, new King(0,2, Piece.Color.White));
        board.setPieceInBoard(1, 5, new Rook(1,5, Piece.Color.Black));
        board.setPieceInBoard(0, 4, new Rook(0,4, Piece.Color.Black));
        board.setPieceInBoard(7, 2, new King(0,2, Piece.Color.Black));

        board.displayBoard();

        System.out.println(board.isKingCheckMate(Piece.Color.White));
        Player player1 = new Player();
        player1.initPlayer(Piece.Color.White); 
        player1.movePlayer(board);

        System.out.println("End");*/
        /*Player player2 = new Player();

        player1.initPlayer(Piece.Color.White);
        player2.initPlayer(Piece.Color.Black);

        Piece.Color winnerOfTheGame = Piece.Color.None;

        int n = 0;
        while (!board.gameIsFinished()) {
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
                board.displayBoard();
                Thread.sleep(5000);
            }

            winnerOfTheGame = board.getWinner();
            System.out.printf("   The %s player wins the game with %s plays\n\n", winnerOfTheGame, n);*/
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