package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import au.edu.cdu.semiexact.util.GlobalVariable;

public class FileOperationTest {
	@Test(expected = IOException.class)
	public void testRetriveProblemInfoByEdgePairIOExp() throws IOException {
		String filePath = ""; 
		new FileOperation<Integer,Integer>().retriveProblemInfoByEdgePair(filePath); 
	}
	
	@Test(expected = NoSuchFileException.class)
	public void testRetriveProblemInfoByEdgePairFoundExp() throws IOException {
		String filePath = "/Users/kwang/Documents/git/semiexact/src/test/resources/sample.txt1"; 
		new FileOperation<Integer,Integer>().retriveProblemInfoByEdgePair(filePath); 
	}
	
	@Test 
	public void testRetriveProblemInfoByEdgePairNormal() throws IOException {
		String filePath = "/Users/kwang/Documents/git/semiexact/src/test/resources/sample.txt"; 
		GlobalVariable<Integer,Integer> gv=new FileOperation<Integer,Integer>().retriveProblemInfoByEdgePair(filePath); 
		System.out.println(gv);
		 
	}
}
