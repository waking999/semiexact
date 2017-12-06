package au.edu.cdu.se.ds;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.ds.IMSC;
import au.edu.cdu.se.ds.MSC6;
import au.edu.cdu.se.util.ConstantValue;

/**
 * a test case to test more complex instances
 */
public class MSC6TestDataset {
	private static final String CLASS_NAME = MSC6TestDataset.class.getSimpleName();
	// private static Logger log = LogUtil.getLogger(MSC4Test.class);

	@Ignore
	@Test
	public void testBHOSLIB() throws IOException {

		IMSC msc = new MSC6();

		TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

	}

	@Ignore
	@Test
	public void testKONECT() throws IOException {
		 

		IMSC msc = new MSC6();

		TestUtil.basicTestLoop(ConstantValue.DATASET_KONECT, CLASS_NAME, msc);

	}

}
