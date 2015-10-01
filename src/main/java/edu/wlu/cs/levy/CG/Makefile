ALL =  KDTree.class

PUB = KDTree.java KeyMissingException.java KeySizeException.java \
      KeyDuplicateException.java

all: $(ALL)

JFLAGS = -Xlint -classpath ../../../../../

KDTree.class: KDTree.java KDNode.class HRect.class KeySizeException.class \
              KeyMissingException.class KeyDuplicateException.class \
              NearestNeighborList.class DistanceMetric.class
	javac $(JFLAGS) KDTree.java

KDNode.class: KDNode.java HRect.class  KeySizeException.class \
             KeyMissingException.class KeyDuplicateException.class
	javac $(JFLAGS) KDNode.java

HRect.class: HRect.java HPoint.class
	javac $(JFLAGS) HRect.java

HPoint.class: HPoint.java
	javac $(JFLAGS) HPoint.java

KeySizeException.class: KeySizeException.java
	javac $(JFLAGS) KeySizeException.java

KeyMissingException.class: KeyMissingException.java
	javac $(JFLAGS) KeyMissingException.java

KeyDuplicateException.class: KeyDuplicateException.java
	javac $(JFLAGS) KeyDuplicateException.java

NearestNeighborList.class: NearestNeighborList.java
	javac $(JFLAGS) NearestNeighborList.java
	
DistanceMetric.class: DistanceMetric.java
	javac $(JFLAGS) DistanceMetric.java
	
doc: $(PUB)
	javadoc -classpath ../../../../../ -public $(PUB)
	
mvdoc:
	rm -rf docs/*
	mv *.html docs
	mv stylesheet.css docs
	mv package-list docs
	mv edu docs
	
clean:
	rm *.class  *.html package-list stylesheet.css *~

backup:
	cp *.java bak
	cp Makefile bak

restore:
	cp bak/*.java .
	cp bak/Makefile .

