// illustrates differences between KD tree search and exhaustive search

import edu.wlu.cs.levy.CG.KDTree;

import java.util.Date;
import java.util.Random;

class kdtime {

    public static void main(String [] args) {

	// grab command-line args
	if (args.length < 3) {
	    System.err.print("Usage: java kdtime <# points> <# dims> ");
	    System.err.println("<# trials> [seed]");
	    System.exit(1);
	}
	int n = Integer.parseInt(args[0]);
	int k = Integer.parseInt(args[1]);
	int t = Integer.parseInt(args[2]);

	// generate N random K-dimensional points in (0,1)
	Random r = args.length > 3 ?               // support random seed as
	    new Random(Long.parseLong(args[3])) :  // optional fourth arg
	    new Random();
	double [][] x = new double[n][k];
	for (int i=0; i<n; ++i) {
	    for (int j=0; j<k; ++j) {
		x[i][j] = r.nextDouble();
	    }
	}

	// build KD-tree with indices as values
	KDTree<Integer> kd = new KDTree<Integer>(k);
	try {
	    for (int i=0; i<n; ++i) {
		kd.insert(x[i], i);
	    }
	}
	catch (Exception e) {
	    System.err.println(e);
	    System.exit(0);
	}

	// compare search
	linear_search(x, t, r);
	kd_search(x, t, kd, r);

	// compare nearest-neighbor
	linear_nearest(x, t, r);
	kd_nearest(x, t, kd, r);
    }

    // do linear search
    static void linear_search(double [][] x, int t, Random r) {
	
	long before = getTimeMillis();
	for (int i=0; i<t; ++i) {

	    // pick a random point
	    double [] targ = x[(int)(x.length*r.nextDouble())];
	    
	    search(x, targ);
	}
	long millis = getTimeMillis() - before;
	System.out.println(t + " linear searches took " + millis + " msec.");
    }

    
    // do KD-tree search
    static void kd_search(double [][] x, int t, KDTree kd, Random r) {

	long before = getTimeMillis();
	try {
	    for (int i=0; i<t; ++i) {
		
		// pick a random point
		double [] targ = x[(int)(x.length*r.nextDouble())];
		
		kd.search(targ);
	    }
	}
	catch (Exception e) {
	    System.err.println(e);
	    System.exit(0);
	}	
	long millis = getTimeMillis() - before;
	System.out.println(t + " KD-tree searches took " + millis + " msec.");
    }

	
    // do linear nearest neighbor
    static void linear_nearest(double [][] x, int t, Random r) {

	int k = x[0].length;
	long before = getTimeMillis();
	for (int i=0; i<t; ++i) {

	    double [] targ = random_point(r, k);
	    
	    int n = neighbor(x, targ);

	}
	long millis = getTimeMillis() - before;
	System.out.println(t + " linear nearest took " + millis + " msec.");
	
    }


    // do KD-tree nearest neighbor
    static void kd_nearest(double [][] x, int t, KDTree kd, Random r) {

	int k = x[0].length;
	long before = getTimeMillis();
	try {
	    for (int i=0; i<t; ++i) {
		
		double [] targ = random_point(r, k);
		Integer nbr = (Integer)kd.nearest(targ);
		int n = nbr.intValue();
	    }
	}
	catch (Exception e) {
	    System.err.println(e);
	    System.exit(0);
	}	
	long millis = getTimeMillis() - before;
	System.out.println(t + " KD-tree nearest took " + millis + " msec.");
    }
    // linear search for exact match
    private static int search(double [][] x, double [] targ) {

	for (int i=0; i<x.length; ++i) {
	    if (equal(x[i], targ)) return i;
	}

	return -1;
    }

    // linear search for index of neighbor
    private static int neighbor(double [][] x, double [] targ) {

	double mindst = Double.POSITIVE_INFINITY;
	int minidx = -1;

	for (int i=0; i<x.length; ++i) {
	    double d = sqrdst(x[i], targ);
	    if (d < mindst) {
		mindst = d;
		minidx = i;
	    }
	}
	
	return minidx;
    }

    // square of Euclidean distance between points
    private static double sqrdst(double [] p, double [] q) {
	double dst = 0;
	for (int i=0; i<p.length; ++i) {
	    double dif = p[i] - q[i];
	    dst += dif*dif;
	}
	return dst;
    }

    // point equality test
    private static boolean equal(double [] p, double [] q) {
	for (int i=0; i<p.length; ++i) {
	    if (p[i] != q[i]) return false;
	}
	return true;
    }

    private static double [] random_point(Random r, int k) {

	double [] x = new double[k];
	for (int i=0; i<k; ++i) {
	    x[i] = r.nextDouble();
	}
	return x;
    }

    private static long getTimeMillis() {
	Date d = new Date();
	return d.getTime();
    }
}
