package au.edu.cdu.semiexact.algo.mis;

import java.util.Arrays;

import au.edu.cdu.semiexact.algo.AlgorithmParameter;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.MISGlobalVariable;
import au.edu.cdu.semiexact.util.Util;

public class MIS1<VT> implements IMIS<VT> {

	@Override
	public int branch(MISGlobalVariable<VT> gv, AlgorithmParameter ap) {
		int[] deg = gv.getDeg(); 
		return branch(gv, deg);
	}

	private int branch(MISGlobalVariable<VT> gv, int[] deg) {
		//basic case
		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		int vCount = deg[0];

		
		
		if (vCount == 0) {
			solCount = gv.getSolCount();
			if (bestSolCount < solCount) {
				bestSolCount = solCount;
				int[] sol = gv.getSol();
				int[] bestSol = Arrays.copyOf(sol, solCount + 1);
				gv.setBestSol(bestSol);
				gv.setBestSolCount(bestSolCount);
			}
			return bestSolCount;
		}
		
		
		//preprocess
		int vCount1 = deg[0];
		
		int v = Util.getMinUtilVerIndex(gv,deg,vCount);
		
		if (v == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
		}
		
		
		
		if(deg[v]==0){
			
	 	 
			Util.addVerToSolution(gv, deg, vCount1, v);  
 
			return gv.getSolCount();
		}
		
		//left branch -- v is discarded
		
		int[] copyDeg = Arrays.copyOf(deg, deg.length); 
		Util.deleteVertex(gv, copyDeg, vCount1, v);
		
		int vCount2 = vCount1 - 1;	 
		copyDeg[0] = vCount2; 
		int res1 = branch(gv, copyDeg);

 		copyDeg = null; 	
		
		//right branch --v is selected
 		int solPtr=gv.getSolPtr();
 		solCount=gv.getSolCount();
 		
 		int tmpSolPtr=solPtr;
 		int tmpSolCount=solCount;
 		
		Util.addVerToSolution(gv, deg, vCount1, v);  
		
		int res2 = branch(gv, deg);
		
		solPtr=tmpSolPtr;
		solCount=tmpSolCount;
		
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		if (res1 > res2) {

			return res1;
		} else {
			return res2;
		}
 				
	}
}
