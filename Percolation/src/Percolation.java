/*----------------------------------------------------------------
 *  Author:        Justin Wong
 *  Written:       14/09/2014
 *  Last updated: 14/09/2014
 *
 *  Compilation:   percolation.java
 *  Runs percolation simulation
 *----------------------------------------------------------------*/

public class Percolation {
	//Variable declarations
	private boolean open = true;
	private boolean closed = false;
	private int size;
	private WeightedQuickUnionUF quickFind;
	//False -> close | True -> open
	private boolean[][] grid; 
	   
	//Private member functions
    private void fillArray(){
    	for (int i = 0; i<size; i++){
    		for (int j = 0; j<size; j++){
    			grid[i][j] = closed;			
    		}
    	}
    }
    
    private void setSize(int N){
    	size = N;
    }
    
    //Percolation API
	// create N-by-N grid, with all sites blocked
    public Percolation(int N){
    	if (N<= 0){
    		throw new IllegalArgumentException("size must be greater than 0");
    	}
    	
    	else{
    		setSize(N);
    		quickFind = new WeightedQuickUnionUF(size*size);
    		grid = new boolean[size][size];
    		fillArray();
    	}
     }
   
    // open site (row i, column j) if it is not already
    public void open(int i, int j){		
    	//Check out of bounds
    	if (i<1 || i>size || j<1 || j>size){
    		throw new IndexOutOfBoundsException("row index i out of bounds");
    	}
    	
    	if (!isOpen(i, j)){
    		grid[i-1][j-1] = open;
    	}
    	
    	//Union adjacent open sites:
    	//Possible to union top?
    	if (i>1 && isOpen(i-1,j)){
    		quickFind.union(size*(i-1)+(j-1), size*(i-2)+(j-1));
    	}
    	
    	//Possible to union bottom?
    	if (i<size && isOpen(i+1,j)){
    		quickFind.union(size*(i-1)+(j-1), size*(i)+(j-1));
    	}
    	
    	//Possible to union left?
    	if (j>1 && isOpen(i,j-1)){
    		quickFind.union(size*(i-1)+(j-1), size*(i-1)+(j-2));
    	}	
    	
    	//Possible to union right?
    	if (j<size && isOpen(i,j+1)){
    		quickFind.union(size*(i-1)+(j-1), size*(i-1)+j);
    	}	
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
    	//Check out of bounds
    	if (i<1 || i>size || j<1 || j>size){
    		throw new IndexOutOfBoundsException("row index i out of bounds");
    	}
    	
    	return grid[i-1][j-1];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
    	//Check out of bounds
    	if (i<1 || i>size || j<1 || j>size){
    		throw new IndexOutOfBoundsException("row index i out of bounds");
    	}
    	
    	for (int index = 0; index<size; index++){
    		if (isOpen(i, j) && quickFind.connected(index, size*(i-1)+(j-1))){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    // does the system percolate?
    public boolean percolates(){
    	for (int i=0; i<size; i++){
    		for (int j=0; j<size; j++){
    			int lastRowMember = (size-1)*size + j;
    			if (quickFind.connected(i, lastRowMember)){
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    // test client, optional
    public static void main(String[] args){
    }
}
