/**
 * Exception that indicates that a the bits are not valid BitTree bits for the given tree
 *
 * @author Samuel A. Rebelsky
 * @author Zakariye Abdilahi
 */
public class InvalidBitsException extends Exception {
    /**
     * Create a new exception.
     */
    public InvalidBitsException() {
      super("Invalid Bit pattern entered");
    }
  
    /**
     * Create a new exception with a particular message.
     */
  
    public InvalidBitsException(String msg) {
      super(msg);
    } 
  }