package raymond_li;
import java.io.File;
/**
 * This class implements the actual file search program that given the name
 * of a directory, explores all its subdirectories and files and does 2 things:
 * 1) computes the total size of all files and subdirectories and 2)prints a 
 * list of the n largest files (their sizes and absolute paths)
 * This class reads and verifies data given on command line, runs the algorithm
 * that explores the directory structure and then produces output for the user
 * @author Raymond Li
 * @version 3/30/14
 *
 */
public class DirectorySize {
	
	private static GenericSortedLinkedList linkedList = new GenericSortedLinkedList();
	//use separate linked list to store directories so they don't repeat
	private static GenericSortedLinkedList<File> ec = new GenericSortedLinkedList<File>();

	/**
	 * This is the main method that is responsible for verifying command line
	 * arguments, starting the algorithm, and printing results to the console
	 * 
	 * @param args
	 *            First arg is the directory to be explored, second arg is the
	 *            number of files shown to user Giving more or less than 2
	 *            arguments result in errors and program termination
	 */
	public static void main(String[] args) {
		// check if correct number of arguments (2) are given. Terminate program
		// if incorrect.
		if (args.length < 2) {
			System.out.println("Error: Less than 2 files provided in command line. Shutting down program.");
			System.exit(0);
		}

		else if (args.length == 2) {
			String listDirectory = args[0];
			int filesDisplayed = Integer.parseInt(args[1]);

			// Check if file exists
			File file = new File(listDirectory);
			if (!file.exists()) {
				System.out.println("File does not exist. Terminating program.");
				System.exit(0);
			}
			double totalSize = explorDir(file);

			// formats total space used output for bytes, KBs, MBs, and GBs
			if (totalSize < 1024) {
				System.out.printf("Total space used: " + "%.2f" + " bytes\n",
						totalSize);
			} else if (totalSize >= 1024 && totalSize < 1048576) {
				double kb = totalSize / 1024;
				System.out.printf("Total space used: " + "%.2f" + " KB\n", kb);
			} else if (totalSize >= 1048576 && totalSize < 1073741824) {
				double mb = totalSize / (1024 * 1024);
				System.out.printf("Total space used: " + "%.2f" + " MB\n", mb);
			} else if (totalSize >= 1073741824) {
				double gb = totalSize / (1024 * 1024 * 1024);
				System.out.printf("Total space used: " + "%.2f" + " GB\n", gb);
			}
			// print # of files requested, followed by the file sizes and paths
			if (filesDisplayed == 1)
				System.out.println("Largest file:");
			else
				System.out.println("Largest " + args[1] + " files:");
			System.out.print(linkedList.toStringMaxNum(filesDisplayed));
		}

		else {
			System.out
					.println("Error: More than 2 files provided in command line. Shutting down program.");
			System.exit(0);
		}
	}

	/**
	 * Recursive method that explores all subdirectories within given top level
	 * directory. Returns total size of directory structure and creates linked
	 * list of all the files stored at all levels of the directory tree starting
	 * at the top level directory
	 * 
	 * @param potentialDirName
	 *            File object that represents top level directory
	 * @return Returns total size of directory structure
	 */
	public static double explorDir(File potentialDirName) {

		double totalSize = 0;
		//check if it's a direcotry and (for EC) that it has not already been added
		if (potentialDirName.isDirectory() && ec.contains(potentialDirName) == false) {
			// add size of directory to totalSize
			totalSize += potentialDirName.length();
			//Extra credit: insert directories into separate linked list to ensure they don't repeat
			ec.orderedInsert(potentialDirName);
			// get the list of all the files and subdirectories in
			// potentialDirName
			File[] fileList = potentialDirName.listFiles();
			// recursive call for each of the files and the subdirectories
			if (fileList != null) {
				for (int i = 0; i < fileList.length; i++) {
					//store visited folders in an array
					//if visited, don't visit
					totalSize += explorDir(fileList[i]);	
				}
			}
		} else if (potentialDirName.isFile()) {
			// add size of file to totalSize
			totalSize += potentialDirName.length();
			// insert the file into the linked list
			linkedList.orderedInsert(new FileOnDisk(potentialDirName
					.getAbsolutePath(), potentialDirName.length()));
		}
		return totalSize;
	}

}
