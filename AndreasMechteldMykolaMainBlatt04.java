public class Main {
	// Static variables to count the number of comparisons
	private static int comparisonsOfMergeSort;
	private static int comparisonsOfQuickSort;
	
	private final static int UPPER_BOUND = 100;

	/* START OF TESTING */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		java.util.Random random = new java.util.Random();

		@SuppressWarnings("rawtypes")
		java.util.Map mergeSortComparisons = new java.util.TreeMap<Integer, Integer>();
		@SuppressWarnings("rawtypes")
		java.util.Map quickSortComparisons = new java.util.TreeMap<Integer, Integer>();

		for (int arraySize = 100; arraySize <= 2000; arraySize = arraySize + 100) {
			// Create a new array with double values and fill it with random numbers.
			double[] doubleRandomArray = new double[arraySize];
			for (int i = 0; i < doubleRandomArray.length; i++) {
				doubleRandomArray[i] = random.nextDouble();
			}

			// Sort with mergeSort
			double[] copy1 = java.util.Arrays.copyOf(doubleRandomArray, doubleRandomArray.length);
			mergeSort(copy1);
			mergeSortComparisons.put(arraySize, comparisonsOfMergeSort);
			for (int i = 0; i < copy1.length - 1; i++) {
				if (copy1[i] > copy1[i + 1]) {
					System.err.println("Error in mergeSort at position " + i);
					break;
				}
			}

			// Sort with quickSort
			double[] copy2 = java.util.Arrays.copyOf(doubleRandomArray, doubleRandomArray.length);
			quickSort(copy2);
			quickSortComparisons.put(arraySize, comparisonsOfQuickSort);
			for (int i = 0; i < copy2.length - 1; i++) {
				if (copy2[i] > copy2[i + 1]) {
					System.err.println("Error in quickSort at position " + i);
					break;
				}
			}
		}

		// Create a new array with integer values and fill it with random numbers.
		int[] intRandomArray = new int[1000];
		for (int i = 0; i < intRandomArray.length; i++) {
			intRandomArray[i] = random.nextInt(UPPER_BOUND);
		}

		countingSort(intRandomArray);
		
		// Sort with countingSort countingSort(intRandomArray);
		for (int i = 0; i < intRandomArray.length - 1; i++) {
			if (intRandomArray[i] > intRandomArray[i + 1]) {
				System.err.println("Error in countingSort at position " + i);
				break;
			}
		}

		System.out.println("If no errors have been printed, then you passed the test ");

		System.out.println("Merge Sort");
		mergeSortComparisons.forEach((key, value) -> System.out.println(key + ": " + value));

		System.out.println();

		System.out.println("Quick Sort");
		quickSortComparisons.forEach((key, value) -> System.out.println(key + ": " + value));

	}
	/* END OF TESTING */

	/* START OF MERGESORT */
	/**
	 * Sorts an array with mergesort.
	 * 
	 * @param array
	 *            - the array to be sorted.
	 */
	public static void mergeSort(double[] array) {
		divideAndMerge(array, 0, array.length - 1);
	}

	static void divideAndMerge(double[] array, int lower, int upper) {
		if (lower < upper) {
			int middle = (lower + upper) / 2;
			// recursively divide array into parts
			divideAndMerge(array, lower, middle);
			divideAndMerge(array, middle + 1, upper);
			merge(array, lower, middle, upper);
		}
	}

	static void merge(double[] array, int lower, int middle, int upper) {
		double[] temp = new double[array.length];
		temp = array.clone(); // make a copy of an array

		int leftBorder = lower;
		int rightBorder = middle + 1;
		int current = lower;

		while (leftBorder <= middle && rightBorder <= upper) {
			// count number of comparisons
			comparisonsOfMergeSort++;
			if (temp[leftBorder] <= temp[rightBorder]) {
				array[current] = temp[leftBorder];
				leftBorder++;

			} else {
				array[current] = temp[rightBorder];
				rightBorder++;
			}
			// shift current element one position further
			current++;
		}

		int rest = middle - leftBorder;
		// fill array with new values
		for (int i = 0; i <= rest; i++) {
			array[current + i] = temp[leftBorder + i];
		}
	}

	/* END OF MERGESORT */

	/* START OF QUICKSORT */
	/**
	 * Sorts an array with quicksort.
	 * 
	 * @param array
	 *            - the array to be sorted.
	 */
	public static void quickSort(double[] array) {
		sort(array, 0, array.length - 1);
	}
	
	static void sort(double array[], int lower, int upper) {
		if (lower < upper) {
			int pivot = divide(array, lower, upper);
			sort(array, lower, pivot - 1);
			sort(array, pivot + 1, upper);
		}
	}

	static int divide(double array[], int lower, int upper) {
		double pivot = array[upper];
		int smallestElement = (lower - 1); // index of smallest element
		for (int i = lower; i <= upper - 1; i++) {
			// count number of comparisons
			comparisonsOfQuickSort++;
			if (array[i] <= pivot) {
				smallestElement++;
				swapElements(array, smallestElement - 1, i);
			}
		}
		swapElements(array, smallestElement, upper);

		return smallestElement + 1;
	}
	
	private static void swapElements(double[] arr, int lower, int upper) {
		double temp = arr[lower + 1];
		arr[lower + 1] = arr[upper];
		arr[upper] = temp;
	}
 
	/* END OF QUICKSORT */

	/* START OF COUNTINGSORT */
	/**
	 * Sorts an array of integers with countingsort.
	 * 
	 * @param array
	 *            - the array to be sorted.
	 */
	public static void countingSort(int[] array) {
		int[] count = new int[UPPER_BOUND];
		// java initializes arrays of primitives with default values(in our case 0's)
		// store count of each number
		for (int i = 0; i < array.length; i++) {
			count[array[i]] = count[array[i]] + 1;
		}
		// evaluate count array to cumulative position indexes 
		for (int i = 1; i < UPPER_BOUND; i++) {
			count[i] = count[i] + count[i - 1];
		}
		// create new output sorted array
		int[] output = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			output[count[array[i]] - 1] = array[i];
			--count[array[i]];
		}
		// clone output to input array
		System.arraycopy(output, 0, array, 0, array.length);
	}

	/* END OF COUNTINGSORT */
}

