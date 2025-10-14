############################################################
JAVA=java
JAVAC=javac
MYCPATH=.::./../Code/Library.jar
############################################################

all::

############################################################

clean: ; rm -f *~
cleanall: clean
cleanall: ; rm -f *.class

############################################################

all:: \
Assign05_01
Assign05_01: \
Assign05_01.class; \
$(JAVA) -cp $(MYCPATH) Assign05_01
Assign05_01.class: \
Assign05_01.java ; \
$(JAVAC) -cp $(MYCPATH) Assign05_01.java

############################################################

all:: \
Assign05_02
Assign05_02: \
Assign05_02.class; \
$(JAVA) -cp $(MYCPATH) Assign05_02
Assign05_02.class: \
Assign05_02.java ; \
$(JAVAC) -cp $(MYCPATH) Assign05_02.java

############################################################
