fichJar = pi.jar
DOC_DIR=html

limpiar:
	rm -f *.jar
	rm -rf bin	

compilar:
	rm -f *.jar
	rm -rf bin
	mkdir bin
	find src/main/java -name "*.java" | xargs javac -cp bin -d bin

jar: compilar
	jar cvfm $(fichJar) Manifest.txt -C bin . 

javadoc:
	mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) -encoding utf-8 -docencoding utf-8 -charset utf-8 $(shell find ./src/main/java -type f -name "*.java")

ejecutar:
	java -cp bin pr2.App
