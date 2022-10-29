all:
	make clean && make game && java Game

game:
	javac Game.java

clean:
	rm -f *.class