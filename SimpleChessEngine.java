import java.io.*;
import java.util.*;

public class SimpleChessEngine {
	/*1
	3 3 4
	R C 4
	N B 3
	Q C 1
	Q A 4
	N A 2
	R C 2
	*/
    
    private static class Piece {
        int row;
        int col;
        int piece;
        boolean alive;
        public Piece(int r, int c, int p){
            row = r;
            col = c;
            piece = p;
            alive = true;
        }
    }
    
    static int moves;
    
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(s.readLine());
        for(int qs = 0; qs < q; qs++){
            int[][] board = new int[4][4];
            StringTokenizer st = new StringTokenizer(s.readLine());
            int w = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(m % 2 == 0) moves = m-1;
            else moves = m;
            Piece[] white = new Piece[w];
            Piece[] black = new Piece[b];
            for(int i = 0; i < w; i++){
                st = new StringTokenizer(s.readLine());
                char c = st.nextToken().charAt(0);
                int colm = (int)st.nextToken().charAt(0)-'A';
                int row = Integer.parseInt(st.nextToken())-1;
                if(c == 'Q'){
                    board[row][colm] = 1;
                    white[i] = new Piece(row, colm, 1);
                }else if (c == 'N'){
                    board[row][colm] = 2;
                    white[i] = new Piece(row, colm, 2);
                }else if (c == 'B'){
                    board[row][colm] = 3;
                    white[i] = new Piece(row, colm, 3);
                } else {
                    board[row][colm] = 4;
                    white[i] = new Piece(row, colm, 4);
                }
            }
            for(int i = 0; i < b; i++){
                st = new StringTokenizer(s.readLine());
                char c = st.nextToken().charAt(0);
                int colm = (int)st.nextToken().charAt(0)-'A';
                int row = Integer.parseInt(st.nextToken())-1;
                if(c == 'Q'){
                    board[row][colm] = 5;
                    black[i] = new Piece(row, colm, 5);
                }else if (c == 'N'){
                    board[row][colm] = 6;
                    black[i] = new Piece(row, colm, 6);
                }else if (c == 'B'){
                    board[row][colm] = 7;
                    black[i] = new Piece(row, colm, 7);
                } else {
                    board[row][colm] = 8;
                    black[i] = new Piece(row, colm, 8);
                }
            }
            boolean x = solve(board, black, white, 1, true);
            if(x)System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean solve(int[][] board, Piece[] black, Piece[] white, int turn, boolean whiteTurn){
        if(turn > moves) return false;
        if(whiteTurn){
            for(int i = 0; i < white.length; i++){
                Piece p = white[i];
                if(board[p.row][p.col] != p.piece){
                    continue;
                }
                if(p.piece == 1){
                    if(p.col + 1 < board.length){
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] == 5){
                                return true;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] >= 1 && board[p.row][j] <= 4){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.col - 1 >= 0){
                        for(int j = p.col-1; j >= 0; j--){
                            if(board[p.row][j] == 5){
                                return true;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col-1; j >= 0; j--){
                        	if(board[p.row][j] >= 1 && board[p.row][j] <= 4){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row + 1 < board.length){
                        for(int j = p.row+1; j < board.length; j++){
                            if(board[j][p.col] == 5){
                                return true;
                            } else if (board[j][p.col] != 0)break;
                        }
                        for(int j = p.row+1; j < board.length; j++){
                        	if(board[j][p.col] >= 1 && board[j][p.col] <= 4){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0){
                        for(int j = p.row-1; j >= 0; j--){
                            if(board[j][p.col] == 5){
                                return true;
                            } else if (board[j][p.col] != 0){
                                break;
                            }
                        }
                        for(int j = p.row-1; j >= 0; j--){
                        	if(board[j][p.col] >= 1 && board[j][p.col] <= 4){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                } else if (p.piece == 2){
                    if(p.row + 2 < board.length && p.col + 1 < board.length){
                    	if(board[p.row+2][p.col+1] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+2][p.col+1] = p.piece;
                            copy[p.row+2][p.col+1] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row + 2 < board.length && p.col - 1 >= 0){
                    	if(board[p.row+2][p.col-1] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+2][p.col-1] = p.piece;
                            copy[p.row+2][p.col-1] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row - 2 >= 0 && p.col - 1 >= 0) {
                    	if(board[p.row-2][p.col-1] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-2][p.col-1] = p.piece;
                            copy[p.row-2][p.col-1] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row - 2 >= 0 && p.col + 1 < board.length){
                    	if(board[p.row-2][p.col+1] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-2][p.col+1] = p.piece;
                            copy[p.row-2][p.col+1] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row + 1 < board.length && p.col + 2 < board.length){
                    	if(board[p.row+1][p.col+2] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+1][p.col+2] = p.piece;
                            copy[p.row+1][p.col+2] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row + 1 < board.length && p.col - 2 >= 0){
                    	if(board[p.row+1][p.col-2] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+1][p.col-2] = p.piece;
                            copy[p.row+1][p.col-2] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row - 1 >= 0 && p.col - 2 >= 0) {
                    	if(board[p.row-1][p.col-2] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-1][p.col-2] = p.piece;
                            copy[p.row-1][p.col-2] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                    if(p.row - 1 >= 0 && p.col + 2 < board.length){
                    	if(board[p.row-1][p.col+2] == 5){
                    		return true;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-1][p.col+2] = p.piece;
                            copy[p.row-1][p.col+2] = 0;
                    		if(solve(copy, black, white, turn+1, false))return true;
                    	}
                    }
                } else if (p.piece == 3){
                    if(p.row + 1 < board.length && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 5){
                                return true;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 1 && board[j][x] <= 4){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                } else {
                    if(p.col + 1 < board.length){
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] == 5){
                                return true;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col+1; j < board.length; j++){
                        	if(board[p.row][j] >= 1 && board[p.row][j] <= 4){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.col - 1 >= 0){
                        for(int j = p.col-1; j >= 0; j--){
                            if(board[p.row][j] == 5){
                                return true;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col-1; j >= 0; j--){
                        	if(board[p.row][j] >= 1 && board[p.row][j] <= 4){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row + 1 < board.length){
                        for(int j = p.row+1; j < board.length; j++){
                            if(board[j][p.col] == 5){
                                return true;
                            } else if (board[j][p.col] != 0)break;
                        }
                        for(int j = p.row+1; j < board.length; j++){
                        	if(board[j][p.col] >= 1 && board[j][p.col] <= 4){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                    if(p.row - 1 >= 0){
                        for(int j = p.row-1; j >= 0; j--){
                            if(board[j][p.col] == 5){
                                return true;
                            } else if (board[j][p.col] != 0){
                                break;
                            }
                        }
                        for(int j = p.row-1; j >= 0; j--){
                        	if(board[j][p.col] >= 1 && board[j][p.col] <= 4){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))break;
                                else return true;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, false))continue;
                                else return true;
                            }
                        }
                    }
                }
            }
        } else {
        	for(int i = 0; i < black.length; i++){
                Piece p = black[i];
                if(board[p.row][p.col] != p.piece){
                    continue;
                }
                if(p.piece == 5){
                    if(p.col + 1 < board.length){
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] == 1){
                                return false;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] >= 5 && board[p.row][j] <= 8){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.col - 1 >= 0){
                        for(int j = p.col-1; j >= 0; j--){
                            if(board[p.row][j] == 1){
                                return false;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col-1; j >= 0; j--){
                        	if(board[p.row][j] >= 5 && board[p.row][j] <= 8){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row + 1 < board.length){
                        for(int j = p.row+1; j < board.length; j++){
                            if(board[j][p.col] == 1){
                                return false;
                            } else if (board[j][p.col] != 0)break;
                        }
                        for(int j = p.row+1; j < board.length; j++){
                        	if(board[j][p.col] >= 5 && board[j][p.col] <= 8){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0){
                        for(int j = p.row-1; j >= 0; j--){
                            if(board[j][p.col] == 1){
                                return false;
                            } else if (board[j][p.col] != 0){
                                break;
                            }
                        }
                        for(int j = p.row-1; j >= 0; j--){
                        	if(board[j][p.col] >= 5 && board[j][p.col] <= 8){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                } else if (p.piece == 6){
                    if(p.row + 2 < board.length && p.col + 1 < board.length){
                    	if(board[p.row+2][p.col+1] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+2][p.col+1] = p.piece;
                            copy[p.row+2][p.col+1] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row + 2 < board.length && p.col - 1 >= 0){
                    	if(board[p.row+2][p.col-1] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+2][p.col-1] = p.piece;
                            copy[p.row+2][p.col-1] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row - 2 >= 0 && p.col - 1 >= 0) {
                    	if(board[p.row-2][p.col-1] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-2][p.col-1] = p.piece;
                            copy[p.row-2][p.col-1] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row - 2 >= 0 && p.col + 1 < board.length){
                    	if(board[p.row-2][p.col+1] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-2][p.col+1] = p.piece;
                            copy[p.row-2][p.col+1] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row + 1 < board.length && p.col + 2 < board.length){
                    	if(board[p.row+1][p.col+2] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+1][p.col+2] = p.piece;
                            copy[p.row+1][p.col+2] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row + 1 < board.length && p.col - 2 >= 0){
                    	if(board[p.row+1][p.col-2] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row+1][p.col-2] = p.piece;
                            copy[p.row+1][p.col-2] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row - 1 >= 0 && p.col - 2 >= 0) {
                    	if(board[p.row-1][p.col-2] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-1][p.col-2] = p.piece;
                            copy[p.row-1][p.col-2] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                    if(p.row - 1 >= 0 && p.col + 2 < board.length){
                    	if(board[p.row-1][p.col+2] == 1){
                    		return false;
                    	} else {
                    		int[][] copy = new int[4][4];
                            for(int z = 0; z < 4; z++){
                                for(int y = 0; y < 4; y++){
                                    copy[z][y] = board[z][y];
                                }
                            }
                            copy[p.row-1][p.col+2] = p.piece;
                            copy[p.row-1][p.col+2] = 0;
                    		if(solve(copy, black, white, turn+1, true))return false;
                    	}
                    }
                } else if (p.piece == 7){
                    if(p.row + 1 < board.length && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row+1; j < board.length; j++){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row + 1 < board.length && p.col - 1 >= 0){
                        int x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j = p.row+1; j < board.length; j++){
                            x--;
                            if(x < 0)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0 && p.col + 1 < board.length){
                        int x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] == 1){
                                return false;
                            } else if (board[j][x] != 0){
                                break;
                            }
                        }
                        x = p.col;
                        for(int j  = p.row-1; j >= 0; j--){
                            x++;
                            if(x >= board.length)break;
                            if(board[j][x] >= 5 && board[j][x] <= 8){
                                break;
                            }
                            if (board[j][x] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][x] = p.piece;
                                copy[j][x] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                } else {
                    if(p.col + 1 < board.length){
                        for(int j = p.col+1; j < board.length; j++){
                            if(board[p.row][j] == 1){
                                return false;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col+1; j < board.length; j++){
                        	if(board[p.row][j] >= 5 && board[p.row][j] <= 8){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.col - 1 >= 0){
                        for(int j = p.col-1; j >= 0; j--){
                            if(board[p.row][j] == 1){
                                return false;
                            } else if (board[p.row][j] != 0)break;
                        }
                        for(int j = p.col-1; j >= 0; j--){
                        	if(board[p.row][j] >= 5 && board[p.row][j] <= 8){
                                break;
                            }
                            if (board[p.row][j] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[p.row][j] = p.piece;
                                copy[p.row][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row + 1 < board.length){
                        for(int j = p.row+1; j < board.length; j++){
                            if(board[j][p.col] == 1){
                                return false;
                            } else if (board[j][p.col] != 0)break;
                        }
                        for(int j = p.row+1; j < board.length; j++){
                        	if(board[j][p.col] >= 5 && board[j][p.col] <= 8){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                    if(p.row - 1 >= 0){
                        for(int j = p.row-1; j >= 0; j--){
                            if(board[j][p.col] == 1){
                                return false;
                            } else if (board[j][p.col] != 0){
                                break;
                            }
                        }
                        for(int j = p.row-1; j >= 0; j--){
                        	if(board[j][p.col] >= 5 && board[j][p.col] <= 8){
                                break;
                            }
                            if (board[j][p.col] != 0){
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))break;
                                else return false;
                            } else {
                                int[][] copy = new int[4][4];
                                for(int z = 0; z < 4; z++){
                                    for(int y = 0; y < 4; y++){
                                        copy[z][y] = board[z][y];
                                    }
                                }
                                copy[j][p.col] = p.piece;
                                copy[j][p.col] = 0;
                                if(!solve(copy, black, white, turn+1, true))continue;
                                else return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
