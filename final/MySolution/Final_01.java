/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus apostrophe (')
// And words are separated by non-letters-apostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/
import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {
    
    static LnStrm<FnList<Character>> pg2701_word$strmize() {
        // Get the character stream from Final_00
        LnStrm<Character> charStrm = Final_00.pg2701_char$strmize();
        
        // Build word stream on top of character stream
        return word_stream_helper(charStrm, FnListSUtil.nil());
    }
    
    // Helper function to build the word stream
    private static LnStrm<FnList<Character>> word_stream_helper(
        LnStrm<Character> charStrm, 
        FnList<Character> currentWord) {
        
        return new LnStrm<FnList<Character>>(
            () -> {
                LnStcn<Character> charCons = charStrm.eval0();
                
                // If no more characters
                if (charCons.nilq()) {
                    // If we have a current word, emit it
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
                
                // Check if character is part of a word (letter or apostrophe)
                if (isWordChar(ch)) {
                    // Convert to lowercase if uppercase
                    char lowerCh = toLowerCase(ch);
                    // Add to current word and continue
                    FnList<Character> newWord = FnListSUtil.cons(lowerCh, currentWord);
                    return new LnStcn<FnList<Character>>(
                        FnListSUtil.reverse(newWord),
                        word_stream_helper(restStrm, FnListSUtil.nil())
                    );
                    
                } else {
                    // Non-word character - word boundary
                    if (!currentWord.nilq()) {
                        // We have a complete word - emit it and start fresh
                        FnList<Character> completedWord = FnListSUtil.reverse(currentWord);
                        return new LnStcn<FnList<Character>>(
                            completedWord,
                            word_stream_helper(restStrm, FnListSUtil.nil())
                        );
                    } else {
                        // No current word, skip this character
                        return word_stream_helper(restStrm, FnListSUtil.nil()).eval0();
                    }
                }
            }
        );
    }
    
    // Check if character is a letter or apostrophe
    private static boolean isWordChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || 
               (ch >= 'A' && ch <= 'Z') || 
               (ch == '\'');
    }
    
    // Convert uppercase to lowercase
    private static char toLowerCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch - 'A' + 'a');
        }
        return ch;
    }
    
    // Helper to convert FnList<Character> to String for printing
    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // HX-2025-12-16: minimal testing
        // Print first 50 words from Moby Dick
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
        return; /*void*/
    }
}