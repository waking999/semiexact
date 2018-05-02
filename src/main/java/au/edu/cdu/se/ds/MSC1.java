package au.edu.cdu.se.ds;

/*
  1. the exactly same as the basic algorithm
 */

import au.edu.cdu.se.util.ExistQualifiedSet;
import au.edu.cdu.se.util.Util;

import java.util.List;

public class MSC1 {
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

        // subset rule
        ExistQualifiedSet exist = Util.existSubset(s);
        if (exist.isExist()) {
            int setIndex = exist.getSetIndex();
            List<Integer> si = s.get(setIndex);
            List<List<Integer>> sCopy = Util.copyList(s);
            Util.removeSet(sCopy, si);
            return branch(sCopy);
        }

        // unique set for an element
        List<Integer> uList = Util.unionSets(s);
        exist = Util.existUniqueSetForAElement(uList, s);
        if (exist.isExist()) {
            int setIndex = exist.getSetIndex();
            List<Integer> si = s.get(setIndex);
            List<List<Integer>> sCopy = Util.copyList(s);
            sCopy = Util.deleteSet(sCopy, si);
            return 1 + branch(sCopy);
        }
        // base rule
        List<Integer> si = Util.getMaxCardinalitySet(s);
        int siLen = si.size();
        if (siLen <= 2) {
            return polyMsc(s);
        }
        // branch
        return Math.min(branch(Util.removeSet(Util.copyList(s), si)), 1 + branch(Util.deleteSet(Util.copyList(s), si)));

    }

    private int polyMsc(List<List<Integer>> s) {
        // TODO: this is not really right, need to be revised.
        return s.size();
    }
}
