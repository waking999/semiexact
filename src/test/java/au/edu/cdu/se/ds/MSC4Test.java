package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.ConstantValue;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * test class for msc4
 */
public class MSC4Test {
    private static Logger log = LogUtil.getLogger(MSC4Test.class);

    // @Ignore
    @Test
    public void testBuildMaxMatching1() {

        DSGlobalVariable gv = TestUtil.getTC2Rep();

        testBuildMaxMatching(gv);

    }

    private void testBuildMaxMatching(DSGlobalVariable gv) {
        log.debug(TestUtil.FUNCTION_SEP);
        MSC4 msc = new MSC4();
        int[] card = gv.getCard();
        int[] freq = gv.getFreq();
        int size = msc.buildMaxMatching(gv, card, freq);
        assertEquals(3, size);
        int[] mate = gv.getMate();
        assertEquals(3, mate[1]);
        assertEquals(1, mate[3]);
        assertEquals(4, mate[2]);
        assertEquals(2, mate[4]);
        assertEquals(6, mate[5]);
        assertEquals(5, mate[6]);
        assertEquals(ConstantValue.MATE_EXPOSE, mate[7]);
    }

    // @Ignore
    @Test
    public void testPreProcess1() {

        DSGlobalVariable gv = TestUtil.getTC1Rep();

        testPreProcess(gv);
    }

    // @Ignore
    @Test
    public void testPreProcess2() throws IOException {

        DSGlobalVariable gv = TestUtil.getTC1RepFile();

        testPreProcess(gv);
    }

    private void testPreProcess(DSGlobalVariable gv) {
        log.debug(TestUtil.FUNCTION_SEP);
        MSC4 msc = new MSC4();
        int[] card = gv.getCard();
        int[] freq = gv.getFreq();
        msc.preProcess(gv, card, freq);

        assertEquals(2, gv.getSolCount());
    }

    // @Ignore
    @Test
    public void testKHighest1() {

        DSGlobalVariable gv = TestUtil.getTC1Rep();

        testKHighest(gv);

    }

    // @Ignore
    @Test
    public void testKHighest2() throws IOException {

        DSGlobalVariable gv = TestUtil.getTC1RepFile();

        testKHighest(gv);

    }

    private void testKHighest(DSGlobalVariable gv) {
        log.debug(TestUtil.FUNCTION_SEP);
        MSC4 msc = new MSC4();
        int[] card = gv.getCard();
        int maxCardSet = AlgoUtil.getMaxCardinalitySetIndex(gv, card, card[0]);

        int maxCard = card[maxCardSet];

        int kMax = msc.kHighest(gv, card, maxCard, card[0]);
        assertEquals(16, kMax);
    }

    // @Ignore
    @Test
    public void testBranch3() {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC2Rep();

        testBranch(gv);
        assertTrue(AlgoUtil.isValidDSSolution(gv));
        assertEquals(4, gv.getBestSolCount());
    }

    // @Ignore
    @Test
    public void testBranch1() {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC1Rep();

        testBranch(gv);
        assertTrue(AlgoUtil.isValidDSSolution(gv));
        assertEquals(2, gv.getBestSolCount());
    }

    // @Ignore
    @Test
    public void testBranch2() throws IOException {
        log.debug(TestUtil.FUNCTION_SEP);
        DSGlobalVariable gv = TestUtil.getTC1RepFile();

        testBranch(gv);
        assertTrue(AlgoUtil.isValidDSSolution(gv));
        assertEquals(2, gv.getBestSolCount());
    }

    private void testBranch(DSGlobalVariable gv) {
        MSC4 msc = new MSC4();

        msc.branch(gv, null);

    }

}