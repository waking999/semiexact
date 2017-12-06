package au.edu.cdu.se.ds;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.ds.MSC4;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.Util;
import au.edu.cdu.se.util.ds.DSGlobalVariable;

/**
 * a test case to test more complex instances
 */
public class MSC4TestDataset {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);

	private void basicTest(String folder, String[] fileNames, int[] expectedSizes) throws IOException {
		for (int i = 0; i <  fileNames.length; i++) {
			String fileName = fileNames[i];
			String filePath = TestUtil.getCurrentPath() + "/src/test/resources/" + folder + "/" + fileName;

			DSGlobalVariable<String, String> gv = new FileOperation().readGraphByEdgePair(filePath);
			MSC4<String, String> msc = new MSC4<String, String>();

			long start = System.nanoTime();
			msc.branch(gv, null);
			long end = System.nanoTime();

			Assert.assertEquals(expectedSizes[i], gv.getBestSolCount());
			Assert.assertTrue(Util.isValidSolution(gv));

			StringBuffer sb = new StringBuffer();
			sb.append(fileName).append(":").append(gv.getBestSolCount()).append(":").append((end - start) / 1000000000)
					.append(" s.");
			log.debug(sb.toString());
		}
	}

	@Test
	public void testDIMCS() throws IOException {
		String folder = "DIMACS";
		String[] fileNames = { "brock200_2.clq" };
		int[] expectedSizes = { 4 };
		basicTest(folder, fileNames, expectedSizes);

	}

}
