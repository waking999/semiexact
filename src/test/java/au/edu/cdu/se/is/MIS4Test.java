package au.edu.cdu.se.is;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.is.ISGlobalVariable;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class MIS4Test {
    @Test
    public void testCase1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/sample1.txt";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS4();
        mis.setGv(gv);

        int rtn=mis.run();
        Assert.assertEquals(3,rtn);
    }

    @Test
    public void testCase_konect_000027_zebra() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/KONECT/000027_zebra.konect";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS4();
        mis.setGv(gv);

        int rtn=mis.run();

        int[] idxSol=gv.getIdxSol();
        int idxSolSize=gv.getIdxSolSize();
        int[] copyIdxSol=Arrays.copyOf(idxSol,idxSolSize);

        gv = FileOperation.readGraphByEdgePairForIS(filePath);
        gv.setIdxSol(copyIdxSol);
        gv.setIdxSolSize(idxSolSize);

        Assert.assertTrue(AlgoUtil.isIndepedentSet(gv));

        Assert.assertEquals(7,rtn);

        Assert.assertTrue(AlgoUtil.isMaximalIndepedentSet(gv));


    }

    @Test
    public void testCase_bhoslib_frb30_15_1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/BHOSLIB/frb30-15-mis/frb30-15-1.mis";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS4();
        mis.setGv(gv);

        int rtn=mis.run();

        int[] idxSol=gv.getIdxSol();
        int idxSolSize=gv.getIdxSolSize();
        int[] copyIdxSol=Arrays.copyOf(idxSol,idxSolSize);

        gv = FileOperation.readGraphByEdgePairForIS(filePath);
        gv.setIdxSol(copyIdxSol);
        gv.setIdxSolSize(idxSolSize);

        Assert.assertTrue(AlgoUtil.isIndepedentSet(gv));

        Assert.assertEquals(30,rtn);

        Assert.assertTrue(AlgoUtil.isMaximalIndepedentSet(gv));
    }
}
