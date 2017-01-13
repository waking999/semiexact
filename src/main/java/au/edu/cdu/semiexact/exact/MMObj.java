package au.edu.cdu.semiexact.exact;

import java.util.Map;

public class MMObj {
	//maximum matching number
	private int mnum;
	//maximum matching
	private Map<Integer,Integer> matching;
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public Map<Integer, Integer> getMatching() {
		return matching;
	}
	public void setMatching(Map<Integer, Integer> matching) {
		this.matching = matching;
	}
	public MMObj(int mnum, Map<Integer, Integer> matching) {
		super();
		this.mnum = mnum;
		this.matching = matching;
	}
 
}
