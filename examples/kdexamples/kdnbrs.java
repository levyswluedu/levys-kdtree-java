// Illustrates n-nearest-neighbors method of KDTree class.  Creates a KDTree
// of M keys, and finds N neighbors of D-dimensional point in center of space
// (all coords = 0.5), with N, D, M specified on command line.

import edu.wlu.cs.levy.CG.KDTree;

import java.util.List;
import java.util.Iterator;

class kdnbrs {

    public static void main(String [] args) {

	java.util.Random r = new java.util.Random(0);

	if (args.length < 3) {
	    System.err.print("Usage: java kdnbrs <# points> <# dims>");
	    System.err.println(" <# nbrs>");
	    System.exit(1);
	}

	int m = Integer.parseInt(args[0]);
	int d = Integer.parseInt(args[1]);
	int n = Integer.parseInt(args[2]);

	double [][] keys = new double [m][d];

	double [] targ = new double [d];
	for (int k=0; k<d; ++k) {
	    targ[k] = 0.5;
	}

	// make a D-dimensional KD-tree
	KDTree<Integer> kd = new KDTree<Integer>(d);

	try {

	    // add M randomly keyed nodes

	    for (int i=0; i<m; ++i) {
		for (int j=0; j<d; ++j) {
		    keys[i][j] = r.nextDouble();
		}
		kd.insert(keys[i], i);
	    }

	    // get N nearest neighbors and show their keys

	    long start = time();

	    List<Integer> nbrs = kd.nearest(targ, n);
	
	    System.err.println((time() - start) + " msec");

	    //System.exit(0);

	    for (int j : nbrs) {
		for (int k=0; k<d; ++k) {
		    System.out.print(keys[j][k] + " ");
		}
		System.err.println();
	    }
	}
	catch (Exception e) {
	    System.err.println(e);
	}
    }	

    private static long time() {
	java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
	return cal.getTimeInMillis();
    }



}
