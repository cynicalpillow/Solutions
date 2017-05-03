import java.util.*;
import java.math.*;
import java.io.*;
public class BinarySearchInRange {
	public static void main(String args[]) throws Exception {
	}
	public static boolean binarySearch(ArrayList<Integer> a, int l, int u){
		int low = 0;
	    int high = a.size()-1;
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if(a.get(mid) > u) {
	            high = mid - 1;
	        } else if(a.get(mid) < l) {
	            low = mid + 1;
	        } else {
	            return true;
	        }
	    }
	    return false;
	}
}
