import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 */
public class LetterCombinationsPhone {


    /**
     * Auxiliary method.
     * 
     * Returns a list of characters associated with the 
     * specified digit.
     */
    static List<String> digitToCharList(char digit) {
        if (digit == '2')
            return Arrays.asList("a", "b", "c"); 
        else if (digit == '3')
            return Arrays.asList("d", "e", "f"); 
        else if (digit == '4')
            return Arrays.asList("g", "h", "i"); 
        else if (digit == '5')
            return Arrays.asList("j", "k", "l"); 
        else if (digit == '6')
            return Arrays.asList("m", "n", "o"); 
        else if (digit == '7')
            return Arrays.asList("p", "q", "r", "s"); 
        else if (digit == '8')
            return Arrays.asList("t", "u", "v"); 
        else 
            return Arrays.asList("w", "x", "y", "z"); 
    }


    /**
     * Given two lists generate all combinations.
     */
    static List<String> combine1(List<String> lst1, List<String> lst2) {

        // **** initialization ****
        List<String> lst = new ArrayList<String>();

        // **** ****
        for (int i = 0; i < lst1.size(); i++) {

            // **** ****
            String str = lst1.get(i);

            // **** ****
            for (int j = 0; j < lst2.size(); j++) {
                lst.add(str + lst2.get(j));
            }
        }

        // **** return list ****
        return lst;
    }


    /**
     * Combine two lists.
     * 
     * Runtime: 1 ms, faster than 75.13% of Java online submissions.
     * Memory Usage: 37.5 MB, less than 94.65% of Java online submissions.
     */
    static List<String> letterCombinations(String digits) {

        // **** sanity checks ****
        if (digits == null || digits.length() == 0)
            return new ArrayList<String>();

        // **** initialization ****
        List<String> lst = digitToCharList(digits.charAt(0));

        // **** loop generating combinations O(n) ****
        for (int i = 1; i < digits.length(); i++) {
            lst = combine1(lst, digitToCharList(digits.charAt(i)));
        }

        // **** return list ****
        return lst;
    }


    /**
     * Entry call for recursion.
     * 
     * Runtime: 4 ms, faster than 43.83% of Java online submissions.
     * Memory Usage: 39.1 MB, less than 44.05% of Java online submissions.
     */
    static List<String> letterCombinations2(String digits) {

        // **** initialization ****
        List<String> result = new ArrayList<String>();

        // **** sanity ckeck(s) ****
        if (digits == null || digits.length() == 0)
            return result;

        // **** ****
        String[] digitToLetters = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
        };

        // **** recursive call ****
        letterCombinations2(result, digits, "", 0, digitToLetters);

        // **** return result ****
        return result;
    }


    /**
     * Recursive call.
     */
    static void letterCombinations2(    List<String> result,
                                        String digits, 
                                        String current,
                                        int ndx,
                                        String[] digitToLetters) {

        // **** base call ****
        if (ndx >= digits.length()) {

            // **** ****
            result.add(current);

            // **** ****
            return;
        }

        // **** get string for this digit ****
        String letters = digitToLetters[digits.charAt(ndx) - '0'];

        // **** ****
        for (int i = 0; i < letters.length(); i++) {
            letterCombinations2(result, digits, current + letters.charAt(i), 
                                ndx + 1, digitToLetters);
        }
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read digits ****
        String digits = br.readLine().trim();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< digits ==>" + digits + "<==");

        // **** slower attempt ****
        List<String> lst = letterCombinations2(digits);

        // ???? ????
        System.out.println("main <<< lst: " + lst.toString());

        // **** faster attempt ****
        lst = letterCombinations(digits);

        // ???? ????
        System.out.println("main <<< lst: " + lst.toString());
    }
}