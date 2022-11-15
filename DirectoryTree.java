package hw5;

/**
 * The <code>DirectoryTree Class</code> class implements a tree of <code>DirectoryNode</code>
 * objects.
 *
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class DirectoryTree {
    DirectoryNode root;
    DirectoryNode cursor;


    /**
     * Initializes a DirectoryTree object with a single DirectoryNode named "root".
     * Preconditions: None.
     * Postconditions:
     *      The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
     *
     * @param root
     */
    public DirectoryTree(DirectoryNode root){
        this.root = root;
        cursor = root;
    }

    /** Resets the cursor to the root of the tree
     *
     * Preconditions:
     * None.
     *
     * Postconditions:
     * The cursor now references the root node of the tree.
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     *
     * Preconditions:
     * 'name' references a valid directory ('name' cannot reference a file).
     *
     * Postconditions:
     * The cursor now references the directory with the name indicated by name.
     * If a child could not be found with that name, then the user is prompted to enter a different directory name.
     * If the name was not a directory, a NotADirectoryException hs been thrown
     *
     * @param name
     * @throws NotADirectoryException
     */
    public void changeDirectory(String name) throws NotADirectoryException{
        if (cursor.getLeft()!= null && !cursor.getLeft().isFile && cursor.getLeft().getActualName().equals(name)){
            cursor = cursor.getLeft();
        } else if (cursor.getMiddle()!= null && !cursor.getMiddle().isFile && cursor.getMiddle().getActualName().equals(name)){
            cursor = cursor.getMiddle();
        } else if (cursor.getRight()!= null && !cursor.getRight().isFile &&  cursor.getRight().getActualName().equals(name)){
            cursor = cursor.getRight();
        } else {
            throw new NotADirectoryException();
        }
    }

    /**
     * Returns a String containing the path of directory names from the root node of the tree to the cursor, with each name separated by a forward slash "/".
     *
     * Preconditions:
     * None.
     *
     * Postconditions:
     * The cursor remains at the same DirectoryNode.
     *
     * @return
     *      String containing the path of directory names from the root node of the tree to the cursor, with each name separated by a forward slash "/".
     */
    public String presentWorkingDirectory(){
        String name = this.cursor.getName();
        return name;
    }

    /**
     * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
     * e.g. dev home bin if the cursor is at root in the example above.
     *
     * Preconditions:
     * None.
     *
     * Postconditions:
     * The cursor remains at the same DirectoryNode.
     *
     * @return
     *      A formatted String of DirectoryNode names.
     */
    public String listDirectory(){
        String children = "";
        if (cursor.getLeft()!= null){
            children = children + cursor.getLeft().getActualName() + " ";
        }
        if (cursor.getMiddle()!= null){
            children = children + cursor.getMiddle().getActualName() + " ";
        }
        if (cursor.getRight()!= null){
            children = children + cursor.getRight().getActualName() + " ";
        }
        return children;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
     *
     * Preconditions:
     * None.
     *
     * Postconditions:
     * The cursor remains at the same DirectoryNode
     *
     */
    public void printDirectoryTree(){
        this.cursor.traverseDirectoryTree("");
    }

    /**
     * Creates a directory with the indicated name and adds it to the children of the cursor node.
     *
     * Preconditions:
     * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     *
     * Postconditions:
     * A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown
     *
     * @param name
     * @throws IllegalArgumentException
     * @throws FullDirectoryException
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
        if(name.contains("/")){
            throw new IllegalArgumentException();
        }
        DirectoryNode directory = new DirectoryNode(cursor.getName() + "/"+name);
        cursor.addChild(directory);
    }

    /**
     * Brief:
     * Creates a file with the indicated name and adds it to the children of the cursor node.
     *
     * Preconditions:
     * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     *
     * Postconditions:
     * A new DirectoryNode has been added to the children of the cursor, or an exception has been thrown.
     *
     * @param name
     * @throws IllegalArgumentException
     * @throws FullDirectoryException
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
        if(name.contains("/")){
            throw new IllegalArgumentException();
        }
        DirectoryNode file = new DirectoryNode(cursor.getName() + "/"+name);
        file.isFile = true;
        cursor.addChild(file);
    }

    /** Returns a boolean indicating true if the name passed as the second input is a file and if it is in the directory, otherwise false
     *
     * @param name
     * @return
     *      A boolean indicating true if the name passed as the second input is a file and if it is in the directory, otherwise false
     */
    public boolean isChildAFile(String name){
        if(cursor.getLeft()!= null && cursor.getLeft().isFile && cursor.getLeft().getActualName().equals(name)){
            return true;
        } else if(cursor.getMiddle() != null && cursor.getMiddle().isFile && cursor.getMiddle().getActualName().equals(name)){
            return true;
        } else if(cursor.getRight() != null && cursor.getRight().isFile && cursor.getRight().getActualName().equals(name)){
            return true;
        }
        return false;
    }

}
