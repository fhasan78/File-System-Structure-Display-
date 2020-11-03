import java.util.ArrayList;
import java.util.Iterator;

/**
 * CS1027 - Assignment4
 * 
 * @author FatimaHasan This class represents a tree storing information of the
 *         file of objects in the computers file system
 */
public class FileTree {

	private TreeNode<FileObject> root;// instance variable

	/**
	 * This is the constructor for the class, it creates a new file object, and
	 * uses an auxiliary method to explore the folder and create the
	 * corresponding nodes of the tree. The exception will not be handled, it
	 * will simply be re-thrown by the constructor
	 * 
	 * @param fileObjectName
	 * @throws FileObjectException
	 */

	public FileTree(String fileObjectName) throws FileObjectException {
		FileObject fileObject = new FileObject(fileObjectName);
		root = new TreeNode<FileObject>(fileObject, null);
		exploreFolder(fileObject, root);
	}

	/**
	 * The exploreFolder method is the auxiliary recursive method the
	 * constructor uses to create the tree. In the first call the
	 * TreeNode<FileObject> obj will be the root.
	 * 
	 * @param fileObject
	 * @param parent
	 */
	private void exploreFolder(FileObject fileObject, TreeNode<FileObject> parent) {
		Iterator<FileObject> folderFile = fileObject.directoryFiles();
		while (folderFile != null && folderFile.hasNext()) {
			FileObject nextFileObj = folderFile.next();
			TreeNode<FileObject> node = new TreeNode<FileObject>(nextFileObj, parent);
			node.setParent(parent);
			parent.addChild(node);
			if (nextFileObj.isDirectory()) {// recursive case
				exploreFolder(nextFileObj, node);
			}
		}
	}

	/**
	 * The getRoot() method simply returns the root of the tree.
	 * 
	 * @return the root of the tree.
	 */
	public TreeNode<FileObject> getRoot() {
		return root;
	}

	/**
	 * The Iterator<String> filesOfType(String type) method returns an iterator
	 * storing string objects with all the names of the files of the specified
	 * type represented by nodes of the specified FileTree. It is implemented
	 * with a recursive auxiliary method
	 * 
	 * @param type
	 * @return an iterator storing string objects
	 */
	public Iterator<String> filesOfType(String type) {
		ArrayList<String> fileList = new ArrayList<String>();
		FileObject rootOfFileobj = root.getData();
		if (rootOfFileobj.isFile()) {
			if (rootOfFileobj.getName().endsWith(type)) {
				fileList.add(rootOfFileobj.getLongName());
			}
		} else {
			filesOfType(rootOfFileobj, type, fileList);
		}
		return fileList.iterator();
	}

	/**
	 * The filesOfType(FileObject node, String type, ArrayList<String> fileList)
	 * method is the recursive method that the Iterator<String>
	 * filesOfType(String type) method uses. The base part is when the the node
	 * represents a file and the recursive part is when its a folder.
	 * 
	 * @param node
	 * @param type
	 * @param fileList
	 */
	private void filesOfType(FileObject node, String type, ArrayList<String> fileList) {
		Iterator<FileObject> nodeR = node.directoryFiles();
		while (nodeR != null && nodeR.hasNext()) {
			FileObject childFileObj = nodeR.next();
			if (childFileObj.isFile()) {
				String longName = childFileObj.getLongName();
				if (longName.endsWith(type)) {
					fileList.add(longName);// all files are added to the list
				}
			} else if (childFileObj.isDirectory()) {
				filesOfType(childFileObj, type, fileList);
			}
		}
	}

	/**
	 * The findFile method will look for a file with the specified name inside
	 * this tree using an auxiliary recursive method.
	 * 
	 * @param name
	 * @return string containing the absolute path to the file, if the the file
	 *         exists
	 */
	public String findFile(String name) {
		FileObject rootFileObj = root.getData();
		return findFile(rootFileObj, name);
	}

	/**
	 * The findFile(FileObject rootfileobj, String name) method is the auxiliary
	 * method that the findFile(String name) uses. If the file is found, then a
	 * string containing the absolute path to the file will be returned,
	 * otherwise an empty string will be returned.
	 * 
	 * @param rootfileobj
	 * @param name
	 * @return a string containing the absolute path to the file
	 */
	private String findFile(FileObject rootfileobj, String name) {
		Iterator<FileObject> childFO = rootfileobj.directoryFiles();
		while (childFO != null && childFO.hasNext()) {
			FileObject child = childFO.next();
			if (child.isFile()) {
				if (child.getName().equals(name)) {
					return child.getLongName();
				}
			} else if (child.isDirectory()) {
				return findFile(child, name);
			}
		}
		return "";
	}

	public Iterator<String> duplicatedFiles() {
		return null;
	}

	public Iterator<String> duplicatedFolders() {
		return null;
	}
}
