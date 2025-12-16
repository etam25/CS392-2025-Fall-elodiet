/*
// HX: 0 points for Final_00
// Final_00 provides [pg2701_char$strmize] for
// constructing a FnList of characters in pg2701.txt
*/

import Library.FnList.*;
import Library.LnStrm.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class Final_00 {
    public static
	LnStrm<Character> pg2701_char$strmize() {
	Path path = Paths.get("./../Data/pg2701.txt");
	String content = "";
	try {
	    content = Files.readString(path, StandardCharsets.UTF_8);
	} catch (IOException e) {
	    // HX: content is left to be empty!
	}
	return pg2701$helper_char$strmize(content, content.length(), 0);
    }
    private static
	LnStrm<Character>
	pg2701$helper_char$strmize(String cs, int n, int i) {
	return new LnStrm<Character>(
          () -> {
	      if (i >= n) {
		  return new LnStcn<Character>();
	      } else {
		  return new LnStcn<Character>
		      (cs.charAt(i), pg2701$helper_char$strmize(cs, n, i+1));
	      }
	  }
        );
    }
    /*
    // HX-2025-12-16: minimal testing
    public static void main(String[] args) {
	Character ch;
	LnStcn<Character> cxs;
	LnStrm<Character> fxs = pg2701_char$strmize();
	int i = 0;
	while (i < 1000) {
	    i += 1;
	    cxs = fxs.eval0(); ch = cxs.hd(); fxs = cxs.tl(); System.out.print(ch);
	}
	return;
    }
    */
}
