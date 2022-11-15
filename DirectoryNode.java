package hw5;

/**
 * The <code>DirectoryNode Class</code> class implements <code>DirectoryNode</code>
 * objects.
 *
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class DirectoryNode {
    String name;
    DirectoryNode left;
    DirectoryNode middle;
    DirectoryNode right;

    boolean isFile;

    public DirectoryNode() {

    }

    /** Makes an instance of DicrectoryNode and sets its name to the given parameter
     *
     *
     * @param name
     */
    public DirectoryNode(String name) {
        this.name = name;
    }

    /** Returns a string representation of the name of the node
     *
     * @return
     *      String representation of the name of the node
     *
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the node that call this method to the passes paramater
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the left subnode of the node that calls the method
     *
     * @return
     *      The left subnode of the node that calls the method
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /** Returns the middle subnode of the node that calls this method
     *
     * @return
     *      The middle subnode of the node that calls this method
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /** Returns the right subnode of the node that calls this method
     *
     * @return
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Adds newChild to any of the open child positions of this node (left, middle, or right).
     * NOTE: Children should be added to this node in left-to-right order, i.e. left is filled first, middle is filled second, and right is filled last
     *
     * Preconditions:
     * This node is not a file.
     * There is at least one empty position in the children of this node (left, middle, or right).
     *
     * Postconditions:
     * newChild has been added as a child of this node. If there is no room for a new node, throw a FullDirectoryException.
     *
     * @param newChild
     * @throws FullDirectoryException
     * @throws NotADirectoryException
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
        if(this.isFile){
            throw new NotADirectoryException();
        } else{
            if(this.getLeft() == null){
                this.left = newChild;
            } else if(this.getMiddle() == null){
                this.middle = newChild;
            } else if(this.getRight() == null){
                this.right = newChild;
            } else {
                throw new FullDirectoryException();
            }
        }
    }

    /** Traverses through a node and its children recursively and prints the tree of nodes in a properly fomartted manner
     *
     * @param spaces
     */
    public void traverseDirectoryTree(String spaces){
        if(this.isFile){
            System.out.print(spaces);
            System.out.println("- "+this.getActualName());
            System.out.print("\b\b\b");
        } else{
            System.out.print(spaces);
            System.out.println("|- "+this.getActualName());
            System.out.print("\b\b\b");
        }
        spaces = spaces + "   ";
        if(this.getLeft() != null){
            //System.out.print(spaces);
            this.getLeft().traverseDirectoryTree(spaces);
            //spaces = spaces + "\b\b\b";
        }
        if(this.getMiddle() != null){
            //System.out.print(spaces);
            this.getMiddle().traverseDirectoryTree(spaces);
            //spaces = spaces + "\b\b\b";
        }
        if(this.getRight() != null){
            //System.out.print(spaces);
            this.getRight().traverseDirectoryTree(spaces);
            //spaces = spaces + "\b\b\b";
        }
    }

    /** Returns the actual String representation name without the path of the directory or the file
     *
     * @return
     *      String representation of name without the path of the directory or the file
     */
    public String getActualName(){
        String name = "";
        String[] nameArray = this.getName().split("/");
        name = nameArray[nameArray.length-1];
        return name;
    }
}
