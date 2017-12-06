package au.edu.cdu.se.ds;

import org.apache.log4j.Logger;

import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.Util;
import au.edu.cdu.se.util.ds.DSGlobalVariable;

public class GreedyMSC {
	private static Logger log = LogUtil.getLogger(GreedyMSC.class);

	public static int run(DSGlobalVariable gv, int[] card, int[] freq) {
		log.debug("GreedyMSC is called");
		int solCount = gv.getSolCount();
		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		int crossLvlNum = 1;
		int s1 = card[0];
		int e1 = freq[0];

		while (e1 > 0) {
			int set = Util.getMaxCardinalitySetIndex(gv, card, s1);
			int tempCard = card[set];
			Util.addSetToCover(gv, card, freq, s1, e1, set);
			e1 = e1 - tempCard;
			s1 = s1 - 1;

			sol[solPtr++] = set;
			solCount++;
			crossLvlNum++;
		}

		card[0] = s1;
		freq[0] = e1;
		gv.setCard(card);
		gv.setFreq(freq);
		gv.setSol(sol);
		gv.setSolCount(solCount);
		gv.setSolPtr(solPtr);

		return crossLvlNum - 1;
	}
}
