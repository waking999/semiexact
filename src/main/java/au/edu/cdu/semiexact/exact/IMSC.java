package au.edu.cdu.semiexact.exact;

import au.edu.cdu.semiexact.util.GlobalVariable;

public interface IMSC<ET,ST> {
	public int branch(GlobalVariable<ET, ST> gv);
}
