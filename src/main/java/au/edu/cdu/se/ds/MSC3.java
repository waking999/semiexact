package au.edu.cdu.se.ds;

import java.util.List;
import java.util.Map;
import java.util.Set;

import au.edu.cdu.se.util.ExistQualifiedSet;
import au.edu.cdu.se.util.Util;

/**
 * 
 * @author kwang1
 * 
 *         1. the exactly same as the basic algorithm 2. apply the reduction
 *         rule exhaustedly 3. return not only solution size but also solutions
 *
 */
public class MSC3 {
	Map<Integer, List<Integer>> map;

	public void setMap(Map<Integer, List<Integer>> map) {
		this.map = map;
	}

	public void setRr(ReturnResult<Integer> rr) {
		this.rr = rr;
	}

	ReturnResult<Integer> rr;

	public ReturnResult<Integer> run() {
		return branch(map, rr);
	}

	private ReturnResult<Integer> branch(Map<Integer, List<Integer>> map, ReturnResult<Integer> rr) {

		if (map == null || map.size() == 0) {
			return rr;
		}

		// subset rule
		ExistQualifiedSet exist = Util.existSubset(map);
		if (exist.isExist()) {
			do {
				int setIndex = exist.getSetIndex();
				map.remove(Integer.valueOf(setIndex));
				exist = Util.existSubset(map);
			} while (exist.isExist());
			Map<Integer, List<Integer>> mapCopy = Util.copyMap(map);

			return branch(mapCopy, rr);
		}
		List<Integer> uList = Util.unionSets(map);

		// unique set for an element
		exist = Util.existUniqueSetForAElement(uList, map);
		if (exist.isExist()) {
			int setIndex = exist.getSetIndex();
			List<Integer> si = map.get(setIndex);

			Util.deleteSet(map, si);

			exist = Util.existSubset(map);
			if (exist.isExist()) {
				do {
					int setIndexPrime = exist.getSetIndex();
					map.remove(Integer.valueOf(setIndexPrime));
					exist = Util.existSubset(map);
				} while (exist.isExist());
			}

			rr.getResults().add(setIndex);
			rr.setResultSize(rr.getResultSize() + 1);
			Map<Integer, List<Integer>> mapCopy = Util.copyMap(map);

			return branch(mapCopy, rr);
		}
		int siIndex = Util.getMaxCardinalitySetIndex(map);
		List<Integer> si = map.get(siIndex);

		// base rule
		int siLen = si.size();
		if (siLen <= 2) {
			return polyMsc(map, rr);
		}

		// branch
		Map<Integer, List<Integer>> mapCopy = Util.copyMap(map);
		mapCopy.remove(siIndex);
		ReturnResult<Integer> rr1 = branch(mapCopy, rr);

		mapCopy = Util.copyMap(map);
		Util.deleteSet(mapCopy, si);
		rr.getResults().add(siIndex);
		rr.setResultSize(rr.getResultSize() + 1);
		ReturnResult<Integer> rr2 = branch(mapCopy, rr);

		if (rr1.getResultSize() < rr2.getResultSize()) {
			return rr1;
		} else {
			return rr2;
		}

	}

	private ReturnResult<Integer> polyMsc(Map<Integer, List<Integer>> map, ReturnResult<Integer> rr) {
		// TODO: this is not really right, need to be revised.
		Set<Integer> keySet = map.keySet();

		List<Integer> results = rr.getResults();
		results.addAll(keySet);

		return new ReturnResult<Integer>(rr.getResultSize() + keySet.size(), results);

	}
}
