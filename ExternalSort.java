import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalSort {
	// track number of values read
	public static int numValues = 0;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);	// Scanner object to get input from kb
		
		int runsize = input.nextInt();	// get runsize from user
		Path t1 = Paths.get("src/T1.txt");	// Path to T1 for input
		
		Path p = extsort(t1, runsize);
		
	}
	
	public static Path extsort(Path t1, int runsize) {
		// Get paths for each text file
		Path t2 = Paths.get("src/T2.txt");
		Path t3 = Paths.get("src/T3.txt");
		Path t4 = Paths.get("src/T4.txt");
		
		try {
			// Read from t1
			Scanner input = new Scanner(t1);
			// Write to t3 and t4
			PrintWriter t3Write = new PrintWriter(t3.toString());
			PrintWriter t4Write = new PrintWriter(t4.toString());
			
			// Str and int arr to store ints
			String str = null;
			int[] arr;
			
			
	}
}
