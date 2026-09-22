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
            weightedQuickUnionUF.union(0,row * (n - 1) + col);
        }
        if(row == n){
            weightedQuickUnionUF.union(n * n + 1, row * (n - 1) + col);
        }
        makeUnions(row, col);
    }
    private void makeUnions(int r, int c){
        n = n - 1;
        if(checkValid(r - 1, c) && grid[r-2][c-1]){
            weightedQuickUnionUF.union(r * n + c, (r - 1) * n + c);
        }
        if(checkValid(r + 1, c) && grid[r][c-1]){
            weightedQuickUnionUF.union(r * n + c, (r + 1) * n + c);
        }
        if(checkValid(r, c - 1) && grid[r-1][c-2]){
            weightedQuickUnionUF.union(r * n + c, r * n + (c - 1));
        }
        if(checkValid(r, c + 1) && grid[r-1][c]){
            weightedQuickUnionUF.union(r * n + c, r * n + (c + 1));
        }
        n = n + 1;
    }
    private boolean checkValid(int r, int c){
        return r <= n && c <= n && r > 0 && c > 0;
    }
    public boolean isOpen(int row, int col){
        if(!checkValid(row, col)){
            throw new IllegalArgumentException("Error! Index outside its prescribed range [1,n]!");
        }
        return grid[row - 1][col - 1];
    }
    public boolean isFull(int row, int col){
        return isOpen(row, col) && weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(row * (n - 1) + col);
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
        return weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(n * n + 1);
    }

    public static void main(String[] args) {
        System.out.println("--- all sites in a 3x3 grid ---");
        Percolation three = new Percolation(3);
        three.open(1,1);
        three.open(1,2);
        three.open(1,3);
        three.open(2,1);
        three.open(2,2);
        three.open(2,3);
        three.open(3,1);
        three.open(3,2);
        three.open(3,3);

        Percolation connected = new Percolation(5);
        connected.open(1, 1);
        connected.open(2, 1);
        System.out.println(connected.isFull(2, 1));

        Percolation backwash = new Percolation(3);
        backwash.open(1, 2);
        backwash.open(2, 2);
        backwash.open(3, 2);
        System.out.println(backwash.percolates());
    }
}
