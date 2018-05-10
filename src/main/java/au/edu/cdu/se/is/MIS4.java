package au.edu.cdu.se.is;


import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConnectComponents;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.is.ISGlobalVariable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This is the basic implementation of branch search tree with:
 * 1. degree 0 reduction rule
 * 2. degree 1 reduction rule
 * 3. get solutions
 * 4. divid graph into components to be processed
 */
public class MIS4 implements IMIS {
    private final ThreadLocal<ISGlobalVariable> wholeG; // to represent the original graph
    // parameters for fpt subroutine



    private ISGlobalVariable[] compGs;



    MIS4() {
        wholeG = new ThreadLocal<>();
    }


    public void setAp(ISAlgoParam ap) {

    }

    @Override
    public void setGv(ISGlobalVariable g) {
        this.wholeG.set(g);
        ConnectComponents cc = new ConnectComponents();
        cc.setG(g);
        List<Set<Integer>> components = cc.getConnectComponents();
        int componentsSize = components.size();
        compGs = new ISGlobalVariable[componentsSize];
        processComponents(components);
    }

    private void processComponents(List<Set<Integer>> components) {

        ISGlobalVariable g = this.wholeG.get();
        int[] idxDegree = g.getIdxDegree();
        int[][] idxAL = g.getIdxAL();
        int[][] idxIM = g.getIdxIM();

        int componentsSize = components.size();
        for (int i = 0; i < componentsSize; i++) {
            ISGlobalVariable compG;
            compG = new ISGlobalVariable();
            Set<Integer> compVers = components.get(i);
            int compVerCnt = compVers.size();

            int[] compGLabLst = new int[compVerCnt];
            int[] compGIdxLst = new int[compVerCnt];
            int[] compGIdxDegree = new int[compVerCnt];



            int[][] compGIdxIM = new int[compVerCnt][compVerCnt];
            for (int j = 0; j < compVerCnt; j++) {
                Arrays.fill(compGIdxIM[j], ConstantValue.IMPOSSIBLE_VALUE);
            }
            int[][] compGIdxAL = new int[compVerCnt][];
            int[] compGIdxSol = new int[compVerCnt];
            Arrays.fill(compGIdxSol, ConstantValue.IMPOSSIBLE_VALUE);
            int compGIdxSolSize = 0;

            Iterator<Integer> compVersIt = compVers.iterator();
            int cursor = 0;
            while (compVersIt.hasNext()) {
                int vIdx = compVersIt.next();
                compGLabLst[cursor] = vIdx;
                compGIdxLst[cursor] = cursor;
                compGIdxDegree[cursor] = idxDegree[vIdx];

                cursor++;
            }

            compG.setIdxSol(compGIdxSol);
            compG.setIdxSolSize(compGIdxSolSize);
            compG.setVerCnt(compVerCnt);
            compG.setActVerCnt(compVerCnt);
            compG.setIdxAL(compGIdxAL);
            compG.setIdxIM(compGIdxIM);
            compG.setIdxDegree(compGIdxDegree);
            compG.setIdxLst(compGIdxLst);
            compG.setLabLst(compGLabLst);

            compVersIt = compVers.iterator();
            while (compVersIt.hasNext()) {
                int vIdx = compVersIt.next();
                int compGVIdx = AlgoUtil.getIdxByLab(compG, vIdx);
                int[] vIdxAL = idxAL[vIdx];
                int vIdxALSize = vIdxAL.length;
                compGIdxAL[compGVIdx] = new int[vIdxALSize];
                for (int j = 0; j < vIdxALSize; j++) {
                    int uIdx = vIdxAL[j];
                    int compGUIdx = AlgoUtil.getIdxByLab(compG, uIdx);
                    compGIdxAL[compGVIdx][j] = compGUIdx;
                    compGIdxIM[compGUIdx][compGVIdx] = idxIM[uIdx][vIdx];
                }

            }

            this.compGs[i] = compG;
        }

    }

    @Override
    public int run() {

        int compGsSize = this.compGs.length;
        int[][] sols = new int[compGsSize][];


        int wholeSolSize = 0;

        IMIS mis = new MIS3();

        for (int i = 0; i < compGsSize; i++) {
            mis.setGv(compGs[i]);
            mis.run();
            //Assert.assertTrue(AlgoUtil.isValidSolution(compGs[i]));
            wholeSolSize += compGs[i].getIdxSolSize();
            //we have to get label to put into the solution since the idx may be conflict for the components.
            int[] sol = AlgoUtil.getLabSolution(compGs[i]);
            sols[i] = sol;
        }

        int[] wholeSol = new int[wholeSolSize];
        int cursor = 0;
        for (int i = 0; i < compGsSize; i++) {
            int[] sol = sols[i];

            for (int aSol : sol) {
                if (aSol != ConstantValue.IMPOSSIBLE_VALUE) {
                    wholeSol[cursor++] = aSol;
                }
            }
        }


        this.wholeG.get().setIdxSol(wholeSol);
        this.wholeG.get().setIdxSolSize(wholeSolSize);

        return wholeSolSize;

    }


}
