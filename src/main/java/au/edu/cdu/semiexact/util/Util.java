package au.edu.cdu.semiexact.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static final int IMPOSSIBLE_SET_INDEX = -1;

	/**
	 * convert an integer array to an integer list
	 * 
	 * @param a,
	 *            an integer array
	 * @return an integer list
	 */
	public static List<Integer> arrayToList(int[] a) {
		int aLen = a.length;
		if (aLen == 0) {
			return null;
		}

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < aLen; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}

		return list;
	}

	/**
	 * if set s1 is a subset of set s2.
	 * 
	 * @param s1,
	 *            a set
	 * @param s2,
	 *            a set
	 * @return true: s1 is a subset of s2; false: otherwise
	 */
	public static <T> boolean is1Subset2(List<T> s1, List<T> s2) {
		if (s1 == null) {
			return true;
		}
		if (s2 == null) {
			return false;
		}
		int s1Len = s1.size();
		int s2Len = s2.size();
		if (s2Len < s1Len) {
			return false;
		}
		for (int i = 0; i < s1Len; i++) {
			if (!s2.contains(s1.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check if there is a set si is a subset of a set sj in the list s.
	 * 
	 * @param s,
	 *            a list containing sets
	 * @return an object containing the flag and the subset index. if the flag
	 *         is false, the index should be ignored
	 */
	public static <T> ExistQualifiedSet existSubset(List<List<T>> s) {
		if (s == null)
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		int sLen = s.size();
		if (sLen < 2) {
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		}
		for (int i = 0; i < sLen; i++) {
			for (int j = 0; j < sLen; j++) {
				if ((i != j) && (is1Subset2(s.get(i), s.get(j)))) {
					return new ExistQualifiedSet(true, i);
				}
			}
		}
		return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
	}

	/**
	 * check if there an unique set si containing an element u
	 * 
	 * @param u,
	 *            an element
	 * @param s,
	 *            a list containing sets
	 * @return an object containing the flag and the subset index. if the flag
	 *         is false, the index should be ignored
	 */
	public static <T> ExistQualifiedSet existUniqueSetForAElement(T u, List<List<T>> s) {
		if (s == null) {
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		}
		int sLen = s.size();
		int count = 0;
		int containSetIndex = IMPOSSIBLE_SET_INDEX;
		for (int i = 0; i < sLen; i++) {
			List<T> si = s.get(i);
			if (si.contains(u)) {
				count++;
				containSetIndex = i;
			}
			if (count > 1) {
				break;
			}
		}
		if (count != 1) {
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		}

		return new ExistQualifiedSet(true, containSetIndex);
	}

	/**
	 * check if there an unique set si containing an element u in an element
	 * list
	 * 
	 * @param uList,
	 *            an element list
	 * @param s,
	 *            a list containing sets
	 * @return an object containing the flag and the subset index. if the flag
	 *         is false, the index should be ignored
	 */
	public static <T> ExistQualifiedSet existUniqueSetForAElement(List<T> uList, List<List<T>> s) {
		if (uList == null) {
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		}
		if (s == null) {
			return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
		}
		int uLen = uList.size();
		for (int i = 0; i < uLen; i++) {
			ExistQualifiedSet exist = existUniqueSetForAElement(uList.get(i), s);
			if (exist.isExist()) {
				return exist;
			}
		}
		return new ExistQualifiedSet(false, IMPOSSIBLE_SET_INDEX);
	}

	/**
	 * union all elements of the subsets of s
	 * 
	 * @param s,
	 *            a list containing sets
	 * @return a list containing all elements of the subsets of s
	 */
	public static <T> List<T> unionSets(List<List<T>> s) {
		if (s == null) {
			return null;
		}
		int sLen = s.size();
		if (sLen == 0) {
			return null;
		}
		if (sLen == 1) {
			return s.get(0);
		}
		List<T> uList = new ArrayList<T>();
		for (List<T> l : s) {
			for (T u : l) {
				if (!uList.contains(u)) {
					uList.add(u);
				}
			}
		}
		return uList;
	}

	/**
	 * a set s1 minus a set s2
	 * 
	 * @param s1,
	 *            a set
	 * @param s2,
	 *            a set
	 */
	public static <T> List<T> set1Minus2(List<T> s1, List<T> s2) {
		if (s1 == null) {
			return s1;
		}
		if (s2 == null) {
			return s1;
		}
		List<T> rtnList = Util.copyList(s1);

		for (T t : s2) {
			if (s1.contains(t)) {
				rtnList.remove(t);
			}
		}
		if (rtnList.size() == 0) {
			return null;
		}
		return rtnList;

	}

	/**
	 * make a copy of a list
	 * 
	 * @param s,
	 *            a list
	 * @return a copy of the list
	 */
	public static <T> List<T> copyList(List<T> s) {
		if (s == null) {
			return null;
		}
		List<T> c = new ArrayList<T>();
		c.addAll(s);
		return c;
	}

	/**
	 * delete a set r from a list containing sets: {s' is not empty: s'=si\r, si
	 * belongs s}
	 * 
	 * @param s,
	 *            a list containing sets
	 * @param r,
	 *            a set
	 * @return the list after deleting the set
	 */
	public static <T> List<List<T>> deleteSet(List<List<T>> s, List<T> r) {
		if (s == null) {
			return s;
		}

		if (r == null) {
			return s;
		}

		if (!s.contains(r)) {
			return s;
		}
		List<T> rCopy = copyList(r); 
		
		int sLen = s.size();

		 
		for (int i = 0; i < sLen; i++) {
			List<T> l = s.get(i);
			List<T> lCopy=copyList(l);
			lCopy = Util.set1Minus2(lCopy, rCopy);
			if (lCopy == null || lCopy.size() == 0) {
				s.remove(l);
			}else{
				int lIndex=s.indexOf(l);
				s.set(lIndex, lCopy);
			}
			sLen=s.size();

		}

//		for (List<T> l : s) {
//			List<T> lCopy=copyList(l);
//			lCopy = Util.set1Minus2(lCopy, rCopy);
//			if (lCopy == null || lCopy.size() == 0) {
//				s.remove(l);
//			}else{
//				int lIndex=s.indexOf(l);
//				s.set(lIndex, lCopy);
//			}
//		}

		return s;
	}

	/**
	 * get the max cardinality set in the list s containing sets
	 * 
	 * @param s,
	 *            a list containing sets
	 * @return the max cardinality set in the list s
	 */
	public static <T> List<T> getMaxCardinalitySet(List<List<T>> s) {
		if (s == null) {
			return null;
		}

		int maxCardinality = 0;
		List<T> rtnList = null;

		for (List<T> l : s) {
			int cardinality = l.size();
			if (cardinality > maxCardinality) {
				maxCardinality = cardinality;
				rtnList = l;
			}
		}

		return rtnList;
	}

	/**
	 * remove a set r from a list containing sets: s\{r}
	 * 
	 * @param s,
	 *            a list containing sets
	 * @param r,
	 *            a set
	 * @return the list after removing the set
	 */
	public static <T> List<List<T>> removeSet(List<List<T>> s, List<T> r) {
		if (s == null) {
			return null;
		}
		if (r == null) {
			return s;
		}
		s.remove(r);
		return s;
	}

}
