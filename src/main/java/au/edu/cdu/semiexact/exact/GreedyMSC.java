package au.edu.cdu.semiexact.exact;

import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.Util;

public class GreedyMSC {
	public static <ET, ST> void run(GlobalVariable<ET, ST> gv, int[] card, int[] freq, int allowCrossLvlNum) {
		int solCount = gv.getSolCount();
		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		int crossLvlNum = 1;

		while (crossLvlNum <= allowCrossLvlNum && freq[0] > 0) {
			int set = Util.getMaxCardinalitySetIndex(gv, card);
			Util.addSetToCover(gv, card, freq, set);
			sol[solPtr++] = set;
			solCount++;
			crossLvlNum++;
		}

		gv.setSol(sol);
		gv.setSolCount(solCount);
		gv.setSolPtr(solPtr);
	}
}
