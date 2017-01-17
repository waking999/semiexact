package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

/**
 * test class for msc4
 *
 */
public class MSC4Test {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);

	// @Ignore
	@Test
	public void testBuildMaxMatching1() {

		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBuildMaxMatching(gv);

	}

	private void testBuildMaxMatching(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int size = msc.buildMaxMatching(gv, card);
		Assert.assertEquals(3, size);
		int[] mate = gv.getMate();
		Assert.assertEquals(3, mate[1]);
		Assert.assertEquals(1, mate[3]);
		Assert.assertEquals(4, mate[2]);
		Assert.assertEquals(2, mate[4]);
		Assert.assertEquals(6, mate[5]);
		Assert.assertEquals(5, mate[6]);
		Assert.assertEquals(ConstantValue.MATE_EXPOSE, mate[7]);
	}

	// @Ignore
	@Test
	public void testPreProcess1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testPreProcess(gv);
	}

	// @Ignore
	@Test
	public void testPreProcess2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testPreProcess(gv);
	}

	private void testPreProcess(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int[] freq = gv.getFreq();
		msc.preProcess(gv, card, freq);

		Assert.assertEquals(2, gv.getSolCount());
	}

	// @Ignore
	@Test
	public void testKHighest1() {

		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testKHighest(gv);

	}

	// @Ignore
	@Test
	public void testKHighest2() throws IOException {

		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testKHighest(gv);

	}

	private void testKHighest(GlobalVariable<String, String> gv) {
		log.debug(TestUtil.FUNCTION_SEP);
		MSC4<String, String> msc = new MSC4<String, String>();
		int[] card = gv.getCard();
		int maxCardSet = Util.getMaxCardinalitySetIndex(gv, card);

		int maxCard = card[maxCardSet];

		int kMax = msc.kHighest(gv, card, maxCard);
		Assert.assertEquals(16, kMax);
	}

	// @Ignore
	@Test
	public void testBranch3() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(4, gv.getBestSolCount());
	}

	// @Ignore
	@Test
	public void testBranch1() {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());
	}

	// @Ignore
	@Test
	public void testBranch2() throws IOException {
		log.debug(TestUtil.FUNCTION_SEP);
		GlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());
	}

	private void testBranch(GlobalVariable<String, String> gv) {
		MSC4<String, String> msc = new MSC4<String, String>();

		msc.branch(gv);

	}

}