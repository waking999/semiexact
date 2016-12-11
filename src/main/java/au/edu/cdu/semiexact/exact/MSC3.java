package au.edu.cdu.semiexact.exact;

import java.util.List;
import java.util.Map;
import java.util.Set;

import au.edu.cdu.semiexact.util.ExistQualifiedSet;
import au.edu.cdu.semiexact.util.Util;

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
		return branch(map,rr);
	}

	public ReturnResult<Integer> branch(Map<Integer, List<Integer>> map, ReturnResult<Integer> rr) {

		if (map == null || map.size() == 0) {
			return rr;
		}
		ExistQualifiedSet exist = Util.existSubset(map);
		if (exist.isExist()) {
			do {
				int setIndex = exist.getSetIndex();
				map.remove(new Integer(setIndex));
				exist = Util.existSubset(map);
			} while (exist.isExist());
			Map<Integer, List<Integer>> mapCopy = Util.copyMap(map);

			return branch(mapCopy, rr);
		}
		List<Integer> uList = Util.unionSets(map);
		exist = Util.existUniqueSetForAElement(uList, map);
		if (exist.isExist()) {
			int setIndex = exist.getSetIndex();
			List<Integer> si = map.get(setIndex);

			Util.deleteSet(map, si);

			exist = Util.existSubset(map);
			if (exist.isExist()) {
				do {
					int setIndexPrime = exist.getSetIndex();
					map.remove(new Integer(setIndexPrime));
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

		int siLen = si.size();
		if (siLen <= 2) {
			return polyMsc(map, rr);
		}

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
		Set<Integer> keySet = map.keySet();

		List<Integer> results = rr.getResults();
		results.addAll(keySet);

		return new ReturnResult<Integer>(rr.getResultSize() + keySet.size(), results);

	}
}
