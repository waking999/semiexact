package au.edu.cdu.semiexact.algo.msc;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.algo.msc.MM;
import au.edu.cdu.semiexact.algo.msc.MMObj;

/**
 * test class for max matching class
 */
public class MMTest {

	@Test
	public void testCase1() {

		Map<Integer, List<Integer>> g = TestUtil.getTestCase1ForMaxMatching();

		MM mm = new MM();
		MMObj o = mm.maxMatching(g);
		Assert.assertEquals(3, o.getMnum());

	}

	@Test
	public void testCase2() {

		Map<Integer, List<Integer>> g = TestUtil.getTestCase2ForMaxMatching();

		MM mm = new MM();
		MMObj o = mm.maxMatching(g);
		Assert.assertEquals(3, o.getMnum());

	}

}
