import java.util.Comparator;
import java.util.Iterator;

/**
 * CS1027 - Assignment4 
 * 
 * @author FatimaHasan This class represents the nodes of the tree that will
 *         store the information about the file system. This class has 3
 *         instance variables: parent, children, and data
 */
public class TreeNode<T> {
	private TreeNode<T> parent;// parent is a pointer to the parent of this node
	private ListNodes<TreeNode<T>> children;// children is a reference to a list
											// storing the children of this node
	private T data;// data points to the data object stored in this node

	/**
	 * This is the first constructor, it will set parent and data to null and
	 * children will be set to an empty ListNodes<TreeNode<T>> object
	 */
	public TreeNode() {
		parent = null;
		data = null;
		children = new ListNodes<TreeNode<T>>();

	}

	/**
	 * This is the second constructor , it will set parent and data to p and d
	 * that are passed as parameters
	 * 
	 * @param d
	 * @param p
	 */
	public TreeNode(T d, TreeNode<T> p) {
		parent = p;
		data = d;
		children = new ListNodes<TreeNode<T>>();
	}

	/**
	 * The setParent(TreeNode<T> p) method will set the parent of this node to p
	 * 
	 * @param p
	 */
	public void setParent(TreeNode<T> p) {
		this.parent = p;
	}

	/**
	 * The TreeNode<T> getParent() returns the parent of this node
	 * 
	 * @return the parent of this node
	 */
	public TreeNode<T> getParent() {
		return parent;
	}

	/**
	 * The void addChild(TreeNode<T> c) method add the given node c to the list
	 * of children of this node
	 * 
	 * @param c
	 */
	public void addChild(TreeNode<T> c) {
		children.add(c);
	}

	/**
	 * The Iterator<TreeNode<T>> getChildren() method returns the children of
	 * this node
	 * 
	 * @return an iterator containing the children nodes of this node
	 */
	public Iterator<TreeNode<T>> getChildren() {
		return children.getList();
	}

	/**
	 * The Iterator<TreeNode<T>> getChildren(Comparator<TreeNode<T>> sorter)
	 * method returns the children nodes of this node
	 * 
	 * @param sorter
	 * @return an iterator containing the children nodes of this node sorted in
	 *         the order specified in the parameter
	 */
	public Iterator<TreeNode<T>> getChildren(Comparator<TreeNode<T>> sorter) {
		return children.sortedList(sorter);
	}

	/**
	 * The T getData() method returns the data of this node
	 * 
	 * @return the data stored in this node
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * The void setData(T d) method stores in this node the data object
	 * referenced by d
	 * 
	 * @param d
	 */
	public void setData(T d) {
		this.data = d;
	}
}
