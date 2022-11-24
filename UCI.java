import java.util.*;

public class UCI {
    
    static String ENGINENAME = "PauwelsPommier";
    static Board board;
    static Player player;
    static int nbMoves;
    private static int firstMove = 0;
    private int ourParity = 1;

    public void uciCommunication() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String inputString = input.nextLine();

            if (inputString.equals("uci"))
                inputUCI();
            else if (inputString.startsWith("setoption"))
                inputSetOption(inputString);
            else if (inputString.equals("isready"))
                inputIsReady();
            else if (inputString.equals("ucinewgame"))
                inputUCINewGame();
            else if (inputString.startsWith("position"))
                inputPosition(inputString);
            else if (inputString.startsWith("go"))
                inputGo();
            else if (inputString.equals("quit"))
                inputQuit();
            else if (inputString.equals("print"))
                inputPrint();
        }
    }

    public static void inputUCI() {
        System.out.println("id name " + ENGINENAME);
        System.out.println("id author PauwelsPommier");
        //options go here
        System.out.println("uciok");
    }

    public static void inputSetOption(String inputString) {
        //set options
    }

    public static void inputIsReady() {
        System.out.println("readyok");
    }

    public static void inputUCINewGame() {
        board = new Board();
        board.initBoard();
        player = new Player();
        nbMoves = 0;
        firstMove = 0;
        // System.out.printf("Profondeur alphabeta : %s\n", player.depth);
    }

    public void inputPosition(String input) {
        input = input.substring(9).concat(" ");

        if (input.equals("startpos ")) {
            ourParity = 0;
            setPlayerColor();
            ourMove();
        }

        if (input.contains("moves")) {
            String[] lastMoveList = input.split(" ");
            String lastMove = lastMoveList[lastMoveList.length - 1];
            input = lastMove;
            int inputLength = ((String) input).length();

            setPlayerColor();

            if ((inputLength == 4) || (inputLength == 5)) { // 5 for the newline
                // Make the ennemy move on our board
                board.movePieceArena(input);
                nbMoves += 1;

                // Make our move on the board and give it to the Arena plateform
                if (nbMoves % 2 == this.ourParity)
                    ourMove();
            }
        }
    }

    public static void inputGo() {
        
    }

    public static void inputQuit() {
        System.exit(0);
    }

    public static void inputPrint() {
        Piece[][] boardPieces = board.getBoard();
        Piece currentPiece;
        Piece.Color currentColor = Piece.Color.None;
        Piece.Type currentType = Piece.Type.None;

        System.out.println("  h  h  f  e  d  c  b  a\n");
        for (int r = 0; r <= Board.boardLength; r++) {
            for (int c = 0; c <= Board.boardLength; c++) {
                currentPiece = boardPieces[r][c];
                currentColor = currentPiece.getColor();
                currentType = currentPiece.getType();

                if (currentType == Piece.Type.Pawn)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  p");
                    else
                        System.out.printf("  P");
                else if (currentType == Piece.Type.Rook)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  r");
                    else
                        System.out.printf("  R");
                else if (currentType == Piece.Type.Knight)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  k");
                    else
                        System.out.printf("  K");
                else if (currentType == Piece.Type.Bishop)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  b");
                    else
                        System.out.printf("  B");
                else if (currentType == Piece.Type.Queen)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  q");
                    else
                        System.out.printf("  Q");
                else if (currentType == Piece.Type.King)
                    if (currentColor == Piece.Color.Black)
                        System.out.printf("  a");
                    else
                        System.out.printf("  A");
                else
                    System.out.printf("  .");
            }
            if (!(r < Board.boardLength))
                System.out.println("\n\n  h  h  f  e  d  c  b  a");
            else
                System.out.println("\n");
        }
    }



    private void setPlayerColor() {
        if (this.firstMove == 0) {
            if (this.ourParity == 0)
                player.initPlayer(Piece.Color.Black);
            else if (this.ourParity == 1)
                player.initPlayer(Piece.Color.White);
            this.firstMove = 1;
        }
        System.out.println("OUR COLOR = " + player.color);
    }

    private void ourMove() {
        try {
            Move move = player.movePlayer(board);
            board.movePiece(move.start_position, move.end_position);

            String arenaMoveStart = Board.lettersDict.get(move.start_position[1] + 1) + Integer.toString(move.start_position[0] + 1);
            String arenaMoveEnd = Board.lettersDict.get(move.end_position[1] + 1) + Integer.toString(move.end_position[0] + 1);
            String arenaMove = arenaMoveStart + arenaMoveEnd;

            System.out.println("bestmove " + arenaMove);

            nbMoves += 1;
        } 
        catch (InterruptedException e) { }
    }
}