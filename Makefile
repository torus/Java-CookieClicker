TARGET = Ex_11_1001015132.class

run: Ex_11_1001015132.class
	java Ex_11_1001015132

Ex_11_1001015132.class: CookieClicker.java

%.class: %.java
	javac $^
