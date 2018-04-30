import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author arwinstanley
 * @Date 4/30/18
 * 
 * This Class is simulation of a Board filled with random ints, attempting to traverse it and finding the greatest sum of entries 
 */
public class RecursiveLongestPath {
	private static int board [][];
	private static int row;
	private static int col;
	private static int startRow;
	private static int startCol;
	/**
	 * @param rowT is the number of rows
	 * @param colT is the number of columns
	 * @return none
	 * 
	 * fills the board to the specified amount
	 * 
	 */
	public static void fillBoard(int rowT, int colT) {
		board = new int[rowT][colT];
		for(int i = 0; i<rowT;i++) {
			for(int j = 0; j < colT; j++) {
				board[i][j] =(int) ((Math.random()*4)+3);
			}
		}
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * sets the start position to be a random position in the board
	 * 
	 */
	public static void setStart(){
		startRow = (int)(Math.random()*row);
		startCol = (int)(Math.random()*col);
	}
	/**
	 * @param rowR is the index of rows
	 * @param colR is the index of columns
	 * @return an int that is the value of the largest possible path
	 * 
	 * TBD
	 * 
	 */
	public static int recurse(int rowR, int colR) {
		return 0;
	}
	/**
	 * @param rowR is the index of rows
	 * @param colR is the index of columns
	 * @return an int that is the value of the largest possible path
	 * 
	 * traverses the board starting at the start point, going only down and right, until it reaches the end and finding the greatest possible path
	 * 
	 */
	public static int recurseDownRight(int rowR, int colR) {
		//ender
		if(colR+1>=col && rowR+1>=row) {
			return (board[rowR][colR]);
		}
		if(colR+1>=col) {
			return (board[rowR][colR] + recurseDownRight(rowR+1, colR));
		}
		if(rowR+1>=row) {
			return (board[rowR][colR] + recurseDownRight(rowR, colR+1));
		}
           int output = 0;
           output = (recurseDownRight(rowR, colR+1)>recurseDownRight(rowR+1, colR)) ? recurseDownRight(rowR, colR+1) : recurseDownRight(rowR+1, colR);
		return (board[rowR][colR] + output);
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * prints out the 'board' in a readable way to the console
	 * 
	 */
	public static void printBoard() {
		for(int i = 0; i<row;i++) {
			System.out.print("|");
			for(int j = 0; j<col;j++) {
				System.out.print(""+board[i][j]+ "|");
			}
			System.out.println();
		}
	}
	/**
	 * @param none
	 * @return none
	 * 
	 * prints the location of the start point
	 * 
	 */
	public static void printStart() {
		System.out.println("The starting point is {"+startRow+", "+ startCol+"}" );
	}
	/**
	 * @param args is an array of arguments the program takes in on startup
	 * @return none
	 * 
	 * traverses the board starting at the start point, going only down and right, until it reaches the end and finding the greatest possible path
	 * 
	 */
	public static void  main(String [] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the number of rows:");
		row = input.nextInt();
		while(row == 0) {
			System.out.println("Please enter a valid number of rows:");
			row = input.nextInt();
		}
		System.out.println("Please enter the number of cols:");
		col = input.nextInt();
		while(col == 0) {
			System.out.println("Please enter a valid number of cols:");
			col = input.nextInt();
		}
		fillBoard(row, col);
		setStart();
		printBoard();
		printStart();
		System.out.println("End Point is "+ (row-1) + ", "+ (col-1));
		System.out.println(""+recurseDownRight(startRow, startCol));
		input.close();
	}
}
