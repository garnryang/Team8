package edu.psu.sweng500.team8.coreDataStructures;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/** Static class for common functions
 * TODO: Move and/or give this a more specific name as it evolves
 */
public class Helpers {
	public static Set<Integer> getValidNumberSet() {
		return new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	}
}
