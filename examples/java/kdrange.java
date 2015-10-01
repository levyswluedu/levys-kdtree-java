// illustrates behavior of KDTree range search

import edu.wlu.cs.levy.CG.KDTree;

import java.util.Arrays;

import java.util.List;
import java.util.Iterator;

class kdrange {

    public static void main(String [] args) {

	// check arguments
	if (args.length < 3) {
	    System.err.println("Usage: java kdrange <gridsize> <xradius> " +
                               "<yradius>");
	    System.exit(1);
	}
	int gsize = Integer.parseInt(args[0]);
	int xrad = Integer.parseInt(args[1]);
	int yrad = Integer.parseInt(args[2]);


	// make a KD-tree
	KDTree<Integer> kd = new KDTree<Integer>(2);

	// plot grid and add nodes
	for (int i=0; i<gsize; ++i) {
	    for (int j=0; j<gsize; ++j) {
		int n = i * gsize + j + 1;
		if (i == gsize/2 && j==gsize/2) {
		    System.out.print("*\t");
		}
		else {
		    System.out.print(n + "\t");
		}
		double [] key = {i, j};
		try {
		    kd.insert(key, n);
		}
		catch (Exception e) {
		    System.err.println(e);
		}
	    }
	    System.out.println();
	}
		     

	try {

	    // get objects in range of center
	    double [] lo = {gsize/2-xrad,gsize/2-yrad};
	    double [] hi = {gsize/2+xrad,gsize/2+yrad};
	    List<Integer> o = kd.range(lo, hi);

	    // dump them to stdout
	    for (int i : o) {
		System.out.println(i);
	    }
	}
	catch (Exception e) {
	    System.err.println(e);
	}
    }
}
