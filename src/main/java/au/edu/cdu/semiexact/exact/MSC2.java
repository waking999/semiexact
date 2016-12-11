package au.edu.cdu.semiexact.exact;

import java.util.List;

import au.edu.cdu.semiexact.util.ExistQualifiedSet;
import au.edu.cdu.semiexact.util.Util;

public class MSC2 {
	private List<List<Integer>> s;

	public void setS(List<List<Integer>> s) {
		this.s = s;
	}

	public int run() {
		return branch(s);
	}

	private int branch(List<List<Integer>> s) {
		if (s == null || s.size() == 0)
			return 0;
		ExistQualifiedSet exist = Util.existSubset(s);
		if (exist.isExist()) {
			do {
				int setIndex = exist.getSetIndex();
				List<Integer> si = s.get(setIndex);
				Util.removeSet(s, si);
				exist = Util.existSubset(s);
			} while (exist.isExist());
			List<List<Integer>> sCopy = Util.copyList(s);
			return branch(sCopy);
		}
		List<Integer> uList = Util.unionSets(s);
		exist = Util.existUniqueSetForAElement(uList, s);
		if (exist.isExist()) {
			int setIndex = exist.getSetIndex();
			List<Integer> si = s.get(setIndex);
			List<List<Integer>> sCopy = Util.copyList(s);
			sCopy = Util.deleteSet(sCopy, si);

			exist = Util.existSubset(sCopy);
			if (exist.isExist()) {
				do {
					int setIndexPrime = exist.getSetIndex();
					List<Integer> siPrime = sCopy.get(setIndexPrime);
					Util.removeSet(sCopy, siPrime);
					exist = Util.existSubset(sCopy);
				} while (exist.isExist());
			}

			return 1 + branch(sCopy);
		}
		List<Integer> si = Util.getMaxCardinalitySet(s);
		int siLen = si.size();
		if (siLen <= 2) {
			return polyMsc(s);
		}
		return Math.min(branch(Util.removeSet(Util.copyList(s), si)), 1 + branch(Util.deleteSet(Util.copyList(s), si)));

	}

	private int polyMsc(List<List<Integer>> s) {
		return s.size();
	}
}
