package de.algo;


public class Main {	
	/* START OF TESTING */
	public static int[] emptyArray     = {};  // empty
	public static int[] singletonArray = {1}; // = (1)
	public static int[] generalArray   = new int[333];
	
	public static void setUpGeneralArray() {
		for (int i=0; i<generalArray.length; i++) {
			generalArray[i] = 3*i;
		}
	}
	
	public static void searchInArray(int[] array, int key) {
		int linearResult   = linearSearch(array, key);
		int binaryResult   = binarySearch(array, key);
		int interpolResult = interpolationSearch(array, key);
		
		if (linearResult != -1) {
			System.out.println("Linear search: " + key + " found in possition " + linearResult);
		}
		else {
			System.out.println("Linear search: " + key + " not found in array");
		}
		
		if (binaryResult != -1) {
			System.out.println("Binary search: " + key + " found in possition " + binaryResult);
		}
		else {
			System.out.println("Binary search: " + key + " not found in array");
		}
		
		if (interpolResult != -1) {
			System.out.println("Interpolation search: " + key + " found in possition " + interpolResult);
		}
		else {
			System.out.println("Interpolation search: " + key + " not found in array");
		}
		System.out.println();
	}
	
    public static void main(String[] args) {
    	setUpGeneralArray();
    	
    	// the keys to search
    	int[] keys = {-10000, -1000, -333, -100, -2, -1, 0, 1, 2, 100, 333, 1000, 10000};
		
		// test all keys
		for (int i=0; i < keys.length; i++) {
			System.out.println("Searching for key " + keys[i] + " in the first array:"); 
			searchInArray(emptyArray, keys[i]);
			System.out.println("Searching for key " + keys[i] + " in the second array:");
			searchInArray(singletonArray, keys[i]);
			System.out.println("Searching for key " + keys[i] + " in the third array:");
			searchInArray(generalArray, keys[i]);
			System.out.println();
		}
    }
    /* END OF TESTING */
      
    
    /**
     * Implementation of linear search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    public static int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}    
        return -1;
    }
    
    
    /**
     * Implementation of binary search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array, int key) {
    	int low = 0;
    	int high = array.length - 1;
    	while (low <= high) {
    		// key is in array[low ... high] or not in array at all
    		int middle = low + (high - low) / 2;
    		if (key < array[middle]) {
				high = middle - 1;
			} else if (key > array[middle]) {
				low = middle + 1;
			} else {
				return middle;
			}
		}
    	return -1;
    }
    
    
    /**
     * Implementation of interpolation search. Return the index of key in the array.
     * Return -1 if key is not contained in the array.
     * @param array
     * @param key
     * @return
     */
    public static int interpolationSearch(int[] array, int key) {
    	if (isTrivial(array)) {
    		if (array.length == 0) return -1; // brake if empty arr
        	if (array.length == 1) {
        		if (key == array[0]) {
    				return 0;
    			}
        	} else {
    			return -1;
    		}
		} else {
	    	int low = 0;
	    	int high = array.length - 1;
	    	while (array[low] != array[high] && array[low] <= key && array[high] >= key) {
	    		final int middle = (((key - array[low]) * (high - low)) / (array[high] - array[low])) + low;
	    		if (key < array[middle]) {
					high = middle - 1;
				} else if (key > array[middle]) {
					low = middle + 1;
				} else {
	    			return middle;
	    		}
			}
	    	if(array[low] == key) {
	    		return low;
	    	}
		}
    	return -1;
    }
    
    public static boolean isTrivial(int[] array) {
    	return array.length < 2;
    }
}

