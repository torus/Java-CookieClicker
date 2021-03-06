NUMBER = 15
BASENAME = Ex_$(NUMBER)_1001015132
TARGET = $(BASENAME).class
ZIPDIR = 1001015132_$(NUMBER)
SOURCES = CookieClicker.java $(BASENAME).java

run: $(TARGET)
	java $(BASENAME)

zip: $(ZIPDIR).zip

$(ZIPDIR).zip: $(ZIPDIR)
	cp -R $(SOURCES) resources ../$(NUMBER)/*.doc $(ZIPDIR)
	[ -f $(ZIPDIR).zip ] && rm $(ZIPDIR).zip || true
	zip -r $(ZIPDIR).zip $(ZIPDIR)

$(ZIPDIR):
	[ -d $(ZIPDIR) ] && rm -r $(ZIPDIR) || true
	mkdir -p $(ZIPDIR)

$(TARGET): $(SOURCES)

%.class: %.java
	javac -Xlint:deprecation $^

clean:
	rm -f *.zip *.class *~
	rm -rf 1001015132_*
