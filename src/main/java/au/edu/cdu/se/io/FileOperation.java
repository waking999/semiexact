package au.edu.cdu.se.io;

import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import au.edu.cdu.se.util.is.ISGlobalVariable;

import java.io.*;
import java.util.Arrays;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * implement operation system file operations
 */
public class FileOperation {
    private static final String BLANK = " ";

    /**
     * read edge pair information from a file to generate graph representations
     *
     * @param filePath, file path and name
     * @return graph representation
     * @throws IOException, IOException
     */
    public static DSGlobalVariable readGraphByEdgePairForDS(String filePath) throws IOException {

        DSGlobalVariable g = new DSGlobalVariable();

        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
        String line0 = lines.get(0);
        String[] line0Array = line0.split(BLANK);
        String numOfVerStr = line0Array[0];
        String numOfEdgStr = line0Array[1];

        int numOfVer = Integer.parseInt(numOfVerStr);
        int numOfEdg = Integer.parseInt(numOfEdgStr);

        int mallocsize = numOfVer + 1; // valid index starts from 1

        int[] card = new int[mallocsize];
        int[] freq = new int[mallocsize];
        int[] sL = new int[mallocsize];
        int[] sIL = new int[mallocsize];
        int[] eL = new int[mallocsize];
        int[] eIL = new int[mallocsize];
        int[][] sIM = new int[mallocsize][mallocsize];
        int[][] eIM = new int[mallocsize][mallocsize];

        for (int i = 0; i <= numOfVer; i++) {
            if (i > 0) {
                card[i] = 1;
                freq[i] = 1;
            } else {
                card[i] = numOfVer;
                freq[i] = numOfVer;
            }

            if (i > 0) {
                sL[i] = i;
                sIL[i] = i;
                eL[i] = i;
                eIL[i] = i;
            } else {
                sL[i] = ConstantValue.IMPOSSIBLE_VALUE;
                sIL[i] = ConstantValue.IMPOSSIBLE_VALUE;
                eL[i] = ConstantValue.IMPOSSIBLE_VALUE;
                eIL[i] = ConstantValue.IMPOSSIBLE_VALUE;
            }

            for (int j = 0; j <= numOfVer; j++) {
                sIM[i][j] = ConstantValue.IMPOSSIBLE_VALUE;
                eIM[i][j] = ConstantValue.IMPOSSIBLE_VALUE;
            }
        }

        String tmpLine;
        for (int i = 1; i <= numOfEdg; i++) {
            tmpLine = lines.get(i);
            String[] tmpLineArray = tmpLine.split(BLANK);
            String u1Str = tmpLineArray[0];
            String v1Str = tmpLineArray[1];
            int u = Integer.parseInt(u1Str);
            int v = Integer.parseInt(v1Str);

            if (u != v) {
                sIM[u][v] = 1;
                sIM[v][u] = 1;
                eIM[u][v] = 1;
                eIM[v][u] = 1;

                card[u]++;
                card[v]++;
                freq[u]++;
                freq[v]++;
            }
        }

        for (int i = 1; i <= numOfVer; i++) {

            sIM[i][i] = 1;
            eIM[i][i] = 1;
        }

        int[][] sAL = new int[mallocsize][];
        int[][] eAL = new int[mallocsize][];

        for (int i = 1; i <= numOfVer; i++) {
            sAL[i] = new int[card[i] + 1];
            eAL[i] = new int[freq[i] + 1];

            sAL[i][0] = ConstantValue.IMPOSSIBLE_VALUE;
            eAL[i][0] = ConstantValue.IMPOSSIBLE_VALUE;

            int sCur = 1;
            int eCur = 1;
            for (int j = 1; j <= numOfVer; j++) {
                if (sIM[j][i] == 1) {
                    sIM[j][i] = sCur;
                    sAL[i][sCur] = j;
                    sCur++;
                }
                if (eIM[j][i] == 1) {
                    eIM[j][i] = eCur;
                    eAL[i][eCur] = j;
                    eCur++;
                }
            }
        }

        int[] mate = new int[mallocsize];
        for (int i = 0; i <= numOfVer; i++) {
            mate[i] = ConstantValue.IMPOSSIBLE_VALUE;
        }

        int[] sol = new int[mallocsize];
        int[] bestSol = new int[mallocsize];
        for (int i = 0; i <= numOfVer; i++) {
            sol[i] = ConstantValue.IMPOSSIBLE_VALUE;
            bestSol[i] = ConstantValue.IMPOSSIBLE_VALUE;
        }
        g.setSol(sol);
        g.setBestSol(bestSol);
        g.setSolPtr(1); // valid index starts from 1;

        g.setsCount(numOfVer);
        g.setsAL(sAL);
        g.setsIL(sIL);
        g.setsIM(sIM);
        g.setsL(sL);
        g.setsIL(sIL);
        g.setCard(card);

        g.seteCount(numOfVer);
        g.seteAL(eAL);
        g.seteIL(eIL);
        g.seteIM(eIM);
        g.seteL(eL);
        g.seteIL(eIL);
        g.setFreq(freq);

        g.setBestSolCount(numOfVer);
        g.setSolCount(0);
        g.setMate(mate);

        return g;

    }


    /**
     * read data file to initialize the global variables for the data structure
     * and
     * algorithms
     *
     * @param filePath, file path and name
     * @return a series of variables  wrapped global variable to present a graph
     * @throws IOException, ioexception if io error happens
     */
    public static ISGlobalVariable readGraphByEdgePairForIS(String filePath) throws IOException {
        // access the input file
        // Path path = Paths.get(filePath);

        BufferedInputStream fis;
        ISGlobalVariable gv = null;

        fis = new BufferedInputStream(new FileInputStream(filePath));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {


            String line;
            line = reader.readLine();

            String[] line0Array = line.split(BLANK);

            /*
             * the first line shows vertex count and edge count, which are separated
             * by a blank
             */
            String vCountStr = line0Array[0].trim();
            int vCount = Integer.parseInt(vCountStr);
            int mallocsize = vCount + 1;

            // initialize the global variables
            gv = new ISGlobalVariable();

            AlgoUtil.initISGlobalVariable(gv, vCount);

            int[] verLst = gv.getLabLst();
            int[][] idxIM = gv.getIdxIM();
            int[] idxDegree = gv.getIdxDegree();
            int[][] idxAL = gv.getIdxAL();

            // read each line of the input file

            int currentVCount = 0;
            // int min = Integer.MAX_VALUE;
            // int max = Integer.MIN_VALUE;

            while ((line = reader.readLine()) != null) {

                // the edge pair is presented by two vertex labels separated by a
                // blank
                String[] tmpLineArray = line.split(BLANK);
                String uStr = tmpLineArray[0];
                String vStr = tmpLineArray[1];

                int uLab = Integer.parseInt(uStr);
                // if (uLab > max) {
                // max = uLab;
                // }
                // if (uLab < min) {
                // min = uLab;
                // }
                int vLab = Integer.parseInt(vStr);
                // if (vLab > max) {
                // max = vLab;
                // }
                // if (vLab < min) {
                // min = vLab;
                // }
                // we get the index of the vertices
                int uIdx = AlgoUtil.getIdxByLab(gv, uLab);
                // if this vertex is not in the list, add it to vertex list
                if (uIdx == ConstantValue.IMPOSSIBLE_VALUE) {
                    verLst[currentVCount] = Integer.parseInt(uStr);
                    uIdx = currentVCount;

                    currentVCount++;
                }

                int vIdx = AlgoUtil.getIdxByLab(gv, vLab);
                if (vIdx == ConstantValue.IMPOSSIBLE_VALUE) {
                    verLst[currentVCount] = Integer.parseInt(vStr);
                    vIdx = currentVCount;
                    currentVCount++;
                }

                if (uIdx != vIdx) {
                    // we don't allow self circle of each vertex

                    // we set the incident matrix cells of the two vertices are set
                    // to not null
                    // first
                    idxIM[uIdx][vIdx] = ConstantValue.NOT_NULL;
                    idxIM[vIdx][uIdx] = ConstantValue.NOT_NULL;

                    // the degree of the two vertices will increase
                    idxDegree[uIdx]++;
                    idxDegree[vIdx]++;

                }

            }

            // calculate im and al
            for (int i = 0; i < vCount; i++) {

                idxAL[i] = new int[idxDegree[i]];
                Arrays.fill(idxAL[i], ConstantValue.IMPOSSIBLE_VALUE);

                int currentPos = 0;
                for (int j = 0; j < vCount; j++) {
                    if (idxIM[j][i] == ConstantValue.NOT_NULL) {
                        idxIM[j][i] = currentPos;
                        idxAL[i][currentPos] = j;
                        currentPos++;
                    }

                }
            }

            int[] sol = new int[mallocsize];
            int[] bestSol = new int[mallocsize];
            for (int i = 0; i <= vCount; i++) {
                sol[i] = ConstantValue.IMPOSSIBLE_VALUE;
                bestSol[i] = ConstantValue.IMPOSSIBLE_VALUE;
            }
            gv.setIdxSol(sol);
            gv.setIdxSolSize(0);
            gv.setBestSol(bestSol);
            gv.setBestSolSize(0);

            // initialize weight
            //AlgoUtil.initWeight(gv);
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        } finally {
            return gv;
        }
    }
}