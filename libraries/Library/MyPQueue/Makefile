########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp $(Library) MyPQueue.java
comp:: ; javac -cp $(Library) MyPQueueBase.java
comp:: ; javac -cp $(Library) MyPQueueArray.java
comp:: ; javac -cp $(Library) MyPQueueFullExn.java
comp:: ; javac -cp $(Library) MyPQueueEmptyExn.java

########################(end of [Makefile])########################
