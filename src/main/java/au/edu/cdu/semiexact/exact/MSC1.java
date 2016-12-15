package au.edu.cdu.semiexact.exact;
/**
 * 1. the exactly same as the basic algorithm
 */
import java.util.List;

import au.edu.cdu.semiexact.util.ExistQualifiedSet;
import au.edu.cdu.semiexact.util.Util;

public class MSC1 {
		if(s==null || s.size()==0) return 0;
		ExistQualifiedSet exist=Util.existSubset(s);
		if(exist.isExist()){
			int setIndex=exist.getSetIndex();
			List<Integer> si=s.get(setIndex);
			List<List<Integer>> sCopy=Util.copyList(s);
			Util.removeSet(sCopy, si);
		}
		List<Integer> uList=Util.unionSets(s);
		exist=Util.existUniqueSetForAElement(uList, s);
		if(exist.isExist()){
			int setIndex=exist.getSetIndex();
			List<Integer> si=s.get(setIndex);
			List<List<Integer>> sCopy=Util.copyList(s);
			sCopy=Util.deleteSet(sCopy, si);
		}
		List<Integer> si=Util.getMaxCardinalitySet(s);
		int siLen=si.size();
		if(siLen<=2){
			return polyMsc(s);
		}
		
		 
	}
	
	private int polyMsc(List<List<Integer>> s){
		return s.size();
	}
}
