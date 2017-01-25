package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.ConstantValue;

/**
 * a test case to test more complex instances
 */
public class MSC5TestDataset {
	// private static Logger log = LogUtil.getLogger(MSC4Test.class);

	//@Ignore
	@Test
	public void testDIMCS() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG1;

		String[] instanceNames = { "brock200_2",
				// "brock200_4",
				// "brock400_2", "brock400_4", "brock800_2",
				// "brock800_4",
		};

		IMSC<String, String> msc = new MSC5<String, String>();

		TestUtil.basicTest(instanceNames, algTableName, msc);

	}

	@Test
	public void testKONECT() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG1;

		String[] instanceCodes = { // "DavidCopperfield",
				// "Jazz",
				//"PDZBase",
			 //"Rovira", 
				"Euroroad", 
				//"Hamster", "HamsterFul",
				// "Facebook"

		};
		IMSC<String, String> msc = new MSC5<String, String>();

		TestUtil.basicTest(instanceCodes, algTableName, msc);

	}

}
