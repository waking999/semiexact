package au.edu.cdu.se.util;

import au.edu.cdu.se.util.ds.DSGlobalVariable;
import au.edu.cdu.se.util.is.ISGlobalVariable;

import java.util.*;

/**
 * this is a util class for algorithm relevant methods
 *
 * @author kwang1
 */
public class AlgoUtil {
//    /**
//     * get the closed neighborhood of a set of vIdx
//     *
//     * @param g,      variables representing a graph
//     * @param idxSet, a set of vertex indices
//     * @return the closed neighborhood
//     */
//    public static Set<Integer> getCloseNeigs(ISGlobalVariable g, int[] idxSet) {
//        Set<Integer> neigs = new HashSet<>();
//        int[][] idxAL = g.getIdxAL();
//        int[] idxDegree = g.getIdxDegree();
//
//        for (int vIdx : idxSet) {
//            neigs.add(vIdx);
//            int degree = idxDegree[vIdx];
//            int[] vNeigs = idxAL[vIdx];
//            for (int j = 0; j < degree; j++) {
//                neigs.add(vNeigs[j]);
//            }
//
//        }
//
//        return neigs;
//    }

//    /**
//     * convert a giIdx array to gIdx array. It implies that the label of vertices in graph gi is the indices of vertices in graph g.
//     *
//     * @param giIdxSet, the indices of vertices in graph gi
//     * @param giLabLst, the labels of vertices in graph gi
//     * @return the indices of vertices in g
//     */
//    public static int[] convertGIIdxToGIdx(int[] giIdxSet, int[] giLabLst) {
//        int[] gIdxSet = new int[giIdxSet.length];
//        for (int i = 0; i < giIdxSet.length; i++) {
//            gIdxSet[i] = giLabLst[giIdxSet[i]];
//        }
//        return gIdxSet;
//    }

//    private static int getFirstUnusedIdx(ISGlobalVariable g) {
//        int[] idxLst = g.getIdxLst();
//        int actVerCnt = g.getActVerCnt();
//        int verCnt = g.getVerCnt();
//        if (actVerCnt == verCnt) {
//            return verCnt;
//        }
//        return idxLst[actVerCnt];
//
//    }

//    /**
//     * add a vertex v to a new graph
//     *
//     * @param g,    the global variable of a source graph
//     * @param gi,   the global variable of a graph to add vertex
//     * @param vIdx, the index of v in gv
//     */
//    public static void addVerToGI(ISGlobalVariable g, ISGlobalVariable gi, int vIdx) {
//        boolean[] idxAdded = g.getIdxAdded();
//        if (idxAdded[vIdx]) {
//            return;
//        }
//        int[][] idxAL = g.getIdxAL();
//        int[] idxDegree = g.getIdxDegree();
//
//        int[][] giIdxAL = gi.getIdxAL();
//        int[][] giIdxIM = gi.getIdxIM();
//        int[] giIdxDegree = gi.getIdxDegree();
//        int[] giLabLst = gi.getLabLst();
//        int[] giIdxLst = gi.getIdxLst();
//
//        // mark v is added to gvi
//        idxAdded[vIdx] = true;
//        /*
//         * since we start index from 0, the current vertex count is also the
//         * position
//         * index for the new vertex;
//         */
//        int giCurrVerCnt = gi.getActVerCnt();
//
//        /*
//         * since gi is a new graph, we use the index of g as the label of gi
//         * such that
//         * we can keep the connection between gi and g ( gi.lab pointing to
//         * g.idx)
//         */
//        giLabLst[giCurrVerCnt] = vIdx;
//        giIdxLst[giCurrVerCnt] = getFirstUnusedIdx(gi);
//
//        // increase the the number of current vertex;
//        giCurrVerCnt++;
//        gi.setActVerCnt(giCurrVerCnt);
//        if (gi.getVerCnt() < gi.getActVerCnt()) {
//            gi.setVerCnt(giCurrVerCnt);
//        }
//
//        // get the new index of v in gi
//        int giVIdx = AlgoUtil.getIdxByLab(gi, vIdx);
//
//        // the neighbor count of v in gv
//        int vGVNeigCnt = idxDegree[vIdx];
//        // prepare the same-size neighbor array of v in gi;
//        giIdxAL[giVIdx] = new int[vGVNeigCnt];
//        Arrays.fill(giIdxAL[giVIdx], ConstantValue.IMPOSSIBLE_VALUE);
//
//        // get the neighbours of v in g
//        int[] vGNeighs = idxAL[vIdx];
//
//        for (int uIdx : vGNeighs) {
//            // for any neighbour u of v in g
//            int uIdxPos = Util.getContiansEleIdxFromZero(giLabLst, gi.getActVerCnt(), uIdx);
//            if (uIdxPos != ConstantValue.IMPOSSIBLE_VALUE) {
//                // if u in gi, get the index of u in gi
//                int giUIdx = giIdxLst[uIdxPos];
//
//                // set the AL of giUIdx, giVIdx
//                int giUIdxDegree = giIdxDegree[giUIdx];
//                giIdxAL[giUIdx][giUIdxDegree] = giVIdx;
//                int giVIdxDegree = giIdxDegree[giVIdx];
//                giIdxAL[giVIdx][giVIdxDegree] = giUIdx;
//
//                // set the IM of giUIdx, giVIdx
//                giIdxIM[giVIdx][giUIdx] = giUIdxDegree;
//                giIdxIM[giUIdx][giVIdx] = giVIdxDegree;
//
//                // set the degree of giUIdx, giVIdx;
//                giIdxDegree[giUIdx]++;
//                giIdxDegree[giVIdx]++;
//
//            }
//        }
//
//    }

    /**
     * delete a vertex from a graph by the index of the vertex
     *
     * @param g,    variables representing a graph
     * @param vIdx, index of a vertex
     */
    public static void deleteVertex(ISGlobalVariable g, int vIdx) {
        int actVerCnt = g.getActVerCnt();
        int[] labLst = g.getLabLst();
        int[] idxLst = g.getIdxLst();
        int[][] idxAL = g.getIdxAL();

        int[] idxDegre = g.getIdxDegree();
        // get the pos of vIdx in idxLst
        int vIdxPos = Util.getContiansEleIdxFromZero(idxLst, actVerCnt, vIdx);
        if (vIdxPos == ConstantValue.IMPOSSIBLE_VALUE) {
            return;
        }

        int d = idxDegre[vIdx];

        // swap v with the bottom of verLst
        int lastVer = labLst[actVerCnt - 1];
        labLst[actVerCnt - 1] = labLst[vIdxPos];
        labLst[vIdxPos] = lastVer;

        // swap vIdx with the bottom of idxLst
        int lastIdx = idxLst[actVerCnt - 1];
        idxLst[actVerCnt - 1] = idxLst[vIdxPos];
        idxLst[vIdxPos] = lastIdx;

        // delete edges of this vertex and its neighbors
        for (int j = d - 1; j >= 0; j--) {
            int uIdx = idxAL[vIdx][j];
            deleteEdge(g, uIdx, vIdx);
        }

        g.setActVerCnt(actVerCnt - 1);

    }


    /**
     * delete the edge between u and v in a graph
     *
     * @param g     , a graph
     * @param uIdx, index of one end vertex
     * @param vIdx, index of another end vertex
     */
    private static void deleteEdge(ISGlobalVariable g, int uIdx, int vIdx) {
        deleteVFromU(g, uIdx, vIdx);
        deleteVFromU(g, vIdx, uIdx);
    }

    /**
     * delete the edge between u and v in a graph, where is from the u side
     *
     * @param g,    the global variable of the graph
     * @param uIdx, index of one end vertex
     * @param vIdx, index of another end vertex
     */
    private static void deleteVFromU(ISGlobalVariable g, int uIdx, int vIdx) {

        int[] gvIdxDegree = g.getIdxDegree();
        int[][] gvIdxIM = g.getIdxIM();
        int[][] gvIdxAL = g.getIdxAL();

        // delete from u side
        int uIdxDegree = gvIdxDegree[uIdx];
        int vIdxIMPosOfUIdx = gvIdxIM[vIdx][uIdx];
        int uLastNeigIdx = gvIdxAL[uIdx][uIdxDegree - 1];
        // swap vIdx to the bottom of the neigh in idxAL
        gvIdxAL[uIdx][uIdxDegree - 1] = vIdx;
        gvIdxAL[uIdx][vIdxIMPosOfUIdx] = uLastNeigIdx;
        // swap vIdx's position for neigh in IdxIM
        gvIdxIM[vIdx][uIdx] = uIdxDegree - 1;
        gvIdxIM[uLastNeigIdx][uIdx] = vIdxIMPosOfUIdx;
        // decrement the degree of u
        gvIdxDegree[uIdx]--;
    }

    /**
     * need a new copy of a graph so that the changes on the new copy will not
     * affect the original graph
     * It is not necessary to copy all fields in global variable since not all
     * information is needed in the new copy. just the part relevant to graph.
     *
     * @param g, variables representing a graph
     * @return the copy of variables representing the graph
     */
    public static ISGlobalVariable copyGraphInGloablVariable(ISGlobalVariable g) {
        ISGlobalVariable gNew = new ISGlobalVariable();

        gNew.setVerCnt(g.getVerCnt());
        // labLst
        int[] labLst = g.getLabLst();
        gNew.setLabLst(Arrays.copyOf(labLst, labLst.length));
        // idxLst
        int[] idxLst = g.getIdxLst();
        gNew.setIdxLst(Arrays.copyOf(idxLst, idxLst.length));
        // degree
        int[] idxDegree = g.getIdxDegree();
        gNew.setIdxDegree(Arrays.copyOf(idxDegree, idxDegree.length));
        // sol
        int[] idxSol = g.getIdxSol();
        gNew.setIdxSol(Arrays.copyOf(idxSol, idxSol.length));
        //sol size
        gNew.setIdxSolSize(g.getIdxSolSize());
        // IM
        int[][] idxIM = g.getIdxIM();
        int idxIMLen = idxIM.length;
        int[][] gvStarIdxIM = new int[idxIMLen][];
        for (int i = 0; i < idxIMLen; i++) {
            int[] idxIMRow = idxIM[i];
            gvStarIdxIM[i] = Arrays.copyOf(idxIMRow, idxIMRow.length);
        }
        gNew.setIdxIM(gvStarIdxIM);
        // AL
        int[][] idxAL = g.getIdxAL();
        int idxALLen = idxAL.length;
        int[][] gvStarIdxAL = new int[idxALLen][];
        for (int i = 0; i < idxALLen; i++) {
            int[] idxALRow = idxAL[i];
            if (idxALRow != null) {
                gvStarIdxAL[i] = Arrays.copyOf(idxALRow, idxALRow.length);
            } else {
                break;
            }
        }
        gNew.setIdxAL(gvStarIdxAL);

        gNew.setActVerCnt(g.getActVerCnt());

        return gNew;
    }

    /**
     * get the index of a vertex by its label
     *
     * @param g,   variables representing a graph
     * @param lab, label of a vertex
     * @return index of the vertex
     */
    public static int getIdxByLab(ISGlobalVariable g, int lab) {
        int actVerCnt = g.getActVerCnt();
        int[] labLst = g.getLabLst();
        int[] idxLst = g.getIdxLst();

        for (int i = 0; i < actVerCnt; i++) {
            if (lab == labLst[i]) {
                return idxLst[i];
            }
        }
        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * initialize the global variables with the number of vertex
     *
     * @param g,      variables representing a graph
     * @param vCount, number of vertices in the graph
     */
    public static void initISGlobalVariable(ISGlobalVariable g, int vCount) {
        // labLst does not have valid value
        int[] labLst = new int[vCount];
        Arrays.fill(labLst, ConstantValue.IMPOSSIBLE_VALUE);
        // the index of each vertex is the sequence no. initially
        int[] idxLst = new int[vCount];
        for (int i = 0; i < vCount; i++) {
            idxLst[i] = i;
        }

        // the degree of each vertex is 0 initially
        int[] idxDegree = new int[vCount];
        Arrays.fill(idxDegree, 0);

        // the vote of each vertex is 0 initially
        //  float[] idxVote = new float[vCount];
        //  Arrays.fill(idxVote, 0);

        // the weight of each vertex is 0 initially
        // float[] idxWeight = new float[vCount];
        //  Arrays.fill(idxWeight, 0);

        // the dominated status of each vertex is false initially
//        boolean[] idxDomed = new boolean[vCount];
//        Arrays.fill(idxDomed, false);


        // the added status of each vertex is false initially
//        boolean[] idxAdded = new boolean[vCount];
//        Arrays.fill(idxAdded, false);

        /*
         * the incident matrix of each vertex is set to be impossible value
         * initially
         */
        int[][] idxIM = new int[vCount][vCount];
        for (int i = 0; i < vCount; i++) {
            Arrays.fill(idxIM[i], ConstantValue.IMPOSSIBLE_VALUE);
        }

        // the adjacent list
        int[][] idxAL = new int[vCount][];

        // the solution
        int[] idxSol = new int[vCount];
        Arrays.fill(idxSol, ConstantValue.IMPOSSIBLE_VALUE);
        int idxSolSize = 0;

        g.setIdxSol(idxSol);
        g.setIdxSolSize(idxSolSize);
        g.setVerCnt(vCount);
        g.setActVerCnt(vCount);
        g.setIdxAL(idxAL);
        //g.setIdxDomed(idxDomed);
        ///g.setIdxAdded(idxAdded);
        g.setIdxIM(idxIM);
        g.setIdxDegree(idxDegree);
        g.setIdxLst(idxLst);
        g.setLabLst(labLst);
        //g.setIdxVote(idxVote);
        // g.setIdxWeight(idxWeight);
    }

//    /**
//     * adjust values of gi's own variables at initialization
//     */
//    public static void adjustGIInitStatus(ISGlobalVariable gi) {
//        // active vertex count and vertex count should be 0
//        gi.setActVerCnt(0);
//        gi.setVerCnt(0);
//
//        // idxLst does not have valid values
//        int[] giIdxLst = gi.getIdxLst();
//        Arrays.fill(giIdxLst, ConstantValue.IMPOSSIBLE_VALUE);
//
//    }

    /**
     * convert the idx solution into label solution
     *
     * @param g, the variables representing a graph
     * @return vertex solution in label format
     */
    public static int[] getLabSolution(ISGlobalVariable g) {
        int idxSolSize = g.getIdxSolSize();
        int[] idxSol = g.getIdxSol();
        int[] labLst = g.getLabLst();
        int[] sol = new int[idxSolSize];
        for (int i = 0; i < idxSolSize; i++) {
            //need to get pos of idx in idxLst since the global variables have been modified and do not follow the initial order any more, hence can not use labLst[idxLst[i]] simply
            int pos = Util.getContiansEleIdxFromZero(g.getIdxLst(), g.getVerCnt(), idxSol[i]);

            sol[i] = labLst[pos];
        }
        return sol;
    }

//    public static String getLabSolutionStr(ISGlobalVariable g) {
//        StringBuilder sb = new StringBuilder();
//        int[] sol = AlgoUtil.getLabSolution(g);
//
//        for (int aSol : sol) {
//            sb.append(aSol).append(",");
//        }
//        return sb.substring(0, sb.length() - 1);
//    }

//    /**
//     * initialize the vote and weight after a graph loaded
//     *
//     * @param g, variables representing a graph
//     */
//    public static void initWeight(ISGlobalVariable g) {
//        int actVerCnt = g.getActVerCnt();
//        int[] idxDegree = g.getIdxDegree();
//        float[] idxVote = g.getIdxVote();
//        float[] idxWeight = g.getIdxWeight();
//        int[][] idxAL = g.getIdxAL();
//
//        for (int i = 0; i < actVerCnt; i++) {
//            int degree = idxDegree[i];
//            float vote = 1.0f / (1 + degree);
//            idxVote[i] = vote;
//            idxWeight[i] = vote;
//        }
//
//        for (int i = 0; i < actVerCnt; i++) {
//            int[] iIdxNeigh = idxAL[i];
//
//            float iWeight = idxWeight[i];
//            int iNeighCnt = idxDegree[i];
//
//            for (int j = 0; j < iNeighCnt; j++) {
//                int jIdx = iIdxNeigh[j];
//                float jVote = idxVote[jIdx];
//                iWeight += jVote;
//            }
//            idxWeight[i] = iWeight;
//        }
//    }

//    /**
//     * adjust the weight of the graph after the domination status of some
//     * vertices change
//     *
//     * @param g,    variables representing a graph
//     * @param uIdx, index of a vertex which will be adjusted
//     */
//    public static void adjustWeight(ISGlobalVariable g, int uIdx) {
//        int[][] idxAL = g.getIdxAL();
//        int[] idxDegree = g.getIdxDegree();
//        int uDegree = idxDegree[uIdx];
//
//        int[] uIdxNeigs = idxAL[uIdx];
//        boolean[] idxDomed = g.getIdxDomed();
//
//        float[] idxWeight = g.getIdxWeight();
//        float[] idxVote = g.getIdxVote();
//
//        idxWeight[uIdx] = 0f;
//
//        for (int i = 0; i < uDegree; i++) {
//            int vIdx = uIdxNeigs[i];
//            if (idxWeight[vIdx] - 0 > ConstantValue.FLOAT_NO_DIFF) {
//                if (!idxDomed[uIdx]) {
//                    idxWeight[vIdx] -= idxVote[uIdx];
//                }
//                if (!idxDomed[vIdx]) {
//                    idxDomed[vIdx] = true;
//                    idxWeight[vIdx] -= idxVote[vIdx];
//
//                    int[] vIdxNeigs = idxAL[vIdx];
//
//                    int vDegree = idxDegree[vIdx];
//
//                    for (int j = 0; j < vDegree; j++) {
//                        int wIdx = vIdxNeigs[j];
//
//                        if (idxWeight[wIdx] - 0 > ConstantValue.FLOAT_NO_DIFF) {
//                            idxWeight[wIdx] -= idxVote[vIdx];
//                        }
//                    }
//                }
//            }
//        }
//        idxDomed[uIdx] = true;
//
//    }

//    /**
//     * to check if all vertices are dominated
//     *
//     * @param g, variables representing a graph
//     * @return true if the graph is dominated, false otherwise
//     */
//    public static boolean isAllDominated(ISGlobalVariable g) {
//        int verCnt = g.getVerCnt();
//        for (int i = 0; i < verCnt; i++) {
//            if (g.getIdxDomed()[i]) {
//                continue;
//            }
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * get a vertex with the highest weight among the vertices
//     *
//     * @param g, variables representing a graph
//     * @return index of a vertex with the highest weight
//     */
//    public static int getHighestWeightVertexIdx(ISGlobalVariable g) {
//        int actVerCount = g.getActVerCnt();
//        int[] idxLst = g.getIdxLst();
//
//        float[] idxWeight = g.getIdxWeight();
//
//        float maxWeight = Float.MIN_VALUE;
//
//        int retIdx = 0;
//
//        for (int i = 0; i < actVerCount; i++) {
//            int vIdx = idxLst[i];
//            if ((idxWeight[vIdx] - 0 > ConstantValue.FLOAT_NO_DIFF)
//                    && (idxWeight[vIdx] - maxWeight > ConstantValue.FLOAT_NO_DIFF)) {
//                maxWeight = idxWeight[vIdx];
//                retIdx = vIdx;
//            }
//        }
//
//        if (Math.abs(maxWeight - 0f) <= ConstantValue.FLOAT_NO_DIFF) {
//            return ConstantValue.IMPOSSIBLE_VALUE;
//        }
//
//        return retIdx;
//    }

//    /**
//     * get a vertex with the lowest weight among unadded vertices
//     *
//     * @param g, variables representing a graph
//     * @return a vertex with the lowest weight among unadded vertices
//     */
//    public static int getUnaddedLowestWeightVertexIdx(ISGlobalVariable g) {
//        int actVerCount = g.getActVerCnt();
//        int[] idxLst = g.getIdxLst();
//        boolean[] idxAdded = g.getIdxAdded();
//
//        float[] idxWeight = g.getIdxWeight();
//
//        float minWeight = Float.MAX_VALUE;
//
//        int retIdx = ConstantValue.IMPOSSIBLE_VALUE;
//
//        for (int i = 0; i < actVerCount; i++) {
//            int vIdx = idxLst[i];
//
//            if ((!idxAdded[vIdx]) && (idxWeight[vIdx] >= 0) && (idxWeight[vIdx] < minWeight)) {
//                minWeight = idxWeight[vIdx];
//                retIdx = vIdx;
//            }
//        }
//
//        return retIdx;
//    }

//    /**
//     * get a vertex with the lowest weight among undominated vertices
//     *
//     * @param g, variables representing a graph
//     * @return a vertex with the lowest weight among undominated vertices
//     */
//    public static int getUndomedLowestWeightVertexIdx(ISGlobalVariable g) {
//        int actVerCount = g.getActVerCnt();
//        int[] idxLst = g.getIdxLst();
//        boolean[] idxDomed = g.getIdxDomed();
//
//        float[] idxWeight = g.getIdxWeight();
//
//        float minWeight = Float.MAX_VALUE;
//
//        int retIdx = ConstantValue.IMPOSSIBLE_VALUE;
//
//        for (int i = 0; i < actVerCount; i++) {
//            int vIdx = idxLst[i];
//
//            if ((!idxDomed[vIdx]) && (idxWeight[vIdx] > 0) && (idxWeight[vIdx] < minWeight)) {
//                minWeight = idxWeight[vIdx];
//                retIdx = vIdx;
//            }
//        }
//
//        if (Math.abs(minWeight - 0f) <= ConstantValue.FLOAT_NO_DIFF) {
//            return ConstantValue.IMPOSSIBLE_VALUE;
//        }
//
//        return retIdx;
//    }

//    /**
//     * get an neighbor with the highest weight of a vertex
//     *
//     * @param g,    variables representing a graph
//     * @param vIdx, index of a vertex
//     * @return an neighbor with the highest weight of a vertex
//     */
//    public static int getHighestWeightNeighIdx(ISGlobalVariable g, int vIdx) {
//        int[][] idxAL = g.getIdxAL();
//
//        int[] idxDegree = g.getIdxDegree();
//
//        int vDegree = idxDegree[vIdx];
//        int[] vIdxNeigs = idxAL[vIdx];
//        float[] idxWeight = g.getIdxWeight();
//        float minWeight = idxWeight[vIdx];
//
//        int retIdx = vIdx;
//
//        for (int i = 0; i < vDegree; i++) {
//            int uIdx = vIdxNeigs[i];
//            if ((idxWeight[uIdx] > minWeight)) {
//                minWeight = idxWeight[uIdx];
//                retIdx = uIdx;
//
//            }
//        }
//
//        return retIdx;
//    }

//    /**
//     * to check if a solution is valid
//     *
//     * @param g, variables representing a graph
//     *           global variables
//     * @return true if it is valid, otherwise false
//     */
//    public static boolean isValidSolution(ISGlobalVariable g) {
//
//        int[] idxLst = g.getIdxLst();
//        if(idxLst==null){
//            return false;
//        }
//        Set<Integer> idxLstList = new HashSet<>();
//
//        int[] idxSol = g.getIdxSol();
//        int idxSolSize = g.getIdxSolSize();
//        int[][] idxAL = g.getIdxAL();
//
//        putVerticesIntoASet(idxSol, idxLstList, idxSolSize, idxAL);
//
//        return (idxLstList.size() == idxLst.length);
//    }

    /*
    public static boolean isValidSolution(ISGlobalVariable g, int[][] sols) {

        int[] idxLst = g.getIdxLst();
        Set<Integer> idxLstList = new HashSet<>();

        int[][] idxAL = g.getIdxAL();

        for (int[] sol : sols) {
            int solSize = sol.length;
            putVerticesIntoASet(sol, idxLstList, solSize, idxAL);
        }

        return (idxLstList.size() == idxLst.length);
    }
    */

//    /**
//     * to check if a solution is valid
//     *
//     * @param g, variables reprsenting a graph
//     * @return true if it is valid, otherwise false
//     */
//    public static boolean isValidSolution(ISGlobalVariable g, int[] solTry) {
//
//        int[] idxLst = g.getIdxLst();
//        Set<Integer> idxLstList = new HashSet<>();
//
//        int solTrySize = solTry.length;
//        int[][] idxAL = g.getIdxAL();
//
//        putVerticesIntoASet(solTry, idxLstList, solTrySize, idxAL);
//
//        return (idxLstList.size() == idxLst.length);
//    }

//    private static void putVerticesIntoASet(int[] solTry, Set<Integer> idxLstList, int solTrySize, int[][] idxAL) {
//        for (int i = 0; i < solTrySize; i++) {
//            int vIdx = solTry[i];
//            int[] vNeigs = idxAL[vIdx];
//            for (int uIdx : vNeigs) {
//                idxLstList.add(uIdx);
//            }
//            idxLstList.add(vIdx);
//        }
//    }

//    /**
//     * try to remove distance to 1 vertices from the solution to see if we can get
//     * smaller result
//     *
//     * @param g,        variables representing a graph
//     * @param distance, how many vertices to be removed from solution.
//     * @return a minimized solution if applicable
//     */
//    static int[] minimal(ISGlobalVariable g, int distance) {
//
//        int[] idxSol = g.getIdxSol();
//        int idxSolSize = g.getIdxSolSize();
//        int[] ds = Arrays.copyOf(idxSol, idxSolSize);
//
//        for (int d = distance; d >= 1; d--) {
//            List<int[]> combins = Util.getAllRoutOfNCombines(ds, idxSolSize - d);
//
//            for (int[] com : combins) {
//                boolean flag = AlgoUtil.w0risValidSolution(g, com);
//                if (flag) {
//                    return com;
//                }
//            }
//        }
//
//        return ds;
//    }


    private static int getACertainDegreeVertex(ISGlobalVariable gv, int degree) {
        int[] idxDegree = gv.getIdxDegree();
        int[] idxLst = gv.getIdxLst();
        int actVerCnt = gv.getActVerCnt();
        for (int i = 0; i < actVerCnt; i++) {
            if (idxDegree[idxLst[i]] == degree) {
                return idxLst[i];
            }
        }

        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * get the first vertex whose degree is zero
     *
     * @param gv, variables representing a graph
     * @return vertex index
     */
    public static int getDegreeZeroVertex(ISGlobalVariable gv) {
        return getACertainDegreeVertex(gv, 0);
    }

    /**
     * get the first vertex whose degree is one
     *
     * @param gv, variables representing a graph
     * @return vertex index
     */
    public static int getDegreeOneVertex(ISGlobalVariable gv) {
        return getACertainDegreeVertex(gv, 1);
    }


    /**
     * get the first highest degree vertex index
     *
     * @param gv, variables representing a graph
     * @return vertex index
     */
    public static int getHighestDegreeVertexIdx(ISGlobalVariable gv) {
        int maxDegree = ConstantValue.IMPOSSIBLE_VALUE;
        int pos = ConstantValue.IMPOSSIBLE_VALUE;

        int[] idxDegree = gv.getIdxDegree();
        int actVerCnt = gv.getActVerCnt();
        for (int i = 0; i < actVerCnt; i++) {
            if (idxDegree[i] > maxDegree) {
                maxDegree = idxDegree[i];
                pos = i;
            }
        }
        int[] idxLst = gv.getIdxLst();
        return idxLst[pos];
    }

    /**
     * delete closed neighborhood of vertex v
     *
     * @param gv,   variables representing a graph
     * @param vIdx, index of vertex v
     */
    public static void deleteClosedNeighs(ISGlobalVariable gv, int vIdx) {
        int[][] idxAL = gv.getIdxAL();
        int[] idxDegree = gv.getIdxDegree();
        int vDegree = idxDegree[vIdx];

        int[] vNeigs = idxAL[vIdx];
        //delete N(v)
        for (int i = vDegree - 1; i >= 0; i--) {
            AlgoUtil.deleteVertex(gv, vNeigs[i]);
        }
        //delete v
        AlgoUtil.deleteVertex(gv, vIdx);
    }

    /**
     * if a solution is valid
     *
     * @param gv, global variables
     * @return true if it is valid, otherwise false
     */
    public static boolean isValidDSSolution(DSGlobalVariable gv) {

        int bestSolCount = gv.getBestSolCount();
        int[] bestSol = gv.getBestSol();
        int[][] sAL = gv.getsAL();
        int[] eL = gv.geteL();
        int[] copyEL = Arrays.copyOf(eL, eL.length);
        int eCount = gv.geteCount();

        int count = 0;

        for (int i = 1; i <= bestSolCount; i++) {
            int[] eLi = sAL[bestSol[i]];
            for (int ei : eLi) {
                if (ei != ConstantValue.IMPOSSIBLE_VALUE) {
                    int tmpIdx = Util.getContainsEleIdxFromOne(copyEL, copyEL.length - 1, ei);
                    if (tmpIdx != ConstantValue.IMPOSSIBLE_VALUE) {
                        count++;
                        copyEL[tmpIdx] = ConstantValue.IMPOSSIBLE_VALUE;
                    }
                }
            }
        }

        return count == eCount;
    }

    /**
     * delete a set
     *
     * @param gv,   global variables
     * @param card, set cardinality
     * @param freq, element frequency* @param sToDelIdx, the index of the set to
     *              be deleted
     */
    public static void deleteSet(DSGlobalVariable gv, int[] card, int[] freq, int sActCount, int s) {
        int[] sL = gv.getsL();
        int[] sIL = gv.getsIL();
        int[][] sAL = gv.getsAL();
        // int[][] eAL = gv.geteAL();
        // int[][] eIM = gv.geteIM();

        // deleteVertex(card, sL, sIL, sAL, freq, eAL, eIM, sToDelIdx);
        int last = sL[sActCount];
        int currentIdx = sIL[s];
        sL[currentIdx] = last;
        sL[sActCount] = s;
        sIL[last] = currentIdx;
        sIL[s] = sActCount;

        int d = card[s];
        for (int i = 1; i <= d; i++) {
            int e = sAL[s][i];
            decreaseElementFrequency(gv, freq, e, s);
        }

        card[s] = 0;

        gv.setsIL(sIL);
        gv.setsAL(sAL);
        // gv.seteAL(eAL);
        // gv.seteIM(eIM);
        gv.setsL(sL);
        gv.setCard(card);

    }

    /**
     * decrease the cardinality of a set
     *
     * @param gv,   global variable
     * @param card, set cardinality
     * @param s,    the index of the set to be decreased
     * @param e,    the index of the element to be deleted
     */
    private static void decreaseSetCardinality(DSGlobalVariable gv, int[] card, int s, int e) {
        // int[] card = gv.getCard();
        int[][] sAL = gv.getsAL();
        int[][] sIM = gv.getsIM();

        // deleteEdge(card, sAL, sIM, s, e);

        int i = sIM[e][s];
        int j = card[s];
        int x = sAL[s][j];
        sAL[s][i] = x;
        sIM[x][s] = i;
        sAL[s][j] = e;
        sIM[e][s] = j;
        card[s]--;

        // gv.setCard(card);
        gv.setsAL(sAL);
        gv.setsIM(sIM);
        gv.setCard(card);
    }

    /**
     * delete element
     *
     * @param gv,        global variable
     * @param card,      set cardinalities
     * @param freq,      element frequency
     * @param eActCount, active element count
     * @param e,         the index of the element to be deleted
     * @param source,    the set where e to be delete from
     */
    static void deleteElement(DSGlobalVariable gv, int[] card, int[] freq, int eActCount,
                              int e, int source) {
        int[] eL = gv.geteL();
        int[] eIL = gv.geteIL();
        int[][] eAL = gv.geteAL();
        // int[][] sAL = gv.getsAL();
        // int[][] sIM = gv.getsIM();

        // deleteVertex(freq, eL, eIL, eAL, card, sAL, sIM, e);

        int last = eL[eActCount];
        int currentIdx = eIL[e];
        eL[currentIdx] = last;
        eL[eActCount] = e;
        eIL[last] = currentIdx;
        eIL[e] = eActCount;

        int d = freq[e];
        for (int i = 1; i <= d; i++) {
            int s = eAL[e][i];
            if (s == source) {
                continue;
            }
            decreaseSetCardinality(gv, card, s, e);
        }

        freq[e] = 0;

        gv.seteIL(eIL);
        // gv.setsAL(sAL);
        gv.seteAL(eAL);
        // gv.setsIM(sIM);
        gv.seteL(eL);
    }

    /**
     * get the set index which contains an element of frequency one
     *
     * @param gv,   global variables
     * @param freq, element frequency
     * @return set index
     */
    public static int getSetOfFrequencyOneElement(DSGlobalVariable gv, int[] freq, int eActCount) {
        // int eActCount = freq[0];

        int[] eL = gv.geteL();

        int[][] eAL = gv.geteAL();

        for (int i = 1; i <= eActCount; i++) {
            int j = eL[i];
            if (freq[j] == 1) {
                return eAL[j][1];
            }
        }

        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * if set1 is a subset of set2
     *
     * @param gv,    global variables
     * @param card,  set cardinalities
     * @param s1Idx, set1 index
     * @param s2Idx, set2 index
     * @return true: set1 is a subset of set2; false: otherwise
     */

    static boolean is1Subset2(DSGlobalVariable gv, int[] card, int s1Idx, int s2Idx) {
        if (s1Idx == s2Idx) {
            return false;
        }

        int s1Card = card[s1Idx];
        int s2Card = card[s2Idx];

        if (s1Card == 0 || s2Card == 0 || s1Card > s2Card) {
            return false;
        }

        int[][] sAL = gv.getsAL();
        int[] s1Array = sAL[s1Idx];
        int[] s2Array = sAL[s2Idx];

        int count = 0;
        for (int i = 1; i <= s1Card; i++) {
            int tmp = s1Array[i];
            if (Util.setContainsEleFromOne(s2Array, s2Card, tmp)) {
                count++;
            }
        }

        return count == s1Card;

    }

    /**
     * if a set is a subset of another set, return the former set index
     *
     * @param gv,   global variable
     * @param card, set cardinalities
     * @return a subset of another set
     */
    public static int getSubset(DSGlobalVariable gv, int[] card) {
        int[] sL = gv.getsL();
        int sActCount = card[0];

        for (int i = 1; i <= sActCount - 1; i++) {
            int isL = sL[i];
            if (card[isL] <= 0) {
                continue;
            }
            for (int j = i + 1; j <= sActCount; j++) {

                int jsL = sL[j];
                if (card[jsL] <= 0) {
                    continue;
                }

                if (is1Subset2(gv, card, isL, jsL)) {
                    return isL;
                } else if (is1Subset2(gv, card, jsL, isL)) {
                    return jsL;
                }

            }
        }

        return ConstantValue.IMPOSSIBLE_VALUE;
    }

    /**
     * convert global variables into the format useful for calculating maximum
     * matching
     *
     * @param gv,   global variables
     * @param card, set cardinalities
     * @return an adjacency list of elements format
     */
    public static Map<Integer, List<Integer>> transferGVIntoMMParam(DSGlobalVariable gv, int[] card, int[] freq) {
        // TODO: sL is not right
        int[] sL = gv.getsL();
        int[] eL = gv.geteL();
        int[][] sAL = gv.getsAL();
        int sActCount = gv.getsCount();
        // int sActCount = card[0];
        int eActCount = freq[0];

        Map<Integer, List<Integer>> eleG = new HashMap<>();

        Map<Integer, Integer> actEleIdxMap = new HashMap<>(eActCount);
        for (int i = 1; i <= eActCount; i++) {
            actEleIdxMap.put(eL[i], i);
        }

        for (int i = 1; i <= sActCount; i++) {
            int sLi = sL[i];
            if (card[sLi] > 0) {
                int[] sEL = sAL[sLi];

                for (int j = 1; j <= card[sLi]; j++) {
                    int sELj = sEL[j];

                    if (!eleG.containsKey(sELj)) {

                        List<Integer> tmpList = new ArrayList<>();
                        eleG.put(sELj, tmpList);

                    }
                    List<Integer> tmpList = eleG.get(sELj);

                    for (int k = 1; k <= card[sLi]; k++) {
                        if (j == k) {
                            continue;
                        }
                        int sELk = sEL[k];
                        if (!tmpList.contains(sELk)) {

                            tmpList.add(sELk);
                            if (!eleG.containsKey(sELk)) {

                                List<Integer> tmpList2 = new ArrayList<>();
                                eleG.put(sELk, tmpList2);

                            }

                        }
                    }
                }

            }
        }

        Map<Integer, List<Integer>> elePosG = new HashMap<>();

        Set<Integer> gKeySet = eleG.keySet();
        for (Integer key : gKeySet) {
            List<Integer> vList = eleG.get(key);
            List<Integer> v1List = new ArrayList<>(vList.size());
            for (Integer v : vList) {
                v1List.add(actEleIdxMap.get(v));
            }
            elePosG.put(actEleIdxMap.get(key), v1List);
        }

        return elePosG;

    }

    /**
     * get the max cardinality set index in the list s containing sets
     *
     * @param gv,   global variables
     * @param card, set cardinalities
     * @return the max cardinality set index in the list s
     */
    public static int getMaxCardinalitySetIndex(DSGlobalVariable gv, int[] card, int sActCount) {

        int maxCard = ConstantValue.IMPOSSIBLE_VALUE;
        int index = ConstantValue.IMPOSSIBLE_VALUE;

        int[] sL = gv.getsL();
        // int sCount = card[0];
        // int sCount=gv.getsCount();

        for (int i = 1; i <= sActCount; i++) {
            int j = sL[i];
            if (card[j] <= 0) {
                continue;
            }
            if (card[j] > maxCard) {
                index = j;
                maxCard = card[j];
            }
            if (card[j] >= maxCard && j < index) {
                index = j;
                maxCard = card[j];
            }
        }

        return index;
    }

    /**
     * decrease element frequency
     *
     * @param gv,   global variables
     * @param freq, frequency
     * @param e,    the index of the element to be decreased
     * @param s,    the index of the set to be deleted
     */
    private static void decreaseElementFrequency(DSGlobalVariable gv, int[] freq, int e, int s) {
        // int[] freq = gv.getFreq();
        int[][] eAL = gv.geteAL();
        int[][] eIM = gv.geteIM();

        // deleteEdge(freq, eAL, eIM, eToDecIdx, sToDelIdx);
        int i = eIM[s][e];
        int j = freq[e];
        int x = eAL[e][j];
        eAL[e][i] = x;
        eIM[x][e] = i;
        eAL[e][j] = s;
        eIM[s][e] = j;
        freq[e]--;

        // gv.setFreq(freq);
        gv.seteAL(eAL);
        gv.seteIM(eIM);
        gv.setFreq(freq);

    }

    /**
     * add a set to solution
     *
     * @param gv,        global variable
     * @param card,      set cardinalities
     * @param freq,      element frequency
     * @param sActCount, active set count
     * @param eActCount, active element count
     * @param s,         the index of the set to be added
     */
    public static void addSetToCover(DSGlobalVariable gv, int[] card, int[] freq, int sActCount, int eActCount, int s) {
        int[] sL = gv.getsL();
        // int sActCount = card[0];
        int[] sIL = gv.getsIL();
        int[][] sAL = gv.getsAL();

        int last = sL[sActCount];
        int currentIdx = sIL[s];
        sL[currentIdx] = last;
        sL[sActCount] = s;
        sIL[last] = currentIdx;
        sIL[s] = sActCount;

        int d = card[s];

        // int i = sL[sToAddIdx];
        // sL[i] = last;
        // sL[sActCount] = sToAddIdx;
        // sIL[last] = i;
        // sIL[sToAddIdx] = sActCount;

        for (int j = d; j >= 1; j--) {
            int e = sAL[s][j];
            deleteElement(gv, card, freq, eActCount - (j - 1), e, s);

        }
        card[s] = 0;

        gv.setsL(sL);
        gv.setsIL(sIL);
        gv.setsAL(sAL);
        card[0] = sActCount - 1;
    }

    public static boolean isIndepedentSet(ISGlobalVariable gv) {
        int[] idxSol = gv.getIdxSol();
        int idxSolSize = gv.getIdxSolSize();
        int[][] idxAL = gv.getIdxAL();
        int[] idxDegree = gv.getIdxDegree();

        for (int i = 0; i < idxSolSize; i++) {
            int vIdx = idxSol[i];
            int[] vNeigs = idxAL[vIdx];
            int vDegree = idxDegree[vIdx];
            for (int j = 0; j < vDegree; j++) {
                int uIdx = vNeigs[j];
                if (Util.setContainsEleFromZero(idxSol, idxSolSize, uIdx)) {
                    return false;
                }
            }

        }

        return true;

    }

    public static boolean isMaximalIndepedentSet(ISGlobalVariable gv) {
        int[] idxSol = gv.getIdxSol();
        int idxSolSize = gv.getIdxSolSize();

        Arrays.stream(idxSol, 0, idxSolSize).forEach(vIdx -> AlgoUtil.deleteClosedNeighs(gv, vIdx));
        return gv.getActVerCnt() == 0;

    }
}
