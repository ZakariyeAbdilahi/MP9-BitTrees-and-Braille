import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Bit Tree class.
 * @author Samuel A. Rebelsky
 * @author Zakariye Abdilahi
 */

public class BitTree<V> {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Root node
   */
  BitTreeNode<V> root;

  /**
   * Depth of tree
   */
  int depth;

  /**
   * A cached value (useful in some circumstances.
   */
  V cachedValue;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /* BitTree(int n)
   * builds a tree that will store mappings from strings of n bits to strings.
   * We’d build our tree for mapping braille to ASCII with new BitTree(6).
   */
  public BitTree(int n) {
    this.depth = n;
    this.cachedValue = null;
    this.root = null;
  } // BitTree(int n)

  // +-----------+---------------------------------------------------------
  // |  Methods  |
  // +-----------+

  /* set(String bits, String value)
    follows the path through the tree given by bits (adding nodes as appropriate)
    and adds or replaces the value at the end with value. set should throw an 
    exception if bits is the inappropriate length or contains values other than 0 or 1.
  */ 
  public void set(String bits, String value) throws InvalidBitsException{
    validBits(bits);
    this.root  = setHelper(bits, (V) value, this.root);
  } // set(String bits, String value)

  /* get(String bits)
   *  follows the path through the tree given by bits, returning the value at the end. 
   *  If there is no such path, or if bits is the incorrect length, get should throw an exception.
  */
  public String get(String bits) throws InvalidBitsException {
    validBits(bits);
    return (String) getHelper(bits, this.root);
  } // get(String bits)

  /* dump(PrintWriter pen)
  * prints out the contents of the tree in CSV format. For example, one row of our braille tree will be “101100,M”
  */
  public void dump(PrintWriter pen) {
    pen.println(dumpHelper(root, ""));
  } // dump(PrintWriter pen)

  /* load(InputStream source)
   * reads a series of lines of the form bits,value and stores them in the tree.
   */

  public void load(InputStream source) {
  Scanner eyes = new Scanner(source);
  while (eyes.hasNextLine()) {
    String[] inputs = eyes.nextLine().split(",");
    String bits = inputs[0];
    String value = inputs[1];
    try {
      this.set(bits, value);
    } catch (InvalidBitsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // try-catch
  } // while
  eyes.close();
  } // load(InputStream source)

  // +-----------+---------------------------------------------------------
  // | Helper   Methods  |
  // +-------------------+

  /* dumpHelper(BitTreeNode<V> root, String curLine)
   * Read dump() docs, dumpHelper does the bulk of the work
   */
  private String dumpHelper(BitTreeNode<V> root, String curLine) {
    if (root == null) {
      return "";
    } // if
    if (root.value != null) {
      curLine += ","+root.value;
      return curLine+"\n";
    } // if
    return dumpHelper(root.left, curLine+"0")+dumpHelper(root.right, curLine+"1");
  } // dumpHelper(BitTreeNode<V> root, String curLine)

  /* setHelper(String bits, V value, BitTreeNode<V> root)
   * Read set() docs, setHelper does the bulk of the work
   */
  private BitTreeNode<V> setHelper(String bits, V value, BitTreeNode<V> root) throws InvalidBitsException {
    if (root == null) {
      root = new BitTreeNode<V>(null);
    } // if
    if (bits.length() == 0) {
      this.cachedValue = root.value;
      root.value = value;
      return root;
    } // if
    char curNode = bits.charAt(0);
    if (curNode == '0') {
      root.left = setHelper(bits.substring(1), value, root.left);
    } else if (curNode == '1') {
      root.right = setHelper(bits.substring(1), value, root.right);
    } else {
      throw new InvalidBitsException();
    } // if
    return root;
  } // setHelper(String bits, V value, BitTreeNode<V> root)

  /* getHelper(String bits, BitTreeNode<V> root)
   * Read get() docs, setHelper does the bulk of the work
   */
  private V getHelper(String bits, BitTreeNode<V> root) throws InvalidBitsException {
    if (bits.length() == 0) {
      return root.value;
    } // if
    char curNode = bits.charAt(0);
    if (curNode == '0') {
      return getHelper(bits.substring(1), root.left);
    } else if (curNode == '1') {
      return getHelper(bits.substring(1), root.right);
    } else {
      throw new InvalidBitsException();
    } // if
  } // getHelper(String bits, BitTreeNode<V> root)


  // +-----------+---------------------------------------------------------
  // | Private  Methods  |
  // +-------------------+

  /* validBits (String bits)
    checks if the input bits are valid format
   */
  private boolean validBits (String bits) throws InvalidBitsException {
    if (bits.length() != this.depth || !isBinary(bits)) {
      throw new InvalidBitsException();
    } // if
    return true;
  } // validBits (String bits)

  /* isInteger(String num)
   * checks if a string is a binary value
   */
  private boolean isBinary(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!(str.charAt(i) == '0' || str.charAt(i) == '1')) {
        return false;
      } // if
    } // for
    return true;
  } // isInteger(String num)

} // BitTree class