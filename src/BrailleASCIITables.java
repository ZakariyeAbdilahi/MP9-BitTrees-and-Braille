import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Braille to Ascii Table class.
 * @author Samuel A. Rebelsky
 * @author Zakariye Abdilahi
 */
public class BrailleASCIITables {


  // +-----------------+---------------------------------------------------------
  // |  Fields     |
  // +-----------------+
  // initialise the BitTrees holding the relevant characters.
  @SuppressWarnings("rawtypes")
static BitTree asciiBraille = new BitTree<>(8);
  @SuppressWarnings("rawtypes")
static BitTree brailleAscii = new BitTree<>(6);
  @SuppressWarnings("rawtypes")
static BitTree brailleUnicode = new BitTree<>(6);


  // +-----------------+---------------------------------------------------------
  // | Constructor     |
  // +-----------------+
  public BrailleASCIITables() throws FileNotFoundException {
    // get file paths
    String asciiBraillePath = "ASCIIToBrailleTable.txt";
    String brailleAsciiPath = "BrailleToAsciiTable.txt";
    String brailleUnicodePath = "BrailleToUnicodeTable.txt";

    // create trees
    asciiBraille.load(new FileInputStream(asciiBraillePath));
    brailleAscii.load(new FileInputStream(brailleAsciiPath));
    brailleUnicode.load(new FileInputStream(brailleUnicodePath));

  } // BrailleASCIITables()

  // +-----------------+---------------------------------------------------------
  // | Static Methods  |
  // +-----------------+

  /* toBraille(char letter)
   * converts an ASCII character to a string of bits representing the corresponding
   * braille character.
   */
  public static String toBraille(char letter) throws InvalidBitsException {
    int letterAscii = (int) letter;
    String binaryLetter = Integer.toBinaryString(letterAscii);
    binaryLetter = String.format("%8s", binaryLetter).replace(' ', '0');
    return asciiBraille.get(binaryLetter);
  } // toBraille(char letter)

  /* toASCII(String bits)
   * converts a string of bits representing a braille character to the corresponding 
   * ASCII character
  */
  public static String toAscii(String bits) throws InvalidBitsException {
    return brailleAscii.get(bits);
  } // toAscii(String bits)

  /*toUnicode(String bits)
   * converts a string of bits representing a braille character to the corresponding 
   * Unicode braille character for those bits
   */
  public static String toUnicode(String bits) throws InvalidBitsException {
    return brailleUnicode.get(bits);
  } // toUnicode(String bits)
}