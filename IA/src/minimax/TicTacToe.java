package minimax;

import java.util.Scanner;

public class TicTacToe {
	
	private char[][] board;
	private int bmX, bmY;
	
	public TicTacToe() {
		this.board = new char[3][3];
		bmX = bmY = 0;
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
		
		System.out.println("IA:");
		minimax(0, 'O', scan);
		board[bmX][bmY] = 'O';
		print();
		userPlay(scan);
	}
	
	private int minimax(int depth, char turn, Scanner scan) {
		char winner = getWinner();
		if(winner == 'X') {
			return -1;
		} else if(winner == 'O') {
			return 1;
		}
		
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		boolean draw = true;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '#') 
					draw = false;
			}
		}
		
		if(draw) {
			return 0;
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '#') {
					
					if(turn == 'O') { //ia
						board[i][j] = 'O';
						int score = minimax(depth + 1, 'X', scan);
						max = Math.max(score, max);
												
						if(score == 0 && depth == 0) {
							bmX = i;
							bmY = j;
						}
						
						if(score == 1) {
							board[i][j] = '#';
							break;
						}
						
						if(i == 2 && j == 2 && max < 0 && depth == 0) {
							bmX = i;
							bmY = j;
						}
						
					} else if(turn == 'X') {
						board[i][j] = 'X';
						int score = minimax(depth + 1, 'O', scan);
						min = Math.min(min, score);
						
						if(min == -1) {
							board[i][j] = '#';
							break;
						}
					}
					
					board[i][j] = '#';
				}
			}
		}
		
		return turn == 'O' ? max : min;
	}
	
	private char getWinner() {
		if(board[0][0] != '#' && board[0][0] == board[0][1] && board[0][0] == board[0][2]) { //1 linha
			return board[0][0];
		} else if(board[1][0] != '#' && board[1][0] == board[1][1] && board[1][0] == board[1][2]) { //2 linha
			return board[1][0];
		} else if(board[2][0] != '#' && board[2][0] == board[2][1] && board[2][0] == board[2][2]) {// 3 linha
			return board[2][0];
		} else if(board[0][0] != '#' && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {//1 coluna
			return board[0][0];
		} else if(board[0][1] != '#' && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {//2 coluna
			return board[0][1];
		} else if(board[0][2] != '#' && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {//3 coluna
			return board[0][2];
		} else if(board[0][0] != '#' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {//diagonal 1
			return board[0][0];
		} else if(board[0][2] != '#' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {//diagonal 2
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

	public int getBmX() {
		return bmX;
	}

	public void setBmX(int bmX) {
		this.bmX = bmX;
	}

	public int getBmY() {
		return bmY;
	}

	public void setBmY(int bmY) {
		this.bmY = bmY;
	}

}
