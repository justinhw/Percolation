/*----------------------------------------------------------------
 *  Author:        Justin Wong
 *  Written:       14/09/2014
 *  Last updated: 16/09/2014
 *
 *  Compilation:   PercolationStats.java
 *  Calculates statistics for several iterations of percolation
 *----------------------------------------------------------------*/

public class PercolationStats {
	private double[] results;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		int i, j, numOpen;
		Percolation perc;
		results = new double[T];
		
		if (N <= 0 || T<= 0){
			throw new IllegalArgumentException("Nonnegative arguments");
		}
		
		for (int m=0; m < T; m++){
			numOpen = 0;
			perc = new Percolation(N);
			while(!perc.percolates()){
				do{ //Find coordinates i, j until a closed cell is found
					i = StdRandom.uniform(1, N +1);
					j = StdRandom.uniform(1, N +1);
				}while(perc.isOpen(i,j));
				perc.open(i,j);
				numOpen++;
			}
			results[m] = ((double) numOpen) / (N*N);
		}
	}
	
	// sample mean of percolation threshold
    public double mean(){
    	return StdStats.mean(results);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
    	if (results.length <= 1){
    		return Double.NaN;
    	}
    	
    	return StdStats.stddev(results);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo(){
    	return mean() - ((1.96*stddev())/Math.sqrt(results.length));
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi(){
    	return mean() + ((1.96*stddev())/Math.sqrt(results.length));
    }
    
    // test client, described below
    public static void main(String[] args){
    	int N = Integer.parseInt(args[0]);
    	int T = Integer.parseInt(args[1]);
    	PercolationStats percStats = new PercolationStats(N, T);
    	
    	String confidence = percStats.confidenceLo() + ", " + percStats.confidenceHi();
    	StdOut.println("mean = " + percStats.mean());
    	StdOut.println("stddev = " + percStats.stddev());
    	StdOut.println("95% confidence interval = " + confidence);
    }
}