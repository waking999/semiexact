package au.edu.cdu.se.util;

import au.edu.cdu.se.util.ds.DSGlobalVariable;

import java.util.*;

/**
 * an util class for common functions
 */
public class Util {

    /**
     * convert an integer array to an integer list
     *
     * @param a, an integer array
     * @return an integer list
     */
    public static List<Integer> arrayToList(int[] a) {
        int aLen = a.length;
        if (aLen == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        for (int anA : a) {
            if (!list.contains(anA)) {
                list.add(anA);
            }
        }

        return list;
    }

    /**
     * if set s1 is a subset of set s2.
     *
     * @param s1, a set
     * @param s2, a set
     * @return true: s1 is a subset of s2; false: otherwise
     */
    static <T> boolean is1Subset2(List<T> s1, List<T> s2) {
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
        for (T aS1 : s1) {
            if (!s2.contains(aS1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if there is a set si is a subset of a set sj in the list s.
     *
     * @param s, a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    public static <T> ExistQualifiedSet existSubset(List<List<T>> s) {
        if (s == null)
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        int sLen = s.size();
        if (sLen < 2) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < sLen; j++) {
                if ((i != j) && (is1Subset2(s.get(i), s.get(j)))) {
                    return new ExistQualifiedSet(true, i);
                }
            }
        }
        return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
    }

    /**
     * check if there is a set si is a subset of a set sj in the list s.
     *
     * @param map, a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    public static ExistQualifiedSet existSubset(Map<Integer, List<Integer>> map) {
        if (map == null)
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        int mapLen = map.size();
        if (mapLen < 2) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }
        Set<Integer> keySet = map.keySet();
        for (Integer i : keySet) {
            for (Integer j : keySet) {
                if ((!i.equals(j)) && (is1Subset2(map.get(i), map.get(j)))) {
                    return new ExistQualifiedSet(true, i);
                }
            }
        }

        return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
    }

    /**
     * check if there an unique set si containing an element u
     *
     * @param u, an element
     * @param s, a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    static <T> ExistQualifiedSet existUniqueSetForAElement(T u, List<List<T>> s) {
        if (s == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }
        int sLen = s.size();
        int count = 0;
        int containSetIndex = ConstantValue.IMPOSSIBLE_VALUE;
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
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }

        return new ExistQualifiedSet(true, containSetIndex);
    }

    /**
     * check if there an unique set si containing an element u
     *
     * @param u,   an element
     * @param map, a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    private static ExistQualifiedSet existUniqueSetForAElement(Integer u, Map<Integer, List<Integer>> map) {
        if (map == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }

        int count = 0;
        int containSetIndex = ConstantValue.IMPOSSIBLE_VALUE;

        Set<Integer> keySet = map.keySet();

        for (Integer key : keySet) {
            List<Integer> si = map.get(key);
            if (si.contains(u)) {
                count++;
                containSetIndex = key;
            }
            if (count > 1) {
                break;
            }
        }

        if (count != 1) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }

        return new ExistQualifiedSet(true, containSetIndex);
    }

    /**
     * check if there an unique set si containing an element u in an element
     * list
     *
     * @param uList, an element list
     * @param s,     a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    public static <T> ExistQualifiedSet existUniqueSetForAElement(List<T> uList, List<List<T>> s) {
        if (uList == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }
        if (s == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }

        for (T anUList : uList) {
            ExistQualifiedSet exist = existUniqueSetForAElement(anUList, s);
            if (exist.isExist()) {
                return exist;
            }
        }
        return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
    }

    /**
     * check if there an unique set si containing an element u in an element
     * list
     *
     * @param uList, an element list
     * @param map,   a list containing sets
     * @return an object containing the flag and the subset index. if the flag
     * is false, the index should be ignored
     */
    public static ExistQualifiedSet existUniqueSetForAElement(List<Integer> uList, Map<Integer, List<Integer>> map) {
        if (uList == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }
        if (map == null) {
            return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
        }


        for (Integer anUList : uList) {
            ExistQualifiedSet exist = existUniqueSetForAElement(anUList, map);
            if (exist.isExist()) {
                return exist;
            }
        }
        return new ExistQualifiedSet(false, ConstantValue.IMPOSSIBLE_VALUE);
    }

    /**
     * union all elements of the subsets of s
     *
     * @param s, a list containing sets
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
        List<T> uList = new ArrayList<>();
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
     * union all elements of the subsets of s
     *
     * @param map, a list containing sets
     * @return a list containing all elements of the subsets of s
     */
    public static <T> List<T> unionSets(Map<T, List<T>> map) {
        if (map == null) {
            return null;
        }
        int mapLen = map.size();
        if (mapLen == 0) {
            return null;
        }
        Set<T> keySet = map.keySet();

        List<T> uList = new ArrayList<>();

        for (T key : keySet) {
            List<T> l = map.get(key);
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
     * @param s1, a set
     * @param s2, a set
     * @return the elements in set s1 not in s2
     */
    static <T> List<T> set1Minus2(List<T> s1, List<T> s2) {
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
     * @param s, a list
     * @return a copy of the list
     */
    public static <T> List<T> copyList(List<T> s) {
        if (s == null) {
            return null;
        }
        return new ArrayList<>(s);
    }

    /**
     * make a copy of a map
     *
     * @param map, a map
     * @return a copy of the map
     */
    public static <T> Map<T, List<T>> copyMap(Map<T, List<T>> map) {
        if (map == null) {
            return map;
        }
        Map<T, List<T>> mapCopy = new HashMap<>();

        Set<T> keySet = map.keySet();
        for (T key : keySet) {
            List<T> lCopy = copyList(map.get(key));
            mapCopy.put(key, lCopy);
        }
        return mapCopy;
    }

    /**
     * delete a set r from a list containing sets: {s' is not empty: s'=si\r, si
     * belongs s}
     *
     * @param s, a list containing sets
     * @param r, a set
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
            List<T> lCopy = copyList(l);
            lCopy = Util.set1Minus2(lCopy, rCopy);
            if (lCopy == null || lCopy.size() == 0) {
                s.remove(l);
            } else {
                int lIndex = s.indexOf(l);
                s.set(lIndex, lCopy);
            }
            sLen = s.size();

        }

        return s;
    }

    /**
     * delete a set r from a list containing sets: {s' is not empty: s'=si\r, si
     * belongs s}
     *
     * @param map, a list containing sets
     * @param r,   a set
     */
    public static <T> void deleteSet(Map<T, List<T>> map, List<T> r) {
        if (map == null) {
            return;
        }
        if (r == null) {
            return;
        }
        List<T> rCopy = copyList(r);

        Set<T> keySet = map.keySet();
        for (T key : keySet) {
            List<T> l = map.get(key);
            l = Util.set1Minus2(l, rCopy);
            map.replace(key, l);
        }
        Collection<List<T>> values = map.values();
        while (values.remove(null))
            ;

    }

    /**
     * get the max cardinality set in the list s containing sets
     *
     * @param s, a list containing sets
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

//    /**
//     * get the max cardinality set index in the list s containing sets
//     *
//     * @param s, a list containing sets
//     * @return the max cardinality set index in the list s
//     */
//    public static <T> int getMaxCardinalitySetIndex(List<List<T>> s) {
//        if (s == null) {
//            return ConstantValue.IMPOSSIBLE_VALUE;
//        }
//
//        int maxCardinality = 0;
//        List<T> rtnList = null;
//
//        for (List<T> l : s) {
//            int cardinality = l.size();
//            if (cardinality > maxCardinality) {
//                maxCardinality = cardinality;
//                rtnList = l;
//            }
//        }
//
//        return s.indexOf(rtnList);
//    }

    /**
     * get the max cardinality set index in the list s containing sets
     *
     * @param map, a list containing sets
     * @return the max cardinality set index in the list s
     */
    public static int getMaxCardinalitySetIndex(Map<Integer, List<Integer>> map) {
        if (map == null) {
            return ConstantValue.IMPOSSIBLE_VALUE;
        }

        int maxCardinality = 0;
        int rtnIndex = ConstantValue.IMPOSSIBLE_VALUE;

        Set<Integer> keySet = map.keySet();

        for (Integer key : keySet) {
            List<Integer> l = map.get(key);
            int cardinality = l.size();
            if (cardinality > maxCardinality) {
                maxCardinality = cardinality;
                rtnIndex = key;
            }
        }

        return rtnIndex;
    }

    /**
     * get the max cardinality set index in the list s containing sets
     *
     * @param gv,   global variables
     * @param card, set cardinalities
     * @return the max cardinality set index in the list s
     */
    public static int getMaxCardinalitySetIndex(DSGlobalVariable gv, int[] card, int sActCount) {

        int maxCard = ConstantValue.IMPOSSIBLE_VALUE;
        int index = ConstantValue.IMPOSSIBLE_VALUE;

        int[] sL = gv.getsL();
        // int sCount = card[0];
        // int sCount=gv.getsCount();

        for (int i = 1; i <= sActCount; i++) {
            int j = sL[i];
            if (card[j] <= 0) {
                continue;
            }
            if (card[j] > maxCard) {
                index = j;
                maxCard = card[j];
            }
            if (card[j] >= maxCard && j < index) {
                index = j;
                maxCard = card[j];
            }
        }

        return index;
    }

    /**
     * remove a set r from a list containing sets: s\{r}
     *
     * @param s, a list containing sets
     * @param r, a set
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

    /**
     * convert a map of <index,set> to a list of sets
     *
     * @param map,a map of <index,set>
     * @return a list of sets
     */
    static <T> List<List<T>> convertMapToListOfSet(Map<T, List<T>> map) {
        if (map == null) {
            return null;
        }

        Set<T> keySet = map.keySet();
        if (keySet.isEmpty()) {
            return null;
        }

        List<List<T>> list = new ArrayList<>();
        for (T key : keySet) {
            List<T> l = map.get(key);
            if (!list.contains(l)) {
                list.add(l);
            }
        }
        return list;
    }

    /**
     * if the set (with limited setSize) contains the element
     *
     * @param set,     the set
     * @param setSize, limit the set size
     * @param ele,     the element
     * @return true: the set contains the element; false: otherwise
     */
    private static boolean setContiansEle(int[] set, int setSize, int ele) {
        int rtnIdx = getContiansEleIdx(set, setSize, ele);
        return rtnIdx != ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * if a set contains an element, return the index of the element in the set
     *
     * @param set,     the set
     * @param setSize, limit the set size
     * @param ele,     the element
     * @return the index of the element in the set
     */
    private static int getContiansEleIdx(int[] set, int setSize, int ele) {
        for (int i = 1; i <= setSize; i++) {
            if (ele == set[i]) {
                return i;
            }
        }
        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    // /**
    // * delete the edge from a vertex of index u to a vertex of index v
    // *
    // * @param deg,
    // * degree list in u side
    // * @param al,
    // * adjacency list in u side
    // * @param im,
    // * incidence matrix in u side
    // * @param u,
    // * vertex index u
    // * @param v,
    // * vertex index v
    // */
    // private static void deleteEdge(int[] deg, int[][] al, int[][] im, int u,
    // int v) {
    // int i = im[v][u];
    // int j = deg[u];
    // int x = al[u][j];
    //
    // al[u][i] = x;
    // al[u][j] = v;
    //
    // im[x][u] = i;
    // im[v][u] = j;
    //
    // deg[u]--;
    //
    // }
    //
    // /**
    // * delete a vertex of index v
    // *
    // * @param deg1,
    // * the degree list of vertices in v side
    // * @param deg2,
    // * the degree list of vertices in the other side
    // * @param il1,
    // * the index list of vertices in v side
    // * @param al1,
    // * the adjacency list of vertices in v side
    // * @param al2,
    // * the adjacency list of vertices in the other side
    // * @param im2,
    // * the incidence matrix of vertices in the other side
    // * @param v,
    // * vertex index
    // * @param n,
    // * number of active vertices in v side
    // */
    // private static void deleteVertex(int[] deg1, int[] l1, int[] il1, int[][]
    // al1, int[] deg2, int[][] al2, int[][] im2,
    // int v) {
    // int n = deg1[0];
    // int d = deg1[v];
    // int last = l1[n];
    // int i = il1[v];
    // l1[i] = last;
    // l1[n] = v;
    // il1[last] = i;
    // il1[v] = n;
    //
    // for (int j = d; j >= 1; j--) {
    // int u = al1[v][j];
    // deleteEdge(deg2, al2, im2, u, v);
    //
    // }
    // deg1[v] = 0;
    // deg1[0] = n - 1;
    //
    // }

    /**
     * delete a set
     *
     * @param gv,   global variables
     * @param card, set cardinality
     * @param freq, element frequency* @param sToDelIdx, the index of the set to
     *              be deleted
     */
    public static void deleteSet(DSGlobalVariable gv, int[] card, int[] freq, int sActCount, int s) {
        int[] sL = gv.getsL();
        int[] sIL = gv.getsIL();
        int[][] sAL = gv.getsAL();
        // int[][] eAL = gv.geteAL();
        // int[][] eIM = gv.geteIM();

        // deleteVertex(card, sL, sIL, sAL, freq, eAL, eIM, sToDelIdx);
        int last = sL[sActCount];
        int currentIdx = sIL[s];
        sL[currentIdx] = last;
        sL[sActCount] = s;
        sIL[last] = currentIdx;
        sIL[s] = sActCount;

        int d = card[s];
        for (int i = 1; i <= d; i++) {
            int e = sAL[s][i];
            decreaseElementFrequency(gv, freq, e, s);
        }

        card[s] = 0;

        gv.setsIL(sIL);
        gv.setsAL(sAL);
        // gv.seteAL(eAL);
        // gv.seteIM(eIM);
        gv.setsL(sL);
        gv.setCard(card);

    }

    /**
     * decrease element frequency
     *
     * @param gv,   global variables
     * @param freq, frequency
     * @param e,    the index of the element to be decreased
     * @param s,    the index of the set to be deleted
     */
    private static void decreaseElementFrequency(DSGlobalVariable gv, int[] freq, int e, int s) {
        // int[] freq = gv.getFreq();
        int[][] eAL = gv.geteAL();
        int[][] eIM = gv.geteIM();

        // deleteEdge(freq, eAL, eIM, eToDecIdx, sToDelIdx);
        int i = eIM[s][e];
        int j = freq[e];
        int x = eAL[e][j];
        eAL[e][i] = x;
        eIM[x][e] = i;
        eAL[e][j] = s;
        eIM[s][e] = j;
        freq[e]--;

        // gv.setFreq(freq);
        gv.seteAL(eAL);
        gv.seteIM(eIM);
        gv.setFreq(freq);

    }

    /**
     * decrease the cardinality of a set
     *
     * @param gv, global variable
     * @param card, set cardinality
     * @param s,  the index of the set to be decreased
     * @param e,  the index of the element to be deleted
     */
    private static void decreaseSetCardinality(DSGlobalVariable gv, int[] card, int s, int e) {
        // int[] card = gv.getCard();
        int[][] sAL = gv.getsAL();
        int[][] sIM = gv.getsIM();

        // deleteEdge(card, sAL, sIM, s, e);

        int i = sIM[e][s];
        int j = card[s];
        int x = sAL[s][j];
        sAL[s][i] = x;
        sIM[x][s] = i;
        sAL[s][j] = e;
        sIM[e][s] = j;
        card[s]--;

        // gv.setCard(card);
        gv.setsAL(sAL);
        gv.setsIM(sIM);
        gv.setCard(card);
    }

    /**
     * delete element
     *
     * @param gv,        global variable
     * @param card,      set cardinalities
     * @param freq,      element frequency
     * @param eActCount, active element count
     * @param e,         the index of the element to be deleted
     * @param source,    the set where e to be delete from
     */
    static void deleteElement(DSGlobalVariable gv, int[] card, int[] freq, int eActCount,
                              int e, int source) {
        int[] eL = gv.geteL();
        int[] eIL = gv.geteIL();
        int[][] eAL = gv.geteAL();
        // int[][] sAL = gv.getsAL();
        // int[][] sIM = gv.getsIM();

        // deleteVertex(freq, eL, eIL, eAL, card, sAL, sIM, e);

        int last = eL[eActCount];
        int currentIdx = eIL[e];
        eL[currentIdx] = last;
        eL[eActCount] = e;
        eIL[last] = currentIdx;
        eIL[e] = eActCount;

        int d = freq[e];
        for (int i = 1; i <= d; i++) {
            int s = eAL[e][i];
            if (s == source) {
                continue;
            }
            decreaseSetCardinality(gv, card, s, e);
        }

        freq[e] = 0;

        gv.seteIL(eIL);
        // gv.setsAL(sAL);
        gv.seteAL(eAL);
        // gv.setsIM(sIM);
        gv.seteL(eL);
    }

    /**
     * add a set to solution
     *
     * @param gv,        global variable
     * @param card,      set cardinalities
     * @param freq,      element frequency
     * @param sActCount, active set count
     * @param eActCount, active element count
     * @param s,         the index of the set to be added
     */
    public static void addSetToCover(DSGlobalVariable gv, int[] card, int[] freq, int sActCount, int eActCount, int s) {
        int[] sL = gv.getsL();
        // int sActCount = card[0];
        int[] sIL = gv.getsIL();
        int[][] sAL = gv.getsAL();

        int last = sL[sActCount];
        int currentIdx = sIL[s];
        sL[currentIdx] = last;
        sL[sActCount] = s;
        sIL[last] = currentIdx;
        sIL[s] = sActCount;

        int d = card[s];

        // int i = sL[sToAddIdx];
        // sL[i] = last;
        // sL[sActCount] = sToAddIdx;
        // sIL[last] = i;
        // sIL[sToAddIdx] = sActCount;

        for (int j = d; j >= 1; j--) {
            int e = sAL[s][j];
            deleteElement(gv, card, freq, eActCount - (j - 1), e, s);

        }
        card[s] = 0;

        gv.setsL(sL);
        gv.setsIL(sIL);
        gv.setsAL(sAL);
        card[0] = sActCount - 1;
    }

    /**
     * get the set index which contains an element of frequency one
     *
     * @param gv,   global variables
     * @param freq, element frequency
     * @return set index
     */
    public static int getSetOfFrequencyOneElement(DSGlobalVariable gv, int[] freq, int eActCount) {
        // int eActCount = freq[0];

        int[] eL = gv.geteL();

        int[][] eAL = gv.geteAL();

        for (int i = 1; i <= eActCount; i++) {
            int j = eL[i];
            if (freq[j] == 1) {
                return eAL[j][1];
            }
        }

        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * if set1 is a subset of set2
     *
     * @param gv,    global variables
     * @param card,  set cardinalities
     * @param s1Idx, set1 index
     * @param s2Idx, set2 index
     * @return true: set1 is a subset of set2; false: otherwise
     */

    static boolean is1Subset2(DSGlobalVariable gv, int[] card, int s1Idx, int s2Idx) {
        if (s1Idx == s2Idx) {
            return false;
        }

        int s1Card = card[s1Idx];
        int s2Card = card[s2Idx];

        if (s1Card == 0 || s2Card == 0 || s1Card > s2Card) {
            return false;
        }

        int[][] sAL = gv.getsAL();
        int[] s1Array = sAL[s1Idx];
        int[] s2Array = sAL[s2Idx];

        int count = 0;
        for (int i = 1; i <= s1Card; i++) {
            int tmp = s1Array[i];
            if (Util.setContiansEle(s2Array, s2Card, tmp)) {
                count++;
            }
        }

        return count == s1Card;

    }

    /**
     * if a set is a subset of another set, return the former set index
     *
     * @param gv,   global variable
     * @param card, set cardinalities
     * @return a subset of another set
     */
    public static int getSubset(DSGlobalVariable gv, int[] card) {
        int[] sL = gv.getsL();
        int sActCount = card[0];

        for (int i = 1; i <= sActCount - 1; i++) {
            int isL = sL[i];
            if (card[isL] <= 0) {
                continue;
            }
            for (int j = i + 1; j <= sActCount; j++) {

                int jsL = sL[j];
                if (card[jsL] <= 0) {
                    continue;
                }

                if (is1Subset2(gv, card, isL, jsL)) {
                    return isL;
                } else if (is1Subset2(gv, card, jsL, isL)) {
                    return jsL;
                }

            }
        }

        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * convert global variables into the format useful for calculating maximum
     * matching
     *
     * @param gv,   global variables
     * @param card, set cardinalities
     * @return an adjacency list of elements format
     */
    public static Map<Integer, List<Integer>> transferGVIntoMMParam(DSGlobalVariable gv, int[] card, int[] freq) {
        // TODO: sL is not right
        int[] sL = gv.getsL();
        int[] eL = gv.geteL();
        int[][] sAL = gv.getsAL();
        int sActCount = gv.getsCount();
        // int sActCount = card[0];
        int eActCount = freq[0];

        Map<Integer, List<Integer>> eleG = new HashMap<>();

        Map<Integer, Integer> actEleIdxMap = new HashMap<>(eActCount);
        for (int i = 1; i <= eActCount; i++) {
            actEleIdxMap.put(eL[i], i);
        }

        for (int i = 1; i <= sActCount; i++) {
            int sLi = sL[i];
            if (card[sLi] > 0) {
                int[] sEL = sAL[sLi];

                for (int j = 1; j <= card[sLi]; j++) {
                    int sELj = sEL[j];

                    if (!eleG.containsKey(sELj)) {

                        List<Integer> tmpList = new ArrayList<>();
                        eleG.put(sELj, tmpList);

                    }
                    List<Integer> tmpList = eleG.get(sELj);

                    for (int k = 1; k <= card[sLi]; k++) {
                        if (j == k) {
                            continue;
                        }
                        int sELk = sEL[k];
                        if (!tmpList.contains(sELk)) {

                            tmpList.add(sELk);
                            if (!eleG.containsKey(sELk)) {

                                List<Integer> tmpList2 = new ArrayList<>();
                                eleG.put(sELk, tmpList2);

                            }

                        }
                    }
                }

            }
        }

        Map<Integer, List<Integer>> elePosG = new HashMap<>();

        Set<Integer> gKeySet = eleG.keySet();
        for (Integer key : gKeySet) {
            List<Integer> vList = eleG.get(key);
            List<Integer> v1List = new ArrayList<>(vList.size());
            for (Integer v : vList) {
                v1List.add(actEleIdxMap.get(v));
            }
            elePosG.put(actEleIdxMap.get(key), v1List);
        }

        return elePosG;

    }

    /**
     * if a solution is valid
     *
     * @param gv, global variables
     * @return true if it is valid, otherwise false
     */
    public static boolean isValidSolution(DSGlobalVariable gv) {

        int bestSolCount = gv.getBestSolCount();
        int[] bestSol = gv.getBestSol();
        int[][] sAL = gv.getsAL();
        int[] eL = gv.geteL();
        int[] copyEL = Arrays.copyOf(eL, eL.length);
        int eCount = gv.geteCount();

        int count = 0;

        for (int i = 1; i <= bestSolCount; i++) {
            int[] eLi = sAL[bestSol[i]];
            for (int ei : eLi) {
                if (ei != ConstantValue.IMPOSSIBLE_VALUE) {
                    int tmpIdx = getContiansEleIdx(copyEL, copyEL.length - 1, ei);
                    if (tmpIdx != ConstantValue.IMPOSSIBLE_VALUE) {
                        count++;
                        copyEL[tmpIdx] = ConstantValue.IMPOSSIBLE_VALUE;
                    }
                }
            }
        }

        return count == eCount;
    }

    /**
     * generate a batch num by date and time
     *
     * @return a number shows records processed together
     */
    public static String getBatchNum() {
        Date date = new Date(); // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new
        // calendar
        // instance
        calendar.setTime(date); // assigns calendar to given date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h
        // format
        int min = calendar.get(Calendar.MINUTE);

        StringBuilder sb = new StringBuilder();
        String monthStr = String.format("%02d", month + 1);
        String dayStr = String.format("%02d", day);

        sb.append(year).append(monthStr).append(dayStr).append("-").append(hour).append(min);
        return sb.toString();

    }

//    /**
//     * convert the idx solution into label solution
//     *
//     * @param g, variables representing a graph
//     * @return array of solution in label
//     */
//    private static int[] getLabSolution(DSGlobalVariable g) {
//        int idxSolSize = g.getSolCount();
//        int[] idxSol = g.getSol();
//        int[] labLst = g.getsL();
//        int[] sol = new int[idxSolSize];
//        for (int i = 0; i < idxSolSize; i++) {
//            sol[i] = labLst[idxSol[i]];
//        }
//        return sol;
//    }


//    public static String getLabSolutionStr(DSGlobalVariable g) {
//        StringBuilder sb = new StringBuilder();
//        int[] sol = Util.getLabSolution(g);
//        for (int aSol : sol) {
//            sb.append(aSol).append(",");
//        }
//        return sb.substring(0, sb.length() - 1);
//    }
}