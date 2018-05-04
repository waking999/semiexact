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
}
