package minimax;

import java.util.Scanner;

public class TicTacToe {
	
	private char[][] board;
	
	public TicTacToe() {
		this.board = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = '#';
			}
		}
		print();
	}
	
	public void userPlay(Scanner scan) {
		System.out.println("Jogue (1-9):");
		int location = scan.nextInt();
		if(location < 1 || location > 9 || !isAvailable(location)) {
			System.out.println("Jogada invalida!");
			userPlay(scan);
			return;
		}
		set('X', location);
		print();
		if(getWinner() == 'X') {
			System.out.println("Usuario venceu!");
			System.exit(0);
		}
		iaPlay(scan);
	}
	
	public void iaPlay(Scanner scan) {
		int bestLocationI = 0, bestLocationJ = 0;
		int bestScore = Integer.MIN_VALUE;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '#') {
					board[i][j] = 'O';
					int score = minimax(0, true);
					board[i][j] = '#';
					if(score > bestScore) {
						bestScore = score;
						bestLocationI = i;
						bestLocationJ = j;
					}					
				}
			}
		}
		board[bestLocationI][bestLocationJ] = 'O';
		System.out.println("IA:");
		print();
		if(getWinner() == 'O') {
			System.out.println("IA venceu!");
			System.exit(0);
		}
		userPlay(scan);
	}
	
	private int minimax(int depth, boolean isMaximizing) {
		char winner = getWinner();
		if(winner == 'X') {
			return depth - 10;
		} else if(winner == 'O') {
			return 10 - depth;
		}
		
		if(isMaximizing) {
			int bestScore = Integer.MIN_VALUE;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(board[i][j] == '#') {
						board[i][j] = 'O';
						int score = minimax(depth + 1, false);
						board[i][j] = '#';
						bestScore = Math.max(bestScore, score);
					}
				}
			}
			return bestScore;
		} else {
			int bestScore = Integer.MAX_VALUE;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(board[i][j] == '#') {
						board[i][j] = 'X';
						int score = minimax(depth + 1, true);
						board[i][j] = '#';
						bestScore = Math.min(bestScore, score);
					}
				}
			}			
			return bestScore;
		}
	}
	
	private char getWinner() {
		if(board[0][0] == board[0][1] && board[0][0] == board[0][2]) { //1 linha
			return board[0][0];
		} else if(board[1][0] == board[1][1] && board[1][0] == board[1][2]) { //2 linha
			return board[1][0];
		} else if(board[2][0] == board[2][1] && board[2][0] == board[2][2]) {// 3 linha
			return board[2][0];
		} else if(board[0][0] == board[1][0] && board[0][0] == board[2][0]) {//1 coluna
			return board[0][0];
		} else if(board[0][1] == board[1][1] && board[0][1] == board[2][1]) {//2 coluna
			return board[0][1];
		} else if(board[0][2] == board[1][2] && board[0][2] == board[2][2]) {//3 coluna
			return board[0][2];
		} else if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {//diagonal 1
			return board[0][0];
		} else if(board[0][2] == board[1][1] && board[0][2] == board[2][0]) {//diagonal 2
			return board[0][2];
		} else {
			return 'e';
		}
	}
	
	private void set(char player, int location) {
		switch (location) {
		case 1:
			board[0][0]	= player;
			break;
		case 2:
			board[0][1]	= player;
			break;
		case 3:
			board[0][2]	= player;
			break;
		case 4:
			board[1][0]	= player;
			break;
		case 5:
			board[1][1]	= player;
			break;
		case 6:
			board[1][2]	= player;
			break;
		case 7:
			board[2][0]	= player;
			break;
		case 8:
			board[2][1]	= player;
			break;
		case 9:
			board[2][2]	= player;
			break;
		}
	}
	
	private boolean isAvailable(int location) {
		switch (location) {
		case 1:
			if(board[0][0] == '#')
			return true;		
		case 2:
			if(board[0][1] == '#')
				return true;
		case 3:
			if(board[0][2] == '#')
				return true;
		case 4:
			if(board[1][0] == '#')
				return true;
		case 5:
			if(board[1][1] == '#')
				return true;
		case 6:
			if(board[1][2] == '#')
				return true;
		case 7:
			if(board[2][0] == '#')
				return true;
		case 8:
			if(board[2][1] == '#')
				return true;
		case 9:
			if(board[2][2] == '#')
				return true;
		}
		return false;
	}
	
	private void print() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
