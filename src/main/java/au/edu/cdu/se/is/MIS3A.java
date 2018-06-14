package au.edu.cdu.se.is;


import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.is.ISGlobalVariable;

import java.util.Arrays;

/**
 * This is the basic implementation of branch search tree with:
 * 1. degree 0 reduction rule
 * 2. degree 1 reduction rule
 * 3. get solutions
 * 4. add greedy
 */
public class MIS3A implements IMIS {
    private ISGlobalVariable gv;
    private ISAlgoParam ap;

    MIS3A() {
    }

    public void setGv(ISGlobalVariable gv) {
        this.gv = gv;

    }

    public void setAp(ISAlgoParam ap) {
        this.ap = ap;
    }


    private int bestSolSize;
    private int[] bestSol;
    private int unacceptedResultSize;
    private int acceptedResultSize;


    public int run() {

        long start = System.nanoTime();

        acceptedResultSize = ap.getAcceptedResultSize();
        unacceptedResultSize = ap.getUnacceptedResultSize();
        int threshold = ap.getThreshold();
        long allowedRunningTime = ap.getAllowedRunningTime();

        bestSolSize = 0;

        System.out.println("unacceptedResultSize=" + unacceptedResultSize + ",bestSolSize=" + bestSolSize + ",acceptedResultSize=" + acceptedResultSize);


        branch(gv, start, allowedRunningTime, threshold,
                0);

        gv.setBestSolSize(bestSolSize);
        gv.setBestSol(bestSol);

        return bestSolSize;
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


    private int branch(ISGlobalVariable gv, long start, long allowedRunningTime,
                       int threshold, int level) {

        long current = System.nanoTime();

        if (!AlgoUtil.existEdges(gv)) {
            return bestSolSize;
        }

        if (isMomentOfHappy(current, start, allowedRunningTime)) {
            gv.setModel(ConstantValue.MODEL_HAPPY);

            int idxSolSize = gv.getIdxSolSize();
            if (bestSolSize < idxSolSize) {
                int[] idxSol = gv.getIdxSol();
                bestSol = Arrays.copyOf(idxSol, idxSolSize);
                bestSolSize = idxSolSize;

            }

            return bestSolSize;
        }

        int idxSolSize = gv.getIdxSolSize();
        if (isMomentOfHurry(current, start, allowedRunningTime,
                idxSolSize, threshold)) {
            /*
             * larger than unacceptedResultSize, our algorithm is going to stop
             * exponential search at level min-theshold
             */
            gv.setModel(ConstantValue.MODEL_HURRY);
            IGreedyMIS algo = new GreedyMIS1();
            algo.setGv(gv);

            algo.run();
//            level += rtn;
            idxSolSize = gv.getIdxSolSize();
            if (bestSolSize < idxSolSize) {
                int[] idxSol = gv.getIdxSol();
                bestSol = Arrays.copyOf(idxSol, idxSolSize);
                bestSolSize = idxSolSize;

                if (bestSolSize >= unacceptedResultSize && bestSolSize < acceptedResultSize) {
                    //unacceptedResultSize = Math.max(unacceptedResultSize+(acceptedResultSize-unacceptedResultSize+1)/2, bestSolSize );
                    unacceptedResultSize=unacceptedResultSize+(bestSolSize-unacceptedResultSize+1)/2;
                    acceptedResultSize = acceptedResultSize - (acceptedResultSize - bestSolSize) / 4;
                    System.out.println("unacceptedResultSize=" + unacceptedResultSize + ",bestSolSize=" + bestSolSize + ",acceptedResultSize=" + acceptedResultSize);
                }
            }

            return bestSolSize;
        }


        int actVerCnt = gv.getActVerCnt();

        if (actVerCnt == 0) {
            idxSolSize = gv.getIdxSolSize();


            if (bestSolSize < idxSolSize) {
                int[] idxSol = gv.getIdxSol();

                bestSol = Arrays.copyOf(idxSol, idxSolSize);
                bestSolSize = idxSolSize;
            }

            return bestSolSize;
        }

        idxSolSize = gv.getIdxSolSize();
        if (bestSolSize < idxSolSize) {

            int[] idxSol = gv.getIdxSol();
            bestSol = Arrays.copyOf(idxSol, idxSolSize);
            bestSolSize = idxSolSize;
        }

        while (preProcess(gv)) {
            actVerCnt = gv.getActVerCnt();
            if (actVerCnt == 0) {
                idxSolSize = gv.getIdxSolSize();
                if (bestSolSize < idxSolSize) {
                    int[] idxSol = gv.getIdxSol();

                    bestSol = Arrays.copyOf(idxSol, idxSolSize);
                    bestSolSize = idxSolSize;
                }

                return bestSolSize;

            }
        }


        actVerCnt = gv.getActVerCnt();
        if (actVerCnt == 0) {
            idxSolSize = gv.getIdxSolSize();
            bestSolSize = gv.getBestSolSize();
            if (bestSolSize < idxSolSize) {
                int[] idxSol = gv.getIdxSol();

                bestSol = Arrays.copyOf(idxSol, idxSolSize);
                bestSolSize = idxSolSize;
            }

            return bestSolSize;

        }

        idxSolSize = gv.getIdxSolSize();
        if (bestSolSize < idxSolSize) {

            int[] idxSol = gv.getIdxSol();
            bestSol = Arrays.copyOf(idxSol, idxSolSize);
            bestSolSize = idxSolSize;
        }


        //branch 1; v in the solution
        int vIdx = AlgoUtil.getHighestDegreeVertexIdx(gv);
        ISGlobalVariable gvTmp = AlgoUtil.copyGraphInGloablVariable(gv);

        int[] idxSol = gvTmp.getIdxSol();
        idxSolSize = gvTmp.getIdxSolSize();
        idxSol[idxSolSize++] = vIdx;


        AlgoUtil.deleteClosedNeighs(gvTmp, vIdx);
        gvTmp.setIdxSolSize(idxSolSize);
        gvTmp.setIdxSol(idxSol);

        int b1 = branch(gvTmp, start, allowedRunningTime,
                threshold, level + 1);


        AlgoUtil.deleteVertex(gv, vIdx);
        int b2 = branch(gv, start, allowedRunningTime,
                threshold, level + 1);

        if (b1 >= b2) {
            gv = gvTmp;
            return b1;
        } else {
            return b2;
        }
    }

    private boolean isMomentOfHappy(long current, long start, long allowedRunningTime) {
        return (current - start >= allowedRunningTime) && (bestSolSize >= acceptedResultSize);
    }


    private boolean isMomentOfHurry(long current, long start, long allowedRunningTime, int idxSolSize, int threshold) {

        return (current - start >= allowedRunningTime) && (bestSolSize <= unacceptedResultSize)
                && (acceptedResultSize - idxSolSize <= threshold);

    }

}
