package au.edu.cdu.se.ds;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.io.FileOperation;
import au.edu.cdu.se.util.AlgoUtil;
import au.edu.cdu.se.util.LogUtil;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * a test case to test more complex instances
 */
public class MSC4TestDataset {
    private static Logger log = LogUtil.getLogger(MSC4Test.class);

    private void basicTest(String folder, String[] fileNames, int[] expectedSizes) throws IOException {
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            String filePath = TestUtil.getBasePath() + "/src/test/resources/" + folder + "/" + fileName;

            DSGlobalVariable gv = FileOperation.readGraphByEdgePair(filePath);
            MSC4 msc = new MSC4();

            long start = System.nanoTime();
            msc.branch(gv, null);
            long end = System.nanoTime();

            Assert.assertEquals(expectedSizes[i], gv.getBestSolCount());
            Assert.assertTrue(AlgoUtil.isValidDSSolution(gv));

            String sb = fileName + ":" + gv.getBestSolCount() + ":" + (end - start) / 1000000000 +
                    " s.";
            log.debug(sb);
        }
    }

    @Test
    public void testDIMCS() throws IOException {
        String folder = "DIMACS";
        String[] fileNames = {"brock200_2.clq"};
        int[] expectedSizes = {4};
        basicTest(folder, fileNames, expectedSizes);

    }

}
