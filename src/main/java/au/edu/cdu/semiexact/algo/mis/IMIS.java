package au.edu.cdu.semiexact.algo.mis;

import au.edu.cdu.semiexact.algo.AlgorithmParameter;
import au.edu.cdu.semiexact.util.MISGlobalVariable;

public interface IMIS<VT> {
	public int branch(MISGlobalVariable<VT> gv, AlgorithmParameter ap);
}
