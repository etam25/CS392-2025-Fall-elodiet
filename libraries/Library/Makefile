########################(beg of [Makefile])########################

clean:: ; rm -f */*~

cleanall:: clean
cleanall:: ; rm -f */*.class

###################################################################

compall:: FnList
compall:: FnArray
compall:: FnTuple
compall:: MyStack
compall:: MyQueue
compall:: FnGtree
compall:: BinSearch

###################################################################

FnList:: ; make -C FnList comp
FnArray:: ; make -C FnArray comp
FnTuple:: ; make -C FnTuple comp

###################################################################

MyStack:: ; make -C MyStack comp
MyQueue:: ; make -C MyQueue comp

###################################################################

FnGtree:: ; make -C FnGtree comp

###################################################################

BinSearch:: ; make -C BinSearch comp

###################################################################L

ROOT=./..

Library_jar: ; \
jar -cvf Library.jar \
$(ROOT)/Library/FnList/*.class \
$(ROOT)/Library/FnArray/*.class $(ROOT)/Library/FnTuple/*.class \
$(ROOT)/Library/MyStack/*.class $(ROOT)/Library/MyQueue/*.class \
$(ROOT)/Library/FnGtree/*.class $(ROOT)/Library/BinSearch/*.class

###################################################################

########################(end of [Makefile])########################
