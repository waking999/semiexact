package au.edu.cdu.semiexact.algo.mis;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.io.FileOperation;
import au.edu.cdu.semiexact.util.LogUtil;
import au.edu.cdu.semiexact.util.MISGlobalVariable; 

/**
 * test class for msc1
 *
 */
public class MIS1Test {
	private static Logger log = LogUtil.getLogger(MIS1Test.class);
	@Test
	public void testCase1() throws IOException{ 
		
		log.debug(TestUtil.FUNCTION_SEP);
		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt" ;

		MISGlobalVariable<Integer> gv = new FileOperation().readGraphForMISByEdgePair(filePath);

		MIS1<Integer> mis = new MIS1<Integer>();

		mis.branch(gv, null); 
		Assert.assertEquals(3, gv.getBestSolCount());
	}

}
