# K-D tree (Java & C# implemenation)
### by Prof. Simon D. Levy

Installing this JAR into local mvn repo:

```
mvn install:install-file -DgroupId=omegha.kdtree -DartifactId=kdtree -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true -Dfile=kd.jar
```

A KD-tree is a data structure for efficient search and nearest-neighbor(s) computation of points in K-dimensional space. I like programming in Java and couldn't find any Java KD-tree implementations on the Web, so I wrote this one. (There appear to be better implementations now, like the ones mentioned on this site.) I also thank the following contributors:

  - **Eric Burnett**, who patched a memory leak and suggested an improvement for memory allocation.
  - **Bjoern Heckel**, who added the ability to return multiple nearest neighbors, instead of just one.
  - **Andrey Fomin**, who has provided a Polish translation of this page.
  - **Michael Lorton**, who added unit tests, changed the interface to use generics and modern container classes, and made the code thread-safe (though as with all aspects of software, we make no guarantees). If you want to use the unit tests, you will have to obtain the junit package from Sourceforge. Michael is now using KDTree to help match people on his SpeedDate website.

The file kd.jar contains the Java class edu.wlu.cs.levy.CG.KDTree, which supports the standard KD-tree operations insert, delete, equality search, range search, and nearest-neighbor. There is also a version for C#, written by Marco A Alvarez, in KDTreeDLL.zip.

The JAR file contains the class files of course, but also all the source and the Makefile, for anyone wishing to hack the code (KDTree.nearest could probably be improved). The demonstration programs kddemo, kdtime, kdrange, and kdnbrs show you how to use the KDTree class. To run these programs, do the following in Unix (or the equivalent Java commands on your platform);

```
% javac -classpath .:kd.jar kddemo.java
% javac -classpath .:kd.jar kdrange.java
% javac -classpath .:kd.jar kdtime.java
% javac -classpath .:kd.jar kdnbrs.java
% java -classpath .:kd.jar kddemo
% java -classpath .:kd.jar kdrange
% java -classpath .:kd.jar kdtime 10000 2 100
                                 (for example)
% java -classpath .:kd.jar kdnbrs 10000 3 100
                                 (for example)
```

Demonstration programs can be found under examples/ directory

Please send comments, questions, and suggestions to levys@wlu.edu.

## License

Like all software I've posted, KDTree is released under the Gnu Lesser General Public License.

## Contact

Associate Professor and Department Head

Computer Science Department

407 Parmly Hall Washington & Lee University

 Lexington, VA 24450

