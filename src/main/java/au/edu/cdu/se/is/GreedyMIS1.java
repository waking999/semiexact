package au.edu.cdu.se.is;


import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.is.ISGlobalVariable;
import org.apache.log4j.Logger;

/**
 * get the largest degree vertex in G and delete it with incident edges, the remaining vertices are indepedent set
 */
public class GreedyMIS1 implements IGreedyMIS {
    private static Logger log = LogUtil.getLogger(GreedyMIS1.class);

    @Override
    public void setGv(ISGlobalVariable gv) {
        this.gv = gv;
    }

    private ISGlobalVariable gv;

    GreedyMIS1(ISGlobalVariable gv) {
        this.gv = gv;
    }

    GreedyMIS1() {

    }

    @Override
    public int run() {
        log.debug("GreedyMIS1 is called");


        while (AlgoUtil.existEdges(gv)) {
            int vIdx = AlgoUtil.getHighestDegreeVertexIdx(gv);
            AlgoUtil.deleteVertex(gv, vIdx);

        }

        int actVerCnt = gv.getActVerCnt();
        int idxSolSize = gv.getIdxSolSize();
        int[] idxSol = gv.getIdxSol();
        int[] idxLst = gv.getIdxLst();
        int crossLvlNum=actVerCnt;

        for (int i = 0; i < actVerCnt; i++) {
            idxSol[idxSolSize++] = idxLst[i];
        }

        gv.setIdxSolSize(idxSolSize);
        return crossLvlNum;
    }

}
