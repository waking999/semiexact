package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

public class FileOperationTest {
	@Test(expected = IOException.class)
	public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
		String filePath = ""; 
		FileOperation.retriveProblemInfoByEdgePair(filePath); 
	}
	
	@Test(expected = NoSuchFileException.class)
	public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
		String filePath = "/Users/kwang/Documents/git/semiexact/src/test/resources/sample.txt1"; 
		FileOperation.retriveProblemInfoByEdgePair(filePath); 
	}
	
	@Test 
	public void testRetriveProblemInfoByEdgePairNormal() throws IOException {
		String filePath = "/Users/kwang/Documents/git/semiexact/src/test/resources/sample.txt"; 
		GlobalVariable gv=FileOperation.retriveProblemInfoByEdgePair(filePath); 
		System.out.println(gv);
		gv.getEal();
	}
}
