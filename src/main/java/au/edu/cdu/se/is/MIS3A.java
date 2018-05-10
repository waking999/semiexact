package au.edu.cdu.se.is;

import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.is.ISGlobalVariable;

/**
 * This is the basic implementation of branch search tree with:
 * 1. degree 0 reduction rule
 * 2. degree 1 reduction rule
 * 3. get solutions
 * 4. add parameter k
 */
public class MIS3A implements IMIS {


    private ISGlobalVariable gv;
    private ISAlgoParam ap;

    MIS3A(ISGlobalVariable gv, ISAlgoParam ap) {
        this.gv = gv;

    }

    MIS3A() {

    }

    public void setGv(ISGlobalVariable gv) {
        this.gv = gv;
    }

    public void setAp(ISAlgoParam ap) {
        this.ap = ap;
    }


    public int run() {
        return branch(this.gv);
    }

    private boolean preProcess(ISGlobalVariable gv) {

        int idxSolSize = gv.getIdxSolSize();
        int[] idxSol = gv.getIdxSol();

        int count = 0;

        //reduction rule 1: degree zero
        int vIdx = AlgoUtil.getDegreeZeroVertex(gv);
        while (vIdx != ConstantValue.IMPOSSIBLE_VALUE) {
            count++;
            //if v's degree is 0, v is in the solution
            idxSol[idxSolSize] = vIdx;
            idxSolSize++;

            //delete v
            AlgoUtil.deleteVertex(gv, vIdx);

            vIdx = AlgoUtil.getDegreeZeroVertex(gv);
        }

        //reduction rule 2: degree one
        vIdx = AlgoUtil.getDegreeOneVertex(gv);
        while (vIdx != ConstantValue.IMPOSSIBLE_VALUE) {
            count++;
            //if v's degree is 0, v is in the solution
            idxSol[idxSolSize] = vIdx;
            idxSolSize++;

            //delete N[v]
            AlgoUtil.deleteClosedNeighs(gv, vIdx);

            vIdx = AlgoUtil.getDegreeOneVertex(gv);
        }

        gv.setIdxSol(idxSol);
        gv.setIdxSolSize(idxSolSize);

        return count > 0;

    }


    private int branch(ISGlobalVariable gv) {
        int actVerCnt = gv.getActVerCnt();

        if (actVerCnt == 0) {
            return gv.getIdxSolSize();
        }


        while (preProcess(gv)) {
            actVerCnt = gv.getActVerCnt();
            if (actVerCnt == 0) {
                return gv.getIdxSolSize();
            }
        }

        actVerCnt = gv.getActVerCnt();
        if (actVerCnt == 0) {
            return gv.getIdxSolSize();
        }


        //branch 1; v in the solution
        int vIdx = AlgoUtil.getHighestDegreeVertexIdx(gv);
        ISGlobalVariable gvTmp = AlgoUtil.copyGraphInGloablVariable(gv);

        int[] idxSol = gvTmp.getIdxSol();
        int idxSolSize = gvTmp.getIdxSolSize();
        idxSol[idxSolSize++] = vIdx;

        AlgoUtil.deleteClosedNeighs(gvTmp, vIdx);
        gvTmp.setIdxSolSize(idxSolSize);
        gvTmp.setIdxSol(idxSol);

        int b1 = branch(gvTmp);


        AlgoUtil.deleteVertex(gv, vIdx);
        int b2 = branch(gv);

        if (b1 > b2) {
            gv = gvTmp;
            return b1;
        } else {
            return b2;
        }
    }


}
