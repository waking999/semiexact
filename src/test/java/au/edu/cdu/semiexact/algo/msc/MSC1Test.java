package au.edu.cdu.semiexact.algo.msc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.algo.msc.MSC1;

/**
 * test class for msc1
 *
 */
public class MSC1Test {

	@Test
	public void testCase1() {
		List<List<Integer>> list = TestUtil.getTestCase1ForBasicMSC();

		MSC1 msc = new MSC1();
		msc.setS(list);
		int result = msc.run();
		Assert.assertEquals(2, result);
	}

}
