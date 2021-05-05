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
			
			// read all numbers of T1
			while (input.hasNextInt()) {
				str = read(input, str, runsize);	// read 4 values and store in arr
				arr = convertToArr(str);			// Convert str to arr
				Arrays.sort(arr);					// Sort arr
				str = convertToStr(arr);	// Convert sorted arr to str
				t3Write.print(str);			// Write sorted arr to file t3
				
				str = read(input, str, runsize);	// read 4 values and store in arr
				arr = convertToArr(str);			// Convert str to arr
				Arrays.sort(arr);					// Sort arr
				str = convertToStr(arr);	// Convert sorted arr to str
				t4Write.print(str);			// Write sorted arr to file t4
			}
			// close t3 and t4 after writing
			t3Write.close();
			t4Write.close();
			
			// Get number of runs depending if even
			int numRuns = getNumRuns(runsize);
			
			for (int i = 0; i < numRuns; i++) {
				if (i % 2 == 0) {
					Scanner t3Read = new Scanner(t3);
					Scanner t4Read = new Scanner(t4);
					boolean print = false;
					
					PrintWriter t1Write = new PrintWriter(t1.toString());
					PrintWriter t2Write = new PrintWriter(t2.toString());
					
					while (t3Read.hasNextInt()) {
						//str = read(t3Read, str, (int)(runsize * Math.pow(2, i)));
						int count = 0;
						str = "";
						str = read(t3Read, str, (int)(runsize * Math.pow(2,  i)));
						
						int counts = 0;
						while (t4Read.hasNextInt() && counts < runsize * Math.pow(2, i)) {
							str += t4Read.nextInt() + " ";
							counts++;
						}
						arr = convertToArr(str);	// Convert str to arr
						Arrays.sort(arr);			// Sort arr
						str = convertToStr(arr);	// Convert sorted arr to str
						
						// print data on tap1 or tap2 based on the condition
						if (!print) t1Write.print(str);
						else t2Write.print(str);
						// set other tap
						print = !print;
					}
					t1Write.close();
					t2Write.close();
				}
				else {
					str = "";
					// open input tap files T1 and T2
					Scanner t1Read = new Scanner(t1);
					Scanner t2Read = new Scanner(t2);
					boolean print = false;
					// open outputfiles tap T3 and T4
					PrintWriter tap3 = new PrintWriter(t3.toString());
					PrintWriter tap4 = new PrintWriter(t4.toString());
					// loop until tap1 has next input
					while (t1Read.hasNextInt()) {
						int count = 0;
						while (t1Read.hasNextInt() && count < runsize * Math.pow(2,  i)) {
							str += t1Read.nextInt() + " ";
							count++;
						}
						int counts = 0;
						while (t2Read.hasNextInt() && counts < runsize * Math.pow(2,  i)) {
							str += t2Read.nextInt() + " ";
							counts++;
						}
						arr = convertToArr(str);	// Convert str to arr
						Arrays.sort(arr);			// Sort arr
						str = convertToStr(arr);	// Convert sorted arr to str
						// print data on tap1 or tap2 based on the condition
						if (!print)
						tap3.print(str);
						else
						tap4.print(str);
						// set other tap
						print = !print;
				}
				// close all taps
				tap3.close();
				tap4.close();
			}
		}
	}
	
	private static int[] convertToArr(String s) {
		String[] strArr = s.split(" ");	// " " token
		int[] arr = new int[strArr.length];	// create array
		
		// str to int arr
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(strArr[i]);
		
		return arr;
	}
	
	public static String read(Scanner input, String str, int runsize) {
		str = "";	// Initialize string when making arr
		int numElem = 0;	// count elements
		
		while (input.hasNextInt() && numElem < runsize) {
			str += input.nextInt() + " ";
			numElem++;
			numValues++;
		}
		return str;
	}
		
	private static String convertToStr(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (Integer i : a) {
			sb.append(i);
			sb.append(" ");
		}
		return sb.toString();
	}
}
