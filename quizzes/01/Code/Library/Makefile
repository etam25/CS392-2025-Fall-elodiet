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
compall:: MyRefer

###################################################################

FnGseq:: ; make -C FnGseq comp
FnStrn:: ; make -C FnStrn comp
FnStrn:: ; make -C FnInt1 comp
FnList:: ; make -C FnList comp
FnA1sz:: ; make -C FnA1sz comp

###################################################################

FnGseq:: ; make -C LnGseq comp
FnList:: ; make -C LnList comp

###################################################################

MyStack:: ; make -C MyRefer comp

###################################################################

ROOT=./..

Library_jar: ; \
jar -cvf Library.jar \
$(ROOT)/Library/FnGseq/*.class \
$(ROOT)/Library/LnGseq/*.class \
$(ROOT)/Library/FnInt1/*.class \
$(ROOT)/Library/FnStrn/*.class \
$(ROOT)/Library/FnList/*.class \
$(ROOT)/Library/LnList/*.class \
$(ROOT)/Library/FnA1sz/*.class \
$(ROOT)/Library/MyRefer/*.class \

###################################################################

########################(end of [Makefile])########################
