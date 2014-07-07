NUMBER = 12
BASENAME = Ex_$(NUMBER)_1001015132
TARGET = $(BASENAME).class
ZIPDIR = $(BASENAME)
SOURCES = CookieClicker.java $(BASENAME).java

run: $(TARGET)
	java $(BASENAME)

zip: $(ZIPDIR)
	zip -r $(ZIPDIR).zip $(ZIPDIR)

$(ZIPDIR):
	mkdir -p $(ZIPDIR)
	[ -d $(ZIPDIR) ]
	cp $(SOURCES) $(ZIPDIR)

$(TARGET): $(SOURCES)

%.class: %.java
	javac $^
