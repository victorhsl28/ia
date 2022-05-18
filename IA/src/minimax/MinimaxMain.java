package minimax;

import java.util.Scanner;

public class MinimaxMain {
	
	public static TicTacToe game;

	
	public MinimaxMain() {
		game = new TicTacToe();
		Scanner scan = new Scanner(System.in);
		game.userPlay(scan);
	}
	


}
