########################(beg of [Makefile])########################

clean:: ; rm -f */*~

cleanall:: clean
cleanall:: ; rm -f */*.class

###################################################################

compall:: FnGseq
compall:: FnInt1
compall:: FnStrn
compall:: FnList
compall:: FnA1sz
compall:: FnTuple
<<<<<<< HEAD
=======
compall:: MyRefer
>>>>>>> upstream/main
compall:: MyStack
compall:: MyQueue
compall:: FnGtree
compall:: BinSearch

###################################################################

FnGseq:: ; make -C FnGseq comp
FnStrn:: ; make -C FnStrn comp
FnStrn:: ; make -C FnInt1 comp
FnList:: ; make -C FnList comp
FnA1sz:: ; make -C FnA1sz comp
FnTuple:: ; make -C FnTuple comp

###################################################################

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
FnGseq:: ; make -C LnGseq comp
FnList:: ; make -C LnList comp

###################################################################

>>>>>>> upstream/main
MyStack:: ; make -C MyRefer comp
>>>>>>> upstream/main
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
$(ROOT)/Library/FnGseq/*.class \
$(ROOT)/Library/LnGseq/*.class \
$(ROOT)/Library/FnInt1/*.class \
$(ROOT)/Library/FnStrn/*.class \
$(ROOT)/Library/FnList/*.class \
<<<<<<< HEAD
$(ROOT)/Library/FnArray/*.class $(ROOT)/Library/FnTuple/*.class \
<<<<<<< HEAD
=======
=======
$(ROOT)/Library/LnList/*.class \
$(ROOT)/Library/FnA1sz/*.class \
$(ROOT)/Library/FnTuple/*.class \
>>>>>>> upstream/main
$(ROOT)/Library/MyRefer/*.class \
>>>>>>> upstream/main
$(ROOT)/Library/MyStack/*.class $(ROOT)/Library/MyQueue/*.class \
$(ROOT)/Library/FnGtree/*.class $(ROOT)/Library/BinSearch/*.class

###################################################################

########################(end of [Makefile])########################
