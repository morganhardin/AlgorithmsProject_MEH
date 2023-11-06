import java.util.Scanner;
import java.util.Random;

public class UniqueIntegers 
{
	/** Creats static integer variables to keep track of the number of operations. */
	protected static int operationsMerge = 0;
	protected static int operationsInsertion = 0;
	
	/** Randomly inputs integer into an array and does not allow duplicates within 
	 * the array.*/
	public static void UniqueIntegers(int[] A, int n)
	{
		int i = 0, r = 0, j = 0;
		boolean dup = false;
		Random random = new Random(); // creates new instance of Random class
		
		while (i < n)
		{
			j = 0;
			r = random.nextInt(n * n * n) + n; // sets r equal to random integer between 0 and n * n
			operationsMerge++;
			operationsInsertion++;
			
			while (j < i)
			{
				operationsMerge++;
				operationsInsertion++;
				if (A[i] == A[j]) // if element in A[i] equals r, it's a duplicate so loop is broken
				{
					dup = true;
					break;
				}
				j = j + 1;
			}
			if (dup == false) // if it's not a duplicate, sets A[i] equal to r
			{
				operationsMerge++;
				operationsInsertion++;
				A[i] = r;
			}
			i = i + 1;
		}
	}
	
	/** Called from within sort method that will merge that already sorted values back into the
	 * array after they have been sorted. */
	public static void merge(int arr[], int l, int m, int r) // merges the sorted array back together
    { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        for (int i = 0; i < n1; ++i) // loops through left side and adds left values to left array
        {
        	operationsMerge++;
            L[i] = arr[l + i]; 
        }
        for (int j = 0; j < n2; ++j) // loops through right side and adds right values to right array
        {
        	operationsMerge++;
            R[j] = arr[m + 1 + j];
        }
  
        int i = 0, j = 0, k = l;
        
        while (i < n1 && j < n2) // loops through to merge left and right arrays and values
        { 
        	operationsMerge++;
            if (L[i] <= R[j]) // adds left value into array based on its sorted position
            { 
            	operationsMerge++;
                arr[k] = L[i]; 
                i++; 
            } 
            else // adds right value into array based on its sorted position
            { 
            	operationsMerge++;
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while (i < n1) // adds left value into array based on its sorted position
        { 
        	operationsMerge++;
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) // adds right value into array based on its sorted position
        { 
        	operationsMerge++;
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
	
	/** Sorts the inputted array and then calls the merge method to merge the sorts
	 * back into the same array. */
    static void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
        	operationsMerge++;
            int m = (l + r) / 2; 
  
            sort(arr, l, m); // reursively calls sort with different parameters for the left side
            sort(arr, m + 1, r); // recursively calls sort with different parameters for the right side
  
            merge(arr, l, m, r); // calls merge with sorted values
        } 
    } 
    
    /** Sorts the inputted array by comparing two numbers and swapping to put them into the correct 
     * order and doing multiple passes until there are no more swaps needed for the array to be in
     * order. */
    static void insertion(int A[])
    {
    	int n = A.length;
        for (int i = 1; i < n; ++i) 
        {
        	operationsInsertion++;
            int key = A[i]; // sets key to be compared in array
            int j = i - 1;
 
            /* Move elements of A[0..i-1], that are
            * greater than key, to one index ahead
            * of their current index. */
            while (j >= 0 && A[j] > key) 
            {
            	operationsInsertion++;
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }
    
    /** Loops through inputted array and prints out the value at each index. */
    static void printArray(int arr[]) // prints entire inputted array
    { 
        int n = arr.length; // sets n equal to array A length
        for (int i = 0; i < n; ++i) // loops through each iteration of the array
        {
        	operationsMerge++;
        	operationsInsertion++;
            System.out.print(arr[i] + " "); // prints array value for that index
        }
        System.out.println(); 
    } 
	
    /** Main method that asks for number of elements in array and calls UniqueIntegers, 
     * sort, merge, and printArray to return array before sorting, after sorting, and
     * shows efficiency of the algorithms. */
	public static void main(String[] args) // main method
	{
		Scanner input = new Scanner(System.in); // creates new instance of Scanner class
		
		System.out.print("Enter the number of elements in the list: ");
		int n = input.nextInt(); // can change to int n = 25; to keep array size the same
		
		int[] A = new int[n]; // creates new array A of n integers
		int[] B = new int[n]; // creates new array B of n integers
		
		UniqueIntegers(A, n); // puts n random integers in array A
		for (int i = 0; i < A.length; i++)
		{
			B[i] = A[i]; // copies elements in A into B
		}
		
		System.out.println("\n--------------------------");
		System.out.println("Unique Integers");
		System.out.println("--------------------------");
		
		System.out.println("Array of " + n + " Integers:"); 
        printArray(A); // calls printArray for array A
        System.out.println("\nEfficiency for Original Algorithm: O(n^2)");
		
        System.out.println("\n--------------------------");
		System.out.println("Merge Sort Algorithm");
		System.out.println("--------------------------");
        
		System.out.println("Original Array:"); 
        printArray(A); // calls printArray for array A
		
        sort(A, 0, A.length - 1); // sorts array A
        System.out.println("\nSorted Array Using Merge Sort:"); 
        printArray(A); // calls printArray for sorted array A
        
		System.out.println("\nEfficiency of Merge Sort Algorithm: O(n log n)");
		System.out.println("\nNumber of Operations in Total: " + operationsMerge); // prints number of operations for Merge Sort
		
		System.out.println("\n--------------------------");
		System.out.println("Insertion Sort Algorithm");
		System.out.println("--------------------------");
		
		System.out.println("Original Array:"); 
        printArray(B); // calls printArray for array B
		
		insertion(B); // sorts array B
		System.out.println("\nSorted Array Using Insertion Sort:");
		printArray(B); // calls printArray for array B
		
		System.out.println("\nEfficiency of Insertion Sort Algorithm: O(n log n)");
		System.out.println("\nNumber of Operations in Total: " + operationsInsertion); // prints number of operations for Insertion Sort
		
		input.close(); // close Scanner
	}
}
