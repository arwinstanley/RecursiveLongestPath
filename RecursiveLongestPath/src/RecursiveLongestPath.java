import java.util.Scanner;

public class RecursiveLongestPath {
	private static int board [][];
	private static int row;
	private static int col;
	
	public static void fillBoard(int row, int col) {
		board = new int[row][col];
		for(int i = 0; i<row;i++) {
			for(int j = 0; j < col; j++) {
				board[i][j] =((int)Math.random()*4)+3;
			}
		}
	}
	public static void  main(String [] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the number of rows:");
		while(row == 0) {
			System.out.println("Please enter the number of rows:");
			row = input.nextInt();
		}

	}
}
