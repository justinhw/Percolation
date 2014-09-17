Percolation
======

************************************************
Author: Justin Wong

Written in: Java

Written for: Algorithms 1 - Princeton University

I hereby declare that this code is a product 
of my own efforts. This coded solution has
not been plagiarized from other sources and
has not been plagiarized by others.
************************************************

This repository contains three important files: 
  
    1) Percolation.java
  
    2) PercolationStats.java
  
    3) PercolationVisualizer.java

The files also use Princeton's StdLib and algs4 libraries.

The files aim to simulate percolation models such as: "what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)?" - Princeton University. 

Specifically, this program will read in a set of instructions indicating which cells in a given grid to open (if available) and will try to find a route from one end to the other (percolation). The program will indicate at the end whether the system will percolate or not. To do this, the program uses a weighted quick union algorithm.

Instructions to run the program:

    1) Download zip file.

    2) In the percolation-testing.zip you will find several text files with different instructions (or percolation problems).

    3) Using Eclipse (or any Java IDE), edit PercolationVisualizer.java to read in whichever instructions you wish.

    4) Compile and run PercolationVisualizer.java.
    
    

Example:

![alt tag](http://puu.sh/bCmfG/4f536fde33.png)
