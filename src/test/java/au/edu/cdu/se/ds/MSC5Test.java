package au.edu.cdu.se.ds;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.ds.MSC5;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.Util;
import au.edu.cdu.se.util.ds.DSGlobalVariable;

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
		DSGlobalVariable<String, String> gv = TestUtil.getTC2Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(4, gv.getBestSolCount());
	}

	// @Ignore
	@Test
	public void testBranch1() {
		log.debug(TestUtil.FUNCTION_SEP);
		DSGlobalVariable<String, String> gv = TestUtil.getTC1Rep();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());

	}

	// @Ignore
	@Test
	public void testBranch2() throws IOException {
		log.debug(TestUtil.FUNCTION_SEP);
		DSGlobalVariable<String, String> gv = TestUtil.getTC1RepFile();

		testBranch(gv);
		Assert.assertTrue(Util.isValidSolution(gv));
		Assert.assertEquals(2, gv.getBestSolCount());
	}

	private void testBranch(DSGlobalVariable<String, String> gv) {
		MSC5<String, String> msc = new MSC5<String, String>();

		msc.branch(gv,null);

	}

}