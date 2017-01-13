package au.edu.cdu.semiexact.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.cdu.semiexact.util.ConstantValue;
import au.edu.cdu.semiexact.util.GlobalVariable;

/**
 * implement operation system file operations
 */
public class FileOperation {
	private static final String BLANK = " ";

	/**
	 * read edge pair information from a file to generate graph representations
	 * 
	 * @param filePath,
	 *            file path and name
	 * @return graph representation
	 * @throws IOException
	 */
	public GlobalVariable<String, String> readGraphByEdgePair(String filePath) throws IOException {

		GlobalVariable<String, String> gv = new GlobalVariable<String, String>();

		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
		String line0 = lines.get(0);
		String[] line0Array = line0.split(BLANK);
		String numOfVerStr = line0Array[0];

		int numOfVer = Integer.parseInt(numOfVerStr);

		Map<String, Integer> sLIL = new HashMap<String, Integer>();
		Map<String, Integer> eLIL = new HashMap<String, Integer>();
		for (int i = 0; i < numOfVer; i++) {
			sLIL.put(new Integer(i + 1).toString(), i);
			eLIL.put(new Integer(i + 1).toString(), i);
		}

		int[] card = new int[numOfVer];
		int[] freq = new int[numOfVer];
		int[] sL = new int[numOfVer];
		int[] sIL = new int[numOfVer];
		int[] eL = new int[numOfVer];
		int[] eIL = new int[numOfVer];
		int[][] sIM = new int[numOfVer][numOfVer];
		int[][] eIM = new int[numOfVer][numOfVer];

		for (int i = 0; i < numOfVer; i++) {
			card[i] = 1;
			freq[i] = 1;

			int val = i;
			sL[i] = val;
			sIL[i] = val;
			eL[i] = val;
			eIL[i] = val;

			for (int j = 0; j < numOfVer; j++) {
				sIM[i][j] = ConstantValue.IMPOSSIBLE_VALUE;
				eIM[i][j] = ConstantValue.IMPOSSIBLE_VALUE;
			}
		}

		int linesSize = lines.size();
		String tmpLine = null;
		for (int i = 1; i < linesSize; i++) {
			tmpLine = lines.get(i);
			String[] tmpLineArray = tmpLine.split(BLANK);
			String u1Str = tmpLineArray[0];
			String v1Str = tmpLineArray[1];
			int u1 = Integer.parseInt(u1Str);
			int v1 = Integer.parseInt(v1Str);
			int u = u1 - 1;
			int v = v1 - 1;
			sIM[u][v] = 1;
			sIM[v][u] = 1;
			eIM[u][v] = 1;
			eIM[v][u] = 1;

			card[u]++;
			card[v]++;
			freq[u]++;
			freq[v]++;
		}

		for (int i = 0; i < numOfVer; i++) {
			sIM[i][i] = 1;
			eIM[i][i] = 1;
		}

		int[][] sAL = new int[numOfVer][];
		int[][] eAL = new int[numOfVer][];

		for (int i = 0; i < numOfVer; i++) {
			sAL[i] = new int[card[i]];
			eAL[i] = new int[freq[i]];

			int sCur = 0;
			int eCur = 0;
			for (int j = 0; j < numOfVer; j++) {
				if (sIM[j][i] == 1) {
					sIM[j][i] = sCur;
					sAL[i][sCur] = j;
					sCur++;
				}
				if (eIM[j][i] == 1) {
					eIM[j][i] = eCur;
					eAL[i][eCur] = j;
					eCur++;
				}
			}
		}

		 
		int[] mate = new int[numOfVer];
		for (int i = 0; i < numOfVer; i++) {
			mate[i] = 0;
		}
		gv.setsActCount(numOfVer);
		gv.setsAL(sAL);
		gv.setsIL(sIL);
		gv.setsIM(sIM);
		gv.setsL(sL);
		gv.setsIL(sIL);
		gv.setsLIL(sLIL);
		gv.setCard(card);

		gv.seteActCount(numOfVer);
		gv.seteAL(eAL);
		gv.seteIL(eIL);
		gv.seteIM(eIM);
		gv.seteL(eL);
		gv.seteIL(eIL);
		gv.seteLIL(eLIL);
		gv.setFreq(freq);

		gv.setBestSolCount(numOfVer);
		gv.setSolCount(0);
		gv.setMate(mate);

		return gv;

	}
}
