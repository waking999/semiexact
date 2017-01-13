package au.edu.cdu.semiexact.exact;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * 
 * 1. convert Faisal's c code into java format, which also uses Faisal's graph
 * representation data structure ;
 * 
 * 2. use existing Edmonds max matching algorithm;
 * 
 * 3. add basic greedy as a sub-rountine for semi-exact
 */

public class MSC5<ET, ST> implements IMSC<ET, ST> {
	public int branch(GlobalVariable<ET, ST> gv) {
		return ConstantValue.IMPOSSIBLE_VALUE;
	}
}
