all:
	make clean && make game && java Game

game:
	javac Game.java

jar:
	make clean && make game && jar cfm PauwelsPommier.jar MANIFEST.MF *.class

clean:
	rm -f *.class PauwelsPommier.jar