package au.edu.cdu.se.is;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.is.ISGlobalVariable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class GreedyMIS1Test {
    @Ignore
    @Test
    public void testCase_bhoslib_frb30_15_1() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/BHOSLIB/frb30-15-mis/frb30-15-1.mis";
        ISGlobalVariable gv = FileOperation.readGraphByEdgePairForIS(filePath);
        IGreedyMIS algo=new GreedyMIS1();
        algo.setGv(gv);

        int rtn = algo.run();
        Assert.assertTrue(AlgoUtil.isIndepedentSet(gv));

    }

}
