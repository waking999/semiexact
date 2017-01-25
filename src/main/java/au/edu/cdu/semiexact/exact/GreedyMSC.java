package au.edu.cdu.semiexact.exact;

import org.apache.logging.log4j.Logger;

import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

public class GreedyMSC {
	private static Logger log = LogUtil.getLogger(GreedyMSC.class);

	public static <ET, ST> int run(GlobalVariable<ET, ST> gv, int[] card, int[] freq) {
		log.debug("GreedyMSC is called");
		int solCount = gv.getSolCount();
		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		int crossLvlNum = 1;

		while (freq[0] > 0) {
			int set = Util.getMaxCardinalitySetIndex(gv, card, card[0]);
			Util.addSetToCover(gv, card, freq, card[0], freq[0], set);
			sol[solPtr++] = set;
			solCount++;
			crossLvlNum++;
		}

		gv.setSol(sol);
		gv.setSolCount(solCount);
		gv.setSolPtr(solPtr);

		return crossLvlNum - 1;
	}
}
