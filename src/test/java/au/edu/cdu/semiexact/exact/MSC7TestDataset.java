package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.ConstantValue;

/**
 * a test case to test more complex instances
 */
public class MSC7TestDataset {
	// private static Logger log = LogUtil.getLogger(MSC4Test.class);

	  @Ignore
	@Test
	public void testBHOSLIB() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG7;

		String[] instanceCodes = {  "frb30-15-1", "frb30-15-2", "frb30-15-3", "frb30-15-4", "frb30-15-5",
				"frb35-17-1", "frb35-17-2", "frb35-17-3", "frb35-17-4", "frb35-17-5",
				  "frb40-19-1", "frb40-19-2", "frb40-19-3", "frb40-19-4", "frb40-19-5",
				  "frb45-21-1", "frb45-21-2", "frb45-21-3", "frb45-21-4", "frb45-21-5",
		};
		IMSC<String, String> msc = new MSC6<String, String>();

		TestUtil.basicTest(instanceCodes, algTableName, msc);

	}

	//@Ignore
	@Test
	public void testKONECT() throws IOException {
		String algTableName = ConstantValue.DB_TBNAME_ALG7;

		String[] instanceCodes = { "DavidCopperfield", "Jazz", "PDZBase", "Rovira", "Euroroad", "Hamster", "HamsterFul",
				"Facebook"

		};
		IMSC<String, String> msc = new MSC6<String, String>();

		TestUtil.basicTest(instanceCodes, algTableName, msc);

	}

}
