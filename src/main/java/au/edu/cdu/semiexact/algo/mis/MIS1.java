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
	
	/**
	 * apply reduction rules;
	 * 
	 * @param gv
	 * @return
	 */
	protected boolean preProcess(MISGlobalVariable<VT> gv, int[] deg) {

		int bestSolCount = gv.getBestSolCount();
		int solCount = gv.getSolCount();
		 

		int[] sol = gv.getSol();
		int solPtr = gv.getSolPtr();

		int k1 = -1;
		int count = 0;

		while (k1 != (bestSolCount - solCount - 1)) {
			// super set rule
			k1 = bestSolCount - solCount - 1;
			int supSet = Util.getSupset(gv, deg);
			while (supSet > ConstantValue.IMPOSSIBLE_VALUE) {
				 
				Util.deleteVertex(gv, deg, deg[0], supSet);
				 
				count++;
				supSet = Util.getSupset(gv, deg);
			}
			// degree 0 
			int deg0VerIdx = Util.getDeg0VerIdx(gv, deg, deg[0]);
			while (deg0VerIdx > ConstantValue.IMPOSSIBLE_VALUE) {
				 
				sol[solPtr++] = deg0VerIdx;
				solCount++;
				
				Util.addVerToSolution(gv, deg, deg[0], deg0VerIdx);
				
				count++;
				deg0VerIdx = Util.getDeg0VerIdx(gv, deg, deg[0]);
			}
			// TODO: other rules

		}
		gv.setSol(sol);
		gv.setSolPtr(solPtr);
		gv.setSolCount(solCount);

		//deg[0] = vActCount; 

		if (count > 0) {
			return true;
		}

		return false;
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
		while (preProcess(gv,deg)) {
			if (bestSolCount <= solCount) {
				return bestSolCount;
			}

			if (deg[0] == 0) {
				solCount = gv.getSolCount();
				if (bestSolCount > solCount) {
					bestSolCount = solCount; 
					int[] sol = gv.getSol();
					int[] bestSol = Arrays.copyOf(sol, solCount + 1);
					gv.setBestSol(bestSol);
					gv.setBestSolCount(bestSolCount);
				}
				return bestSolCount;
			}

		 
		}
		
		int vCount1 = deg[0];
		
		int v = Util.getMinUtilVerIndex(gv,deg,vCount);
		
		if (v == ConstantValue.IMPOSSIBLE_VALUE) {
			return bestSolCount;
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
