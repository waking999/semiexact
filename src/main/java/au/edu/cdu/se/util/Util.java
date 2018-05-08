package au.edu.cdu.se.util;


import java.util.*;

/**
 * an util class for common functions
 */
public class Util {
    /**
     * find the position of a value in a range of an array
     *
     * @param array,    the search base
     * @param arrayLen, the range
     * @param val,      the value
     * @return position or ConstantValue.IMPOSSIBLE_VALUE if not found
     */
    private static int getContainsEleIdx(int[] array, int arrayStart, int arrayLen, int val) {
        for (int i = arrayStart; i < arrayLen; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * find the position of a value in a range of an array
     * index start from 0
     *
     * @param array,    the search base
     * @param arrayLen, the range
     * @param val,      the value
     * @return position or ConstantValue.IMPOSSIBLE_VALUE if not found
     */
    static int getContiansEleIdxFromZero(int[] array, int arrayLen, int val) {
        return getContainsEleIdx(array, 0, arrayLen, val);
    }

    /**
     * if a set contains an element, return the index of the element in the set
     * index start from 1
     *
     * @param set,     the set
     * @param setSize, limit the set size
     * @param ele,     the element
     * @return the index of the element in the set
     */
    static int getContainsEleIdxFromOne(int[] set, int setSize, int ele) {
        return getContainsEleIdx(set, 1, setSize, ele);
    }

    /*
     * if the set (with limited setSize) contains the element
     *
     * @param set,     the set
     * @param setSize, limit the set size
     * @param ele,     the element
     * @return true: the set contains the element; false: otherwise
     */
    static boolean setContainsEleFromZero(int[] set, int setSize, int ele) {
        int rtnIdx = getContiansEleIdxFromZero(set, setSize, ele);
        return rtnIdx != ConstantValue.IMPOSSIBLE_VALUE;
    }


    /**
     * if the set (with limited setSize) contains the element
     *
     * @param set,     the set
     * @param setSize, limit the set size
     * @param ele,     the element
     * @return true: the set contains the element; false: otherwise
     */
    static boolean setContainsEleFromOne(int[] set, int setSize, int ele) {
        int rtnIdx = getContainsEleIdxFromOne(set, setSize, ele);
        return rtnIdx != ConstantValue.IMPOSSIBLE_VALUE;
    }


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
            return null;
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
            return null;
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
            return null;
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
        while (values.remove(null)) {
        }

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