package au.edu.cdu.se.is;


import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.is.ISGlobalVariable;

/**
 * This is the basic implementation of branch search tree with:
 * 1. only degree 0 reduction rule
 * 2. get only solution size
 * 3. copy global variables at each branch
 */
public class MIS1 implements IMIS {

    private ISGlobalVariable gv;


    MIS1(ISGlobalVariable gv) {
        this.gv = gv;
    }


    @Override
    public void setGv(ISGlobalVariable gv) {
        this.gv = gv;
    }

    @Override
    public void setAp(ISAlgoParam ap) {

    }

    public int run() {
        return branch(this.gv);
    }


    private int branch(ISGlobalVariable gv) {
        int actVerCnt = gv.getActVerCnt();

        if (actVerCnt == 0) {
            return 0;
        }

        int[][] idxAL = gv.getIdxAL();
        int[] idxDegree = gv.getIdxDegree();


        int vIdx = AlgoUtil.getDegreeZeroVertex(gv);
        if (vIdx != ConstantValue.IMPOSSIBLE_VALUE) {

            int[] vNeigs = idxAL[vIdx];

            for (int i = 0; i < idxDegree[vIdx]; i++) {
                AlgoUtil.deleteVertex(gv, vNeigs[i]);
            }
            AlgoUtil.deleteVertex(gv, vIdx);
            return 1 + branch(gv);
        }

        vIdx = AlgoUtil.getHighestDegreeVertexIdx(gv);
        ISGlobalVariable gvTmp = AlgoUtil.copyGraphInGloablVariable(gv);
        int[] vNeigs = idxAL[vIdx];
        for (int uIdx : vNeigs) {
            AlgoUtil.deleteVertex(gvTmp, uIdx);
        }
        AlgoUtil.deleteVertex(gvTmp, vIdx);
        int b1 = branch(gvTmp);

        AlgoUtil.deleteVertex(gv, vIdx);
        int b2 = branch(gv);

        return Math.max(1 + b1, b2);
    }


}
