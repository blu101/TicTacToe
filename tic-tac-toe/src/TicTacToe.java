import java.lang.reflect.Array;
import java.util.*;

public class TicTacToe {
    //list for both player and cpu on the positions they own
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public static void main(String[] args){
        char [][] gameBoard = {{' ','|',' ','|',' '},
                               {'-','+','-','+','-'},
                               {' ','|',' ','|',' '},
                               {'-','+','-','+','-'},
                               {' ','|',' ','|',' '}};


        printGameBoard(gameBoard);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("You will be 'X' while cpu will be 'O' ");

        //Loop through the game
        while(true) {
            //Player's portion
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your placement (1-9 inc.) on the board:");
            int playerPos = scan.nextInt();

            while((playerPositions.contains(playerPos)) || (cpuPositions.contains(playerPos))){
                System.out.println("Sorry that spot is already taken, please re-enter another spot:");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            //Will check when there is a matching list to one of the winning conditions after player plays
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }


            //Cpu's portion
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            while((playerPositions.contains(cpuPos)) || (cpuPositions.contains(cpuPos))){
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");


            //Will print the board each new spot is taken
            printGameBoard(gameBoard);

            //Will check when there is a matching list to one of the winning conditions after cpu plays
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }


    }
    //more cleaner to make printing the board a method
    public static void printGameBoard(char [][] gameBoard) {
        for (char[] row : gameBoard) { //takes the row
            for (char c : row) { //takes each char in a raw
                System.out.print(c);
            }
            System.out.println();
        }
    }
    //this method will place player/cpu piece on the game board
    public static void placePiece(char [][] gameBoard, int pos, String user){
        //symbol for Player as X, symbol for cpu as O
        char symbol= ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol ='O';
            cpuPositions.add(pos);
        }

        //create the cases of if user/cpu chooses (1-9), the gameboard will exchange symbol determining who the player is.
        switch(pos) {
            case 1:
                gameBoard [0][0] = symbol;
                break;
            case 2:
                gameBoard [0][2] = symbol;
                break;
            case 3:
                gameBoard [0][4] = symbol;
                break;
            case 4:
                gameBoard [2][0] = symbol;
                break;
            case 5:
                gameBoard [2][2] = symbol;
                break;
            case 6:
                gameBoard [2][4] = symbol;
                break;
            case 7:
                gameBoard [4][0] = symbol;
                break;
            case 8:
                gameBoard [4][2] = symbol;
                break;
            case 9:
                gameBoard [4][4] = symbol;
                break;
            default:
                break;
        }

    }
    public static String checkWinner() {
        //create every list that will determine a win for 3 in a row
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List leftDiag = Arrays.asList(1, 5, 9);
        List rightDiag = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(leftDiag);
        winning.add(rightDiag);

        //loops through the list to check match
        for (List l : winning) {
            if (playerPositions.containsAll(l)) { //if the player has any of the combinations
                return "Congratulations you win!";
            } else if (cpuPositions.containsAll(l)) {
                return "Defeat.";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Draw.";
            }
        }
        return "";
    }
}
