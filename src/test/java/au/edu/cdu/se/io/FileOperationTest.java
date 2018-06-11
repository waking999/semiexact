package au.edu.cdu.se.io;

import au.edu.cdu.se.TestUtil;
import au.edu.cdu.se.util.ds.DSGlobalVariable;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * a test class for file operation class
 */
public class FileOperationTest {
    @Test(expected = IOException.class)
    public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
        String filePath = "";
        FileOperation.readGraphByEdgePairForDS(filePath);
    }

    @Test(expected = NoSuchFileException.class)
    public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
        String filePath = TestUtil.getBasePath() + "/src/test/resources/samplea.txt";

        FileOperation.readGraphByEdgePairForDS(filePath);
    }

    @Test
    public void testRetriveProblemInfoByEdgePairNormal() throws IOException {

        String filePath = TestUtil.getBasePath() + "/src/test/resources/sample1.txt";

        DSGlobalVariable gv = FileOperation.readGraphByEdgePairForDS(filePath);
        TestUtil.printGlobalVariableStatus(gv);

    }
}