/**
 * Nodes in a binary tree.
 * @author Samuel A. Rebelsky
 * @param <V>
 */
class BitTreeNode<V> {

    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+
  
    /**
     * The associated value.
     */
    V value;
  
    /**
     * The left subtree.
     */
    BitTreeNode<V> left;
  
    /**
     * The right subtree.
     */
    BitTreeNode<V> right;
  
    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+
  
    /**
     * Create a new node.
     * @param <V>
     */
    public BitTreeNode(V value) {
      this.value = value;
      this.left = null;
      this.right = null;
    } //  BitTreeNode(V value)
  
  } // class  BitTreeNode<V>