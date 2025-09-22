########################(beg of [Makefile])########################

test::

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

test:: \
MyStackListTest
MyStackListTest: \
MyStackListTest.class; java MyStackListTest
MyStackListTest.class: \
MyStackListTest.java; javac MyStackListTest.java

###################################################################

test:: \
MyStackArrayTest
MyStackArrayTest: \
MyStackArrayTest.class; java MyStackArrayTest
MyStackArrayTest.class: \
MyStackArrayTest.java; javac MyStackArrayTest.java

########################(end of [Makefile])########################
