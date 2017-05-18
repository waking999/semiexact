package au.edu.cdu.semiexact.algo.msc;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.algo.msc.IMSC;
import au.edu.cdu.semiexact.algo.msc.MSC7;

/**
 * a test case to test more complex instances
 */
public class MSC7TestDataset {
	// private static Logger log = LogUtil.getLogger(MSC4Test.class);

	//@Ignore
	@Test
	public void testBHOSLIB() throws IOException {

		String[] codes = { "frb30_15_1", "frb30_15_2", "frb30_15_3", "frb30_15_4", "frb30_15_5", "frb35_17_1",
				"frb35_17_2", "frb35_17_3", "frb35_17_4", "frb35_17_5", "frb40_19_1", "frb40_19_2", "frb40_19_3",
				"frb40_19_4", "frb40_19_5", "frb45_21_1", "frb45_21_2", "frb45_21_3", "frb45_21_4", "frb45_21_5", };

		IMSC<String, String> msc = new MSC7<String, String>();

		TestUtil.basicTestLoop(codes, msc);

	}

	 @Ignore
	@Test
	public void testKONECT() throws IOException {

		String[] codes = { //"DavidCopperfield", "Jazz", "PDZBase", "Rovira", "Euroroad", "Hamster", "HamsterFul",
				"Facebook" };

		IMSC<String, String> msc = new MSC7<String, String>();

		TestUtil.basicTestLoop(codes, msc);

	}

}
