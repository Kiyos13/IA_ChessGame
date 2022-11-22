import java.util.*;


public class UCI {
    
    static String ENGINENAME = "PauwelsPommier";
    static Board board;
    static Player player;
    static int nbMoves;

    public static void uciCommunication() {
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
        player.initPlayer(Piece.Color.Black);
        nbMoves = 0;
    }

    public static void inputPosition(String input) {
        input = input.substring(9).concat(" ");

        if (input.contains("moves")) {
            String[] lastMoveList = input.split(" ");
            String lastMove = lastMoveList[lastMoveList.length - 1];
            input = lastMove;

            int inputLength = ((String) input).length();

            if ((inputLength == 4) || (inputLength == 5)) { // 5 for the newline
                // Make the ennemy move on our board
                board.movePieceArena(input);
                nbMoves += 1;

                // Make our move on the board and give it to the Arena plateform
                if (nbMoves % 2 == 1) {
                    try 
                    {
                        Move move = player.movePlayer(board);
                        board.movePiece(move.start_position, move.end_position);

                        String arenaMoveStart = Board.lettersDict.get(move.start_position[1] + 1) + Integer.toString(move.start_position[0] + 1);
                        String arenaMoveEnd = Board.lettersDict.get(move.end_position[1] + 1) + Integer.toString(move.end_position[0] + 1);
                        String arenaMove = arenaMoveStart + arenaMoveEnd;

                        System.out.println("bestmove " + arenaMove);

                        nbMoves += 1;
                    } 
                    catch (InterruptedException e)
                    {
                        
                    }
                }
            }
        }
    }

    public static void inputGo() {
        
    }

    public static void inputQuit() {
        System.exit(0);
    }

    public static void inputPrint() {
        board.displayBoard();
    }
}