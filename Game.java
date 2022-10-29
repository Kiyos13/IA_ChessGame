import java.util.Random;

public class Game {

    private static int[][] chooseMove(int[][][] allPossibleMovesWithStart, int totalNbPossibleMoves) {
        // TODO : AI
        Random random = new Random(System.currentTimeMillis());
        int lower = 0, higher = totalNbPossibleMoves;
        int randomIndexPossibleMoves = random.nextInt(higher - lower) + lower;

        return allPossibleMovesWithStart[randomIndexPossibleMoves];
    }
     
    public static void main(String[] args) {
        Board board = new Board();
        board.displayBoard();

        Piece currentPiece;
        Piece.Color currentColor = Piece.Color.None;
        Piece.Type currentType = Piece.Type.None;
        int currentNbPossibleMoves, totalNbPossibleMoves;
        int[][] currentPossibleMoves = new int[Board.boardLength * Board.boardLength][2];
        int[][] currentPossibleMoveWithStart = new int[2][2];
        int[][][] allPossibleMovesWithStart;
        int[][] currentChoosenMove = new int[2][2];
        int[] currentPositon = new int[2];
        int currentWhitePoints = 0, currentBlackPoints = 0;
        Piece.Color currentWinner, currentPlayer = Piece.Color.White, winnerOfTheGame = Piece.Color.None;


        while (!board.gameIsFinished()) {
        //for (int t = 0; t < 5; t++) {
            allPossibleMovesWithStart = new int[Board.boardLength * Board.boardLength + 1][2][2];
            totalNbPossibleMoves = 0;
            currentNbPossibleMoves = 0;
            for (int r = 0; r <= Board.boardLength; r++) {
                for (int c = 0; c <= Board.boardLength; c++) {
                    currentPiece = board.getPieceInBoard(r, c);
                    currentColor = currentPiece.getColor();
                    currentType = currentPiece.getType();

                    if (currentColor == currentPlayer) {
                        currentPossibleMoves = currentPiece.getPossibleMoves(board);
                        currentNbPossibleMoves = currentPiece.getNbPossibleMoves();

                        for (int i = 0; i < currentNbPossibleMoves; i++) {
                            currentPositon[0] = r;
                            currentPositon[1] = c;
                            currentPossibleMoveWithStart[0] = currentPositon;
                            currentPossibleMoveWithStart[1] = currentPossibleMoves[i];
                            allPossibleMovesWithStart[totalNbPossibleMoves] = currentPossibleMoveWithStart;
                            totalNbPossibleMoves++;
                        }

                        // displayCurrentPossibleMoves(currentColor, currentType, c, r, currentNbPossibleMoves, currentPossibleMoves);
                    }
                }
            }
            currentChoosenMove = chooseMove(allPossibleMovesWithStart, totalNbPossibleMoves);
            currentWhitePoints = board.getWhitePoints();
            currentBlackPoints = board.getBlackPoints();
            currentWinner = board.getWinner();

            displayChoosenMoveAndWinStatus(currentChoosenMove, currentWhitePoints, currentBlackPoints, currentWinner);

            board.movePiece(currentChoosenMove[0], currentChoosenMove[1]);
            board.displayBoard();
            

            if (currentPlayer == Piece.Color.White)
                currentPlayer = Piece.Color.Black;
            else if (currentPlayer == Piece.Color.Black)
                currentPlayer = Piece.Color.White;
        }

        winnerOfTheGame = board.getWinner();
        System.out.printf("   The %s player wins the game.\n\n", winnerOfTheGame);
    }

    /******************** DISPLAYs ********************/
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

    private static void displayChoosenMoveAndWinStatus(int[][] currentChoosenMove, int currentWhitePoints, int currentBlackPoints, Piece.Color currentWinner) {
        System.out.printf("Choosen move = %s%d to %s%d\tWhite points = %s - Black points = %s -> Current winner = %s\n", 
                                Board.lettersDict.get(currentChoosenMove[0][1] + 1), currentChoosenMove[0][0] + 1, 
                                Board.lettersDict.get(currentChoosenMove[1][1] + 1), currentChoosenMove[1][0] + 1,
                                currentWhitePoints, currentBlackPoints, currentWinner);
    }
}