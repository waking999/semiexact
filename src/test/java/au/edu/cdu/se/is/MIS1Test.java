package au.edu.cdu.se.is;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.ds.MSC4;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import au.edu.cdu.se.util.is.ISGlobalVariable;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MIS1Test {
    @Test
    public void testCase1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/sample1.txt";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS1();

        int rtn=mis.branch(gv, null);
        Assert.assertEquals(3,rtn);
    }

    @Test
    public void testCase_bhoslib_frb30_15_1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/BHOSLIB/frb30-15-mis/frb30-15-1.mis";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS1();

        int rtn=mis.branch(gv, null);
        Assert.assertEquals(30,rtn);
    }

    @Test
    public void testCase_konect_000027_zebra() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/KONECT/000027_zebra.konect";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IMIS mis = new MIS1();

        int rtn=mis.branch(gv, null);
        Assert.assertEquals(7,rtn);
    }
}
