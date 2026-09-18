import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] grid;
    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException("Error! Grid must have dimensions greater than zero!");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
    }
    public void open(int row, int col){
        if(row > n - 1 || col > n - 1 || col <= 0 || row <= 0){
            throw new IllegalArgumentException("Error! Index outside its prescribed range [1,n-1]!");
        }
        grid[row][col] = true;
        if(grid[row - 1][col - 1]){

        }
    }
    public boolean isOpen(int row, int col){
        return grid[row][col];
    }
    public int numberOfOpenSites(){
        int count = 0;
        for(boolean[] row : grid){
            for(boolean cell : row){
                if(cell){
                    count++;
                }
            }
        }
        return count;
    }
    public boolean percolates(){
        return false;
    }

    public static void main(String[] args) {

    }
}
