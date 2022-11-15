package hw5;
/**
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/
import java.util.Scanner;

public class BashTerminal {

    public static void main(String[] args){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32;1m";

        String userHost = "[shpanigrahi@Hacker]:";
        boolean isDone = false;
        System.out.println("Starting bash terminal.");
        DirectoryNode root = new DirectoryNode("root");
        DirectoryTree directoryTree = new DirectoryTree(root);
        while(!isDone){
            Scanner sc  = new Scanner(System.in);
            System.out.print(ANSI_GREEN + userHost +ANSI_RESET + " $ ");
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String firstInput = inputArray[0];
            String secondInput = (inputArray.length>1 ? inputArray[1] : null);
            switch(firstInput){
                case("pwd"):
                    System.out.println(directoryTree.presentWorkingDirectory());
                    break;
                case("ls"):
                    if(secondInput != null){
                        if(secondInput.equals("-R")){
                            directoryTree.printDirectoryTree();
                            break;
                        } else{
                            System.out.println("ERROR: Invalid Input");
                            break;
                        }
                    } else{
                        System.out.println(directoryTree.listDirectory());
                        break;
                    }
                case("cd"):
                    try {
                        if(secondInput != null && secondInput.equals("/")){
                            directoryTree.resetCursor();
                            break;
                        } else if(secondInput != null){
                            directoryTree.changeDirectory(secondInput);
                            break;
                        }
                    } catch (NotADirectoryException e) {
                        if(directoryTree.isChildAFile(secondInput)){
                            System.out.println("ERROR: Cannot change directory into a file.");
                        } else{
                            System.out.println("ERROR: No such directory named '"+secondInput+"'");
                        }
                        break;
                    }
                case("mkdir"):
                    try {
                        if(secondInput != null){
                            directoryTree.makeDirectory(secondInput);
                            break;
                        } else{
                            System.out.println("ERROR: No directory name provided");
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: Invalid Input. Try Again");
                        break;
                    } catch (FullDirectoryException e) {
                        System.out.println("ERROR: Present directory is full.");
                        break;
                    }
                case("touch"):
                    try {
                        if(secondInput != null){
                            directoryTree.makeFile(secondInput);
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: Invalid Input. Try Again.");
                    } catch (FullDirectoryException e) {
                        System.out.println("ERROR: Present directory is full.");
                    }
                    break;
                case("exit"):
                    System.out.println("Program terminating normally");
                    isDone = true;
                    break;
                default:
                    System.out.println("ERROR: Command not found");
            }

        }

    }

}
