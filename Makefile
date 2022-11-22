all:
	make clean && make game && java Game

game:
	javac Game.java

jar:
	jar cfm PauwelsPommier.jar MANIFEST.MF *.class

clean:
	rm -f *.class PauwelsPommier.jar