package au.edu.cdu.se.ds;

import au.edu.cdu.se.util.ds.DSGlobalVariable;

public interface IMSC<ET, ST> {
	public int branch(DSGlobalVariable<ET, ST> gv, AlgorithmParameter ap);
}
