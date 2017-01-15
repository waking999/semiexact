package au.edu.cdu.semiexact.exact;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.GlobalVariable;
import au.edu.cdu.semiexact.util.LogUtil;

/**
 * a test case to test more complex instances
 */
public class MSC4Test1 {
	private static Logger log = LogUtil.getLogger(MSC4Test.class);
	private void basicTest(String folder, String[] fileNames) throws IOException {
		for (String fileName : fileNames) {
			String filePath = TestUtil.getCurrentPath() + "/src/test/resources/" + folder +"/"+ fileName;

			GlobalVariable<String, String> gv = new FileOperation().readGraphByEdgePair(filePath);
			MSC4<String, String> msc = new MSC4<String, String>();

			long start=System.nanoTime();
			msc.branch(gv);
			long end=System.nanoTime();
			
			StringBuffer sb=new StringBuffer();
			sb.append(fileName).append(":").append(gv.getSolCount()).append(":").append((end-start)/1000000000 ).append(" s.");
			log.debug(sb.toString());
		}
	}

	@Test
	public void testDIMCS() throws IOException {
		String folder = "DIMACS";
		String[] fileNames = { "brock200_2.clq"  };

		basicTest(folder, fileNames);

	}

}
