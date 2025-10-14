########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp $(Library) MyDeque.java
comp:: ; javac -cp $(Library) MyDequeList.java
comp:: ; javac -cp $(Library) MyDequeFullExn.java
comp:: ; javac -cp $(Library) MyDequeEmptyExn.java

########################(end of [Makefile])########################
