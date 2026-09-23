import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.Random;

public class PercolationStats {
    private int n;
    private int trials;
    private double mean, stddev;
public PercolationStats(int n, int trials){
    if(n <= 0 || trials <= 0){
        throw new IllegalArgumentException("Error! n and trials must be greater than zero!");
    }
    this.n = n;
    this.trials = trials;
    double[] tests = new double[trials];
    for(int i = 0; i < trials; i++){
        Percolation p = new Percolation(n);
        while(!p.percolates()){
            p.open((int) (StdRandom.uniformDouble() * n) + 1, (int) (StdRandom.uniformDouble() * n) + 1);
        }
        tests[i] = (double) p.numberOfOpenSites() / (n * n);
    }
    this.mean = StdStats.mean(tests);
    this.stddev = StdStats.stddev(tests);
}
public double mean(){
    return mean;
}
public double stddev(){
    return stddev;
}
public double confidenceLo(){
    return mean - 1.96 * stddev/Math.sqrt(trials);
}
public double confidenceHi(){
    return mean + 1.96 * stddev/ Math.sqrt(trials);
}

    public static void main(String[] args) {
        PercolationStats large = new PercolationStats(200, 1000);
            System.out.println(large.mean());
    }
}
