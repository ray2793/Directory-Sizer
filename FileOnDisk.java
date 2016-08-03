package raymond_li;
import java.io.File;

/**
 * This class represents a file in the program and implements the
 * Comparable interface. The class stores the name (full name w/
 * absolute path) and its size.
 * @author Raymond Li
 * @version 3/30/14
 *
 */
public class FileOnDisk implements Comparable<FileOnDisk> {
	
	private String pathName; //contains path name of file
	private double size;	 //contains size of file
	
	/**
	 * Constructs a file within the program
	 * @param pathName
	 * 		Contains absolute path name of the file
	 * @param size
	 * 		Contains size of the file
	 */
	public FileOnDisk (String pathName, double size) {
		
			this.pathName = pathName;
			this.size = size;
		}
	/**
	 * First compares files by size and then by alphanumerics if sizes are equal
	 * Returns 1 if current file size is greater than file to be compared
	 * or should be placed higher on the output
	 * Returns -1 if current file size is less than file to be compared
	 * or should be placed lower on the output
	 */
	public int compareTo(FileOnDisk compareFile) {
		
		if (this.size > compareFile.getSize()) {
			return 1;
		}
		else if (this.size < compareFile.getSize()) {
			return -1;
		}
		else {
			
			String currentFile = this.getPathName().toLowerCase();
			String comparedFile = compareFile.getPathName().toLowerCase();
			
			if (currentFile.compareTo(comparedFile) > 0){
				return -1;
			}
			else if (currentFile.compareTo(comparedFile) < 0){
				return 1;
			}
		}
		//This should not happen: Default return case if error occurs
		return 0 ;
	}
	

	@Override
	/**
	 * 	formats file size output to reflect size in bytes, KBs, MBs, and GBs
	 */
	public String toString() {
		
		if (size < 1024) {
            return String.format("%.2f bytes "+ pathName+"\n", size);
        }
        if (size >= 1024 && size < 1048576) {
            double kb = size/1024;
            return String.format("%.2f KB "+ pathName+"\n", kb);
        }
        if (size >= 1048576 && size < 1073741824) { 
            double mb = size/(1024*1024); 
            return String.format("%.2f MB "+ pathName+"\n", mb);
        }
        if (size >=  1073741824) { //Size in GBs
            double gb = size/(1024*1024*1024);
            return String.format("%.2f GB "+ pathName+"\n", gb);
        }
		return "" + size + " " + pathName+"\n";
	}
	/**
	 * Getter method for path name
	 * @return
	 * 		Returns path name
	 */
	public String getPathName() {
		return pathName;
	}
	/**
	 * Setter method for path name of file
	 * @param pathName
	 * 		Sets path name of file
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	/**
	 * Getter method for size of file
	 * @return
	 * 		Returns size of file
	 */
	public double getSize() {
		return size;
	}
	/**
	 * Setter method for size of file
	 * @param size
	 * 		Sets size of file
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	

}
