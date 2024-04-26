/**
 * BrailleASCII class that interacts with the user on the command line.
 * @author Samuel A. Rebelsky
 * @author Zakariye Abdilahi
 */

 public class BrailleASCII {
    public static void main(String[] args) throws Exception {
      if (args.length != 2) {
        System.err.println("Invalid number of parameters");
      }
      String target = args[0];
      String source = args[1];
      String result = translate(target, source);
      System.out.println(result);
    } // main(String[] args)
  
    // +-----------------+---------------------------------------------------------
    // |      Utility Functions     |
    // +----------------------------+
    /* translate(String type, String input)
     * translates to the given 'type' from the given 'input'
     * So type=braille and input='hello' gives the braille version of 'hello'
     */
    public static String translate(String type, String input) throws Exception {
      // initialize conversion tables for later use, never directly accessed
      BrailleASCIITables table = new BrailleASCIITables(); 
      type = type.toLowerCase();
      if (type.equals("braille")) {
        return toBraille(input);
  
      } else if (type.equals("ascii")) {
        return toAscii(input);
  
      } else if (type.equals("unicode")) {
        if (!isBitPattern(input)) {
          // case where we convert an ascii word into unicode braille
          input = toBraille(input);
        } // if
        return toUnicode(input);
  
      } else {
        throw new Exception("Invalid input entered");
      } // if else
    } // translate(String type, String input)
  
    /* isBitPattern(String str)
     * checks if a string is a valid bit pattern
     */
    public static boolean isBitPattern(String str) {
      for (int i = 0; i < str.length(); i++) {
        if (!(str.charAt(i) == '0' || str.charAt(i) == '1')) {
          return false;
        } // if
      } // for
      return true;
    } // isBitPattern(String str)
  
    /* stringSplit(String word, int size)
     * splits a string 'word' into chunks of length 'size'.
     */
    public static String[] stringSplit(String word, int size) throws InvalidBitsException {
      if (word.length()%size != 0) {
        throw new InvalidBitsException();
      } // if
      int totalParts = word.length()/size;
      String[] result = new String[totalParts];
      for (int i = 0; i < totalParts; i++) {
        // get chunks of length 'size' from the word and store in result
        result[i] = word.substring(i*size,(i+1)*size);
      } // for
      return result;
    } // stringSplit(String word, int size)
  
    /* toBraille(String input)
     * converts ascii word into braille bits
     */
    public static String toBraille(String input) throws InvalidBitsException {
      String brailleBits = "";
        for (int i = 0; i < input.length(); i++) {
          brailleBits += BrailleASCIITables.toBraille(input.charAt(i));
        } // for
        return brailleBits;
    } // toBraille(String input)
    /* toAscii(String input)
     * converts braille bits into ascii characters
     */
    public static String toAscii(String input) throws InvalidBitsException {
      String[] brailleBits = BrailleASCII.stringSplit(input, 6);
      String asciiLetters = "";
      for (int i = 0; i < brailleBits.length; i++) {
        asciiLetters += BrailleASCIITables.toAscii(brailleBits[i]);
      } // for
      return asciiLetters;
    } // toAscii(String input)
  
    /* toUnicode(String input)
     * converts braille bits into unicode characters
     */
    public static String toUnicode(String input) throws InvalidBitsException {
    String[] brailleBits = BrailleASCII.stringSplit(input, 6);
      String[] unicodeChars = new String[brailleBits.length];
      for (int i = 0; i < brailleBits.length; i++) {
        unicodeChars[i] = BrailleASCIITables.toUnicode(brailleBits[i]);
      } // for
      return hexToUnicode(unicodeChars);
    } // toUnicode(String input)
  
    /* hexToUnicode(String[] input)
     * converts string hex characters into the actual unicode representation for printing
     */
    public static String hexToUnicode(String[] input) {
      String result = "";
      for (int i = 0; i < input.length; i++) {
        String hexString = input[i];  
        // Convert hex string to an integer
        int codePoint = Integer.parseInt(hexString, 16);
        // Convert the code point to a character
        char unicodeChar = (char) codePoint;
        // Add to result
        result += unicodeChar;
      } // for
      return result;
    } // hexToUnicode()
  } // BraillASCII class