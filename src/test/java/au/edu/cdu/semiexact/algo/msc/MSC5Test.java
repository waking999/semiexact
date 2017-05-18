package au.edu.cdu.semiexact.algo.msc;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.algo.msc.MSC5;
import au.edu.cdu.semiexact.util.MSCGlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.Util;

/**
 * test class for msc5
 *
 */
public class MSC5Test {
	private static Logger log = LogUtil.getLogger(MSC5Test.class);

	// @Ignore
	@Test
	public void testBranch3() {
		log.debug(TestUtil.FUNCTION_SEP);
		MSCGlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidMSCSolution(gv));
		Assert.assertEquals(4, gv.getBestSolCount());
	}

	// @Ignore
	@Test
	public void testBranch1() {
		log.debug(TestUtil.FUNCTION_SEP);
		MSCGlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidMSCSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());

	}

	// @Ignore
	@Test
	public void testBranch2() throws IOException {
		log.debug(TestUtil.FUNCTION_SEP);
		MSCGlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testBranch(gv);
		Assert.assertTrue(Util.isValidMSCSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());
	}

	private void testBranch(MSCGlobalVariable<String, String> gv) {
		MSC5<String, String> msc = new MSC5<String, String>();

		msc.branch(gv,null);

	}

}