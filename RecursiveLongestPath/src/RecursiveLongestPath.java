import java.awt.Point;
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
	 * Should recursively iterate through an array and return the int value of the largest possible path, using all 8 directions
	 * Currently can not be run because it's either un-ending or uses to much data
	 */
	public static int recurse(int rowR, int colR, ArrayList<Point> prev ) {
		ArrayList<Point> test = new ArrayList<Point>();
		for(int i = 0; i < prev.size();i++) {
			test.add(prev.get(i));
		}
		int[] vals = new int[8];
		int output;
		//ender
		for(int i = 0; i < test.size();i++) {
			if(new Point(rowR, colR).equals(test.get(i))) {
				return 0;
			}
		}
		test.add(new Point(rowR, colR));
		if((colR+1>=col && rowR+1>=row)||(colR-1==-1 && rowR+1>=row)||(colR+1>=col && rowR-1==-1)||(colR-1==-1 && rowR-1==-1)) {
			return (board[rowR][colR]);
		}
		if(rowR+1>=row) {
			vals[0] = recurse(rowR, colR-1, test);
			vals[1] = recurse(rowR, colR+1, test);
			vals[2] = recurse(rowR-1, colR+1, test);
			vals[3] = recurse(rowR-1, colR, test);
			vals[4] =recurse(rowR-1, colR-1, test);
			output = vals[4];
			for(int i = 0; i<4;i++ ) {
				if(vals[i]>=vals[i+1]) {
					output = vals[i];
				}
			}
			return board[rowR][colR]+output;
		}
		if(colR+1>=col) {
			vals[0] = recurse(rowR-1, colR, test);
			vals[1] = recurse(rowR+1, colR, test);
			vals[2] = recurse(rowR+1, colR-1, test);
			vals[3] = recurse(rowR, colR-1, test);
			vals[4] =recurse(rowR-1, colR-1, test);
			output = vals[4];
			for(int i = 0; i<4;i++ ) {
				if(vals[i]>=vals[i+1]) {
					output = vals[i];
				}
			}
			return board[rowR][colR]+output;
		}
		if(rowR-1==-1) {
			vals[0] = recurse(rowR, colR-1, test);
			vals[1] = recurse(rowR, colR+1, test);
			vals[2] = recurse(rowR+1, colR+1, test);
			vals[3] = recurse(rowR+1, colR, test);
			vals[4] =recurse(rowR+1, colR-1, test);
			output = vals[4];
			for(int i = 0; i<4;i++ ) {
				if(vals[i]>=vals[i+1]) {
					output = vals[i];
				}
			}
			return board[rowR][colR]+output;
		}
		if(colR-1==-1) {
			vals[0] = recurse(rowR-1, colR, test);
			vals[1] = recurse(rowR+1, colR, test);
			vals[2] = recurse(rowR+1, colR+1, test);
			vals[3] = recurse(rowR, colR+1, test);
			vals[4] =recurse(rowR-1, colR+1, test);
			output = vals[4];
			for(int i = 0; i<4;i++ ) {
				if(vals[i]>=vals[i+1]) {
					output = vals[i];
				}
			}
			return board[rowR][colR]+output;
		}
		vals[0] = recurse(rowR+1, colR, test);
		vals[1] = recurse(rowR, colR+1, test);
		vals[2] = recurse(rowR+1, colR+1, test);
		vals[3] = recurse(rowR-1, colR, test);
		vals[4] =recurse(rowR, colR-1, test);
		vals[5] =recurse(rowR-1, colR-1, test);
		vals[6] =recurse(rowR+1, colR-1, test);
		vals[7] =recurse(rowR-1, colR+1, test);
		
		output = vals[7];
		for(int i = 0; i<7;i++ ) {
			if(vals[i]>=vals[i+1]) {
				output = vals[i];
			}
		}
		//printPrev(test);
		return board[rowR][colR]+output;
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
	 * @param p is the ARrayList to be printed
	 * @return none
	 * 
	 * Prints out the arraylist of Points prev, this is only ever called by recurse
	 * 
	 */
	public static void printPrev(ArrayList<Point> p ) {
		System.out.println();
		System.out.print("{ ");
		for(int i = 0; i<p.size();i++) {
			System.out.print("("+p.get(i).getX()+", "+p.get(i).getY()+"), ");
		}
		System.out.print("}");
		System.out.println();
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
		ArrayList<Point> prev = new ArrayList<Point>();
		prev.add(new Point(-1,-1));
		//READ THE JAVADOC FOR RECURSE
		//System.out.println(""+recurse(startRow, startCol, prev));
		input.close();
	}
}
