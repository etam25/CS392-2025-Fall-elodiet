##################################################################
JAVA=java
JAVAC=javac
##################################################################
clean:: ; rm -f *~
cleanall:: clean
cleanall:: ; rm -f *.class
##################################################################
test:: \
FnInt1Test
FnInt1Test: \
FnInt1Test.class ; \
$(JAVA) -cp .::./../Library.jar FnInt1Test
FnInt1Test.class: FnInt1Test.java; \
$(JAVAC) -cp .::./../Library.jar FnInt1Test.java
##################################################################
test:: \
FnStrnTest
FnStrnTest: \
FnStrnTest.class ; \
$(JAVA) -cp .::./../Library.jar FnStrnTest
FnStrnTest.class: FnStrnTest.java; \
$(JAVAC) -cp .::./../Library.jar FnStrnTest.java
##################################################################
test:: \
FnListTest
FnListTest: \
FnListTest.class ; \
$(JAVA) -cp .::./../Library.jar FnListTest
FnListTest.class: FnListTest.java; \
$(JAVAC) -cp .::./../Library.jar FnListTest.java
##################################################################
test:: \
FnA1szTest
FnA1szTest: \
FnA1szTest.class ; \
$(JAVA) -cp .::./../Library.jar FnA1szTest
FnA1szTest.class: FnA1szTest.java; \
$(JAVAC) -cp .::./../Library.jar FnA1szTest.java
##################################################################
test:: \
LnListTest
LnListTest: \
LnListTest.class ; \
$(JAVA) -cp .::./../Library.jar LnListTest
LnListTest.class: LnListTest.java; \
$(JAVAC) -cp .::./../Library.jar LnListTest.java
##################################################################
######################## end-of(Makefile) ########################
##################################################################
