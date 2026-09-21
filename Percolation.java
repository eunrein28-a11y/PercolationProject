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
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    }
    public void open(int row, int col){
        if(!checkValid(row, col)){
            throw new IllegalArgumentException("Error! Index outside its prescribed range [1,n]!");
        }
        grid[row - 1][col - 1] = true;
        if(row == 1){
            weightedQuickUnionUF.union(0,(col));
        }
        else if(row == n){
            weightedQuickUnionUF.union(n * n + 1, row * (n - 1) + col);
        }
        else{
            makeUnions(row, col);
        }
    }
    private void makeUnions(int r, int c){
        n = n - 1;
        if(checkValid(r - 1, c)){
            weightedQuickUnionUF.union(r * n + c, (r - 1) * n + c);
        }
        if(checkValid(r + 1, c)){
            weightedQuickUnionUF.union(r * n + c, (r + 1) * n + c);
        }
        if(checkValid(r, c - 1)){
            weightedQuickUnionUF.union(r * n + c, r * n + (c - 1));
        }
        if(checkValid(r, c + 1)){
            weightedQuickUnionUF.union(r * n + c, r * n + (c + 1));
        }
    }
    private boolean checkValid(int r, int c){
        return !(r > n || c > n || r <= 0 || c <= 0);
    }
    public boolean isOpen(int row, int col){
        if(!checkValid(row, col)){
            throw new IllegalArgumentException("Error! Index outside its prescribed range [1,n]!");
        }
        return grid[row - 1][col - 1];
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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(isOpen(i,j) && weightedQuickUnionUF.connected(i * n + j, 0) && weightedQuickUnionUF.connected(i * n + j, n * n + 1)){
                    return true;
                }
            }
        }
        return false;
    }
}
