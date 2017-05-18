package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import au.edu.cdu.semiexact.TestUtil;
import au.edu.cdu.semiexact.util.MSCGlobalVariable;

/**
 * a test class for file operation class
 */
public class FileOperationTest {
	@Test(expected = IOException.class)
	public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
		String filePath = "";
		new FileOperation().readGraphForMSCByEdgePair(filePath);
	}

	@Test(expected = NoSuchFileException.class)
	public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/samplea.txt";

		new FileOperation().readGraphForMSCByEdgePair(filePath);
	}

	@Test
	public void testRetriveProblemInfoByEdgePairNormal() throws IOException {

		String filePath = TestUtil.getCurrentPath() + "/src/test/resources/sample1.txt";

		MSCGlobalVariable<String, String> gv = new FileOperation().readGraphForMSCByEdgePair(filePath);
		TestUtil.printGlobalVariableStatus(gv);

	}
}