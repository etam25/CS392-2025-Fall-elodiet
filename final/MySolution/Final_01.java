/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/

import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {
    static LnStrm<FnList<Character>> pg2701_word$strmize() {
        //get character stream from Final_00
        LnStrm<Character> charStrm = Final_00.pg2701.char$strmize();

        //build word stream on top of character stream
        return word_stream_helper(charStrm, FnListSUtil.nil());
    }

    // helper function to build word stream
    private static LnStrm<FnList<Charcter>> word_stream_helper( 
        LnStrm<Character> charStrm,
        FnList<Character> currentWord) {
            return new LnStrm<FnList<Character>>( 
                () -> {
                    LnStcn<Character> charCons = charStrm.eval0();

                    // if no more characters
                    if (charCons.nilq()) {
                        if (!currentWord.nilq()) {
                            return new LnStcn<FnList<Character>>( 
                                FnListSUtil.reverse(currentWord)
                            );
                        } else {
                            return new LnStcn<FnList<Character>>();
                        }
                    }

                    char ch = charCons.hd();
                    LnStrm<Character> restStrm = charCons.tl();
                    
                    // check if character is part of a word (letter or apostrophe)
                    if (isWordChar(ch)) {
                        // convert to lowercase if uppercase
                        char lowerCh = toLowerCase(ch);

                        FnList<Character> newWord = FnListSUtil.cons(loweCh, currentWord);
                        return word_stream_helper(restStrm, newWord).eval0();
                    } else {
                        // non-word character
                        if (!currentWord.nilq()) {
                            FnList<Character> completedWord = FnListSUtil.reverse(currentWord);
                            return new LnStcn<FnList<Character>>( 
                                completedWord,
                                word_stream_helper(restStrm, FnListSUtil.nil())
                            );
                        } else {
                            return word_stream_helper(restStrm, FnListSUtil.nil()).eval0();
                        }
                    }
                }
            );
        }

        // check if character is a letter or apostrophe
        private static boolean isWordChar(char ch) {
            return (ch >= 'a' && ch <= 'z') ||
                   (ch >= 'A' && ch <= 'Z') ||
                   (ch == '\'');
        }

        //convert uppercase to lowercase
        private static char toLowerCase(char ch) {
            if (ch >= 'A' && ch <= 'Z') {
                return (char)(ch - 'A' + 'a');
            }
            return ch;
        }

        // helper to convert FnList<Character> to String 
        private static String wordToString(FnList<Character> word) {
            StringBuilder sb = new StringBuilder();
            word.foritm(ch -> sb.append(ch));
            return sb.toString();
        }

    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$strmize()

    System.out.println("First 50 words from pg2701.txt:");
    LnStrm<FnList<Character>> wordStrm = pg2701_word$strmize();

    int i = 0;
    while (i < 50) {
        LnStcn<FnList<Character>> cons = wordStrm.eval0();
        if (cons.nilq()) {
            break;
        }

        FnList<Character> word = cons.hd();
        System.out.print(wordToString(word) + " ");

        if ((i + 1) % 10 == 0) {
            System.out.println();
        }

        wordStrm = cons.tl();
        i += 1;
    }

    System.out.println();
	return /*void*/;
    }
}
