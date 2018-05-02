package au.edu.cdu.se.ds;

import java.util.Map;

class MMObj {
    // maximum matching number
    private int mnum;
    // maximum matching
    private Map<Integer, Integer> matching;

    MMObj(int mnum, Map<Integer, Integer> matching) {
        super();
        this.mnum = mnum;
        this.matching = matching;
    }

//	public void setMnum(int mnum) {
//		this.mnum = mnum;
//	}

    int getMnum() {
        return mnum;
    }

//	public void setMatching(Map<Integer, Integer> matching) {
//		this.matching = matching;
//	}

    Map<Integer, Integer> getMatching() {
        return matching;
    }

}
