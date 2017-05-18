package au.edu.cdu.semiexact.algo.msc;

import au.edu.cdu.semiexact.algo.AlgorithmParameter;
import au.edu.cdu.semiexact.util.MSCGlobalVariable;

public interface IMSC<ET, ST> {
	public int branch(MSCGlobalVariable<ET, ST> gv, AlgorithmParameter ap);
}
