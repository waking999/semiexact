package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.Util;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * test class for msc5
 */
public class MSC5Test {
    private static Logger log = LogUtil.getLogger(MSC5Test.class);

    // @Ignore
    @Test
    public void testBranch3() {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC2Rep();

        testBranch(gv);
        Assert.assertTrue(Util.isValidSolution(gv));
        Assert.assertEquals(4, gv.getBestSolCount());
    }

    // @Ignore
    @Test
    public void testBranch1() {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC1Rep();

        testBranch(gv);
        Assert.assertTrue(Util.isValidSolution(gv));
        Assert.assertEquals(2, gv.getBestSolCount());

    }

    // @Ignore
    @Test
    public void testBranch2() throws IOException {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC1RepFile();

        testBranch(gv);
        Assert.assertTrue(Util.isValidSolution(gv));
        Assert.assertEquals(2, gv.getBestSolCount());
    }

    private void testBranch(DSGlobalVariable gv) {
        MSC5 msc = new MSC5();

        msc.branch(gv, null);

    }

}