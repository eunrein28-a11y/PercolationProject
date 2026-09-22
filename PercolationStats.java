import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

public class PercolationStats {
    private int n;
    private int trials;
public PercolationStats(int n, int trials){
    this.n = n;
    this.trials = trials;
}
public double mean(){
    double count = 0;
    for(int i = 0; i < trials; i++){
        int c = 0;
        Percolation p = new Percolation(n);
        while(!p.percolates()){
            p.open((int) (Math.random() * n) + 1, (int) (Math.random() * n) + 1);
            c++;
        }
        count += c;
    }
    return (count / (n * n)) / trials;
}
public double stddev(){
    return 0.0;
}
public double confidenceLo(){
    return 0.0;
}
public double confidenceHi(){
    return 0.0;
}

    public static void main(String[] args) {
        PercolationStats large = new PercolationStats(200, 1000);
            System.out.println(large.mean());
    }
}
