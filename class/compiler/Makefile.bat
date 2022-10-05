javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" scanner/Tokens.java
javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" scanner/Scanner1.java

javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" parser/Nodo.java
javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" parser/ScannerCup.java
javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" parser/Sintax.java
javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" parser/sym.java


javac -cp ".:/java_cup.jar:/java-cup-11a.jar:/JFlex.jar" Compiler.java
