package tetris;

public class Board {
    public static final int ROWS = 20;
    public static final int COLS = 10;

    private int[][] grid = new int[ROWS][COLS];//this is the main grid that is displayed in the output

    public boolean fits(Tetromino p) {
        int[][] s = p.getShape();
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (s[i][j] == 0) continue;
                int r = p.getRow() + i;
                int c = p.getCol() + j;
                if (r < 0 || r >= ROWS || c < 0 || c >= COLS) return false;
                if (grid[r][c] != 0) return false;
            }
        }
        return true;
    }//this method prevents the piece from moving outside the board or to collide from the other pieces 

    public void lock(Tetromino p) {
        int[][] s = p.getShape();
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (s[i][j] != 0) {
                    grid[p.getRow() + i][p.getCol() + j] = p.getColorId(); //this method checks if there are any barriers and locks the piece into the place by adding that piece color to the grid
                }
            }
        }
    }
    public int clearLines() {//the most important methdo
        int cleared = 0; //cleared variable keeps count of how many rows were cleared, this is needed for scoring in the future
        for (int r = ROWS - 1; r >= 0; r--) {
            boolean full = true;//this method initially assumes that all the boxes in the row are full
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 0) { full = false; break; }// if the assumption is turned down then the "full" variable is set to false and clearing does not take place
            
            }
            if (full) {
                for (int k = r; k > 0; k--) {
                    grid[k] = grid[k - 1];
                }
                grid[0] = new int[COLS];
                cleared++;
                r++;
            }
        }
        return cleared;
    }//once a row is filled, it clears that row

    public int getCell(int r, int c) {
        return grid[r][c];
    }
}
