import java.util.ArrayList;
import java.util.List;

public class WordBreak {

	public static void print(List<String> dict, String word, int[][] segmented, String out, int i, int j,
			StringBuffer buffer) {

		if (i > j) {
			buffer.append(out + "\n");

		}

		for (int k = i; k <= j; k++) {

			if (segmented[i][k] == 1) {

				String substring = word.substring(i, k + 1);
				//System.out.println(substring);
				if (dict.contains(substring)) {
					//System.out.println(k+1);
					print(dict, word, segmented, out + " " + substring, k + 1, j, buffer);
					
				}
			}
		}

	}
	
	
	static int[][] table(String s, ArrayList<?> a) {
		int n = s.length();
		int array[][] = new int[n][n];

		for (int l = 1; l <= n; l++) {
			for (int i = 0; i < n - l + 1; i++) {
				int j = i + l - 1;

				String str = s.substring(i, j + 1);
				if (dictionaryContains(str, a)) {
					array[i][j] = 1;
					continue;
				}

				for (int k = i; k < j; k++) {
					if (array[i][k] == 1 && array[k + 1][j] == 1) {
						array[i][j] = 1;
						continue;
					}
				}

			}

		}
		return array;

	}

	// if the prefix in dic return true else return false
	private static boolean dictionaryContains(String prefix, ArrayList a) {

		if (a.contains(prefix))
			return true;
		return false;
	}
}