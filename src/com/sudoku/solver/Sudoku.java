package com.sudoku.solver;

public class Sudoku {
	// initialisation de la grille
		public static int[][] GRID_TO_SOLVE = {
				{0,0,0,8,0,5,0,0,0},
				{0,5,0,0,0,0,0,6,0},
				{0,0,0,4,6,9,0,0,0},
				{5,4,0,0,0,0,0,7,2},
				{0,0,3,1,0,7,8,0,0},
				{1,0,9,0,0,0,6,0,5},
				{0,3,5,0,1,0,7,2,0},
				{4,9,0,0,0,0,0,8,3},
				{2,0,0,0,0,0,0,0,4},
		};
		
		private int[][] board;
		public static final int EMPTY = 0; // cellule vide
		public static final int SIZE = 9; // taille de la grille du sudoku
		
		public Sudoku(int[][] board) {
			this.board = new int[SIZE][SIZE];
			
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					this.board[i][j] = board[i][j];
				}
			}
		}
		
		// Check s'il est possible d'avoir un nombre dans le row
		private boolean isInRow(int row, int number) {
			for (int i = 0; i < SIZE; i++)
				if (board[row][i] == number)
					return true;
			
			return false;
		}
		
		// we check si il est possible d'avoir un nombre dans la colonne
		private boolean isInCol(int col, int number) {
			for (int i = 0; i < SIZE; i++)
				if (board[i][col] == number)
					return true;
			
			return false;
		}
		
		// we check s'il est possible d'avoir un nombre dans le carre 3x3
		private boolean isInBox(int row, int col, int number) {
			int r = row - row % 3;
			int c = col - col % 3;
			
			for (int i = r; i < r + 3; i++)
				for (int j = c; j < c + 3; j++)
					if (board[i][j] == number)
						return true;
			
			return false;
		}
		// on check s'il ya un nombre possible dans le row, col et que la position est ok
		private boolean isOk(int row, int col, int number) {
			return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
		}
		
		// Solve method. We will use a recursive BackTracking algorithm.
	       public boolean solve() {
	        for (int row = 0; row < SIZE; row++) {
	         for (int col = 0; col < SIZE; col++) {
	          // on cherche les cellules vide é.é
	          if (board[row][col] == EMPTY) {
	            // we try possible numbers
	            for (int number = 1; number <= SIZE; number++) {
	              if (isOk(row, col, number)) {
	                //si number ok et qu'il respecte les contraintes du sudoku 
	                board[row][col] = number;

	                if (solve()) { // algorithme de  backtracking recursively
	                  return true;
	                } else { // s'il n'y a pas de solution, on vide la cellule et on continue
	                  board[row][col] = EMPTY;
	                }
	             }
	            }

	            return false; // return false
	           }
	          }
	         }

	         return true; // Sudoku solved
		}
		
		public void display() {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					System.out.print(" " + board[i][j]);
				}
			
				System.out.println();
			}
			
			System.out.println();
		}
		
		public static void main(String[] args) {
			Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
			System.out.println("La grille Sudoku a résoudre ");
			sudoku.display();
			
			// we try resolution
			if (sudoku.solve()) {
				System.out.println("Sudoku is solved ");
				System.out.println("Maintenant tu peux montrer à ton papi et ta mamie que c'est toi le plus fort ");
				sudoku.display();
			} else {
				System.out.println("Alert Alert Unsolvable");
			}
		}
	
	
}
