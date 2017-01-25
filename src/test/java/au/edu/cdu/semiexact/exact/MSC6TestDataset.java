package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.ConstantValue;

/**
 * a test case to test more complex instances
 */
public class MSC6TestDataset {
	// private static Logger log = LogUtil.getLogger(MSC4Test.class);

	//@Ignore
	@Test
	public void testBHOSLIB() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG1;

		String[] instanceCodes = { "frb30-15-1", //"frb30-15-2", "frb30-15-3", "frb30-15-4", "frb30-15-5",

		};
		IMSC<String, String> msc = new MSC6<String, String>();

		TestUtil.basicTest(instanceCodes, algTableName, msc);

	}

	@Test
	public void testKONECT() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG1;

		String[] instanceCodes = {// "DavidCopperfield", 
				//"Jazz",
				"PDZBase", 
				//"Rovira", "Euroroad", "Hamster", "HamsterFul",
				//"Facebook"

		};
		IMSC<String, String> msc = new MSC6<String, String>();

		TestUtil.basicTest(instanceCodes, algTableName, msc);

	}

}
