import java.util.Scanner;

public class Connect4{
    private static int [][] board;
    private static int col, row;
    private static Scanner scanner;
    public static void main(String [] args){
        col = 6;
        row = 7;
        board = new int[col][row];
        scanner = new Scanner(System.in);
        System.out.println("-----Connect 4-----");
        int gameOpt;
        for(int i=0;i<col*row; i++){

            int playerNum = (i%2)==0?1:2;
            gameOpt = playerTurn(playerNum);

            if(gameOpt == 9)
                break;
            else if(gameOpt == 0)
                continue;

            drawBoard();
            System.out.println("Player "+playerNum+" Win The Game !!!");
            break;
        }
        System.out.println("The game is end");
    }
    public static int playerTurn(int playerNum){
        drawBoard();
        int input;
        do
            input = readInput(playerNum);
        while(!validateInput(input));
        if (input == 9)
            return 9;
        return winCheck(storeInput(playerNum, input))?1:0;
    }
    public static int[] storeInput(int player, int pos){
        int start = 0;
        while(board[start][pos] != 0)
            start++;
        board[start][pos] = player;
        int [] position = {start,pos};
        return position;
    }
    public static boolean winCheck(int [] cor){
        int upDown = 1+check(cor, new int []{1,0}, 0)+check(cor, new int []{-1,0}, 0);
        int leftRight = 1+check(cor, new int []{0,1}, 0)+check(cor, new int []{0,-1}, 0);
        int lurd = 1+check(cor, new int []{1,-1}, 0)+check(cor, new int []{-1,1}, 0);
        int ruld = 1+check(cor, new int []{1,1}, 0)+check(cor, new int []{-1,-1}, 0);

        if (upDown > 3 || leftRight > 3 || lurd > 3 || ruld > 3)
            return true;
            
        return false;
    }


    public static int check(int [] p, int [] c, int count){
        int preR = p[0];
        int preC = p[1];
        int thisR = p[0]+c[0];
        int thisC = p[1]+c[1];

        if(thisR >-1 && thisR < 6 && thisC >-1 && thisC < 7 && board[preR][preC] == board[thisR][thisC])
            return check(new int[] {thisR,thisC},c,++count);
        
        return count;
    }


    public static boolean validateInput(int input){
		if (input>=0 && input<=6 && board[board.length-1][input]== 0 || input ==9)	
			return true;

		if(input>=0 && input<=6 && board[board.length-1][input]!=0)
			System.out.println("column is full , please input again !!");
		else
			System.out.println("invaild input , please input again !!");

		return false;
	}
    public static void drawBoard(){
        for (int col = board.length-1; col >= 0; col--){

            System.out.print(String.format("%2d%2c",col,'|'));

            for (int row = 0; row < board[col].length; row++)
                System.out.print(String.format("%2d",board[col][row]));
            
            System.out.println();

        }
        System.out.println(String.format("%5s%s"," ","-------------"));
        System.out.print(String.format("%4s"," "));

        for(int row = 0; row < board[0].length; row++)
            System.out.print(String.format("%2d",row));

        System.out.println();
	}
    public static int readInput(int playerNum){
        System.out.print("Player "+playerNum+" type a column (0-6) or 9 to quit curent game: ");
        return scanner.nextInt();
    }
}